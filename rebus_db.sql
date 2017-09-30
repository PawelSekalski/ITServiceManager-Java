create database rebusdb;
use rebusdb;

#drop database rebusdb;

#drop table rebusdb;

#drop table users;

CREATE TABLE users (
    id_u INT AUTO_INCREMENT PRIMARY KEY,
    log VARCHAR(20) UNIQUE,
    pass VARCHAR(20),
    firstn  VARCHAR(20),
    lastn VARCHAR(20),
    perm VARCHAR(3) default '001'
);

insert into users (log, pass, firstn, lastn, perm) values ('a','a','Mariusz','Ziarek','101');
insert into users (log, pass, firstn, lastn) values ('g', 'g', 'x', 'y');

select * from users;

CREATE TABLE centre (
    id_c INT AUTO_INCREMENT PRIMARY KEY,
    townName VARCHAR(20),
    centreName VARCHAR(30) UNIQUE
);

#drop table centre;

insert into centre (townName, centreName) values ('Gdańsk', 'Alchemia'), ('Katowice', 'Silesia Business Park'), ('Kraków' ,'Fronton'), ('Kraków', 'Graffit House'),
	('Kraków', 'K1'), ('Lublin' ,'Zana'), ('Poznań', 'Andresia'), ('Warszawa', 'Equator'), ('Warszawa', 'Metro Świętokrzyska'), ('Warszawa', 'Metropolitan'), ('Warszawa', 'Mokotów'), 
	('Warszawa', 'Mokotów Marynarska'), ('Warszawa', 'North Gate'), ('Warszawa', 'Nowy Świat'), ('Warszawa', 'Sheraton Plaza'), ('Warszawa', 'Skylight'),
    ('Warszawa', 'Warsaw Financial Center'), ('Warszawa', 'Wiśniowy Business Park'), ('Wrocław', 'Pegaz'), ('Wrocław', 'Rynek Stare Miasto');

select id_c, townName, centreName from centre;
select * from centre where id_c=51;

CREATE TABLE info (
    id_i INT AUTO_INCREMENT PRIMARY KEY,
    id_c int,
    IPadress VARCHAR(20) DEFAULT 0,
    VPLSid VARCHAR(25) DEFAULT 0,
    Bandwidth VARCHAR(10) DEFAULT 0,
    PRAid VARCHAR(40) DEFAULT 0,
    PRAline INT DEFAULT 0,
    DDI VARCHAR(60) DEFAULT 0,
    BandwidthPrice DOUBLE DEFAULT 0,
    PRAPrice DOUBLE DEFAULT 0,
    FOREIGN KEY (id_c)
        REFERENCES centre (id_c)
);
#drop table info;

#insert into info (IPadress, VPLSid, bandwidth, PRAid, PRAline, DDI, BandwidthPrice, PRAPrice) values ('91.189.58.152/29', 'NS-17/02/124674-WRO', '40Mbps', 'WO 17/04/066512, WO 17/04/066553', 2, '717478600 – 717478799, 717478800 – 717478999 ', 2600, 1400);
insert into info (id_c) values (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12),(13),(14),(15),(16),(17),(18);
insert into info (id_c, IPadress, VPLSid, bandwidth, PRAid, PRAline, DDI, BandwidthPrice, PRAPrice) values (19, '91.189.58.152/29', 'NS-17/02/124674-WRO', '40Mbps', 'WO 17/04/066512, WO 17/04/066553', 2, '717478600 – 717478799, 717478800 – 717478999 ', 2600, 1400);
insert into info (id_c) values (20);
#insert into data (id_u,

#select * from info;

#select townName, centreName, IPadress, VPLSid, bandwidth, PRAid, PRAline, DDI, BandwidthPrice, PRAPrice from centre natural join info where id_c=19;
#select * from info natural join centre where id_c=19;
#select * from info;
