package com.fellowchicken.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fellowchicken.model.Oppo;
import org.apache.ibatis.annotations.Param;

public interface OppoMapper extends BaseMapper<Oppo> {
    void add(@Param("oppo") Oppo oppo);
}
