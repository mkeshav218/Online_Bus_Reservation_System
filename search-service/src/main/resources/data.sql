create table bus_type (bus_name varchar(20) not null, bus_status varchar(20), bus_type varchar(20), primary key (bus_name));
create table bus_details (route_no integer not null, new_bus_name varchar(20), primary key (route_no));
create table bus_details_new_route (bus_details_route_no integer not null, new_route_path_no integer not null, primary key (bus_details_route_no, new_route_path_no));
create table bus_route(path_no integer not null, bus_no integer, destination varchar(20), distance integer, fare integer, reach_time varchar(20), source varchar(20), start_time varchar(20), route_no integer, primary key (path_no));
create table bus_type_new_bus_details (bus_type_bus_name varchar(20) not null, new_bus_details_route_no integer not null, primary key (bus_type_bus_name, new_bus_details_route_no));

insert into bus_type(bus_name,bus_type,bus_status) values('RajRath','Driverless','Active');
insert into bus_type(bus_name,bus_type,bus_status) values('DeepMala','Driver','Active');
insert into bus_type(bus_name,bus_type,bus_status) values('Balaji','Driver','Active');

insert into bus_details(route_no,new_bus_name) values(1,'DeepMala');
insert into bus_details(route_no,new_bus_name) values(2,'DeepMala');
insert into bus_details(route_no,new_bus_name) values(3,'RajRath');
insert into bus_details(route_no,new_bus_name) values(4,'RajRath');

insert into bus_route(path_no, bus_no,source,destination,route_no,distance,start_time,reach_time,fare) values(1,101,'Pune','Navi Mumbai', 1, 130,'05:00','08:00',260);
insert into bus_route(path_no, bus_no,source,destination,route_no,distance,start_time,reach_time,fare) values(2,101,'Pune','Dombivli', 1, 160,'05:00','09:15',320);
insert into bus_route(path_no, bus_no,source,destination,route_no,distance,start_time,reach_time,fare) values(3,101,'Pune','Nashik', 1, 300,'05:00','12:30',600);
insert into bus_route(path_no, bus_no,source,destination,route_no,distance,start_time,reach_time,fare) values(4,101,'Navi Mumbai','Dombivli', 1, 30,'08:15','09:15',60);
insert into bus_route(path_no, bus_no,source,destination,route_no,distance,start_time,reach_time,fare) values(5,101,'Navi Mumbai','Nashik', 1, 170,'08:15','12:30',340);
insert into bus_route(path_no, bus_no,source,destination,route_no,distance,start_time,reach_time,fare) values(6,101,'Dombivli','Nashik', 1, 140,'09:30','12:30',280);
insert into bus_route(path_no, bus_no,source,destination,route_no,distance,start_time,reach_time,fare) values(7,101,'Nashik','Dombivli', 2, 140,'13:00','16:00',280);
insert into bus_route(path_no, bus_no,source,destination,route_no,distance,start_time,reach_time,fare) values(8,101,'Nashik','Navi Mumbai', 2, 170,'13:00','17:15',340);
insert into bus_route(path_no, bus_no,source,destination,route_no,distance,start_time,reach_time,fare) values(9,101,'Nashik','Pune', 2, 300,'13:00','20:30',600);
insert into bus_route(path_no, bus_no,source,destination,route_no,distance,start_time,reach_time,fare) values(10,101,'Dombivli','Navi Mumbai', 2, 30,'16:15','17:15',60);
insert into bus_route(path_no, bus_no,source,destination,route_no,distance,start_time,reach_time,fare) values(11,101,'Dombivli','Pune', 2, 160,'16:15','20:30',320);
insert into bus_route(path_no, bus_no,source,destination,route_no,distance,start_time,reach_time,fare) values(12,101,'Navi Mumbai','Pune', 2, 130,'15:30','20:30',260);
insert into bus_route(path_no, bus_no,source,destination,route_no,distance,start_time,reach_time,fare) values(13,102,'Navi Mumbai','Dombivli', 3,30,'09:00','09:30',120);
insert into bus_route(path_no, bus_no,source,destination,route_no,distance,start_time,reach_time,fare) values(14,102,'Dombivli','Navi Mumbai', 4,30,'10:00','10:30',120);