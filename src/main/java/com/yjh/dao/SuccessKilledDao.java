package com.yjh.dao;

import com.yjh.entity.SuccessKilled;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SuccessKilledDao {

    int insertSuccessKilled(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);

}
