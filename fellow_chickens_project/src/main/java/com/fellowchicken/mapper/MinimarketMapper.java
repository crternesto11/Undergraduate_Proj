package com.fellowchicken.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fellowchicken.model.Minimarket;
import org.apache.ibatis.annotations.Param;

public interface MinimarketMapper extends BaseMapper<Minimarket> {
    void add(@Param("minimarket") Minimarket minimarket);
}
