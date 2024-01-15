package com.fellowchicken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fellowchicken.model.Warzone;
import com.fellowchicken.service.WarzoneService;
import com.fellowchicken.mapper.WarzoneMapper;
import org.springframework.stereotype.Service;

@Service
public class WarzoneServiceImpl extends ServiceImpl<WarzoneMapper, Warzone>
        implements WarzoneService {
}
