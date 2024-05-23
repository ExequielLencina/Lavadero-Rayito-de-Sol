-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS lavadero_rayito_de_sol;

-- Usar la base de datos
USE lavadero_rayito_de_sol;

-- Crear tabla Cliente
CREATE TABLE Cliente (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(20) NOT NULL
);

-- Crear tabla Servicio
CREATE TABLE Servicio (
    idServicio INT AUTO_INCREMENT PRIMARY KEY,
    tipoServicio VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL
);

-- Crear tabla Pedido
CREATE TABLE Pedido (
    idPedido INT AUTO_INCREMENT PRIMARY KEY,
    fechaIngreso DATE NOT NULL,
    estado VARCHAR(50) NOT NULL,
    tipoEntrega VARCHAR(50) NOT NULL,
    idCliente INT NOT NULL,
    idServicio INT NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente),
    FOREIGN KEY (idServicio) REFERENCES Servicio(idServicio)
);

-- Crear tabla Prenda
CREATE TABLE Prenda (
    idPrenda INT AUTO_INCREMENT PRIMARY KEY,
    tipoPrenda VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    idPedido INT NOT NULL,
    FOREIGN KEY (idPedido) REFERENCES Pedido(idPedido)
);

-- Registro de servicios ofrecidos
INSERT INTO Servicio (tipoServicio, descripcion) VALUES ('Lavado', 'Lavado completo de prendas');
INSERT INTO Servicio (tipoServicio, descripcion) VALUES ('Planchado', 'Planchado de prendas');
INSERT INTO Servicio (tipoServicio, descripcion) VALUES ('Secado', 'Secado de prendas');
INSERT INTO Servicio (tipoServicio, descripcion) VALUES ('Blanqueado', 'Blanqueado de prendas');

-- REgistro de cliente
INSERT INTO Cliente (nombre, direccion, telefono) VALUES ('Exequiel Lencina', 'Calle Falsa 123', '123456789');

-- Registro de pedido
INSERT INTO Pedido (fechaIngreso, estado, tipoEntrega, idCliente, idServicio)
VALUES ('2023-05-23', 'Pendiente', 'A Domicilio', 1, 1);
