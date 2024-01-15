package com.fellowchicken.vo;

import lombok.Data;

import javax.persistence.Column;

@Data
public class CitiesByProvince {
    @Column(name = "cityID")
    private Integer cityID;
    @Column(name = "cityName")
    private String cityName;
    @Column(name = "rank")
    private Integer rank;
    @Column(name = "sale")
    private Double sale;
    @Column(name = "shopNum")
    private Integer shopNum;
    @Column(name = "lon")
    private Double lon;
    @Column(name = "lat")
    private Double lat;

    public CitiesByProvince(Integer cityID,String cityName,Integer rank,Double sale,Integer shopNum,Double lon,Double lat){
        this.cityID = cityID;
        this.cityName = cityName;
        this.rank = rank;
        this.sale = sale;
        this.shopNum = shopNum;
        this.lon = lon;
        this.lat = lat;
    }
}
