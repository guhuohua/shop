package com.wxapp.shopapp.service.impl;


import com.wxapp.shopapp.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

/**
 * 服务器本地对象存储服务
 */
@Service("storageService")
public class LocalStorageService implements StorageService {

//    @Value("${filePath}")
//    private String filePath;
    private String storagePath;

    @Value("${wx.imgUrl}")
    private String address;
    private Path rootLocation;

    public String getStoragePath() {
        return storagePath;
    }

    @Value("${wx.filePath}")
    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;

        this.rootLocation = Paths.get(storagePath);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void store(InputStream inputStream, long contentLength, String contentType, String keyName) {
        try {
            Files.copy(inputStream, rootLocation.resolve(keyName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + keyName, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(rootLocation, 1)
                    .filter(path -> !path.equals(rootLocation))
                    .map(path -> rootLocation.relativize(path));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(String filename) {
        Path file = load(filename);
        try {
            Files.delete(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String generateUrl(String keyName) {
        String url = address + keyName;
        return url;
    }
}