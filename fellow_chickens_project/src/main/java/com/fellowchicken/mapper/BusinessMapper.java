package com.fellowchicken.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fellowchicken.model.Business;
import org.apache.ibatis.annotations.Param;

public interface BusinessMapper extends BaseMapper<Business> {
    void add(@Param("business") Business business);
}
