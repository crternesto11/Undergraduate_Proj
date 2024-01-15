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
@TableName("oppo")
@AllArgsConstructor
@NoArgsConstructor
public class Oppo implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "OppoID", type = IdType.AUTO)
    private Integer oppoID;

    @TableField("MinimarketID")
    private Integer minimarketID;

    @TableField("Address")
    private String address;

    @TableField("Trans")
    private boolean trans;

    @TableField("VisitID")
    private String visitID;

    public Oppo(Integer minimarketID, String address, boolean trans, String visitID) {
        this.minimarketID = minimarketID;
        this.address = address;
        this.trans = trans;
        this.visitID = visitID;
    }

    public Oppo(int minimarketID, String address, boolean trans) {
    }
}