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
@TableName("warzone")
@AllArgsConstructor
@NoArgsConstructor
public class Warzone implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "WarzoneID", type = IdType.AUTO)
    private Integer warzoneID;

    @TableField("WarzoneName")
    private String warzoneName;

    @TableField("Lon")
    private double lon;

    @TableField("Lat")
    private double lat;

    public Warzone(String warzoneName, double lon, double lat) {
        this.warzoneName = warzoneName;
        this.lon = lon;
        this.lat = lat;
    }
}