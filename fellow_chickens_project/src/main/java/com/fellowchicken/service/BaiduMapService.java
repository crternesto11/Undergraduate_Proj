package com.fellowchicken.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BaiduMapService {
    //根据门店编号获取门店销售额
    double getSale(int storeID);

    //根据市场编号，获取门店数量
    int getMarketStoreNum(int minimarketID);

    //根据市场编号，获取市场营业额
    double getMarketSale(int minimarketID);

    //根据城市编号，获取城市内的门店数量
    int getCityShopNum(int cityID);

    //根据城市编号，获取城市的营业额
    double getCitySale(int cityID);

    //根据省级编号，获取各省的门店数
    int getProShopNum(int warzoneID);

    //根据省级编号，获取各省的营业额
    double getProvinceSale(int warzoneID);

    //全国门店数据
    int getShopNum();

    //全国机会点数
    int getAllOppoNum();

    //省级机会点数
    int getProvinceOppoNum(int warzoneID);

    //市级机会点数
    int getCityOppoNum(int cityID);

    //区县机会点数
    int getRegionalOppoNum(int minID);

    //门店经营情况时间集合
    List<Date> getTime(int storeID);

    List<Date> getMarketTime(int marketID);

    List<Date> getCityTime(int cityID);

    List<Date> getProvinceTime(int warzoneID);

    //门店详细经营数据
    void getStoreSale(List<Map<String, Object>> sale_data, int storeV);

    //市场详细信息
    void getMarketSales(List<Map<String, Object>> sale_data, int marketID);

    //城市详细信息
    void getCitySales(List<Map<String, Object>> sale_data, int cityID);

    //省详细信息
    void getProvinceSales(List<Map<String, Object>> saledata, int warzoneID);

    //全国详细信息
    void getChinaSales(List<Map<String, Object>> saledata);
}
