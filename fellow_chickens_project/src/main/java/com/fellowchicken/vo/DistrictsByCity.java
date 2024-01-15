package com.fellowchicken.vo;

import lombok.Data;

import javax.persistence.Column;

@Data
public class DistrictsByCity {
    @Column(name = "minimarketID")
    private Integer minimarketID;
    @Column(name = "districtName")
    private String districtName;
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

    public DistrictsByCity(Integer minimarketID,String districtName,Integer rank,Double sale,Integer shopNum,Double lon,Double lat){
        this.minimarketID = minimarketID;
        this.districtName = districtName;
        this.rank = rank;
        this.sale = sale;
        this.shopNum = shopNum;
        this.lon = lon;
        this.lat = lat;
    }
}
