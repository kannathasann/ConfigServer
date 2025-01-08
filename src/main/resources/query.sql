create database configserver;

create table app (
id int primary key auto_increment,
name varchar(255) unique not null,
description varchar(255)
);

create table feature(
id int primary key auto_increment,
name varchar(255),
description varchar(255),
feature_key varchar(255) unique not null,
app_id int not null,
foreign key(app_id) references app(id) on delete cascade
);

create table config(
id int primary key auto_increment,
name varchar(255),
description varchar(255),
config_key varchar(255) unique not null,
config_values varchar(255),
config_query varchar(255),
feature_id int not null,
foreign key(feature_id) references feature(id) on delete cascade
);
