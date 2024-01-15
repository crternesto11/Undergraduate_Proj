package com.fellowchicken.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fellowchicken.model.Visit;
import com.fellowchicken.model.Warzone;
import org.apache.ibatis.annotations.Param;

public interface WarzoneMapper extends BaseMapper<Warzone> {
    void add(@Param("warzone") Warzone warzone);
}
