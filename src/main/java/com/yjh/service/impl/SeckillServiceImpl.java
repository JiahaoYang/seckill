package com.yjh.service.impl;

import com.yjh.Exception.*;
import com.yjh.dao.SeckillDao;
import com.yjh.dao.SuccessKilledDao;
import com.yjh.dto.Exposer;
import com.yjh.dto.SeckillExcution;
import com.yjh.entity.Seckill;
import com.yjh.entity.SuccessKilled;
import com.yjh.enums.SeckillStateEnum;
import com.yjh.service.SeckillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {

    @Resource
    private SeckillDao seckillDao;
    @Resource
    private SuccessKilledDao successKilledDao;

    //混淆字符串
    private final static String salt = "DADdsadsa&**^9>?dsa";


    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.getAll(0, 10);
    }

    @Override
    public Seckill getById(long id) {
        return seckillDao.getById(id);
    }

    @Override
    public Exposer exposerSeckillUrl(long seckillId) {
        Seckill seckill = getById(seckillId);
        if (seckill == null)
            return new Exposer(false, seckillId);
        Date start = seckill.getStartTime();
        Date end = seckill.getEndTime();
        Date now = new Date();
        if (start.getTime() > now.getTime() || end.getTime() < start.getTime())
            return new Exposer(false, seckillId, now, start, end);
        String md5 = getMd5(seckillId);
        return new Exposer(true, md5, seckillId, now, start, end);
    }

    private String getMd5(long seckillId) {
        String base = seckillId + "/" + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    @Override
    @Transactional
    public SeckillExcution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException {
        if (md5 == null || !md5.equals(getMd5(seckillId)))
            throw new SeckillException("seckill data rewrite");
        Date now = new Date();
        int updateCnt = seckillDao.reduceNumber(seckillId, now);
        if (updateCnt <= 0)
            throw new SeckillCloseException("seckill is closed");
        int insertCnt = successKilledDao.insertSuccessKilled(seckillId, userPhone);
        if (insertCnt <= 0)
            throw new RepeatKillException("repeat seckill");
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
        return new SeckillExcution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
    }
}
