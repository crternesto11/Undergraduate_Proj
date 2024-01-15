package com.fellowchicken.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("city")
@AllArgsConstructor
@NoArgsConstructor
public class City implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "CityID", type = IdType.AUTO)
    private Integer cityID;

    @TableField("CityName")
    private String cityName;

    @TableField("WarzoneID")
    private Integer warzoneID;

    @TableField("Lon")
    private double Lon;

    @TableField("Lat")
    private double Lat;

    public City(String cityName, Integer warzoneID, double lon, double lat) {
        this.cityName = cityName;
        this.warzoneID = warzoneID;
        Lon = lon;
        Lat = lat;
    }
}