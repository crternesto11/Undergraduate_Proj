package com.fellowchicken.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fellowchicken.model.*;
import com.fellowchicken.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BaiduMapServiceImpl implements BaiduMapService {

    @Resource
    private BusinessService businessService;
    @Resource
    private StoreService storeService;
    @Resource
    private MinimarketService minimarketService;
    @Resource
    private CityService cityService;
    @Resource
    private WarzoneService warzoneService;
    @Resource
    private OppoService oppoService;

    //根据门店编号获取门店销售额
    @Override
    public double getSale(int storeID) {
        double shop_sale = 0.0;
        QueryWrapper<Business> query = new QueryWrapper<>();
        query.eq("StoreID", storeID);
        List<Business> list = businessService.list(query);
        for (Business item : list) {
            shop_sale += item.getTurnover();
        }
        return shop_sale;
    }

    //根据市场编号，获取门店数量
    @Override
    public int getMarketStoreNum(int minimarketID) {
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("MinimarketID", minimarketID);
        List<Store> list = storeService.list(queryWrapper);
        return list.size();
    }

    //根据市场编号，获取市场营业额
    @Override
    public double getMarketSale(int minimarketID) {
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>(); //找各区的总消费额
        queryWrapper.eq("MinimarketID", minimarketID);
        List<Store> list = storeService.list(queryWrapper);
        double sale = 0.0;
        for (Store store : list) {
            sale += this.getSale(store.getStoreID());
        }
        return sale;
    }

    //根据城市编号，获取城市内的门店数量
    @Override
    public int getCityShopNum(int cityID) {
        QueryWrapper<Minimarket> query = new QueryWrapper<>();
        query.eq("CityID", cityID);
        List<Minimarket> list = minimarketService.list(query);
        int shop_num = 0;
        for (Minimarket minimarket : list) {
            shop_num += this.getMarketStoreNum(minimarket.getMinimarketID());
        }
        return shop_num;
    }

    //根据城市编号，获取城市的营业额
    @Override
    public double getCitySale(int cityID) {
        QueryWrapper<Minimarket> query = new QueryWrapper<>();
        query.eq("CityID", cityID);
        List<Minimarket> list = minimarketService.list(query);
        double sale = 0.0;
        for (Minimarket minimarket : list) {
            sale += this.getMarketSale(minimarket.getMinimarketID());
        }
        return sale;
    }

    //根据省级编号，获取各省的门店数
    @Override
    public int getProShopNum(int warzoneID) {
        QueryWrapper<City> query = new QueryWrapper<>();
        query.eq("WarzoneID", warzoneID);
        List<City> list = cityService.list(query);
        int shop_num = 0;
        for (City item : list) {
            shop_num += this.getCityShopNum(item.getCityID());
        }
        return shop_num;
    }

    //根据省级编号，获取各省的营业额
    @Override
    public double getProvinceSale(int warzoneID) {
        QueryWrapper<City> query = new QueryWrapper<>();
        query.eq("WarzoneID", warzoneID);
        List<City> list = cityService.list(query);
        double sale = 0.0;
        for (City city : list) {
            sale += this.getCitySale(city.getCityID());
        }
        return sale;
    }

    //全国门店数据
    @Override
    public int getShopNum() {
        int num = 0;
        List<Warzone> list = warzoneService.list();
        for (Warzone warzone : list) {
            num += this.getProShopNum(warzone.getWarzoneID());
        }
        return num;
    }

    //全国机会点数
    @Override
    public int getAllOppoNum() {
        List<Oppo> list = oppoService.list();
        return list.size();
    }

    //省级机会点数
    @Override
    public int getProvinceOppoNum(int warzoneid) {
        int num = 0;
        QueryWrapper<City> query = new QueryWrapper<>();
        query.eq("WarzoneID", warzoneid);
        List<City> list = cityService.list(query);
        for (City item : list) {
            num += this.getRegionalOppoNum(item.getCityID());
        }
        return num;
    }

    //市级机会点数
    @Override
    public int getCityOppoNum(int cityid) {
        int num = 0;
        QueryWrapper<Minimarket> query = new QueryWrapper<>();
        query.eq("CityID", cityid);
        List<Minimarket> list = minimarketService.list(query);
        for (Minimarket item : list) {
            num += this.getRegionalOppoNum(item.getMinimarketID());
        }
        return num;
    }

    //区县机会点数
    @Override
    public int getRegionalOppoNum(int minid) {
        QueryWrapper<Oppo> query = new QueryWrapper<>();
        query.eq("MinimarketID", minid);
        List<Oppo> list = oppoService.list(query);
        return list.size();
    }

    //////以下为经营详细信息

    //门店经营情况时间集合
    @Override
    public List<Date> getTime(int storeid) {  //门店时间跨度
        QueryWrapper<Business> query = new QueryWrapper<>();
        query.eq("StoreID", storeid);
        List<Business> list = businessService.list(query); //获取数据库数据
        Set<Date> settime = new TreeSet<>();
        list.forEach(item -> {  //获取时间,并去重
            settime.add(item.getDateMonth());
        });
        return new ArrayList<>(settime);
    }

    @Override
    public List<Date> getMarketTime(int matketid) {  //区县时间跨度
        QueryWrapper<Store> query = new QueryWrapper<>();
        query.eq("MinimarketID", matketid);
        List<Store> list = storeService.list(query); //获取数据库数据
        Set<Date> settime = new TreeSet<>();
        for (Store item : list) { //获取时间,并去重
            List<Date> list1 = this.getTime(item.getStoreID());
            settime.addAll(list1);
        }
        return new ArrayList<>(settime);
    }

    @Override
    public List<Date> getCityTime(int cityid) {  //城市时间跨度
        QueryWrapper<Minimarket> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("CityID", cityid);
        List<Minimarket> list = minimarketService.list(queryWrapper);
        Set<Date> settime = new TreeSet<>();
        for (Minimarket item : list) {
            List<Date> list1 = this.getMarketTime(item.getMinimarketID());
            settime.addAll(list1);
        }
        return new ArrayList<>(settime);
    }

    @Override
    public List<Date> getProvinceTime(int warzoneid) {  //省时间跨度
        QueryWrapper<City> query = new QueryWrapper<>();
        query.eq("WarzoneID", warzoneid);
        List<City> list = cityService.list(query);
        Set<Date> settime = new TreeSet<>();
        for (City item : list) {
            List<Date> list1 = this.getCityTime(item.getCityID());
            settime.addAll(list1);
        }
        return new ArrayList<>(settime);
    }

    //门店详细经营数据
    @Override
    public void getStoreSale(List<Map<String, Object>> saledata, int storeid) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");  //时间格式转化
        List<Date> timeList = this.getTime(storeid);
        QueryWrapper<Business> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("StoreID", storeid);
        List<Business> list = businessService.list(queryWrapper); //获取数据库数据

        double hall;//堂食收入
        double take_out;//外卖收入
        int orders1; //堂食账单数
        int orders2; //外卖账单数
        for (Date time : timeList) {
            hall = 0.0;
            take_out = 0.0;
            orders1 = 0;
            orders2 = 0;
            Map<String, Object> map = new HashMap<>(); //一条数据
            for (Business item : list) {
                if (time.equals(item.getDateMonth())) {  //归纳同日期的营业额--月
                    if (item.getMode() == 1) {   //堂食
                        hall = item.getTurnover();
                        orders1 = item.getOrders();
                    } else {     //外卖
                        take_out = item.getTurnover();
                        orders2 = item.getOrders();
                    }
                }
            }
            map.put("date", sd.format(time));  //日期-X轴
            map.put("turnover", hall + take_out);
            map.put("hall_turnover", hall);
            map.put("takeout_turnover", take_out);
            map.put("orders", orders1 + orders2);
            map.put("hall_order", orders1);
            map.put("takeout_order", orders2);

            saledata.add(map);
        }
    }

    //市场详细信息
    @Override
    public void getMarketSales(List<Map<String, Object>> saledata, int marketid) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");  //时间格式转化
        List<Date> listtime = this.getMarketTime(marketid);
        QueryWrapper<Store> query = new QueryWrapper<>();
        query.eq("MinimarketID", marketid);
        List<Store> list = storeService.list(query); //获取数据库数据

        double hall;//堂食收入
        double take_out;//外卖收入
        int orders1; //堂食账单数
        int orders2; //外卖账单数
        for (Date time : listtime) {
            hall = 0.0;
            take_out = 0.0;
            orders1 = 0;
            orders2 = 0;
            Map<String, Object> map = new HashMap<>(); //一条数据
            for (Store item : list) {  //获取门店编号
                List<Map<String, Object>> storesale = new ArrayList<>();
                this.getStoreSale(storesale, item.getStoreID()); //每个门店的经营数据
                for (Map i : storesale) {
                    String time1 = sd.format(time);
                    if (time1.equals(i.get("date").toString())) {
                        hall += Double.parseDouble(i.get("hall_turnover").toString());
                        take_out += Double.parseDouble(i.get("takeout_turnover").toString());
                        orders1 += Integer.parseInt(i.get("hall_order").toString());
                        orders2 += Integer.parseInt(i.get("takeout_order").toString());
                    }
                }
            }
            map.put("date", sd.format(time));  //日期-X轴
            map.put("turnover", hall + take_out);
            map.put("hall_turnover", hall);
            map.put("takeout_turnover", take_out);
            map.put("orders", orders1 + orders2);
            map.put("hall_order", orders1);
            map.put("takeout_order", orders2);

            saledata.add(map);
        }
    }

    //城市详细信息
    @Override
    public void getCitySales(List<Map<String, Object>> saledata, int cityid) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");  //时间格式转化
        List<Date> listtime = this.getCityTime(cityid);
        QueryWrapper<Minimarket> query = new QueryWrapper<>();
        query.eq("CityID", cityid);
        List<Minimarket> list = minimarketService.list(query);

        double hall;//堂食收入
        double take_out;//外卖收入
        int orders1; //堂食账单数
        int orders2; //外卖账单数
        for (Date time : listtime) {
            hall = 0.0;
            take_out = 0.0;
            orders1 = 0;
            orders2 = 0;
            Map<String, Object> map = new HashMap<>(); //一条数据
            for (Minimarket item : list) {  //获取区县编号
                List<Map<String, Object>> storesale = new ArrayList<>();
                this.getMarketSales(storesale, item.getMinimarketID()); //每个区县的经营数据
                for (Map i : storesale) {
                    String time1 = sd.format(time);
                    if (time1.equals(i.get("date").toString())) {
                        hall += Double.parseDouble(i.get("hall_turnover").toString());
                        take_out += Double.parseDouble(i.get("takeout_turnover").toString());
                        orders1 += Integer.parseInt(i.get("hall_order").toString());
                        orders2 += Integer.parseInt(i.get("takeout_order").toString());
                    }
                }
            }
            map.put("date", sd.format(time));  //日期-X轴
            map.put("turnover", hall + take_out);
            map.put("hall_turnover", hall);
            map.put("takeout_turnover", take_out);
            map.put("orders", orders1 + orders2);
            map.put("hall_order", orders1);
            map.put("takeout_order", orders2);

            saledata.add(map);
        }
    }

    //省详细信息
    @Override
    public void getProvinceSales(List<Map<String, Object>> saledata, int warzoneid) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");  //时间格式转化
        List<Date> listtime = this.getProvinceTime(warzoneid);
        QueryWrapper<City> query = new QueryWrapper<>();
        query.eq("WarzoneID", warzoneid);
        List<City> list = cityService.list(query);

        double hall;//堂食收入
        double take_out;//外卖收入
        int orders1; //堂食账单数
        int orders2; //外卖账单数
        for (Date time : listtime) {
            hall = 0.0;
            take_out = 0.0;
            orders1 = 0;
            orders2 = 0;
            Map<String, Object> map = new HashMap<>(); //一条数据
            for (City item : list) {  //获取区县编号
                List<Map<String, Object>> storesale = new ArrayList<>();
                this.getCitySales(storesale, item.getCityID()); //每个区县的经营数据
                for (Map i : storesale) {
                    String time1 = sd.format(time);
                    if (time1.equals(i.get("date").toString())) {
                        hall += Double.parseDouble(i.get("hall_turnover").toString());
                        take_out += Double.parseDouble(i.get("takeout_turnover").toString());
                        orders1 += Integer.parseInt(i.get("hall_order").toString());
                        orders2 += Integer.parseInt(i.get("takeout_order").toString());
                    }
                }
            }
            map.put("date", sd.format(time));  //日期-X轴
            map.put("turnover", hall + take_out);
            map.put("hall_turnover", hall);
            map.put("takeout_turnover", take_out);
            map.put("orders", orders1 + orders2);
            map.put("hall_order", orders1);
            map.put("takeout_order", orders2);

            saledata.add(map);
        }
    }

    //全国详细信息
    @Override
    public void getChinaSales(List<Map<String, Object>> saledata) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");  //时间格式转化

        List<Warzone> list = warzoneService.list();
        Set<Date> settime = new TreeSet<>();
        for (Warzone item : list) {
            List<Date> list1 = this.getProvinceTime(item.getWarzoneID());
            settime.addAll(list1);
        }
        List<Date> listtime = new ArrayList<>(settime);  //全国时间跨度

        double hall;//堂食收入
        double take_out;//外卖收入
        int orders1; //堂食账单数
        int orders2; //外卖账单数
        for (Date time : listtime) {
            hall = 0.0;
            take_out = 0.0;
            orders1 = 0;
            orders2 = 0;
            Map<String, Object> map = new HashMap<>(); //一条数据
            for (Warzone item : list) {  //获取区县编号
                List<Map<String, Object>> storesale = new ArrayList<>();
                this.getProvinceSales(storesale, item.getWarzoneID()); //每个省的经营数据
                for (Map i : storesale) {
                    String time1 = sd.format(time);
                    if (time1.equals(i.get("date").toString())) {
                        hall += Double.parseDouble(i.get("hall_turnover").toString());
                        take_out += Double.parseDouble(i.get("takeout_turnover").toString());
                        orders1 += Integer.parseInt(i.get("hall_order").toString());
                        orders2 += Integer.parseInt(i.get("takeout_order").toString());
                    }
                }
            }
            map.put("date", sd.format(time));  //日期-X轴
            map.put("turnover", hall + take_out);
            map.put("hall_turnover", hall);
            map.put("takeout_turnover", take_out);
            map.put("orders", orders1 + orders2);
            map.put("hall_order", orders1);
            map.put("takeout_order", orders2);

            saledata.add(map);
        }
    }
}
