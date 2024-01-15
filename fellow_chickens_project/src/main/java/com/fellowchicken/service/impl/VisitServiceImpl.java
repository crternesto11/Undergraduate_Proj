package com.fellowchicken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fellowchicken.model.Visit;
import com.fellowchicken.service.VisitService;
import com.fellowchicken.mapper.VisitMapper;
import org.springframework.stereotype.Service;

@Service
public class VisitServiceImpl extends ServiceImpl<VisitMapper, Visit>
implements VisitService{

}
