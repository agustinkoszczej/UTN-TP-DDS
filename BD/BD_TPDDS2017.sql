/*create database TPDDS2017;*/

/*use TPDDS2017;*/

/*ENTREGA 1*/

/*Empresa*/
CREATE TABLE Empresa (
empresa_id INT NOT NULL AUTO_INCREMENT,
empresa_nombre VARCHAR (50),
empresa_anioCreacion INT,
PRIMARY KEY(empresa_id));

INSERT INTO Empresa (empresa_nombre, empresa_anioCreacion) VALUES ('Facebook', 2004); /*id=1*/
INSERT INTO Empresa (empresa_nombre, empresa_anioCreacion) VALUES ('Fibertel', 2000); /*id=2*/
INSERT INTO Empresa (empresa_nombre, empresa_anioCreacion) VALUES ('Arena', 2016); /*id=3*/
INSERT INTO Empresa (empresa_nombre, empresa_anioCreacion) VALUES ('Balances Negativos', 2017); /*id=4*/
INSERT INTO Empresa (empresa_nombre) VALUES ('Vacia'); /*id=5*/

/*Balance*/
CREATE TABLE Balance (
balance_id INT NOT NULL AUTO_INCREMENT,
balance_periodo VARCHAR (50),
balance_tipoCuenta INT, /* ENUM('EBITDA','FDS','FreeCashFlow','IngresoNetoOperacionesContinuas','IngresoNetoOperatcionesDiscontinuas','IngresoNeto','Dividendos','CapitalTotal','Deuda','CostoTotal'), */
balance_frecuencia INT, /* ENUM('Mensual','Bimestral','Trimestral','Semestral','Anual'), */
balance_valor DOUBLE,
balance_empresa INT,
PRIMARY KEY(balance_id),
FOREIGN KEY (balance_empresa) references Empresa(empresa_id));

/*Facebook*/
INSERT INTO Balance (balance_periodo, balance_frecuencia, balance_tipoCuenta, balance_valor, balance_empresa)
			VALUES ('201706', 4, 1, 140000000, 1); /*id=1*/
INSERT INTO Balance (balance_periodo, balance_frecuencia, balance_tipoCuenta, balance_valor, balance_empresa)
			VALUES ('201612', 4, 1, 135000000, 1); /*id=2*/
/*Fibertel*/
INSERT INTO Balance (balance_periodo, balance_frecuencia, balance_tipoCuenta, balance_valor, balance_empresa)
			VALUES ('201612', 5, 2, 134000000, 2); /*id=3*/

/*Arena*/
INSERT INTO Balance (balance_periodo, balance_frecuencia, balance_tipoCuenta, balance_valor, balance_empresa)
			VALUES ('201705', 4, 4, 100000, 3); /*id=4*/

/*Balances Negativos*/
INSERT INTO Balance (balance_periodo, balance_frecuencia, balance_tipoCuenta, balance_valor, balance_empresa)
			VALUES ('201705', 4, 4, 5, 4); /*id=5*/
INSERT INTO Balance (balance_periodo, balance_frecuencia, balance_tipoCuenta, balance_valor, balance_empresa)
			VALUES ('201705', 4, 4, -20, 4); /*id=6*/

/*ENTREGA 2*/

/*Indicador*/
CREATE TABLE Indicador (
indicador_id INT NOT NULL AUTO_INCREMENT,
indicador_nombre VARCHAR (50) NOT NULL,
indicador_expresion VARCHAR(100),
PRIMARY KEY(indicador_id));


INSERT INTO Indicador (indicador_nombre, indicador_expresion)
			VALUES('pep', '5'); /*id=1*/
INSERT INTO Indicador (indicador_nombre, indicador_expresion)
			VALUES('juli', 'pep*2'); /*id=2*/
INSERT INTO Indicador (indicador_nombre, indicador_expresion)
			VALUES('IngresoNetoOperacionesContinuas','IngresoNetoOperacionesContinuas'); /*id=3*/
INSERT INTO Indicador (indicador_nombre, indicador_expresion)
			VALUES('ROE','IngresoNetoOperacionesContinuas'); /*id=4*/
INSERT INTO Indicador (indicador_nombre, indicador_expresion)
			VALUES('EBITDAAA','EBITDA'); /*id=5*/


/*ENTREGA 3*/
