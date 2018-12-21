package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Init {
    @Autowired
    UserService userser;
   @RequestMapping("/")
   public ModelAndView init(){

       return new ModelAndView("html/login");
   }
    @RequestMapping("/login/{username}/{password}")
    public String getUser(@PathVariable String username, @PathVariable String password){
        boolean bb =   userser.getuser(username,password);
        if (bb)
            return "00";
        return "11";

    }
    @RequestMapping("/register")
    public String regis(){
        return "register";
    }
    @RequestMapping("/regs/{username}/{password}")
    public String regis(@PathVariable String username, @PathVariable String password){
        userser.saveUser(username,password);
        return "sucess";
    }
    @RequestMapping(value = "/savegoods",method = RequestMethod.POST)
    public String saveGood(){
        return null;
    }


}
