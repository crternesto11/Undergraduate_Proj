package com.fellowchicken.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fellowchicken.common.Result;
import com.fellowchicken.common.ResultGenerator;
import com.fellowchicken.model.City;
import com.fellowchicken.model.Minimarket;
import com.fellowchicken.service.CityService;
import com.fellowchicken.service.MinimarketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@Api(tags = {"city", "field_management"})
@RequestMapping("/field_management/city")
public class CityManagementController {

    @Resource
    private CityService cityService;

    @Resource
    private MinimarketService minimarketService;

    @ResponseBody
    @ApiOperation(value = "get_city_by_page_fixed", notes = "按照指定页码获取固定数量（10）页码")
    @GetMapping("/fixed_page")
    public Result<Page<City>> getCityByPageFixed(@RequestParam int index) {
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("CityID");
        try {
            return ResultGenerator.genSuccessResult(cityService.page(new Page<>(index, 10), queryWrapper));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when select a page of cities by Fixed!");
        }
    }

    @ResponseBody
    @ApiOperation(value = "get_city_by_page_flexible", notes = "按照指定页码和数量获取页码")
    @GetMapping("/flexible_page")
    public Result<Page<City>> getCityByPageFlexible(@RequestParam int index, @RequestParam int num) {
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("CityID");
        try {
            return ResultGenerator.genSuccessResult(cityService.page(new Page<>(index, num), queryWrapper));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when select a page of cities by Flexible!");
        }
    }

    @ResponseBody
    @ApiOperation(value = "get_city", notes = "获取指定城市信息")
    @GetMapping("/get")
    public Result<City> getCity(@RequestParam int cityID) {
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("CityID", cityID);
        try {
            return ResultGenerator.genSuccessResult(cityService.getOne(queryWrapper));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when select a city!");
        }
    }

    @ResponseBody
    @ApiOperation(value = "add_city", notes = "新建城市")
    @GetMapping("/add")
    public Result<Boolean> addCity(@RequestParam String cityName,
                                   @RequestParam int warzoneID,
                                   @RequestParam double longitude,
                                   @RequestParam double latitude) {
        try {
            return ResultGenerator.genSuccessResult(cityService.save(new City(cityName, warzoneID, longitude, latitude)));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when add a city!");
        }
    }


    @ResponseBody
    @ApiOperation(value = "delete_city", notes = "删除城市")
    @GetMapping("/delete")
    public Result<Boolean> deleteCity(@RequestParam int cityID) {
        try {
            QueryWrapper<Minimarket> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("*").eq("CityID", cityID);
            List<Minimarket> minimarketList = minimarketService.list(queryWrapper);
            if (minimarketList.isEmpty()) {
                return ResultGenerator.genSuccessResult(cityService.removeById(cityID));
            } else {
                return ResultGenerator.genFailResult(minimarketList.toString());
            }
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when delete a city!");
        }
    }

    @ResponseBody
    @ApiOperation(value = "revise_city", notes = "修改城市")
    @GetMapping("/revise")
    public Result<Boolean> reviseCityW(@RequestParam int cityID,
                                       @RequestParam String cityName,
                                       @RequestParam int warzoneID,
                                       @RequestParam double longitude,
                                       @RequestParam double latitude) {
        try {
            return ResultGenerator.genSuccessResult(cityService.updateById(new City(cityID, cityName, warzoneID, longitude, latitude)));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when revise a city!");
        }
    }
}
