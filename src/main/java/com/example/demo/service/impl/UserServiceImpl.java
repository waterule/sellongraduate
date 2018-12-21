package com.example.demo.service.impl;

import com.example.demo.entity.Goods;
import com.example.demo.repo.GoodsRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    EntityManager em;
    @Autowired
    UserRepo repo;
    @Autowired
    GoodsRepo godsRepo;
    @Override
    public Long getuser(String username,String password) {
        String sql = "select t.id  from user t   ";
        sql+= "  where t.username =:username";
        sql+= "  and t.password =:password";
        Query query = em.createNativeQuery(sql).setParameter("username",username).setParameter("password",password);
        Object obj = query.getSingleResult();
        if(obj != null ){
            return Long.valueOf(obj.toString());
        }
        return 0L;
    }

    @Override
    public Long saveUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
           return repo.save(user).getId();


    }

    @Override
    public Long saveGoods(Goods gods) {
        return godsRepo.save(gods).getId();
    }


}
