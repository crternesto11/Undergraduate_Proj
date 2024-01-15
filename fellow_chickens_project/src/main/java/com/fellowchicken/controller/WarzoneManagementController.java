package com.fellowchicken.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fellowchicken.common.Result;
import com.fellowchicken.common.ResultGenerator;
import com.fellowchicken.model.City;
import com.fellowchicken.model.Warzone;
import com.fellowchicken.service.CityService;
import com.fellowchicken.service.WarzoneService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@Api(tags = {"warzone", "field_management"})
@RequestMapping("/field_management/warzone")
public class WarzoneManagementController {

    @Resource
    private WarzoneService warzoneService;

    @Resource
    private CityService cityService;

    @ResponseBody
    @ApiOperation(value = "get_warzone_by_page_fixed", notes = "按照指定页码获取固定数量（8）页码")
    @GetMapping("/fixed_page")
    public Result<Page<Warzone>> getWarzoneByPageFixed(@RequestParam int index) {
        QueryWrapper<Warzone> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("WarzoneID");
        try {
            return ResultGenerator.genSuccessResult(warzoneService.page(new Page<>(index, 8), queryWrapper));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when select a page of warzones by Fixed!");
        }
    }

    @ResponseBody
    @ApiOperation(value = "get_warzone_by_page_flexible", notes = "按照指定页码和数量获取页码")
    @GetMapping("/flexible_page")
    public Result<Page<Warzone>> getWarzoneByPageFlexible(@RequestParam int index, @RequestParam int num) {
        QueryWrapper<Warzone> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("WarzoneID");
        try {
            return ResultGenerator.genSuccessResult(warzoneService.page(new Page<>(index, num), queryWrapper));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when select a page of warzones by Flexible!");
        }
    }

    @ResponseBody
    @ApiOperation(value = "get_warzone", notes = "获取指定战区信息")
    @GetMapping("/get")
    public Result<Warzone> getWarzone(@RequestParam int warzoneID) {
        QueryWrapper<Warzone> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("WarzoneID", warzoneID);
        try {
            return ResultGenerator.genSuccessResult(warzoneService.getOne(queryWrapper));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when select a warzone!");
        }
    }

    @ResponseBody
    @ApiOperation(value = "add_warzone", notes = "新建战区")
    @GetMapping("/add")
    public Result<Boolean> addWarzone(@RequestParam String warzoneName,
                                      @RequestParam double longitude,
                                      @RequestParam double latitude) {
        try {
            return ResultGenerator.genSuccessResult(warzoneService.save(new Warzone(warzoneName, longitude, latitude)));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when add a warzone!");
        }
    }


    @ResponseBody
    @ApiOperation(value = "delete_warzone", notes = "删除战区")
    @GetMapping("/delete")
    public Result<Boolean> deleteWarzone(@RequestParam int warzoneID) {
        try {
            QueryWrapper<City> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("*").eq("WarzoneID", warzoneID);
            List<City> cityList = cityService.list(queryWrapper);
            if (cityList.isEmpty()) {
                return ResultGenerator.genSuccessResult(warzoneService.removeById(warzoneID));
            } else {
                return ResultGenerator.genFailResult(cityList.toString());
            }
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when delete a warzone!");
        }
    }

    @ResponseBody
    @ApiOperation(value = "revise_warzone", notes = "修改战区")
    @GetMapping("/revise")
    public Result<Boolean> reviseWarzone(@RequestParam Integer warzoneID,
                                         @RequestParam String warzoneName,
                                         @RequestParam double longitude,
                                         @RequestParam double latitude) {
        try {
            return ResultGenerator.genSuccessResult(warzoneService.updateById(new Warzone(warzoneID, warzoneName, longitude, latitude)));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when revise a warzone!");
        }
    }
}
