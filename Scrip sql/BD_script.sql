-- Verificar si la base de datos existe, si no, crearla
CREATE DATABASE IF NOT EXISTS tienda;
USE tienda;

-- Verificar y crear la tabla clientes
CREATE TABLE IF NOT EXISTS clientes (
  id VARCHAR(255) NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Verificar y crear la tabla productos
CREATE TABLE IF NOT EXISTS productos (
  id VARCHAR(255) NOT NULL,
  descripcion VARCHAR(255) NOT NULL,
  precio DOUBLE NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Verificar y crear la tabla pedido
CREATE TABLE IF NOT EXISTS pedido (
  numero VARCHAR(255) NOT NULL,
  fecha DATE NOT NULL,
  cliente_id VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (numero),
  KEY cliente_id (cliente_id),
  CONSTRAINT pedido_ibfk_1 FOREIGN KEY (cliente_id) REFERENCES clientes (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Verificar y crear la tabla pedido_producto
CREATE TABLE IF NOT EXISTS pedido_producto (
  pedido_numero VARCHAR(255) NOT NULL,
  producto_id VARCHAR(255) NOT NULL,
  PRIMARY KEY (pedido_numero, producto_id),
  KEY producto_id (producto_id),
  CONSTRAINT pedido_producto_ibfk_1 FOREIGN KEY (pedido_numero) REFERENCES pedido (numero),
  CONSTRAINT pedido_producto_ibfk_2 FOREIGN KEY (producto_id) REFERENCES productos (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Verificar y crear la tabla productos_alimentos
CREATE TABLE IF NOT EXISTS productos_alimentos (
  id VARCHAR(50) NOT NULL,
  aporte_calorico FLOAT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT productos_alimentos_ibfk_1 FOREIGN KEY (id) REFERENCES productos (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Verificar y crear la tabla productos_electronicos
CREATE TABLE IF NOT EXISTS productos_electronicos (
  id VARCHAR(50) NOT NULL,
  voltaje_entrada FLOAT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT productos_electronicos_ibfk_1 FOREIGN KEY (id) REFERENCES productos (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
