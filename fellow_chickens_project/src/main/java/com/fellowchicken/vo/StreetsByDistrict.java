package com.fellowchicken.vo;

import lombok.Data;

import javax.persistence.Column;

@Data
public class StreetsByDistrict {
    @Column(name = "storeID")
    private Integer storeID;
    @Column(name = "shopName")
    private String shopName;
    @Column(name = "lon")
    private Double lon;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "shopSale")
    private Double shopSale;

    public StreetsByDistrict(Integer storeID,String shopName,Double lon,Double lat, Double shopSale){
        this.storeID = storeID;
        this.shopName = shopName;
        this.lon = lon;
        this.lat = lat;
        this.shopSale = shopSale;
    }
}
