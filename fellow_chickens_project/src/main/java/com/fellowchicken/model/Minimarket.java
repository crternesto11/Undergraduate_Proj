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
@TableName("minimarket")
@AllArgsConstructor
@NoArgsConstructor
public class Minimarket implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "MinimarketID", type = IdType.AUTO)
    private Integer minimarketID;

    @TableField("MinimarketName")
    private String minimarketName;

    @TableField("CityID")
    private Integer cityID;

    @TableField("Lon")
    private double Lon;

    @TableField("Lat")
    private double Lat;

    public Minimarket(String minimarketName, Integer cityID, double lon, double lat) {
        this.minimarketName = minimarketName;
        this.cityID = cityID;
        Lon = lon;
        Lat = lat;
    }
}