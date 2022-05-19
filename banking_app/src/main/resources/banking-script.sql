create schema banking;

create table user_info (
	username varchar(25) primary key,
	password varchar(25) not null,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	dob varchar(20) not null
	);

create table account (
	id serial primary key,
	username varchar(25),
	account_balance int,
	account_type varchar(10),
	foreign key (username) references user_info(username)
);

drop table user_info;

drop table account;

drop table user_info;

drop table account;

drop table user_info;

drop table account;


insert into user_info values ('alex123', 'alexiscool', 'Alexander', 'Smith','01-01-1994');
insert into user_info values ('bobd', 'bobbyd', 'Bob', 'Dylan', '10-16-1963');
insert into user_info values ('gracieq', 'grace123','Grace', 'Quinn', '03-26-1996');
insert into user_info values ('klh1999', 'kateisgreat', 'Kate', 'Hill', '06-05-1990');
insert into user_info values ('trentfm21', 'trentyboy', 'Trent', 'Mayo', '09-12-1999');
insert into user_info values ('maddie89', 'ilovedogs', 'Madison', 'Cooper', '10-07-1989');
insert into user_info values ('lexihoward', 'theatre321', 'Alexandra', 'Howard', '12-13-2000');

insert into account values (default, 'alex123', 1200, 'Checking');
insert into account values (default, 'bobd', 9000, 'Savings');
insert into account values (default, 'gracieq', 5862, 'Checking');
insert into account values (default, 'klh1999', 9264, 'Savings');
insert into account values (default, 'trentfm21', 700, 'Checking');
insert into account values (default, 'maddie89', 3400, 'Savings');
insert into account values (default, 'lexihoward', 8500, 'Checking');