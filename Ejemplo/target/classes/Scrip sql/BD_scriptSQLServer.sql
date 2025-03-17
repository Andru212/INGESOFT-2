-- Verificar si la base de datos existe, si no, crearla
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'tienda')
    CREATE DATABASE tienda;
GO
USE tienda;
GO

-- Verificar y crear la tabla clientes
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='clientes' AND xtype='U')
BEGIN
    CREATE TABLE clientes (
        id NVARCHAR(100) NOT NULL,
        nombre NVARCHAR(255) NOT NULL,
        PRIMARY KEY (id)
    );
END
GO

-- Verificar y crear la tabla productos
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='productos' AND xtype='U')
BEGIN
    CREATE TABLE productos (
        id NVARCHAR(100) NOT NULL,
        descripcion NVARCHAR(255) NOT NULL,
        precio FLOAT NOT NULL,
        PRIMARY KEY (id)
    );
END
GO

-- Verificar y crear la tabla pedido
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='pedido' AND xtype='U')
BEGIN
    CREATE TABLE pedido (
        numero NVARCHAR(100) NOT NULL,
        fecha DATE NOT NULL,
        cliente_id NVARCHAR(100) NULL,
        PRIMARY KEY (numero),
        FOREIGN KEY (cliente_id) REFERENCES clientes (id)
    );
END
GO

-- Verificar y crear la tabla pedido_producto
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='pedido_producto' AND xtype='U')
BEGIN
    CREATE TABLE pedido_producto (
        pedido_numero NVARCHAR(100) NOT NULL,
        producto_id NVARCHAR(100) NOT NULL,
        PRIMARY KEY NONCLUSTERED (pedido_numero, producto_id),
        FOREIGN KEY (pedido_numero) REFERENCES pedido (numero),
        FOREIGN KEY (producto_id) REFERENCES productos (id)
    );
END
GO

-- Verificar y crear la tabla productos_alimentos
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='productos_alimentos' AND xtype='U')
BEGIN
    CREATE TABLE productos_alimentos (
        id NVARCHAR(100) NOT NULL,
        aporte_calorico FLOAT NOT NULL,
        PRIMARY KEY (id),
        FOREIGN KEY (id) REFERENCES productos (id) ON DELETE CASCADE
    );
END
GO

-- Verificar y crear la tabla productos_electronicos
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='productos_electronicos' AND xtype='U')
BEGIN
    CREATE TABLE productos_electronicos (
        id NVARCHAR(100) NOT NULL,
        voltaje_entrada FLOAT NOT NULL,
        PRIMARY KEY (id),
        FOREIGN KEY (id) REFERENCES productos (id) ON DELETE CASCADE
    );
END
GO
