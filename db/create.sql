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

create table odpowiedzi(
	ido integer primary key auto_increment,
    test integer,
	pytanie integer,
    odpowiedz integer,
    foreign key (pytanie) references pytania(idp),
    foreign key (test) references testy(idt)
);

drop table pytania;
drop table odpowiedzi;
drop table testy;
drop table loginy;

insert into loginy(email,imie,nazwisko,grupa,pass,typ) values ('admin','Admin','Admin','ADMIN','admin','EGZAMINATOR');