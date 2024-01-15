package com.fellowchicken.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fellowchicken.common.Result;
import com.fellowchicken.common.ResultGenerator;
import com.fellowchicken.model.Minimarket;
import com.fellowchicken.model.Store;
import com.fellowchicken.service.MinimarketService;
import com.fellowchicken.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@Api(tags = {"minimarket", "field_management"})
@RequestMapping("/field_management/minimarket")
public class MinimarketManagementController {

    @Resource
    private MinimarketService minimarketService;

    @Resource
    private StoreService storeService;

    @ResponseBody
    @ApiOperation(value = "get_minimarket_by_page_flexible", notes = "按照指定页码和数量获取页码")
    @GetMapping("/flexible_page")
    public Result<Page<Minimarket>> getMinimarketByPageFlexible(@RequestParam int index,
                                                                @RequestParam int num) {
        QueryWrapper<Minimarket> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("MinimarketID");
        try {
            return ResultGenerator.genSuccessResult(minimarketService.page(new Page<>(index, num), queryWrapper));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when select a page of cities by Flexible!");
        }
    }

    @ResponseBody
    @ApiOperation(value = "get_minimarket_by_page_fixed", notes = "按照指定页码获取固定数量（10）页码")
    @GetMapping("/fixed_page")
    public Result<Page<Minimarket>> getMinimarketByPageFixed(@RequestParam int index) {
        QueryWrapper<Minimarket> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("MinimarketID");
        try {
            return ResultGenerator.genSuccessResult(minimarketService.page(new Page<>(index, 10), queryWrapper));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when select a page of cities by Fixed!");
        }
    }

    @ResponseBody
    @ApiOperation(value = "get_minimarket", notes = "获取指定迷你市场信息")
    @GetMapping("/get")
    public Result<Minimarket> getMinimarket(@RequestParam int minimarketID) {
        QueryWrapper<Minimarket> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("MinimarketID", minimarketID);
        try {
            return ResultGenerator.genSuccessResult(minimarketService.getOne(queryWrapper));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when select a warzone!");
        }
    }

    @ResponseBody
    @ApiOperation(value = "add_minimarket", notes = "新建迷你市场")
    @GetMapping("/add")
    public Result<Boolean> addMinimarket(@RequestParam String minimarketName,
                                         @RequestParam int cityID,
                                         @RequestParam Double longitude,
                                         @RequestParam Double latitude) {
        try {
            return ResultGenerator.genSuccessResult(minimarketService.save(new Minimarket(minimarketName,cityID,longitude,latitude)));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when add a warzone!");
        }
    }


    @ResponseBody
    @ApiOperation(value = "delete_minimarket", notes = "删除迷你市场")
    @GetMapping("/delete")
    public Result<Boolean> deleteMinimarket(@RequestParam int minimarketID) {
        try {
            QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("*").eq("MinimarketID", minimarketID);
            List<Store> storeList = storeService.list(queryWrapper);
            if (storeList.isEmpty()) {
                return ResultGenerator.genSuccessResult(minimarketService.removeById(minimarketID));
            } else {
                return ResultGenerator.genFailResult(storeList.toString());
            }
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when delete a warzone!");
        }
    }

    @ApiOperation(value = "revise_minimarket", notes = "修改迷你市场")
    @GetMapping("/revise")
    public Result<Boolean> reviseMinimarket(@RequestParam int minimarketID,
                                            @RequestParam String minimarketName,
                                            @RequestParam int cityID,
                                            @RequestParam double longitude,
                                            @RequestParam double latitude) {
        try {
            return ResultGenerator.genSuccessResult(minimarketService.updateById(new Minimarket(minimarketID, minimarketName, cityID, longitude, latitude)));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when revise a warzone!");
        }
    }

}
