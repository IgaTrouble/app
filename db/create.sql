create database Ankieta;

use Ankieta;

create table loginy(
    email varchar(100) primary key,
    imie varchar(50),
    nazwisko varchar(50),
    grupa varchar(50),
    pass varchar(20),
    typ varchar(20)
);

create table zakresy(
	zakres varchar(10) primary key,
    opis_zakresu varchar(50)
);

create table pytania(
	idp integer primary key auto_increment,
    zakres varchar(10),
    pytanie varchar(100),
    odp1 varchar(100),
    odp2 varchar(100),
    odp3 varchar(100),
    odp4 varchar(100),
    odppopr integer,
    foreign key (zakres) references zakresy(zakres)
);

create table testy(
	idt integer primary key auto_increment,
    kursant varchar(100),
    data_testu date,
    foreign key (kursant) references loginy(email)
);
alter table testy add wynik integer;

create table odpowiedzi(
	ido integer primary key auto_increment,
    test integer,
	pytanie integer,
    odpowiedz integer,
    foreign key (pytanie) references pytania(idp),
    foreign key (test) references testy(idt)
);

#drop table pytania;
#drop table odpowiedzi;
#drop table testy;
#drop table loginy;

insert into zakresy(zakres, opis_zakresu) values ('SQL', 'pytania z bazy danych');
insert into zakresy(zakres, opis_zakresu) values ('GiT', 'pytania z bazy danych');
insert into zakresy(zakres, opis_zakresu) values ('Python', 'pytania z bazy danych');
insert into zakresy(zakres, opis_zakresu) values ('Java', 'pytania z bazy danych');
insert into zakresy(zakres,opis_zakresu) values('FE','Front-End');
insert into zakresy(zakres,opis_zakresu) values('SPRING','Spring');
update zakresy set opis_zakresu='Pytania z GITa' where zakres='GiT';
update zakresy set opis_zakresu='Pytania z JAVy' where zakres='Java';
update zakresy set opis_zakresu='Pytania z Pythona' where zakres='Python';
update zakresy set opis_zakresu='Pytania ze SPRINGa' where zakres='SPRING';

insert into loginy(email,imie,nazwisko,grupa,pass,typ) values ('admin','Admin','Admin','ADMIN','admin','EGZAMINATOR');
insert into loginy(email,imie,nazwisko,grupa,pass,typ) values ('kursant','Kursant','Kursant','TESTOWA','kursant','KURSANT');
insert into loginy(email,imie,nazwisko,grupa,pass,typ) values ('aAdaś','Miauczński','Miał','ad2','elo','KURSANT');
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('SQL','Jakim poleceniem utworzysz nową bazę danych?','create table','use database','altertable','create database', 4);
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('SQL','Do czego służy klauzula WHERE?','Do definiowania warunków zapytania','Do określania gdzie mają zostać wyświetlone wyniki','Do określenia adresu (URL) bazy danych','Do ograniczenia liczby wyników', 1);
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('SQL','Jak uszeregować wyniki w kolumnie wg kolejności alfabetycznej?','asc','alfa','desc','mesc', 3);
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('SQL','limit','3Create table','use database','altertable','create database', 4);
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('SQL','Jak zliczyć rekordy w tabeli "Tab" ?','count tab','select * from tab;','select rekord from tab;','select count(*) from tab;', 4);
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('SQL','Pytanie5?','create table','use database','altertable','create database', 4);
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('SQL','Pytanie6?','create table','use database','altertable','create database', 4);
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('SQL','Pytanie7?','create table','use database','altertable','create database', 4);
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('SQL','Pytanie8?','create table','use database','altertable','create database', 4);
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('SQL','Pytanie9?','create table','use database','altertable','create database', 4);
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('SQL','Pytanie10?','create table','use database','altertable','create database', 4);
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('SQL','Pytanie11?','create table','use database','altertable','create database', 4);
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('SQL','Pytanie12?','create table','use database','altertable','create database', 4);
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('SQL','Pytanie13?','create table','use database','altertable','create database', 4);
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('SQL','Pytanie14?','create table','use database','altertable','create database', 4);

insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('Java','Pytanie20?','create table','use database','altertable','create database', 4);
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('Java','Pytanie21?','create table','use database','altertable','create database', 4);
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('GiT','Pytanie22?','create table','use database','altertable','create database', 4);
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('GiT','Pytanie23?','create table','use database','altertable','create database', 4);
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('Java','Pytanie14?','create table','use database','altertable','create database', 4);

insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('FE','Pytanie80?','create table','use database','altertable','create database', 4);
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values ('SPRING','Pytanie81?','create table','use database','altertable','create database', 4);