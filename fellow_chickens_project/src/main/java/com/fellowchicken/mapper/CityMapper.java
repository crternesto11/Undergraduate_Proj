package com.fellowchicken.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fellowchicken.model.City;
import org.apache.ibatis.annotations.Param;

public interface CityMapper extends BaseMapper<City> {
    void add(@Param("city") City city);
}
