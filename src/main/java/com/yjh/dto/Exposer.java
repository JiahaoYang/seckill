package com.yjh.dto;


import java.util.Date;

/**
 * 暴露秒杀地址，否则返回当前系统时间
 */
public class Exposer {
    private boolean exposered;

    private String md5;

    private Long seckillId;

    private Date now;

    private Date start;

    private Date end;

    public Exposer(boolean exposered, String md5, Long seckillId) {
        this.exposered = exposered;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposered, Long seckillId, Date now, Date start, Date end) {
        this.exposered = exposered;
        this.seckillId = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposered, Long seckillId) {
        this.exposered = exposered;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposered, String md5, Long seckillId, Date now, Date start, Date end) {
        this.exposered = exposered;
        this.md5 = md5;
        this.seckillId = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public boolean isExposered() {
        return exposered;
    }

    public void setExposered(boolean exposered) {
        this.exposered = exposered;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposered=" + exposered +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
