package com.fellowchicken.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fellowchicken.model.Store;
import org.apache.ibatis.annotations.Param;

public interface StoreMapper extends BaseMapper<Store> {
    void add(@Param("store") Store store);
}
