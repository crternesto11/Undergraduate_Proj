package com.fellowchicken.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("business")
@AllArgsConstructor
@NoArgsConstructor
public class Business implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "BusinessID", type = IdType.AUTO)
    private Integer businessID;

    @TableField("StoreID")
    private Integer storeID;

    @TableField("Mode")
    private Integer mode;

    @TableField("DateMonth")
    private Date dateMonth;

    @TableField("Orders")
    private Integer orders;

    @TableField("Turnover")
    private double turnover;

    public Business(Integer storeID, Integer mode, Date dateMonth, Integer orders, double turnover) {
        this.storeID = storeID;
        this.mode = mode;
        this.dateMonth = dateMonth;
        this.orders = orders;
        this.turnover = turnover;
    }
}