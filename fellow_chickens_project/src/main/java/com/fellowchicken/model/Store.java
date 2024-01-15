package com.fellowchicken.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("store")
@AllArgsConstructor
@NoArgsConstructor
public class Store implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "StoreID", type = IdType.AUTO)
    private Integer storeID;

    @TableField("StoreName")
    private String storeName;

    @TableField("MinimarketID")
    private Integer minimarketID;

    @TableField("Address")
    private String address;

    @TableField("Lon")
    private double lon;

    @TableField("Lat")
    private double lat;

    @TableField("OpeningTime")
    private Timestamp openingTime;

    @TableField("StoreMode")
    private Integer storeMode;

    @TableField("Floor")
    private Integer floor;

    @TableField("Square")
    private Integer square;

    @TableField("License")
    private boolean license;

    @TableField("Photo")
    private String photo;

    public Store(String storeName, Integer minimarketID, String address, double lon, double lat, Timestamp openingTime, Integer storeMode, Integer floor, Integer square, boolean license) {
        this.storeName = storeName;
        this.minimarketID = minimarketID;
        this.address = address;
        this.lon = lon;
        this.lat = lat;
        this.openingTime = openingTime;
        this.storeMode = storeMode;
        this.floor = floor;
        this.square = square;
        this.license = license;
    }
}