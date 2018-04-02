package com.yjh.dao;

import com.yjh.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao;

    @Test
    public void reduceNumber() {
        long id = 1000;
    }

    @Test
    public void getById() {
        long id = 1000;
        Seckill seckill = seckillDao.getById(id);
        System.out.println(seckill);
    }

    @Test
    public void getAll() {
        List<Seckill> seckills = seckillDao.getAll(0, 5);
        seckills.forEach(System.out::println);
    }
}