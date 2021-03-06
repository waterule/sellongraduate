package com.example.demo.controller;

import com.example.demo.entity.Goods;
import com.example.demo.repo.GoodsRepo;
import com.example.demo.service.UserService;
import com.example.demo.vo.RespCom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Api("登陆入口")
@RestController

public class Init {
    @Autowired
    UserService userser;
    @Autowired
    GoodsRepo grepo;

    @ApiOperation(value="首页", notes = "首页信息")
   @RequestMapping(value = "/" ,method = RequestMethod.GET)
   public ModelAndView init()
    {
       return new ModelAndView("html/goodlist");
   }
    @ApiOperation(value = "登陆校验")

    @GetMapping("/login/{username}/{password}")

    public String getUser(@PathVariable String username, @PathVariable String password){
        Long usreid  =   userser.getuser(username,password);
        return String.valueOf(usreid);

    }

    @ApiOperation(value = "是否已存在")

    @GetMapping("/login/{username}")

    public String getUser(@PathVariable String username){
        boolean bb =   userser.ishasU(username);
        if(bb)
            return "00";
        return "11";

    }
    @ApiOperation(value = "注册用户")
    @GetMapping("/regs/{username}/{password}")
    public String regis(@PathVariable String username, @PathVariable String password){
        userser.saveUser(username,password);
        return "success";
    }
    @ApiOperation(value = "发布物品")
    @PostMapping(value = "/savegoods")
    public  RespCom saveGood(@RequestBody Goods goods){
        RespCom resp = new RespCom();
        grepo.save(goods);
        resp.setRescode("00");
         return resp;
    }

    @ApiOperation(value = "查询物品list")
    @GetMapping(value = "/getgoods")
    public List<Goods> getgoodlist(){
       return userser.getGoodslist();
    }



}
