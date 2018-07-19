create database HMS;

use HMS;

create table contry(
id int not null auto_increment primary key,
name varchar(100) not null unique,
status int not null Default 1);

create table state(
id int not null auto_increment primary key,
name varchar(50) not null unique,
status int not null Default 1,
contry int REFERENCES contry(id) ON DELETE CASCADE);

create table client(
id int not null auto_increment primary key,
hname varchar(50) not null,
address varchar(50) not null unique,
contact int not null);

create table user(
id int not null auto_increment primary key,
username varchar(20) not null,
password varchar(20) not null,
fname varchar(20) not null,
mname varchar(20) not null,
lname varchar(20) not null,
email varchar(50) not null,
gender varchar(10) not null,
contact int,
status int not null Default 1,
clientid int,
FOREIGN KEY (clientid) REFERENCES client(id));

create table patient(
pid int not null auto_increment primary key,
fname varchar(30) not null,
mname varchar(30) not null,
lname varchar(30) not null,
gender varchar(10) not null,
dob date not null,
bloodgroup varchar(50) not null,
phone int not null,
address varchar(100) not null,
cityid int not null,
stateid int not null,
countryid int not null,
userid int not null,
email varchar(30) not null,
dor date not null,
FOREIGN KEY (cityid) REFERENCES city(id),
FOREIGN KEY (stateid) REFERENCES state(id),
FOREIGN KEY (countryid) REFERENCES contry(id),
FOREIGN KEY (userid) REFERENCES user(id));

create table visit(
vid int not null auto_increment primary key,
vstatus int not null,
dov timestamp not null,
uid int REFERENCES user(id) ON DELETE CASCADE,
pid int REFERENCES patient(pid) ON DELETE CASCADE);

create table service(
id int not null auto_increment primary key,
name varchar(50) not null unique,
amount double not null,
status int not null Default 1);

create table bill(
id int not null auto_increment primary key,
visit int REFERENCES visit(vid) ON DELETE CASCADE,
user int REFERENCES user(id) ON DELETE CASCADE,
amount double default 0.0);

create table service_mapping(
id int not null auto_increment primary key,
service int REFERENCES services(sid) ON DELETE CASCADE,
bill int REFERENCES bill(id) ON DELETE CASCADE);

insert into patient(fname,mname,lname,gender,dob,bloodgroup,phone,address,cityid,stateid,countryid,userid,email,dor)values("fname2","mname2","lname2","male","1996/06/07","B+",8867,"add1",1,1,1,1,"2@gmail.com",1996/05/22);

insert into patient(fname,mname,lname,gender,dob,bloodgroup,phone,address,cityid,stateid,countryid,userid,email,dor) 
values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,)

insert into service(name,amount) values ("Metting",200);

create table ewaste(
id int not null auto_increment primary key,
name varchar(50) not null,
email varchar(50),
contact varchar(50) not null,
address varchar(50) not null unique,
subject varchar(50),
request varchar(50),
status varchar(50) Default 1
);

insert into ewaste values(1,"abc","1@gmail.com","9898","address","subject","qwerty","1");

	