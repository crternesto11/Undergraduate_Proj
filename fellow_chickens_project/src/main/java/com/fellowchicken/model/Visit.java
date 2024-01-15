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
@TableName("visit")
@AllArgsConstructor
@NoArgsConstructor
public class Visit implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "VisitID", type = IdType.AUTO)
    private Integer visitID;

    @TableField("VisitTime")
    private Date visitTime;

    @TableField("Visitor")
    private String visitor;

    @TableField("Comment")
    private String comment;

    @TableField("Files")
    private String files;

    @TableField("StoreID")
    private Integer storeID;

    public Visit(Date visitTime, String visitor, String comment, String files, Integer storeID) {
        this.visitTime = visitTime;
        this.visitor = visitor;
        this.comment = comment;
        this.files = files;
        this.storeID = storeID;
    }

}