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

-- Crear tabla Pedido
CREATE TABLE Pedido (
    idPedido INT AUTO_INCREMENT PRIMARY KEY,
    fechaIngreso VARCHAR(20) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    tipoEntrega VARCHAR(50) NOT NULL,
    tipoServicio VARCHAR(100) NOT NULL,
    tipoPrenda VARCHAR(100) NOT NULL,
    detalle TEXT NOT NULL,
    idCliente INT NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente)
);