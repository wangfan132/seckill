create database seckill;
use seckill;
create table seckill(
	seckill_id bigint not null auto_increment,
	name varchar(120) not null,
	number int not null,
	start_time timestamp not null,
	end_time timestamp not null,
	create_time timestamp not null default current_timestamp,
	primary key(seckill_id),
	key idx_start_time(start_time),
	key idx_end_time(end_time),
	key idx_create_time(create_time)
)engine=innoDB auto_increment=1000 default charset=utf8;

insert into
	seckill(name,number,start_time,end_time)
values
	('1000ÔªÃëÉ±inphone6',500,'2017-12-05 00:00:00','2015-01-02 00:00:00'),
	('800ÔªÃëÉ±inphone5',400,'2015-01-03 00:00:00','2015-01-04 00:00:00'),
	('500ÔªÃëÉ±inphone4',300,'2015-01-05 00:00:00','2015-01-06 00:00:00'),
	('300ÔªÃëÉ±inphone3',200,'2015-01-07 00:00:00','2015-01-08 00:00:00'),
	('200ÔªÃëÉ±inphone1',100,'2015-01-09 00:00:00','2015-01-10 00:00:00');
	
create table success_killed(
	seckill_id bigint not null,
	user_phone bigint not null,
	state tinyint not null default -1,
	create_time timestamp not null,
	primary key(seckill_id,user_phone),
	key idx_create_time(create_time)
)engine=innoDB default charset=utf8;

