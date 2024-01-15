package com.fellowchicken.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fellowchicken.model.Business;
import com.fellowchicken.service.BusinessService;
import com.fellowchicken.mapper.BusinessMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business>
        implements BusinessService {

}
