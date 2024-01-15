package com.fellowchicken.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fellowchicken.model.*;
import com.fellowchicken.service.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*", maxAge = 63342)
@RequestMapping("/Area")
public class AreaController {
    @Resource
    private WarzoneService warzoneService;
    @Resource
    private CityService cityService;
    @Resource
    private MinimarketService minimarketService;
    @Resource
    private StoreService storeService;
    @Resource
    private OppoService oppoService;


    @RequestMapping(name = "initial_data", path = "/initial_data")
    public List<?> initial_list(@RequestParam(name = "pname") String pname) {
        if (pname.endsWith("战区管理")) {
            List<Warzone> arraylist;
            arraylist = warzoneService.list();
            return arraylist;
        } else if (pname.endsWith("城市管理")) {
            List<City> arraylist;
            arraylist = cityService.list();
            return arraylist;
        } else {
            List<Minimarket> arraylist;
            arraylist = minimarketService.list();
            return arraylist;
        }
    }

    @CrossOrigin(origins = "*", maxAge = 63342)
    @RequestMapping(name = "get_wz_data", path = "/get_wz_data")
    public String Add_Store(@RequestParam(name = "sid") String sid,
                            @RequestParam(name = "sname") String sname,
                            @RequestParam(name = "sj") String sj,
                            @RequestParam(name = "sw") String sw
    ) {
        Warzone nw = new Warzone();
        if (!Objects.equals(sid, "")) {
            nw.setWarzoneID(Integer.parseInt(sid));
        }
        nw.setWarzoneName(sname);
        nw.setLon(Double.parseDouble(sj));
        nw.setLat(Double.parseDouble(sw));
        if (warzoneService.saveOrUpdate(nw)) {
            return "添加成功！";
        } else {
            return "添加失败~";
        }
    }

    @CrossOrigin(origins = "*", maxAge = 63342)
    @RequestMapping(name = "get_city_data", path = "/get_city_data")
    public String Add_City(@RequestParam(name = "oid") String oid,
                           @RequestParam(name = "oname") String oname,
                           @RequestParam(name = "oup") String oup,
                           @RequestParam(name = "sj") String sj,
                           @RequestParam(name = "sw") String sw) {
        City ncity = new City();
        if (oid != "") {
            ncity.setCityID(Integer.parseInt(oid));
        }

        ncity.setCityName(oname);
        ncity.setWarzoneID(Integer.parseInt(oup));
        ncity.setLon(Double.parseDouble(sj));
        ncity.setLat(Double.parseDouble(sw));
        if (cityService.saveOrUpdate(ncity)) {
            return "添加成功！";
        } else {
            return "添加失败~";
        }
    }

    @RequestMapping(name = "get_mini_data", path = "/get_mini_data")
    public String Add_Mini(@RequestParam(name = "oid") String oid,
                           @RequestParam(name = "oname") String oname,
                           @RequestParam(name = "oup") String oup,
                           @RequestParam(name = "sj") String sj,
                           @RequestParam(name = "sw") String sw) {
        Minimarket nmini = new Minimarket();
        if (oid != "") {
            nmini.setMinimarketID(Integer.parseInt(oid));
        }

        nmini.setMinimarketName(oname);
        nmini.setCityID(Integer.parseInt(oup));
        nmini.setLon(Double.parseDouble(sj));
        nmini.setLat(Double.parseDouble(sw));
        if (minimarketService.saveOrUpdate(nmini)) {
            return "添加成功！";
        } else {
            return "添加失败~";
        }
    }

    @RequestMapping(name = "info_byid", path = "/info_byid")
    public List<?> Info_byid(@RequestParam(name = "pname") String pname, @RequestParam(name = "s-id") String sid) {
        if (pname.endsWith("战区管理")) {
            QueryWrapper<Warzone> qws = new QueryWrapper<>();
            qws.eq("WarzoneID", Integer.parseInt(sid));
            List<Warzone> list = warzoneService.list(qws);
            return list;
        } else {
            if (pname.endsWith("城市管理")) {
                QueryWrapper<City> qws = new QueryWrapper<>();
                qws.eq("CityID", Integer.parseInt(sid));
                List<City> list = cityService.list(qws);
                return list;
            } else {
                QueryWrapper<Minimarket> qws = new QueryWrapper<>();
                qws.eq("MinimarketID", Integer.parseInt(sid));
                List<Minimarket> list = minimarketService.list(qws);
                return list;
            }
        }
    }

    @RequestMapping(name = "remove_byid", path = "/removw_byid")
    public String Remove_byid(@RequestParam(name = "pname") String pname, @RequestParam(name = "s-id") String sid) {
        Integer u_id = Integer.parseInt(sid);
        if (pname.endsWith("战区管理")) {
            if (warzoneService.removeById(u_id)) {
                return "删除成功！";
            } else {
                return "删除失败~";
            }
        } else {
            if (pname.endsWith("城市管理")) {
                if (cityService.removeById(u_id)) {
                    return "删除成功！";
                } else {
                    return "删除失败~";
                }
            } else {
                if (minimarketService.removeById(u_id)) {
                    return "删除成功！";
                } else {
                    return "删除失败~";
                }
            }
        }
    }


}
