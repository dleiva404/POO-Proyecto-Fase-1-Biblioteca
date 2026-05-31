-- ============================================================
-- Sistema de Gestión de Biblioteca - Colegio Amigos de Don Bosco
-- ============================================================

CREATE DATABASE IF NOT EXISTS `biblioteca`
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_0900_ai_ci;

USE `biblioteca`;

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Tabla: usuarios
-- ----------------------------
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id_usuario`   int          NOT NULL AUTO_INCREMENT,
  `nombre`       varchar(100) NOT NULL,
  `apellido`     varchar(100) DEFAULT NULL,
  `carnet`       varchar(20)  DEFAULT NULL,
  `dui`          varchar(20)  DEFAULT NULL,
  `telefono`     varchar(20)  DEFAULT NULL,
  `correo`       varchar(100) DEFAULT NULL,
  `tipo_usuario` enum('ADMINISTRADOR','PROFESOR','ALUMNO') NOT NULL,
  `password`     varchar(100) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `usuarios` VALUES
(1, 'Admin',  'Sistema', 'admin', '00000000-0', '0000-0000', 'admin@biblioteca.com', 'ADMINISTRADOR', 'admin'),
(2, 'David',  'Leiva',   '1234',  '12345678-9', '7777-1234', 'david@gmail.com',      'ALUMNO',        '1234'),
(3, 'Carlos', 'Pérez',   '5678',  '87654321-0', '7777-5678', 'carlos@gmail.com',     'PROFESOR',      '5678');

-- ----------------------------
-- Tabla: materiales
-- ----------------------------
DROP TABLE IF EXISTS `materiales`;
CREATE TABLE `materiales` (
  `id_material`         int          NOT NULL AUTO_INCREMENT,
  `codigo`              varchar(20)  NOT NULL,
  `titulo`              varchar(150) NOT NULL,
  `categoria`           varchar(50)  DEFAULT NULL,
  `ubicacion`           varchar(100) NOT NULL,
  `cantidad_total`      int          NOT NULL,
  `cantidad_disponible` int          NOT NULL,
  `tipo_material`       enum('LIBRO','OBRA','REVISTA','TESIS','DOCUMENTO','CD') NOT NULL,
  PRIMARY KEY (`id_material`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `materiales` VALUES
(1, 'LB001',  'Java para Principiantes',      'Programación', 'Estante A1', 3, 2, 'LIBRO'),
(2, 'LB002',  'Base de Datos',                'Tecnología',   'Estante B1', 4, 4, 'LIBRO'),
(3, 'RV001',  'National Geographic',          'Ciencia',      'Estante C1', 5, 5, 'REVISTA'),
(4, 'TS001',  'Impacto del IoT en Educación', 'Investigación','Estante D1', 2, 2, 'TESIS'),
(5, 'DOC001', 'Manual de Redes Cisco',        'Manuales',     'Estante E1', 2, 2, 'DOCUMENTO'),
(6, 'CD001',  'Curso de Python Completo',     'Multimedia',   'Estante F1', 4, 4, 'CD');

-- ----------------------------
-- Tabla: libros
-- ----------------------------
DROP TABLE IF EXISTS `libros`;
CREATE TABLE `libros` (
  `id_libro`         int          NOT NULL AUTO_INCREMENT,
  `id_material`      int          NOT NULL,
  `autor`            varchar(100) DEFAULT NULL,
  `editorial`        varchar(100) DEFAULT NULL,
  `num_paginas`      int          DEFAULT NULL,
  `isbn`             varchar(30)  DEFAULT NULL,
  `anio_publicacion` int          DEFAULT NULL,
  `edicion`          varchar(50)  DEFAULT NULL,
  `tipo_pasta`       varchar(50)  DEFAULT NULL,
  `idioma`           varchar(50)  DEFAULT NULL,
  `genero`           varchar(50)  DEFAULT NULL,
  PRIMARY KEY (`id_libro`),
  KEY `id_material` (`id_material`),
  CONSTRAINT `libros_ibfk_1` FOREIGN KEY (`id_material`) REFERENCES `materiales` (`id_material`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `libros` VALUES
(1, 1, 'Herbert Schildt',      'McGraw Hill', 800, '978-0071809252', 2018, '12va', 'Dura', 'Español', 'Tecnología'),
(2, 2, 'Abraham Silberschatz', 'McGraw Hill', 520, '9780073523323',  2019, '7ma',  'Dura', 'Español', 'Educativo');

-- ----------------------------
-- Tabla: obras
-- ----------------------------
DROP TABLE IF EXISTS `obras`;
CREATE TABLE `obras` (
  `id_obra`          int          NOT NULL AUTO_INCREMENT,
  `id_material`      int          NOT NULL,
  `autor`            varchar(100) DEFAULT NULL,
  `editorial`        varchar(100) DEFAULT NULL,
  `num_paginas`      int          DEFAULT NULL,
  `edicion`          varchar(50)  DEFAULT NULL,
  `tipo_pasta`       varchar(50)  DEFAULT NULL,
  `genero`           varchar(50)  DEFAULT NULL,
  `idioma`           varchar(50)  DEFAULT NULL,
  `isbn`             varchar(30)  DEFAULT NULL,
  `anio_publicacion` int          DEFAULT NULL,
  PRIMARY KEY (`id_obra`),
  KEY `id_material` (`id_material`),
  CONSTRAINT `obras_ibfk_1` FOREIGN KEY (`id_material`) REFERENCES `materiales` (`id_material`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Tabla: revistas
-- ----------------------------
DROP TABLE IF EXISTS `revistas`;
CREATE TABLE `revistas` (
  `id_revista`        int          NOT NULL AUTO_INCREMENT,
  `id_material`       int          NOT NULL,
  `editorial`         varchar(100) DEFAULT NULL,
  `num_paginas`       int          DEFAULT NULL,
  `idioma`            varchar(50)  DEFAULT NULL,
  `fecha_publicacion` varchar(20)  DEFAULT NULL,
  `issn`              varchar(30)  DEFAULT NULL,
  `periodicidad`      varchar(50)  DEFAULT NULL,
  `numero_edicion`    int          DEFAULT NULL,
  PRIMARY KEY (`id_revista`),
  KEY `id_material` (`id_material`),
  CONSTRAINT `revistas_ibfk_1` FOREIGN KEY (`id_material`) REFERENCES `materiales` (`id_material`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `revistas` VALUES
(1, 3, 'National Geographic Society', 120, 'Español', '2026-01-01', 'ISSN-0027-9358', 'Mensual', 45);

-- ----------------------------
-- Tabla: tesis
-- ----------------------------
DROP TABLE IF EXISTS `tesis`;
CREATE TABLE `tesis` (
  `id_tesis`         int          NOT NULL AUTO_INCREMENT,
  `id_material`      int          NOT NULL,
  `autor`            varchar(100) DEFAULT NULL,
  `editorial`        varchar(100) DEFAULT NULL,
  `num_paginas`      int          DEFAULT NULL,
  `carrera`          varchar(100) DEFAULT NULL,
  `tema`             varchar(200) DEFAULT NULL,
  `asesor`           varchar(100) DEFAULT NULL,
  `universidad`      varchar(150) DEFAULT NULL,
  `grado`            varchar(50)  DEFAULT NULL,
  `idioma`           varchar(50)  DEFAULT NULL,
  `anio_publicacion` int          DEFAULT NULL,
  PRIMARY KEY (`id_tesis`),
  KEY `id_material` (`id_material`),
  CONSTRAINT `tesis_ibfk_1` FOREIGN KEY (`id_material`) REFERENCES `materiales` (`id_material`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `tesis` VALUES
(1, 4, 'María López', 'UDB', 200, 'Ingeniería en Sistemas', 'IoT Educativo', 'Ing. Carlos Rivas', 'UDB', 'Licenciatura', 'Español', 2025);

-- ----------------------------
-- Tabla: documentos
-- ----------------------------
DROP TABLE IF EXISTS `documentos`;
CREATE TABLE `documentos` (
  `id_documento`     int          NOT NULL AUTO_INCREMENT,
  `id_material`      int          NOT NULL,
  `autor`            varchar(100) DEFAULT NULL,
  `editorial`        varchar(100) DEFAULT NULL,
  `num_paginas`      int          DEFAULT NULL,
  `tipo_documento`   varchar(100) DEFAULT NULL,
  `idioma`           varchar(50)  DEFAULT NULL,
  `anio_publicacion` int          DEFAULT NULL,
  PRIMARY KEY (`id_documento`),
  KEY `id_material` (`id_material`),
  CONSTRAINT `documentos_ibfk_1` FOREIGN KEY (`id_material`) REFERENCES `materiales` (`id_material`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `documentos` VALUES
(1, 5, 'Cisco Systems', 'Cisco Press', 450, 'Manual Técnico', 'Español', 2024);

-- ----------------------------
-- Tabla: cd_dvd
-- ----------------------------
DROP TABLE IF EXISTS `cd_dvd`;
CREATE TABLE `cd_dvd` (
  `id_cd`            int          NOT NULL AUTO_INCREMENT,
  `id_material`      int          NOT NULL,
  `artista`          varchar(100) DEFAULT NULL,
  `disquera`         varchar(100) DEFAULT NULL,
  `genero`           varchar(50)  DEFAULT NULL,
  `idioma`           varchar(50)  DEFAULT NULL,
  `numero_canciones` int          DEFAULT NULL,
  `anio_lanzamiento` int          DEFAULT NULL,
  `duracion`         int          DEFAULT NULL,
  PRIMARY KEY (`id_cd`),
  KEY `id_material` (`id_material`),
  CONSTRAINT `cd_dvd_ibfk_1` FOREIGN KEY (`id_material`) REFERENCES `materiales` (`id_material`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `cd_dvd` VALUES
(1, 6, 'Freddy Vega', 'Platzi', 'Educativo', 'Español', 120, 2024, 480);

-- ----------------------------
-- Tabla: prestamos
-- ----------------------------
DROP TABLE IF EXISTS `prestamos`;
CREATE TABLE `prestamos` (
  `id_prestamo`      int  NOT NULL AUTO_INCREMENT,
  `id_usuario`       int  NOT NULL,
  `id_material`      int  NOT NULL,
  `fecha_prestamo`   date NOT NULL,
  `fecha_devolucion` date DEFAULT NULL,
  `estado`           enum('PRESTADO','DEVUELTO') DEFAULT 'PRESTADO',
  `mora`             decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`id_prestamo`),
  KEY `id_usuario`  (`id_usuario`),
  KEY `id_material` (`id_material`),
  CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`id_usuario`)  REFERENCES `usuarios`   (`id_usuario`),
  CONSTRAINT `prestamos_ibfk_2` FOREIGN KEY (`id_material`) REFERENCES `materiales` (`id_material`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Tabla: configuracion
-- ----------------------------
DROP TABLE IF EXISTS `configuracion`;
CREATE TABLE `configuracion` (
  `clave`  varchar(50)  NOT NULL,
  `valor`  varchar(100) NOT NULL,
  PRIMARY KEY (`clave`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `configuracion` VALUES ('mora_diaria', '0.25');

-- Préstamo de prueba: David (id=2) prestó Java para Principiantes (id=1) con mora acumulada
INSERT INTO `prestamos` VALUES
(1, 2, 1, '2026-04-01', '2026-04-08', 'PRESTADO', 0.00);


SET FOREIGN_KEY_CHECKS = 1;