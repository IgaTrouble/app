create database Ankieta;
#drop database ankieta;
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
    pytanie varchar(250),
    odp1 varchar(250),
    odp2 varchar(250),
    odp3 varchar(250),
    odp4 varchar(250),
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




#pytania sql
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values 
('SQL','Jakim poleceniem utworzysz nową bazę danych?','create table','use database','altertable','create database', 4),
('SQL','Do czego służy klauzula WHERE?','Do definiowania warunków zapytania',
'Do określania gdzie mają zostać wyświetlone wyniki','Do określenia adresu (URL) bazy danych','Do ograniczenia liczby wyników', 1),
('SQL','Jak uszeregować wyniki w kolumnie wg kolejności alfabetycznej?','asc','alfa','desc','mesc', 3),
('SQL', 'Użycie klauzuli UNQUE w deklaracji pola tabeli instrukcji CREATE TABLE oznacza, że:', 'w tym polu nie może pojawić się wartość NULL',
'w żadnym innym polu tej tabeli nie można użyć klauzuli UNIQUE',
'wartości w tym polu nie mogą się powtarzać',
'na tej kolumnie (polu) zostanie automatycznie zalozony indeks', 3),
('SQL', 'Instrukcja SELECT Table_Name FROM User_Tables', 'Zwraca nazwy tabel znajdujących się w obszarze tabel użytkownika', 
'Wypisuje nazwy kolumn z tabeli User_Tables',
'Wypisuje wszystkie rekordy z perspektywy User_Tables',
'Zwraca nazwy tabel utworzonych przez użytkownika', 1),
('SQL', 'Instrukcja ROLLBACK służy do:', 'wycofywania zmian w bazie danych', 'zatwierdzania zmian w bazie danych', 
'usuwania rekordów z bazy danych',
'aktualizacji rekordów w bazie danych', 1),
('SQL', 'Dana jest tabela Osoby(Imie, Nazwisko, Zarobki). Które z instrukcji są składniowo poprawnymi?', 
'SELECT Osoby.Nazwisko, Osoby.Imie, Osoby.Zarobki FROM Osoby HAVING Osoby.Zarobki>1000;',
'INSERT INTO Osoby SELECT * FROM Osoby WHERE Nazwisko = ‘KOWALSKI’;',
'DELETE FROM Osoby WHENEVER Osoby.Zarobki<1000;',
'UPDATE Osoby WHERE Nazwisko=’KOWALSKI’;', 3),
('SQL', 'Dane są 2 tabele Osoby(Imie, Nazwisko, Zarobki, Id_działu) oraz Działy(Id_działu, Nazwa). Poprawne to:',
'SELECT Osoby.Nazwisko, COUNT(Działy.Nazwa) FROM Osoby, Działy WHERE Osoby.Id_działu=Działy.Id_działu GROUP BY Osoby.Nazwisko HAVING COUNT(Id_działu)<2;',
'INSERT INTO Osoby VALUES (‘Jan’, ‘Kowalski’, 2000) WHERE Osoby.Id_działu = 23;',
'DELETE FROM Osoby, Działy WHERE Osoby.Nazwisko=’KOWALSKI’ AND Działy.Nazwa=’KASA’ AND Osoby.Id_działu=Działy.Id_działu;',
'UPDATE Osoby SET Id_działu=NULL WHERE 5>(SELECT COUNT(*) FROM Działy);', 4),
('SQL', 'Wskaż poprawne zapytanie SQL znajdujące stanowiska pracy występujące w działach 10 lub 20;',
'SELECT DISTINCT job FROM emp WHERE deptno = 10 OR deptno = 20;',
'SELECT DISTINCT job FROM emp WHERE deptno = 10 UNION SELECT DISTINCT job FROM emp WHERE deptno = 20;',
'SELECT DISTINCT job FROM emp WHERE deptno = 10 AND deptno = 20;',
'SELECT DISTINCT job FROM emp WHERE deptno = 10 INTERSECT SELECT DISTINCT job FROM emp WHERE deptno = 20;', 1),
('SQL', 'W filmach grają aktorzy. Każdy film ma dokładnie jednego reżysera i jednego lub więcej scenarzystę. Który z poniższych schematów jest najodpowiedniejszy z punktu widzenia zasad projektowania baz danych:',
'Filmy(id_filmu, tytuł, reżyser, gaża_reżysera)
Aktorzy(id_aktora, nazwisko,rola, id_filmu, gaża)
Scenarzyści(id_scenarzysty, nazwisko, id_filmu, gaża);',
'Filmy(id_filmu, tytuł, id_reżysera, gaża_reżysera)
Osoby(id_osoby, nazwisko)
Aktorzy(id_aktora, id_filmu, rola, gaża)
Scenarzyści(id_scenarzysty, id_filmu, gaża);',
'Filmy(id_filmu, tytuł, id_reżysera)
Osoby(id_osoby, nazwisko)
Zespół(id_osoby, id_filmu, rola, gaża);',
'Zespół(tytuł_filmu, nazwisko_osoby, rola, gaża);', 2);

