package com.fellowchicken.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fellowchicken.common.Result;
import com.fellowchicken.common.ResultGenerator;
import com.fellowchicken.model.Store;
import com.fellowchicken.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Slf4j
@RestController
@CrossOrigin
@Api(tags = {"store", "field_management"})
@RequestMapping("/field_management/store")
public class StoreController {

    @Resource
    private StoreService storeService;

    @ResponseBody
    @GetMapping("/fixed_page")
    @ApiOperation(value = "get_store_by_page_fixed", notes = "按照指定页码获取固定数量（8）页码")
    public Result getStoreByPageFixed(@RequestParam int index) {
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("StoreID");
        try {
            return ResultGenerator.genSuccessResult(storeService.page(new Page<>(index, 8), queryWrapper));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when select a page of stores by Fixed!");
        }
    }

    @ResponseBody
    @GetMapping("/flexible_page")
    @ApiOperation(value = "get_store_by_page_flexible", notes = "按照指定页码和数量获取页码")
    public Result getStoreByPageFlexible(@RequestParam int index, @RequestParam int num) {
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("StoreID");
        try {
            return ResultGenerator.genSuccessResult(storeService.page(new Page<>(index, num), queryWrapper));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when select a page of stores by Flexible!");
        }
    }

    @ResponseBody
    @ApiOperation(value = "get_store", notes = "获取指定门店信息")
    @GetMapping("/get_store")
    public Result getStore(@RequestParam int id) {
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("StoreID", id);
        try {
            return ResultGenerator.genSuccessResult(storeService.getOne(queryWrapper));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when select a store!");
        }
    }

    @ResponseBody
    @ApiOperation(value = "add_store", notes = "新建门店")
    @GetMapping("/add_store")
    public Result addStore(@RequestParam String storeName,
                           @RequestParam String minimarketID,
                           @RequestParam String address,
                           @RequestParam String longitude,
                           @RequestParam String latitude,
                           @RequestParam String openingTime,
                           @RequestParam String storeMode,
                           @RequestParam String floor,
                           @RequestParam String square,
                           @RequestParam String license) {
        Store store = new Store(storeName,
                Integer.parseInt(minimarketID),
                address,
                Double.parseDouble(longitude),
                Double.parseDouble(latitude),
                Timestamp.valueOf(openingTime),
                Integer.parseInt(storeMode),
                Integer.parseInt(floor),
                Integer.parseInt(square),
                Boolean.getBoolean(license));
        try {
            return ResultGenerator.genSuccessResult(storeService.save(store));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when add a store!");
        }
    }


    @ResponseBody
    @ApiOperation(value = "delete_store", notes = "删除门店")
    @GetMapping("/delete_store")
    public Result deleteStore(@RequestParam int id) {
        try {
            return ResultGenerator.genSuccessResult(storeService.removeById(id));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when delete a store!");
        }
    }

    @ResponseBody
    @ApiOperation(value = "revise_store", notes = "修改门店")
    @GetMapping("/revise_store")
    public Result reviseStore(@RequestParam int storeID,
                              @RequestParam String storeName,
                              @RequestParam int minimarketID,
                              @RequestParam String address,
                              @RequestParam double longitude,
                              @RequestParam double latitude,
                              @RequestParam Timestamp openingTime,
                              @RequestParam int storeMode,
                              @RequestParam int floor,
                              @RequestParam int square,
                              @RequestParam boolean license,
                              @RequestParam String photo) {
        Store store = new Store(storeID, storeName, minimarketID, address, longitude, latitude, openingTime, storeMode, floor, square, license, photo);
        try {
            return ResultGenerator.genSuccessResult(storeService.updateById(store));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when revise a store!");
        }
    }
}
