package com.fellowchicken.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fellowchicken.common.Result;
import com.fellowchicken.common.ResultGenerator;
import com.fellowchicken.mapper.VisitMapper;
import com.fellowchicken.model.*;
import com.fellowchicken.service.*;
import com.fellowchicken.vo.CitiesByProvince;
import com.fellowchicken.vo.DistrictsByCity;
import com.fellowchicken.vo.ProvincesList;
import com.fellowchicken.vo.StreetsByDistrict;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
@Api(tags = {"baidumap"})
@CrossOrigin(origins = "*", maxAge = 5500)  //跨域访问
@RequestMapping("/baidu_map")   //地图区域数据
public class BaiduMapController {
    @Resource
    private WarzoneService warzoneService;

    @Resource
    private CityService cityService;

    @Resource
    private MinimarketService minimarketService;

    @Resource
    private StoreService storeService;

    @Resource
    private BaiduMapService baiduMapService;

    @Resource
    private VisitService visitService;

    //省级地图数据
    @GetMapping(name = "Pro_data", path = "/pro_data")
    public Result<List<ProvincesList>> pro_list() {
        List<ProvincesList> list = new ArrayList<>();

        List<Warzone> province_list;
        province_list = warzoneService.list();
        for (Warzone item : province_list) {
            int warzoneID = item.getWarzoneID();
            int rank = 0;
            double lon = item.getLon();
            double lat = item.getLat();
            double sale = baiduMapService.getProvinceSale(warzoneID);
            int shopNum = baiduMapService.getProShopNum(warzoneID);
            String provinceName = item.getWarzoneName();

            ProvincesList pl = new ProvincesList(warzoneID, provinceName, rank, sale, shopNum, lon, lat);
            list.add(pl);
        }

        ProvincesList pro;  //排名
        for (int i = 0; i < list.size(); i++) {
            int flag = i;
            Double max = list.get(i).getSale();
            pro = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                if (max < list.get(j).getSale()) {
                    max = list.get(j).getSale();
                    flag = j;
                }
            }
            list.set(i, list.get(flag));
            list.set(flag, pro);
            list.get(i).setRank(i + 1);
        }

