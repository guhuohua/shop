package com.wxapp.shopapp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxapp.shopapp.dao.UserMapper;
import com.wxapp.shopapp.pojo.*;
import com.wxapp.shopapp.service.UserDividendService;
import com.wxapp.shopapp.util.CourierUtils;
import com.wxapp.shopapp.util.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopappApplicationTests {


    @Autowired
    private UserRegionReposition userRegionReposition;


    @Autowired
    @Qualifier("goodsAdReposition")
    private GoodsAdReposition goodsAdReposition;

    @Autowired
    @Qualifier("goodsReposition")
    private GoodsReposition goodsReposition;

    @Autowired
    @Qualifier("goodsTypeReposition")
    private GoodsTypeReposition goodsTypeReposition;

    @Autowired
    @Qualifier("goodsSizeReposition")
    private GoodsSizeReposition goodsSizeReposition;

    @Autowired
    @Qualifier("shopReposition")
    private ShopReposition shopReposition;

    @Autowired
    @Qualifier("shopCarReposotion")
    private ShopCarReposotion shopCarReposotion;

    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;

    @Value("${wx.dividendNum}")
    private double dividendNum;

    @Autowired
    private EmployeeRepostion employeeRepostion;

    @Autowired
    private RoleRepostion roleRepostion;

    @Autowired
    private OrderReposition orderReposition;

    @Autowired
    private ImgUrlReposition imgUrlReposition;


    @Test
    public void name() {
        Object o = CourierUtils.query("3379390063473");
        System.out.println(o);
    }

    @Test
    public void userRegion() {

        // 省份 List
        List<UserRegion> provincesList = userRegionReposition.findByParentId(1);
        List<AddressNode> provincesNodeList = new ArrayList<>(provincesList.size());
        for (UserRegion region : provincesList) {
            // 省份node
            AddressNode provincesNode = new AddressNode();
            // 查询省份下的市级node
            List<UserRegion> cityList = userRegionReposition.findByParentId(region.getId());
            List<AddressNode> cityNode = new ArrayList<>(cityList.size());
            provincesNode.setLabel(region.getName());
            provincesNode.setValue(String.valueOf(region.getParentId()));
            for (UserRegion userRegion : cityList) {
                // 市级node
                AddressNode areaNode = new AddressNode();
                List<UserRegion> areaList = userRegionReposition.findByParentId(userRegion.getId());
                List<AddressNode> areaNodeList = new ArrayList<>(areaList.size());
                for (UserRegion area : areaList) {
                    AddressNode node = new AddressNode();
//                    node.setChildren(nodeList);
                    node.setValue(String.valueOf(area.getParentId()));
                    node.setLabel(area.getName());
                    areaNodeList.add(node);
                }
                areaNode.setLabel(userRegion.getName());
                areaNode.setValue(String.valueOf(userRegion.getParentId()));
                areaNode.setChildren(areaNodeList);
                cityNode.add(areaNode);
            }
            provincesNode.setChildren(cityNode);
            provincesNodeList.add(provincesNode);
//            System.out.println(provincesNode);
        }

//        System.out.println();
        try {
//            for (AddressNode node : provincesNodeList) {
//                File file = new File("D:/area/"+node.getLabel()+".txt");
//                FileOutputStream stream = new FileOutputStream(file);
//                stream.write(JSON.toJSONString(node, false).getBytes("utf-8"));
//                stream.close();
//            }
                File file = new File("D:/area/1.txt");
                FileOutputStream stream = new FileOutputStream(file);
                stream.write(JSON.toJSONString(provincesNodeList, false).getBytes("utf-8"));
                stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        System.out.println(provincesNodeList);

    }

    @Test
    public void imgUrl(){
        List<Goods> list = goodsReposition.findAll();
        for (Goods goods : list) {
            ImgUrl imgUrl = new ImgUrl();
            imgUrl.setGFlag(goods.getGFlag());
            imgUrl.setCreateTime(System.currentTimeMillis());
            imgUrl.setStatus(1);
            imgUrl.setUrl(goods.getImgUrl());
            imgUrlReposition.save(imgUrl);
        }
    }




    @Test
    public void addEmp() {
        Employee employee = new Employee();
        employee.setCreateTime(System.currentTimeMillis());
        employee.setUsername("123456");
        employee.setPassword("123456");
        employee.setNickname("球球");
        employee.setStatus(1);
        employee.setTel("1234567890");
        employee.setRoles(roleRepostion.findAll());
        employeeRepostion.save(employee);
    }

    @Autowired
    private UserDividendService userDividendService;

    @Test
    public void test(){
        List<Order> list = orderReposition.findByConditions(
                "1", "1", "1,2,3", 0, 1);
        System.out.println(list);
    }

    @Test
    public void findEmp() {
        try {
            System.out.println(employeeRepostion.findByUsername("123456"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void addRole() {
        Role role = new Role();
        role.setNote("可以操作一切");
        role.setRoleName("管理员");
        role.setStatus(1);
        roleRepostion.save(role);
    }


    @Test
    public void contextLoads() {
        try {
            System.out.println(1);
            System.out.println(userMapper.findAll());
            System.out.println(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void addNewGoodsAd() {

        List<Goods> list = goodsReposition.findAll();
        for (Goods goods : list) {
            GoodsAd ad = new GoodsAd();
            ad.setImgUrl(goods.getImgUrl());
            ad.setGPrice(goods.getPrice());
            ad.setGFlag(goods.getGFlag());
            ad.setGTitle(goods.getTitle());
            ad.setAdType(new Random().nextInt(3) + 1);
            ad.setStatus(goods.getStatus());
            ad.setGId(goods.getGId());
            goodsAdReposition.save(ad);
        }


    }

    @Test
    public void addNewGoods() {
//        Goods goods = new Goods();
//        goods.setCreateTime(System.currentTimeMillis());
//        goods.setStatus(1);
//        goods.setGFlag("z8Wb3haAhnSvnzIq");
//        goods.setImgUrl("http://localhost:8080/image/goods/pic01.png");
//        goods.setKeyword("导航");
//        goods.setPrice(2860.70);
//        goods.setTitle("宝马新X1X3X4X5X6 1系2系3系5系7系倒车影像安卓大屏导航仪一体机");
//        goods.setOriPrice(3289.90);
//        goods.setSales(92);
//        goods.setType(1);
//        goods.setRepertory(999);
//
//        goodsReposition.save(goods);

        List<GoodsAd> list = goodsAdReposition.findAll();

        for (GoodsAd ad : list) {
            Random random = new Random();
            Goods goods = new Goods();

            goods.setCreateTime(System.currentTimeMillis());
            goods.setStatus(1);


            goods.setGFlag(ad.getGFlag());
            goods.setImgUrl(ad.getImgUrl());
            goods.setKeyword("导航");
            goods.setPrice(ad.getGPrice());
            goods.setTitle(ad.getGTitle());
            goods.setOriPrice(ad.getGPrice() * 1.3);

            goods.setSales(random.nextInt(100));
            goods.setType(1);
            goods.setRepertory(random.nextInt(1000));

            goodsReposition.save(goods);

        }

    }


    @Test
    public void addNewGoodsSize() {
        List<GoodsAd> list = goodsAdReposition.findAll();

        for (GoodsAd ad : list) {
            Random random = new Random();
            GoodsSize size = new GoodsSize();
            size.setCreateTime(System.currentTimeMillis());
            size.setGFlag(ad.getGFlag());
            size.setStatus(1);
            size.setUrl(ad.getImgUrl());
            size.setText("黑色");
            goodsSizeReposition.save(size);

        }

    }


    @Test
    public void addNewShop() {
        Shop shop = new Shop();

        shop.setCreateTime(System.currentTimeMillis());
        shop.setName("球球二号店");
        shop.setStatus(1);
        shop.setUrl(RandomUtils.getRandomString());
        shopReposition.save(shop);

    }


    @Test
    public void addNewShopCar() {
        ShopCar shopCar = new ShopCar();
        shopCar.setCount(99);
        shopCar.setCreateTime(System.currentTimeMillis());
        shopCar.setGFlag("DZPV4MSPycbRfSwS");
        shopCar.setShopId(100);
        shopCar.setSizeId(39);
        shopCar.setUId(1);
        shopCarReposotion.save(shopCar);
    }

    @Test
    public void addGoodsTypes() {

        for (int i = 0; i < 10; i++) {
            GoodsType type = new GoodsType();
            type.setIconUrl("http://localhost:8080/image/goods/pic03.png");
            type.setName("坐垫");
            type.setTypeId(1);
            type.setParentId(1);
            type.setUrl("");
            type.setStatus(1);
            goodsTypeReposition.save(type);

        }
    }

    @Test
    public void addGoodsAd() {
        System.out.println(dividendNum);
    }

    @Test
    public void updateImgUrl() {
        String baseUrl;
        String newUrl;

        baseUrl = "http://localhost:8080";
        baseUrl = "http://qqmxdxyz:7070";
        baseUrl = "117.50.74.117:8080";
        baseUrl = "http://http://http://http://";

        newUrl = "http://qqmxdxyz:7070";

        newUrl = "http://qqmxdxyz:10030";
        newUrl = "http://117.50.74.117:8080";
        newUrl = "http://";
        List<Goods> list = goodsReposition.findAll();
        for (Goods goods : list) {
            String url = goods.getImgUrl();
            if (url.contains(baseUrl)) {
                String s = url.replaceAll(baseUrl, newUrl);
                goods.setImgUrl(s);
                goodsReposition.save(goods);
                System.out.println("goodsID: " + baseUrl + " -> " + s);
            }
        }


        List<GoodsType> goodsTypeList = goodsTypeReposition.findAll();
        for (GoodsType type : goodsTypeList) {
            String url = type.getIconUrl();
            if (url.contains(baseUrl)) {
                String s = url.replaceAll(baseUrl, newUrl);
                type.setIconUrl(s);
                goodsTypeReposition.save(type);
                System.out.println("goodsType: " + baseUrl + " -> " + s);
            }
        }

        List<GoodsSize> goodsSizeList = goodsSizeReposition.findAll();
        for (GoodsSize size : goodsSizeList) {
            String url = size.getUrl();
            if (url.contains(baseUrl)) {
                String s = url.replaceAll(baseUrl, newUrl);
                size.setUrl(s);
                goodsSizeReposition.save(size);
                System.out.println("GoodsSize: " + baseUrl + " -> " + s);
            }
        }
        updataAdImgUrl();
    }

    @Test
    public void updataAdImgUrl() {
        List<GoodsAd> list = goodsAdReposition.findAll();
        for (GoodsAd ad : list) {
            String flag = ad.getGFlag();
            String before = ad.getImgUrl();
            Goods goods = null;
            try {
                goods = goodsReposition.findByGFlag(flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String url = goods.getImgUrl();
            ad.setImgUrl(url);
            goodsAdReposition.save(ad);
            System.out.println("goodsID: " + before + " -> " + url);
        }
    }


}
