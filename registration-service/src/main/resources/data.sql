create table registration (email varchar(30) not null, first_name varchar(20), last_name varchar(20), password varchar(20), phone varchar(20),date_of_birth DATE, gender varchar(20), no_of_booked_ticket integer , wallet integer, role varchar(20), primary key (email));

insert into registration(email,first_name,last_name,password,phone,date_of_birth,gender,no_of_booked_ticket,wallet,role) values('admin@gmail.com','admin','admin','admin','9876543210','1947-08-15','MALE',0,1000,'ADMIN');
