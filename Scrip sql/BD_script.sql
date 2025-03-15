CREATE DATABASE tienda;
USE tienda;

-- tienda.clientes definition

CREATE TABLE clientes (
  id varchar(255) NOT NULL,
  nombre varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- tienda.productos definition

CREATE TABLE productos (
  id varchar(255) NOT NULL,
  descripcion varchar(255) NOT NULL,
  precio double NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- tienda.pedido definition

CREATE TABLE pedido (
  numero varchar(255) NOT NULL,
  fecha date NOT NULL,
  cliente_id varchar(255) DEFAULT NULL,
  PRIMARY KEY (numero),
  KEY cliente_id (cliente_id),
  CONSTRAINT pedido_ibfk_1 FOREIGN KEY (cliente_id) REFERENCES clientes (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- tienda.pedido_producto definition

CREATE TABLE pedido_producto (
  pedido_numero varchar(255) NOT NULL,
  producto_id varchar(255) NOT NULL,
  PRIMARY KEY (pedido_numero,producto_id),
  KEY producto_id (producto_id),
  CONSTRAINT pedido_producto_ibfk_1 FOREIGN KEY (pedido_numero) REFERENCES pedido (numero),
  CONSTRAINT pedido_producto_ibfk_2 FOREIGN KEY (producto_id) REFERENCES productos (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- tienda.productos_alimentos definition

CREATE TABLE productos_alimentos (
  id varchar(50) NOT NULL,
  aporte_calorico float NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT productos_alimentos_ibfk_1 FOREIGN KEY (id) REFERENCES productos (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- tienda.productos_electronicos definition

CREATE TABLE productos_electronicos (
  id varchar(50) NOT NULL,
  voltaje_entrada float NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT productos_electronicos_ibfk_1 FOREIGN KEY (id) REFERENCES productos (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;