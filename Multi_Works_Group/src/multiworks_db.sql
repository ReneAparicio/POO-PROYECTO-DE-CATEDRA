-- Tabla: usuarios
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(50) UNIQUE NOT NULL,
    contrasena_hash VARCHAR(255) NOT NULL,
    rol VARCHAR(20) DEFAULT 'Usuario',
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ultimo_acceso DATETIME
);

-- Tabla: clientes
CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    documento VARCHAR(20) UNIQUE,
    tipo_persona VARCHAR(50),
    telefono VARCHAR(20),
    correo VARCHAR(100),
    direccion VARCHAR(255),
    estado VARCHAR(20) DEFAULT 'Activo',
    creado_por INT,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion DATETIME,
    fecha_inactivacion DATETIME,
    FOREIGN KEY (creado_por) REFERENCES usuarios(id_usuario)
);

-- Tabla: empleados
CREATE TABLE empleados (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    documento VARCHAR(20) UNIQUE,
    tipo_persona VARCHAR(50),
    tipo_contratacion VARCHAR(50),
    telefono VARCHAR(20),
    correo VARCHAR(100),
    direccion VARCHAR(255),
    estado VARCHAR(20) DEFAULT 'Activo',
    creado_por INT,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion DATETIME,
    fecha_inactivacion DATETIME,
    FOREIGN KEY (creado_por) REFERENCES usuarios(id_usuario)
);

-- Tabla: cotizaciones
CREATE TABLE cotizaciones (
    id_cotizacion INT AUTO_INCREMENT PRIMARY KEY,
    titulo_actividad_principal VARCHAR(100) NOT NULL,
    id_cliente INT NOT NULL,
    id_trabajador_principal INT,
    area VARCHAR(100),
    costo_por_hora DECIMAL(10, 2) NOT NULL,
    fecha_inicio_aprox DATETIME,
    fecha_fin_aprox DATETIME,
    horas_aproximadas DECIMAL(8, 2),
    incremento_porcentaje DECIMAL(5, 2) DEFAULT 0.00,
    total_cotizacion DECIMAL(12, 2),
    estado_cotizacion VARCHAR(50) DEFAULT 'Pendiente',
    creado_por INT,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion DATETIME,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
    FOREIGN KEY (id_trabajador_principal) REFERENCES empleados(id_empleado),
    FOREIGN KEY (creado_por) REFERENCES usuarios(id_usuario)
);

-- Tabla: actividades
CREATE TABLE actividades (
    id_actividad INT AUTO_INCREMENT PRIMARY KEY,
    titulo_subtarea VARCHAR(100) NOT NULL,
    descripcion_subtarea TEXT,
    id_cotizacion INT,
    id_empleado_asignado INT,
    estado_actividad VARCHAR(50) DEFAULT 'Pendiente',
    fecha_inicio_planeada DATETIME,
    fecha_fin_planeada DATETIME,
    fecha_inicio_real DATETIME,
    fecha_fin_real DATETIME,
    creado_por INT,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion DATETIME,
    FOREIGN KEY (id_cotizacion) REFERENCES cotizaciones(id_cotizacion),
    FOREIGN KEY (id_empleado_asignado) REFERENCES empleados(id_empleado),
    FOREIGN KEY (creado_por) REFERENCES usuarios(id_usuario)
);