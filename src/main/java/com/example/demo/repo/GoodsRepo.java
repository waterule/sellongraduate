package com.example.demo.repo;

import com.example.demo.entity.Goods;
import org.springframework.data.repository.CrudRepository;

public interface GoodsRepo extends CrudRepository<Goods,Long> {
}
