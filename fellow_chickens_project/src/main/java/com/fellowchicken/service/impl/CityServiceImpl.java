package com.fellowchicken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fellowchicken.model.City;
import com.fellowchicken.service.CityService;
import com.fellowchicken.mapper.CityMapper;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City>
implements CityService{

}
