create database TPDDS2017;

use TPDDS2017;

/*ENTREGA 1*/

/*TipoCuenta*/
CREATE TABLE TipoCuenta (
tipoCuenta_id INT NOT NULL AUTO_INCREMENT,
tipoCuenta_descripcion VARCHAR (50),
PRIMARY KEY(tipoCuenta_id));

INSERT INTO TipoCuenta (tipoCuenta_descripcion) VALUES ("EBITDA"); /*id=1*/
INSERT INTO TipoCuenta (tipoCuenta_descripcion) VALUES ("FDS"); /*id=2*/
INSERT INTO TipoCuenta (tipoCuenta_descripcion) VALUES ("FreeCashFlow"); /*id=3*/
INSERT INTO TipoCuenta (tipoCuenta_descripcion) VALUES ("IngresoNetoOperacionesContinuas"); /*id=4*/
INSERT INTO TipoCuenta (tipoCuenta_descripcion) VALUES ("IngresoNetoOperatcionesDiscontinuas"); /*id=5*/
INSERT INTO TipoCuenta (tipoCuenta_descripcion) VALUES ("IngresoNeto"); /*id=6*/
INSERT INTO TipoCuenta (tipoCuenta_descripcion) VALUES ("Dividendos"); /*id=7*/
INSERT INTO TipoCuenta (tipoCuenta_descripcion) VALUES ("CapitalTotal"); /*id=8*/
INSERT INTO TipoCuenta (tipoCuenta_descripcion) VALUES ("Deuda"); /*id=9*/
INSERT INTO TipoCuenta (tipoCuenta_descripcion) VALUES ("CostoTotal"); /*id=10*/

/*Frecuencia*/
CREATE TABLE Frecuencia (
frecuencia_id INT NOT NULL AUTO_INCREMENT,
frecuencia_descripcion VARCHAR (50),
PRIMARY KEY(frecuencia_id));

INSERT INTO Frecuencia (frecuencia_descripcion) VALUES ("Mensual"); /*id=1*/
INSERT INTO Frecuencia (frecuencia_descripcion) VALUES ("Bimestral"); /*id=2*/
INSERT INTO Frecuencia (frecuencia_descripcion) VALUES ("Trimestral"); /*id=3*/
INSERT INTO Frecuencia (frecuencia_descripcion) VALUES ("Semestral"); /*id=4*/
INSERT INTO Frecuencia (frecuencia_descripcion) VALUES ("Anual"); /*id=5*/

/*Empresa*/
CREATE TABLE Empresa (
empresa_id INT NOT NULL AUTO_INCREMENT,
empresa_nombre VARCHAR (50),
empresa_anioCreacion INT,
PRIMARY KEY(empresa_id));


INSERT INTO Empresa (empresa_nombre, empresa_anioCreacion) VALUES ("Facebook", 2004); /*id=1*/
INSERT INTO Empresa (empresa_nombre, empresa_anioCreacion) VALUES ("Fibertel", 2000); /*id=2*/
INSERT INTO Empresa (empresa_nombre, empresa_anioCreacion) VALUES ("Arena", 2016); /*id=3*/
INSERT INTO Empresa (empresa_nombre, empresa_anioCreacion) VALUES ("Balances Negativos", 2017); /*id=4*/
INSERT INTO Empresa (empresa_nombre) VALUES ("Vacia"); /*id=5*/

/*Balance*/
CREATE TABLE Balance (
balance_id INT NOT NULL AUTO_INCREMENT,
balance_periodo VARCHAR (50),
balance_frecuencia INT,
balance_tipoCuenta INT,
balance_valor DOUBLE,
PRIMARY KEY(balance_id),
FOREIGN KEY (balance_frecuencia) references Frecuencia(frecuencia_id),
FOREIGN KEY (balance_tipoCuenta) references TipoCuenta(tipoCuenta_id));

/*Facebook*/
INSERT INTO Balance (balance_periodo, balance_frecuencia, balance_tipoCuenta, balance_valor) 
			VALUES ("201706", 4, 1, 140000000); /*id=1*/
INSERT INTO Balance (balance_periodo, balance_frecuencia, balance_tipoCuenta, balance_valor) 
			VALUES ("201612", 4, 1, 135000000); /*id=2*/
/*Fibertel*/
INSERT INTO Balance (balance_periodo, balance_frecuencia, balance_tipoCuenta, balance_valor) 
			VALUES ("201612", 5, 2, 134000000); /*id=3*/
			
/*Arena*/
INSERT INTO Balance (balance_periodo, balance_frecuencia, balance_tipoCuenta, balance_valor) 
			VALUES ("201705", 4, 4, 100000); /*id=4*/

/*Balances Negativos*/
INSERT INTO Balance (balance_periodo, balance_frecuencia, balance_tipoCuenta, balance_valor) 
			VALUES ("201705", 4, 4, 5); /*id=5*/
INSERT INTO Balance (balance_periodo, balance_frecuencia, balance_tipoCuenta, balance_valor) 
			VALUES ("201705", 4, 4, -20); /*id=6*/
			
/*BalancesEmpresa*/
CREATE TABLE BalancesEmpresa (
balancesEmpresa_empresa INT NOT NULL,
balancesEmpresa_balance INT NOT NULL,
PRIMARY KEY(balancesEmpresa_empresa, balancesEmpresa_balance),
FOREIGN KEY (balancesEmpresa_empresa) references Empresa(empresa_id),
FOREIGN KEY (balancesEmpresa_balance) references Balance(balance_id));

/*Facebook*/
INSERT INTO BalancesEmpresa (balancesEmpresa_empresa, balancesEmpresa_balance)
			VALUES (1, 1);
INSERT INTO BalancesEmpresa (balancesEmpresa_empresa, balancesEmpresa_balance)
			VALUES (1, 2);

/*Fibertel*/
INSERT INTO BalancesEmpresa (balancesEmpresa_empresa, balancesEmpresa_balance)
			VALUES (2, 3);
			
/*Arena*/
INSERT INTO BalancesEmpresa (balancesEmpresa_empresa, balancesEmpresa_balance)
			VALUES (3, 4);
						
/*Balances Negativos*/
INSERT INTO BalancesEmpresa (balancesEmpresa_empresa, balancesEmpresa_balance)
			VALUES (4, 5);
INSERT INTO BalancesEmpresa (balancesEmpresa_empresa, balancesEmpresa_balance)
			VALUES (4, 6);
			
/*ENTREGA 2*/

/*ENTREGA 3*/
