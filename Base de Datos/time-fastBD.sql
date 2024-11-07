drop database timeFast;
CREATE DATABASE timeFast;
#Creacion de usuario
CREATE USER 'usuarioTimeFast'@'localhost' IDENTIFIED BY 'Lobo41solitari@';
GRANT ALL PRIVILEGES ON timeFast.* TO 'usuarioTimeFast'@'localhost';
FLUSH PRIVILEGES;
#SHOW GRANTS FOR 'usuarioTimeFast'@'localhost';

USE timeFast;

CREATE TABLE rol(
	idRol INTEGER AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE colaborador (
    idColaborador INTEGER AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidoPaterno VARCHAR(50) NOT NULL,
    apellidoMaterno VARCHAR(50) NOT NULL,
    curp CHAR(18) UNIQUE NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    noPersonal VARCHAR(30) UNIQUE NOT NULL,
    contrasena VARCHAR(50) NOT NULL,
    idRol INTEGER NOT NULL,
    fotografia LONGBLOB,
    numeroDeLicencia VARCHAR(20),
    FOREIGN KEY (idRol) REFERENCES rol(idRol)
);

CREATE TABLE tipoUnidad(
	idTipoUnidad INTEGER AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE unidad(
    idUnidad INTEGER AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio YEAR NOT NULL,
    vin CHAR(17) UNIQUE NOT NULL,
    idTipoUnidad INTEGER NOT NULL,
    nii VARCHAR(8) NOT NULL,
    FOREIGN KEY (idTipoUnidad) REFERENCES tipoUnidad(idTipoUnidad)
);

CREATE TABLE cliente(
    idCliente INTEGER AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidoPaterno VARCHAR(50) NOT NULL,
    apellidoMaterno VARCHAR(50) NOT NULL,
    calle VARCHAR(100) NOT NULL,
    numeroDeCasa VARCHAR(10) NOT NULL,
    colonia VARCHAR(50) NOT NULL,
    codigoPostal CHAR(5) NOT NULL,
	ciudad VARCHAR(50) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    telefono VARCHAR(10),
    correo VARCHAR(100)
);


CREATE TABLE estadoDeEnvio(
	idEstadoDeEnvio INTEGER AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE envio(
    idEnvio INTEGER AUTO_INCREMENT PRIMARY KEY,
    idCliente INTEGER NOT NULL,
    origenCalle VARCHAR(100) NOT NULL,
    origenNumero VARCHAR(10) NOT NULL,
    origenColonia VARCHAR(50) NOT NULL,
    origenCodigoPostal CHAR(5) NOT NULL,
    origenCiudad VARCHAR(50) NOT NULL,
    origenEstado VARCHAR(50) NOT NULL,
    noGuia VARCHAR(20) UNIQUE NOT NULL,
    costoDeEnvio FLOAT NOT NULL,
    idEstadoDeEnvio INTEGER NOT NULL,
    idColaborador INTEGER,
    FOREIGN KEY (idCliente) REFERENCES cliente(idCliente),
    FOREIGN KEY (idColaborador) REFERENCES colaborador(idColaborador),
    FOREIGN KEY (idEstadoDeEnvio) REFERENCES estadoDeEnvio(idEstadoDeEnvio)
);

CREATE TABLE paquete(
    idPaquete INTEGER AUTO_INCREMENT PRIMARY KEY,
    idEnvio INTEGER NOT NULL,
    descripcion TEXT NOT NULL,
    peso FLOAT NOT NULL,
    alto FLOAT NOT NULL,
    ancho FLOAT NOT NULL,
    profundidad FLOAT NOT NULL,
    FOREIGN KEY (idEnvio) REFERENCES envio(idEnvio)
);

CREATE TABLE historialDeEnvio(
	idHistorialDeEnvio INTEGER AUTO_INCREMENT PRIMARY KEY,
    idPaquete INTEGER NOT NULL,
	idEnvio INTEGER NOT NULL,
    idColaborador INTEGER NOT NULL,
    motivo VARCHAR(250),
    tiempoDeCambio DATE,
	FOREIGN KEY (idColaborador) REFERENCES colaborador(idColaborador),
    FOREIGN KEY (idEnvio) REFERENCES envio(idEnvio),
    FOREIGN KEY (idPaquete) REFERENCES paquete(idPaquete)
);

CREATE TABLE historialDeBaja(
	idHistorialDeBaja INTEGER AUTO_INCREMENT PRIMARY KEY,
    idUnidad INTEGER NOT NULL,
    motivo VARCHAR(250),
	FOREIGN KEY (idUnidad) REFERENCES unidad(idUnidad)
);

CREATE TABLE conductoresAsignados(
    idConductoresAsignados INTEGER AUTO_INCREMENT PRIMARY KEY,
    idColaborador INTEGER NOT NULL,
    idUnidad INTEGER NOT NULL,
    FOREIGN KEY (idColaborador) REFERENCES colaborador(idColaborador),
    FOREIGN KEY (idUnidad) REFERENCES unidad(idUnidad),
    UNIQUE (idColaborador, idUnidad)
);




