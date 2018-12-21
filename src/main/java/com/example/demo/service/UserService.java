package com.example.demo.service;

import com.example.demo.entity.Goods;

public interface UserService {
    public boolean getuser(String username,String password);
    public Long saveUser(String username,String password);
    public Long saveGoods(Goods gods);
}
