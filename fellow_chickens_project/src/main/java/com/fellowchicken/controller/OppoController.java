package com.fellowchicken.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fellowchicken.common.Result;
import com.fellowchicken.common.ResultGenerator;
import com.fellowchicken.model.Oppo;
import com.fellowchicken.service.OppoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@CrossOrigin
@RestController
@Api(tags = {"oppo", "field_management"})
@RequestMapping("/field_management/oppo")

public class OppoController {

    @Resource
    private OppoService oppoService;

    @ResponseBody
    @GetMapping("/fixed_page")
    @ApiOperation(value = "get_oppo_by_page_fixed", notes = "按照指定页码获取固定数量（10）页码")
    public Result getOppoByPageFixed(@RequestParam int index) {
        QueryWrapper<Oppo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("OppoID");
        try {
            return ResultGenerator.genSuccessResult(oppoService.page(new Page<>(index, 10), queryWrapper));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when select a page of oppos by Fixed!");
        }
    }

    @ResponseBody
    @GetMapping("/flexible_page")
    @ApiOperation(value = "get_oppo_by_page_flexible", notes = "按照指定页码和数量获取页码")
    public Result getOppoByPageFlexible(@RequestParam int index, @RequestParam int num) {
        QueryWrapper<Oppo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("OppoID");
        try {
            return ResultGenerator.genSuccessResult(oppoService.page(new Page<>(index, num), queryWrapper));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when select a page of oppos by Flexible!");
        }
    }

    @ResponseBody
    @ApiOperation(value = "get_oppo", notes = "获取指定机会点信息")
    @GetMapping("/get_oppo")
    public Result getOppo(@RequestParam int oppoID) {
        QueryWrapper<Oppo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("OppoID", oppoID);
        try {
            return ResultGenerator.genSuccessResult(oppoService.getOne(queryWrapper));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when select a oppo!");
        }
    }

    @ResponseBody
    @ApiOperation(value = "add_oppo", notes = "新建机会点")
    @GetMapping("/add_oppo")
    public Result addOppo(@RequestParam int minimarketID,
                          @RequestParam String address,
                          @RequestParam boolean trans) {
        Oppo oppo = new Oppo(minimarketID, address, trans);
        try {
            return ResultGenerator.genSuccessResult(oppoService.save(oppo));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when add a oppo!");
        }
    }


    @ResponseBody
    @ApiOperation(value = "delete_oppo", notes = "删除机会点")
    @GetMapping("/delete_oppo")
    public Result deleteOppo(@RequestParam int oppoID) {
        try {
            return ResultGenerator.genSuccessResult(oppoService.removeById(oppoID));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when delete a oppo!");
        }
    }

    @ResponseBody
    @ApiOperation(value = "revise_oppo", notes = "修改机会点")
    @GetMapping("/revise_oppo")
    public Result reviseOppo(@RequestParam int oppoID,
                             @RequestParam int minimarketID,
                             @RequestParam String address,
                             @RequestParam boolean trans,
                             @RequestParam String visitID) {
        Oppo oppo = new Oppo(oppoID, minimarketID, address, trans, visitID);
        try {
            return ResultGenerator.genSuccessResult(oppoService.updateById(oppo));
        } catch (Exception ignored) {
            return ResultGenerator.genFailResult("Something went wrong when revise a oppo!");
        }
    }

}
