package com.yjh.service;

import com.yjh.Exception.SeckillException;
import com.yjh.dto.Exposer;
import com.yjh.dto.SeckillExcution;
import com.yjh.entity.Seckill;

import java.util.List;

public interface SeckillService {
    List<Seckill> getSeckillList();

    Seckill getById(long id);

    /**
     * 在秒杀开始时输出地址，未开始时输出系统时间
     * @param seckillId
     */
    Exposer exposerSeckillUrl(long seckillId);

    SeckillExcution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException;

    SeckillExcution executeByProcedure(long seckillId, long userPhone, String md5);

}
