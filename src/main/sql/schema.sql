-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE miaosha;

-- 使用数据库
use miaosha;

-- 创建库存表
CREATE TABLE miaosha(
`miaosha_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存的ID',
`name` varchar(120) NOT NULL COMMENT '商品名称',
`number` int NOT NULL COMMENT '商品库存数量',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '商品记录创建时间',
`start_time` timestamp NOT NULL COMMENT '秒杀开始时间',
`end_time` timestamp NOT NULL COMMENT '秒杀结束时间',
PRIMARY KEY (miaosha_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- 初始化库存表数据
insert into miaosha(name,number,start_time,end_time)
values
  ('200元秒杀华为3c',100,'2016-01-01 00:00:00','2017-01-01 00:00:00'),
  ('300元秒杀华为荣耀6',200,'2016-01-01 00:00:00','2017-01-01 00:00:00'),
  ('400元秒杀华为荣耀6plus',300,'2016-01-01 00:00:00','2017-01-01 00:00:00'),
  ('500元秒杀华为mate8',400,'2016-01-01 00:00:00', '2017-01-01 00:00:00'),
  ('1000元秒杀iPhone6s',500,'2016-01-01 00:00:00','2017-01-01 00:00:00');

-- 秒杀成功明细表
CREATE TABLE success_info(
`miaosha_id` bigint NOT NULL COMMENT '秒杀成功的商品的id',
`user_phone` bigint NOT NULL COMMENT '用户电话号码',
`state` tinyint NOT NULL DEFAULT -1 comment '秒杀的商品状态: -1 表示无效，0 表示成功但未付款，1 表示已付款',
`create_time` timestamp NOT  NULL DEFAULT current_timestamp comment '该秒杀成功记录的创建时间',
PRIMARY KEY (miaosha_id,user_phone),/*使用联合主键: 利用商品id和用户电话号码联合确定唯一性，保证同一用户对同一商品不能重复秒杀*/
KEY idx_create_time(create_time)
)engine=InnoDB DEFAULT charset=utf8 comment='秒杀成功明细表';

-- 链接mysql数据库的控制台
mysql -u root -p