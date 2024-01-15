package com.fellowchicken.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fellowchicken.model.Visit;
import org.apache.ibatis.annotations.Param;

public interface VisitMapper extends BaseMapper<Visit> {
    void add(@Param("visit") Visit visit);
}