#pytania git
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values 
('Git','Jakim poleceniem utworzysz nowe repozytorium Git?','git start','git create','git init','git use', 3),
('Git','Jak nazwać plik, który umożliwia ignorowanie danych przez Git?','git ignore','.gitignore','.ignoregit.txt','ignore.txt', 2),
('Git','Jakim dodać wszystkie pliki z repozytorium do śledzenia?','git add all ','git add *','git commit','git add .', 3),
('Git','Co oznacza git push?','pobiera dane z serwera i próbe scalić zmiany z lokalnym repozytorium','pobiera dane z komputera do lokalnego repozytorium',
'przesyła dane z lokalnego repozytorium na serwer','klonuje repozytirum z innego serwera', 3),
('Git','Jakim poleceniem sklonujesz repozytorium z innego serwera?','git rebase','git init','git init new repo url','git clone url', 4),
('Git','Jakim sprawdzic, które pliki są śledzone?','git status','git merge','git start','git use', 1),
('Git','Co oznacza git checkout -b?','przechodzi na inną gałąz', 'tworzy i przechodzi na inna galaz','tworzy nową gałąz','sprawdza jakie są galezie', 2),
('Git','Jakim poleceniem zakomitujesez zmiany?','git add','git commit','git coment','git amend', 2),
('Git','Co oznacza git stash list','pokazuje listę plików','pokazuje nazwy galezi','pokazuje wszystkie zmiany','pokazuje co jest na półce', 4),
('Git','Co oznacza git pull?','pobiera dane z serwera i próbe scalić zmiany z lokalnym repozytorium','pobiera dane z komputera do lokalnego repozytorium',
'przesyła dane z lokalnego repozytorium na serwer','klonuje repozytorium z innego serwera', 1);

#pytania python
insert into pytania(zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) values 
('Python','Który objekt jest niemodyfikowalny?','set','list','tuple','dictionary', 3),
('Python','Jaki będzie wynik tego kodu? print(int("0010"+"1011"?','1021','1011','00101011','101011', 4),
('Python','Jaki będzie wynik tego kodu? *x,y =[1,2], [3,4], [5,6]; print)list(zip(*x+[y]))[1][1]','4','2','12','7', 1),
('Python','Jaki będzie wynik tego kodu? print(abs(max(-3,-4))+1)','3','4','-4','1', 2),
('Python','Co należy zrobić aby odwrócić zmienną a="czy znam odpowiedz"?','reverse.a','a.reverse','left(a)[-1]','a[::-1]', 4),
('Python','Jaki będzie wynik tego kodu? a ="spam"; b="eggs"; a,b = b,a; print (a+b)','error','ab','eggsspam','spameggs', 3),
('Python','Jaki będzie wynik tego kodu? print(5**5%+9*9);','81','9','0','82', 1),
('Python','Jaką długość będzie miała lista list(range(10)?','9','10','11','0', 2),
('Python','Jaki będzie wynik tego kodu print zm = abcdefg?; print(zm[2:]','bdf','aceg','bcdefg','cdefg', 4),
('Python','Pyton należy do gromady:?','płazów','gadów','ssaków','węży', 2);


select * from pytania where zakres = "Python";
select * from loginy;