create schema if not exists sam collate utf8_general_ci;

create table if not exists point_account
(
	id bigint auto_increment
		primary key,
	user_id bigint not null,
	balance int null,
	status varchar(10) null
);

create table if not exists user
(
	id bigint auto_increment
		primary key,
	mobile_phone varchar(20) not null,
	user_name varchar(20) null
);

create table if not exists user_event
(
	id bigint auto_increment
		primary key,
	user_id bigint null,
	type varchar(10) null,
	date date null,
	time time null
);

create table if not exists user_role
(
	id bigint auto_increment
		primary key,
	user_id bigint null,
	role_id bigint null,
	role_name varchar(50) null
);

