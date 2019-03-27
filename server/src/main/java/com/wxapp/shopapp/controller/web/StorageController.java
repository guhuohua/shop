package com.wxapp.shopapp.controller.web;


import com.wxapp.shopapp.annotation.LoginAdmin;
import com.wxapp.shopapp.pojo.ImgUrl;
import com.wxapp.shopapp.pojo.Storage;
import com.wxapp.shopapp.pojo.StorageReposition;
import com.wxapp.shopapp.service.StorageService;
import com.wxapp.shopapp.util.RandomUtils;
import com.wxapp.shopapp.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageReposition storageReposition;

    @Autowired
    private StorageService storageService;

    @Value("${wx.imgUrl}")
    private String imgPath;

    @Value("${wx.filePath}")
    private String filePath;



    @PostMapping("/create")
    public Object create(@LoginAdmin Integer adminId, @RequestParam("file") MultipartFile file) throws IOException {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        String originalFilename = file.getOriginalFilename();
        byte[] bytes = file.getBytes();
        String fileName = DigestUtils.md5DigestAsHex(bytes) + ".jpg";

        String url = imgPath + fileName;
        File newFile = new File(filePath + fileName);
        if (!newFile.exists()) {
            FileOutputStream fos = new FileOutputStream(newFile);
            fos.write(bytes);
            fos.close();
        }
//        String url = store(file.getInputStream(), file.getSize(), file.getContentType(), originalFilename);
        Map<String, Object> data = new HashMap<>();
        data.put("url", url);
        return ResponseUtil.ok(data);
    }

    /**
     * 存储一个文件对象
     *
     * @param inputStream   文件输入流
     * @param contentLength 文件长度
     * @param contentType   文件类型
     * @param fileName      文件索引名
     */
    public String store(InputStream inputStream, long contentLength, String contentType, String fileName) {
        String key = generateKey(fileName);
        storageService.store(inputStream, contentLength, contentType, key);

        String url = generateUrl(key);
        Storage storageInfo = new Storage();
        storageInfo.setName(fileName);
        storageInfo.setSize((int) contentLength);
        storageInfo.setType(contentType);
        storageInfo.setAddTime(LocalDateTime.now());
        storageInfo.setModified(LocalDateTime.now());
        storageInfo.setKey(key);
        storageInfo.setUrl(url);
        storageReposition.save(storageInfo);

        return url;
    }





    @GetMapping("/fetch/{key:.+}")
    public ResponseEntity<Resource> fetch(@PathVariable String key) {
        Storage storage = null;
        try {
            storage = storageReposition.findByKey(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (key == null) {
            ResponseEntity.notFound();
        }
        String type = storage.getType();
        MediaType mediaType = MediaType.parseMediaType(type);

        Resource file = storageService.loadAsResource(key);
        if (file == null) {
            ResponseEntity.notFound();
        }
        return ResponseEntity.ok().contentType(mediaType).body(file);
    }





    /**
     * 生成一个不重复的文件名
     * @param originalFilename
     * @return
     */
    private String generateKey(String originalFilename) {
        int index = originalFilename.lastIndexOf('.');
        String suffix = originalFilename.substring(index);

        String key = null;
        Storage storageInfo = null;

        try {
            do {
                key = RandomUtils.getRandomString(20) + suffix;
                storageInfo = storageReposition.findByKey(key);
            }
            while (storageInfo != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    private String generateUrl(String keyName) {
        return storageService.generateUrl(keyName);
    }
}
