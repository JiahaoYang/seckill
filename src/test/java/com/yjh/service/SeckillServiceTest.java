package com.yjh.service;

import com.yjh.dto.Exposer;
import com.yjh.dto.SeckillExcution;
import com.yjh.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillServiceTest {

    @Resource
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> list =  seckillService.getSeckillList();
        list.forEach(System.out::println);
    }

    @Test
    public void getById() {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        System.out.println(seckill);
    }

    @Test
    public void exposerSeckillUrl() {
        long id = 1000;
        Exposer exposer = seckillService.exposerSeckillUrl(id);
        System.out.println(exposer);
    }

    @Test
    public void executeSeckill() {
        long id = 1000;
        String md5 = "889924dca468ba76dec4f0135e9e5123";
        long phone = 13806188299L;
        SeckillExcution seckillExcution = seckillService.executeSeckill(id, phone, md5);
        System.out.println(seckillExcution);
    }
}