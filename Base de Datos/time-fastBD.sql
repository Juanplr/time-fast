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

CREATE TABLE estadoUnidad(
	idEstadoUnidad INTEGER AUTO_INCREMENT PRIMARY KEY,
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
    idEstadoUnidad INTEGER,
    FOREIGN KEY (idTipoUnidad) REFERENCES tipoUnidad(idTipoUnidad),
	FOREIGN KEY (idEstadoUnidad) REFERENCES estadoUnidad(idEstadoUnidad)
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
    telefono VARCHAR(10) UNIQUE NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL
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
    destino VARCHAR(200),
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
    idEstadoDeEnvio INTEGER NOT NULL,
    idColaborador INTEGER NOT NULL,
    noGuia VARCHAR(20) UNIQUE NOT NULL,
    motivo VARCHAR(250),
    tiempoDeCambio DATE,
	FOREIGN KEY (idColaborador) REFERENCES colaborador(idColaborador),
    FOREIGN KEY (idEstadoDeEnvio) REFERENCES estadoDeEnvio(idEstadoDeEnvio)
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
    UNIQUE (idUnidad)
);


-- Triggers
DELIMITER $$

CREATE TRIGGER trg_delete_colaborador
AFTER DELETE ON colaborador
FOR EACH ROW
BEGIN
    -- Eliminar registros relacionados en historialDeEnvio
    DELETE FROM historialDeEnvio WHERE idColaborador = OLD.idColaborador;

    -- Eliminar registros relacionados en conductoresAsignados
    DELETE FROM conductoresAsignados WHERE idColaborador = OLD.idColaborador;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER trg_delete_unidad
AFTER DELETE ON unidad
FOR EACH ROW
BEGIN
    -- Eliminar registros relacionados en conductoresAsignados
    DELETE FROM conductoresAsignados WHERE idUnidad = OLD.idUnidad;

    -- Eliminar registros relacionados en historialDeBaja
    DELETE FROM historialDeBaja WHERE idUnidad = OLD.idUnidad;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER trg_delete_envio
AFTER DELETE ON envio
FOR EACH ROW
BEGIN
    -- Eliminar registros relacionados en paquete
    DELETE FROM paquete WHERE idEnvio = OLD.idEnvio;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER trg_delete_cliente
AFTER DELETE ON cliente
FOR EACH ROW
BEGIN
    -- Eliminar registros relacionados en envio
    DELETE FROM envio WHERE idCliente = OLD.idCliente;
END$$

DELIMITER ;






-- Datos para la tabla rol
INSERT INTO rol (nombre) VALUES 
('Administrador'), 
('Ejecutivo de tienda'), 
('Conductor');

-- Datos para la tabla colaborador
INSERT INTO colaborador (nombre, apellidoPaterno, apellidoMaterno, curp, correo, noPersonal, contrasena, idRol, fotografia, numeroDeLicencia) VALUES 
('Juan', 'Perez', 'Lopez', 'JUAP900101HDFRRN01', 'juan.perez@example.com', 'EMP123', 'password123', 1, NULL, ''),
('Maria', 'Gomez', 'Hernandez', 'MARG800202HDFRRN02', 'maria.gomez@example.com', 'EMP124', 'password124', 2, NULL, '');

-- Datos para la tabla tipoUnidad
INSERT INTO tipoUnidad (nombre) VALUES 
('Gasolina'),
('Diesel'),
('Eléctrica'),
('Hibrida');

-- Datos para la tabla estadoUnidad
INSERT INTO estadoUnidad (nombre) VALUES 
('Disponible'),
('En mantenimiento');

-- Datos para la tabla unidad
INSERT INTO unidad (marca, modelo, anio, vin, idTipoUnidad, nii, idEstadoUnidad) VALUES 
('Ford', 'F150', 2022, '1FTFW1E50LFA12345', 1, 'NII12345', 1),
('Chevrolet', 'Silverado', 2023, '3GCUYDED3MG123456', 1, 'NII54321', 2);



-- Datos para la tabla estadoDeEnvio
INSERT INTO estadoDeEnvio (nombre) VALUES 
('Pendiente'),
('En tránsito'),
('Entregado'),
("Detenido"),
("Cancelado");

INSERT INTO cliente (
    nombre, apellidoPaterno, apellidoMaterno, calle, numeroDeCasa, colonia, codigoPostal, ciudad, estado, telefono, correo
)
VALUES
('Juan', 'Pérez', 'García', 'Av. Siempre Viva', '123', 'Centro', '45678', 'Ciudad México', 'CDMX', '5523456789', 'juan.perez@example.com'),
('María', 'López', 'Hernández', 'Calle Independencia', '45', 'Reforma', '54321', 'Guadalajara', 'Jalisco', '3323456789', 'maria.lopez@example.com'),
('Carlos', 'Gómez', 'Martínez', 'Boulevard del Sol', '321', 'Las Palmas', '67890', 'Monterrey', 'Nuevo León', '8123456789', 'carlos.gomez@example.com'),
('Ana', 'Ramírez', 'Ortiz', 'Calle Luna', '78', 'Chapultepec', '98765', 'Puebla', 'Puebla', '2223456789', 'ana.ramirez@example.com'),
('Luis', 'Fernández', 'Díaz', 'Av. Hidalgo', '150', 'Centro', '12345', 'Querétaro', 'Querétaro', '4423456789', 'luis.fernandez@example.com');

-- Datos para la tabla envio
INSERT INTO envio (idCliente, origenCalle, origenNumero, origenColonia, origenCodigoPostal, origenCiudad, origenEstado, noGuia, costoDeEnvio, idEstadoDeEnvio, idColaborador,destino) VALUES 
(3, 'Calle A', '101', 'Colonia A', '54321', 'Ciudad A', 'Estado A', 'GUIA001', 150.50, 1, 1,"Calle5"),
(2, 'Calle B', '202', 'Colonia B', '12345', 'Ciudad B', 'Estado B', 'GUIA002', 200.00, 2, 2,"Margaritas");

-- Datos para la tabla paquete
INSERT INTO paquete (idEnvio, descripcion, peso, alto, ancho, profundidad) VALUES 
(1, 'Paquete pequeño', 2.5, 30, 20, 15),
(2, 'Paquete mediano', 5.0, 50, 40, 30);

-- Datos para la tabla historialDeEnvio
INSERT INTO historialDeEnvio (idPaquete, idEnvio, idColaborador, motivo, tiempoDeCambio) VALUES 
(1, 1, 1, 'Asignación inicial', '2024-11-01'),
(2, 2, 2, 'En tránsito', '2024-11-02');

-- Datos para la tabla historialDeBaja
INSERT INTO historialDeBaja (idUnidad, motivo) VALUES 
(2, 'Falla mecánica');

-- Datos para la tabla conductoresAsignados
INSERT INTO conductoresAsignados (idColaborador, idUnidad) VALUES 
(1, 1),
(2, 2);



