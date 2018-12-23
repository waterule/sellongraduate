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
import java.util.List;

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
        Long ll = 0L;
        String sql = "select t.id  from user t   ";
        sql+= "  where t.username =:username";
        sql+= "  and t.password =:password  limit 0,1";
        Query query = em.createNativeQuery(sql).setParameter("username",username).setParameter("password",password);
        try {
            Object obj = query.getSingleResult();
            if(obj != null ){
                ll = Long.valueOf(obj.toString());
            }
        }catch (Exception e){

        }

        return ll;
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

    @Override
    public boolean ishasU(String username) {
        String sql = "select t.id  from user t   ";
        sql+= "  where t.username =:username";
        sql+= "   limit 0,1";
        Query query = em.createNativeQuery(sql).setParameter("username",username);
        try {
            Object obj = query.getSingleResult();
            if(obj != null ){
               return true;
            }
        }catch (Exception e){

        }
        return false;
    }

    @Override
    public List<Goods> getGoodslist() {
        StringBuilder sb = new StringBuilder();
        sb.append("  from ");
        sb.append(Goods.class.getName());
        List<Goods> list = em.createQuery(sb.toString()).getResultList();
        if(list!=null &&list.size()>0){
            return list;
        }
        return null;
    }


}
