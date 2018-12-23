package com.example.demo.service;

import com.example.demo.entity.Goods;

import java.util.List;

public interface UserService {
    public Long getuser(String username,String password);
    public Long saveUser(String username,String password);
    public Long saveGoods(Goods gods);
    public boolean ishasU(String username);
    public List<Goods> getGoodslist();
}
