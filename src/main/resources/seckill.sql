CREATE DATABASE seckill;

USE seckill;

CREATE TABLE seckill (
  `seckill_id`  BIGINT          NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(120) NOT NULL,
  `number`      INT          NOT NULL,
  `start_time`  TIMESTAMP    NOT NULL,
  `end_time`    TIMESTAMP    NOT NULL,
  `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (seckill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8

INSERT INTO seckill(name, number, start_time, end_time)
VALUES
  ('1000元秒杀iphoneX',100,'2018-01-01 00:00:00','2018-01-02 00:00:00'),
  ('800元秒杀ipad',200,'2018-01-01 00:00:00','2018-01-02 00:00:00'),
  ('6600元秒杀mac book pro',300,'2018-01-01 00:00:00','2018-01-02 00:00:00'),
  ('7000元秒杀iMac',400,'2018-01-01 00:00:00','2018-01-02 00:00:00');

CREATE TABLE success_killed (
  `seckill_id` BIGINT NOT NULL,
  `user_phone` BIGINT NOT NULL,
  `state` TINYINT NOT NULL DEFAULT -1 COMMENT '状态：-1：无效 0：成功 1：已付款 2：已发货',
  `create_time` TIMESTAMP NOT NULL,
  PRIMARY KEY(seckill_id, user_phone), /*联合主键*/
  KEY idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8