        return ResultGenerator.genSuccessResult(list);
    }

    //根据省的编号获取该省的市级地图数据
    @GetMapping(name = "city_data", path = "/city_data")
    public Result<List<CitiesByProvince>> city_list(@RequestParam("WarzoneID") Integer warzoneID) {
        List<CitiesByProvince> list = new ArrayList<>();

        List<City> city_list;
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("WarzoneID", warzoneID);  //根据省级编号获取数据
        city_list = cityService.list(queryWrapper);
        for (City item : city_list) {
            int cityID = item.getCityID();
            String cityName = item.getCityName();
            int rank = 1;
            double sale;
            int shopNum;
            Double lon = item.getLon();
            Double lat = item.getLat();
            sale = baiduMapService.getCitySale(cityID);
            shopNum = baiduMapService.getCityShopNum(cityID);

            CitiesByProvince cb = new CitiesByProvince(cityID, cityName, rank, sale, shopNum, lon, lat);
            list.add(cb);
        }

        CitiesByProvince pro;   //排名
        for (int i = 0; i < list.size(); i++) {
            int flag = i;
            Double max = list.get(i).getSale();
            pro = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                if (max < list.get(j).getSale()) {
                    max = list.get(j).getSale();
                    flag = j;
                }
            }
            list.set(i, list.get(flag));
            list.set(flag, pro);
            list.get(i).setRank(i + 1);
        }
        return ResultGenerator.genSuccessResult(list);
    }

    //根据市级编号获取各区地图数据
    @GetMapping(name = "minimarket_data", path = "/minimarket_data")
    public Result<List<DistrictsByCity>> minimarket_list(@RequestParam("CityID") Integer cityId) {
        List<DistrictsByCity> list = new ArrayList<>();

        List<Minimarket> arraylist;
        QueryWrapper<Minimarket> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("CityID", cityId); //根据城市编号获取数据
        arraylist = minimarketService.list(queryWrapper);
        if (!arraylist.isEmpty()) {
            for (Minimarket item : arraylist) {
                int minimarketID = item.getMinimarketID();
                String districtName = item.getMinimarketName();
                int rank = 1;
                double sale;
                sale = baiduMapService.getMarketSale(minimarketID);  //各区的总消费额
                int shopNum;
                shopNum = baiduMapService.getMarketStoreNum(minimarketID); //门店数
                double lon = item.getLon();
                double lat = item.getLat();

                DistrictsByCity di = new DistrictsByCity(minimarketID, districtName, rank, sale, shopNum, lon, lat);
                list.add(di);
            }
        }

        DistrictsByCity pro; //排名
        for (int i = 0; i < list.size(); i++) {
            int flag = i;
            double max = list.get(i).getSale();
            pro = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                if (max < list.get(j).getSale()) {
                    max = list.get(j).getSale();
                    flag = j;
                }
            }
            list.set(i, list.get(flag));
            list.set(flag, pro);
            list.get(i).setRank(i + 1);
        }
        return ResultGenerator.genSuccessResult(list);
    }

    //根据区域编号获取门店地图数据
    @GetMapping(name = "store_data", path = "/store_data")
    public Result<List<StreetsByDistrict>> store_data(@RequestParam("MinimarketID") Integer miniMarketID) {
        List<StreetsByDistrict> list = new ArrayList<>();

        List<Store> store_list;
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("MinimarketID", miniMarketID);
        store_list = storeService.list(queryWrapper);
        if (!store_list.isEmpty()) {
            for (Store item : store_list) {
                int storeID = item.getStoreID();  //获取门店编号
                String shopName = item.getStoreName(); //获取门店名称
                double lon = item.getLon();
                double lat = item.getLat();
                double shopSale;
                shopSale = baiduMapService.getSale(storeID);
                StreetsByDistrict st = new StreetsByDistrict(storeID, shopName, lon, lat, shopSale);
                list.add(st);
            }
        }

        return ResultGenerator.genSuccessResult(list);
    }

    //地图右侧统计信息数据
    @GetMapping(name = "right_data", path = "/right_data")
    public List<Map<String, String>> getRightData(@RequestParam("type") Integer type
            , @RequestParam("ID") Integer id) {
        List<Map<String, String>> list1 = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        int shop_num; //门店数
        int oppo_num; //机会点数
        int num;
        if (type == 1) //全国
        {
            shop_num = baiduMapService.getShopNum();
            map.put("pro", "门店数量");
            map.put("target_num", Integer.toString(shop_num));
            list1.add(map);
            oppo_num = baiduMapService.getAllOppoNum();
            map1.put("pro", "机会点数量");
            map1.put("target_num", Integer.toString(oppo_num));
            list1.add(map1);
            List<Warzone> list = warzoneService.list();
            num = list.size();
            map2.put("pro", "战区数量");
            map2.put("target_num", Integer.toString(num));
            list1.add(map2);
        } else if (type == 2) {  //省级
            shop_num = baiduMapService.getProShopNum(id);
            map.put("pro", "门店数量");
            map.put("target_num", Integer.toString(shop_num));
            list1.add(map);
            oppo_num = baiduMapService.getProvinceOppoNum(id);
            map1.put("pro", "机会点数量");
            map1.put("target_num", Integer.toString(oppo_num));
            list1.add(map1);
            QueryWrapper<City> query = new QueryWrapper<>();
            query.eq("WarzoneID", id);
            List<City> list = cityService.list(query);
            num = list.size();
            map2.put("pro", "城市数量");
            map2.put("target_num", Integer.toString(num));
            list1.add(map2);
        } else if (type == 3) {   //市级
            shop_num = baiduMapService.getCityShopNum(id);
            map.put("pro", "门店数量");
            map.put("target_num", Integer.toString(shop_num));
            list1.add(map);
            oppo_num = baiduMapService.getCityOppoNum(id);
            map1.put("pro", "机会点数量");
            map1.put("target_num", Integer.toString(oppo_num));
            list1.add(map1);
            QueryWrapper<Minimarket> query = new QueryWrapper<>();
            query.eq("CityID", id);
            List<Minimarket> list = minimarketService.list(query);
            num = list.size();
            map2.put("pro", "市场数量");
            map2.put("target_num", Integer.toString(num));
            list1.add(map2);
        } else if (type == 4) {  //区县
            shop_num = baiduMapService.getMarketStoreNum(id);
            map.put("pro", "门店数量");
            map.put("target_num", Integer.toString(shop_num));
            list1.add(map);
            oppo_num = baiduMapService.getRegionalOppoNum(id);
            map1.put("pro", "机会点数量");
            map1.put("target_num", Integer.toString(oppo_num));
            list1.add(map1);
        }
        return list1;
    }

    //弹窗门店数据
    @GetMapping(name = "dialog_data", path = "/dialog_data")
    public Result<Map<String, Object>> getDialogData(@RequestParam("StoreID") Integer storeID) {
        Map<String, Object> map = new HashMap<>();

        Store st = storeService.getById(storeID);
        map.put("name", st.getStoreName());
        map.put("address", st.getAddress());
        map.put("storeid", st.getStoreID());
        map.put("storemode", st.getStoreMode());
        map.put("floor", st.getFloor());
        map.put("square", st.getSquare());
        map.put("monthsale", "10000");
        String photo = st.getPhoto();
        if (photo == null) {
            photo = "http://localhost:8080/images/jyzz.jpeg";
        }
        map.put("photo", photo);
        Minimarket mk = minimarketService.getById(st.getMinimarketID());
        map.put("marketname", mk.getMinimarketName());
        map.put("openningtime", new SimpleDateFormat("yyyy-MM-dd").format(st.getOpeningTime()));

        List<Map<String,Object>> list = new ArrayList<>();
        baiduMapService.getStoreSale(list,storeID);
        Double currmonth_sale = Double.parseDouble(list.get(list.size()-1).get("turnover").toString());
        map.put("turnover",currmonth_sale);

        return ResultGenerator.genSuccessResult(map);
    }

    //历史拜访记录
    @GetMapping(name = "visit_data", path = "/visit_data")
    public Result<List<Map<String, Object>>> getVisitData(@RequestParam("StoreID") Integer storeID) {
        List<Map<String, Object>> mapList = new ArrayList<>();

        QueryWrapper<Visit> query = new QueryWrapper<>();
        query.eq("StoreID", storeID);
        List<Visit> list = visitService.list(query);
        for (Visit item : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("visitID", item.getVisitID());
            map.put("visitTime", item.getVisitTime());
            map.put("visitor", item.getVisitor());
            map.put("Comment", item.getComment());
            String filename = item.getFiles();
            List<String> file = new ArrayList<>();

            if (filename != null) {
                String[] files = filename.split(",");
                for (String st : files) {
                    file.add("http://localhost:8080/photo/" + st);
                }
            }
            map.put("files", file);
            mapList.add(map);
        }
        return ResultGenerator.genSuccessResult(mapList);
    }

    //上传拜访记录
    @PostMapping("/post_visits")
    public <T> Result<T> post_visits(@RequestPart("files") MultipartFile[] files,
                                     @RequestParam("visitTime") String visitTime,
                                     @RequestParam("visitor") String visitor,
                                     @RequestParam("Comment") String comment,
                                     @RequestParam("storeID") Integer storeID) throws IOException, ParseException {
        StringBuilder filename = null;
        File savePos = new File("src/main/resources/static/photo");
        // 获取存放位置的规范路径
        String realPath = savePos.getCanonicalPath();
        if (files.length > 0) {
            for (MultipartFile file : files) {
                file.transferTo(new File(realPath + "/" + file.getOriginalFilename()));
                if (filename == null) {
                    filename = new StringBuilder(Objects.requireNonNull(file.getOriginalFilename()));
                } else {
                    filename.append(",").append(file.getOriginalFilename());
                }
            }
        }
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");  //时间格式转化
        assert filename != null;
        VisitMapper visitMapper = (VisitMapper) visitService.getBaseMapper();
        Visit vt = new Visit();
        vt.setVisitTime(sd.parse(visitTime));
        vt.setVisitor(visitor);
        vt.setComment(comment);
        vt.setFiles(filename.toString());
        vt.setStoreID(storeID);
        visitMapper.add(vt);
        return ResultGenerator.genSuccessResult("添加成功！");
    }

    /*以下为经营数据后端获取*/
    @GetMapping(name = "sale_data", path = "/sale_data")
    public Result<List<Map<String, Object>>> getSaleData(@RequestParam(name = "Type") Integer type, @RequestParam(name = "ID") Integer id) {
        List<Map<String, Object>> sale_data = new ArrayList<>();  //返回数据

        switch (type) {
            case 1://全国
                baiduMapService.getChinaSales(sale_data);
                break;
            case 2://省级
                baiduMapService.getProvinceSales(sale_data, id);
                break;
            case 3://市级
                baiduMapService.getCitySales(sale_data, id);
                break;
            case 4://区县
                baiduMapService.getMarketSales(sale_data, id);
                break;
            default://店面
                baiduMapService.getStoreSale(sale_data, id);
        }
        return ResultGenerator.genSuccessResult(sale_data);
    }
}
