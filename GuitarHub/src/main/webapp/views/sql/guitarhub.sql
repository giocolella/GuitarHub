use mysql;
DROP USER IF EXISTS 'theuser'@'localhost';
CREATE USER 'theuser'@'localhost' IDENTIFIED BY 'theuser';
DROP DATABASE IF EXISTS guitarhub;
CREATE DATABASE guitarhub;
GRANT ALL ON guitarhub.* TO 'theuser'@'localhost';

USE guitarhub;

DROP TABLE IF EXISTS Utente;
CREATE TABLE Utente (
  id bigint NOT NULL AUTO_INCREMENT,
  username varchar(45) not null,
  passwd varchar(100) NOT NULL,
  nome varchar(45),
  cognome varchar(45),
  dataNascita date,
  sesso varchar(10),
  email varchar(150) not null,
  ruolo varchar(45) not null,
  cardNumber varchar(20),
  cardExpDate date,
  cvv varchar(5),
  billingAddress varchar(225),
  PRIMARY KEY (id),
  unique key email (email),
  unique key username (username)
);

DROP TABLE IF EXISTS Ordine;
CREATE TABLE Ordine (
	codice bigint AUTO_INCREMENT,
	idUser bigint NOT NULL,
	destinazione varchar(255) NOT NULL,
	qntProdotti int NOT NULL,
    totale double NOT NULL,
    numeroSpedizione varchar(250) not null,
    dataOrdine TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (codice,idUser),
	FOREIGN KEY (idUser) REFERENCES Utente (id) ON DELETE RESTRICT ON UPDATE RESTRICT 
);

DROP TABLE IF EXISTS dettagliOrdine;
CREATE TABLE dettagliOrdine (
  codice bigint AUTO_INCREMENT,
  idOrdine bigint NOT NULL,
  nomeProd varchar(255) NOT NULL,
  shortDescription varchar(500) not null,
  qntProd int not null,
  prezzoProd double NOT NULL,
  pesoProd double NOT NULL,
  iva int not null default 22,
  PRIMARY KEY (codice,idOrdine),
  FOREIGN KEY (idOrdine) REFERENCES Ordine (codice) ON DELETE RESTRICT ON UPDATE RESTRICT 
);

DROP TABLE IF EXISTS Prodotto;
CREATE TABLE Prodotto (
  codice bigint NOT NULL AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  descrizione text NOT NULL,
  immagine varchar(225) NOT NULL,
  prezzo double NOT NULL,
  marca varchar(50) not null,
  peso double NOT NULL,
  disponibile tinyint NOT NULL DEFAULT 0,
  sconto double NOT NULL DEFAULT 0,
  quantity int NOT NULL,
  iva int not null default 22,
  shortDescription varchar(500) not null,
  PRIMARY KEY (codice)
);

DROP TABLE IF EXISTS Recensione;
CREATE TABLE Recensione (
    id bigint not null auto_increment,
	idProd BIGINT NOT NULL,
	idUser BIGINT NOT NULL,
	titolo VARCHAR(255) NOT NULL,
	descrizione text NOT NULL,
    punteggio tinyint NOT NULL DEFAULT 5,
    dataRec timestamp default current_timestamp,
	PRIMARY KEY (id,idProd,idUser),
	FOREIGN KEY (idUser) REFERENCES Utente (id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (idProd) REFERENCES Prodotto (codice) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS Categoria;
CREATE TABLE Categoria (
    codice bigint auto_increment,
	nome VARCHAR(50) NOT NULL,
	descrizione VARCHAR(400) NOT NULL,
	PRIMARY KEY (codice) 
);

DROP TABLE IF EXISTS Ha;
CREATE TABLE Ha (
	idProd BIGINT NOT NULL,
	idCat bigint NOT NULL,
	PRIMARY KEY (idProd ,idCat) ,
	FOREIGN KEY (idProd) REFERENCES Prodotto (codice) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (idCat) REFERENCES Categoria (codice) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS Indirizzo;
CREATE TABLE Indirizzo (
	codice bigint not null AUTO_INCREMENT,
	idUser bigint NOT NULL,
	nomeInd varchar(45) NOT NULL,
	cognomeInd varchar(45) NOT NULL,
	indirizzo varchar(255) NOT NULL,
	codPostale varchar(5) NOT NULL,
    numeroCivico varchar(20) NOT NULL,
	city varchar(60) NOT NULL,
	provincia varchar(2) NOT NULL,
	telefono varchar(20) NULL,
	PRIMARY KEY (codice,idUser),
	UNIQUE INDEX cidU (idUser),
	FOREIGN KEY (idUser) REFERENCES Utente (id) ON DELETE CASCADE ON UPDATE CASCADE 
);

DROP TABLE IF EXISTS Immagine;
CREATE TABLE Immagine (
    codice bigint not null auto_increment,
    idProd bigint not null,
    larghezza double not null,
    altezza double not null,
    url varchar(300) not null,
    nome varchar(45) not null,
    descrizione varchar(300) not null,
    PRIMARY KEY(codice,idProd),
    FOREIGN KEY (idProd) REFERENCES Prodotto(codice) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Fornitore;
CREATE TABLE Fornitore (
    piva varchar(11) not null,
    indirizzo varchar(255) not null,
    codPostale varchar(5) NOT NULL,
	city varchar(60) NOT NULL,
	provincia varchar(2) NOT NULL,
	telefono varchar(20) NULL,
    nome varchar(45) not null,
    email varchar(45) not null,
    PRIMARY KEY(piva)
);

DROP TABLE IF EXISTS Fornisce;
CREATE TABLE Fornisce(
idProd bigint not null,
idForn varchar(11) not null,
PRIMARY KEY(idProd,idForn),
FOREIGN KEY (idProd) REFERENCES Prodotto(codice) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (idForn) REFERENCES Fornitore(piva) ON DELETE CASCADE ON UPDATE CASCADE
);


