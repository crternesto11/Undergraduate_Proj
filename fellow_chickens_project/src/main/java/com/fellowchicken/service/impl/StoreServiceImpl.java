package com.fellowchicken.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fellowchicken.model.Store;
import com.fellowchicken.service.BusinessService;
import com.fellowchicken.service.StoreService;
import com.fellowchicken.mapper.StoreMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store>
implements StoreService{

}
