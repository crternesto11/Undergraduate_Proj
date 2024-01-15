package com.fellowchicken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fellowchicken.model.Minimarket;
import com.fellowchicken.service.MinimarketService;
import com.fellowchicken.mapper.MinimarketMapper;
import org.springframework.stereotype.Service;

@Service
public class MinimarketServiceImpl extends ServiceImpl<MinimarketMapper, Minimarket>
implements MinimarketService{

}
