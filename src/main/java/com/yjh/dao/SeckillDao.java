package com.yjh.dao;

import com.yjh.entity.Seckill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface SeckillDao {

    int reduceNumber(@Param("seckillId") Long seckillId, @Param("killTime") LocalDate killTime);

    Seckill getById(Long seckillId);

    List<Seckill> getAll(@Param("offset") int offset,@Param("limit") int limit);
}
