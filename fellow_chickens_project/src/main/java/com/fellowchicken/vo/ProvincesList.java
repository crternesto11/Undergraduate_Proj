package com.fellowchicken.vo;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ProvincesList {
    @Column(name = "warzoneID")
    private Integer warzoneID;
    @Column(name = "provinceName")
    private String provinceName;
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

    public ProvincesList(Integer warzoneID,String provinceName,Integer rank,Double sale,Integer shopNum,Double lon,Double lat){
        this.warzoneID = warzoneID;
        this.provinceName = provinceName;
        this.rank = rank;
        this.sale = sale;
        this.shopNum = shopNum;
        this.lon = lon;
        this.lat = lat;
    }
}
