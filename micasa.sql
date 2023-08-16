-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-02-2020 a las 18:19:30
-- Versión del servidor: 10.4.6-MariaDB
-- Versión de PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `micasa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hijos`
--

CREATE TABLE `hijos` (
  `id_hijos` int(11) NOT NULL,
  `cedula_hijos` int(11) DEFAULT NULL,
  `nombre_hijos` varchar(255) NOT NULL,
  `apellido_hijos` varchar(255) NOT NULL,
  `fechaNac_hijos` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `iglesia`
--

CREATE TABLE `iglesia` (
  `id_iglesia` int(11) NOT NULL,
  `nombre_iglesia` varchar(255) NOT NULL,
  `direccion_iglesia` varchar(255) NOT NULL,
  `ciudad_iglesia` varchar(40) NOT NULL,
  `estado_iglesia` varchar(40) NOT NULL,
  `pais_iglesia` varchar(40) NOT NULL,
  `tlf_iglesia` varchar(20) DEFAULT NULL,
  `tlf1_iglesia` varchar(20) DEFAULT NULL,
  `pastor_iglesia` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ministerio`
--

CREATE TABLE `ministerio` (
  `id_ministerio` int(11) NOT NULL,
  `denominacion_ministerio` varchar(255) NOT NULL,
  `pastor_ministerio` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ministerio`
--

INSERT INTO `ministerio` (`id_ministerio`, `denominacion_ministerio`, `pastor_ministerio`) VALUES
(1, 'Adoración y Alabanza', 'Pastor Omar Figueroa'),
(2, 'Ujieres', 'Cristobula Olfila'),
(4, 'Ministerio Mujeres', 'Mrian Cabrices');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id_persona` int(11) NOT NULL,
  `cedula_persona` int(11) NOT NULL,
  `nombre_persona` varchar(255) NOT NULL,
  `apellido_persona` varchar(255) NOT NULL,
  `fechaNac_persona` date DEFAULT NULL,
  `dirhab_persona` varchar(255) DEFAULT NULL,
  `telfijo_persona` varchar(20) DEFAULT NULL,
  `telcel_persona` varchar(20) DEFAULT NULL,
  `id_profesion_persona` int(11) DEFAULT NULL,
  `ocupacion_persona` varchar(255) DEFAULT NULL,
  `correo_persona` varchar(50) DEFAULT NULL,
  `bautizado_persona` char(2) NOT NULL,
  `iglesiaProv_persona` varchar(100) DEFAULT NULL,
  `observacion_persona` varchar(255) DEFAULT NULL,
  `id_ministerio_persona` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id_persona`, `cedula_persona`, `nombre_persona`, `apellido_persona`, `fechaNac_persona`, `dirhab_persona`, `telfijo_persona`, `telcel_persona`, `id_profesion_persona`, `ocupacion_persona`, `correo_persona`, `bautizado_persona`, `iglesiaProv_persona`, `observacion_persona`, `id_ministerio_persona`) VALUES
(1, 11111, 'nnnnnn', 'aaaaa', '2020-07-01', 'ddddd', '33333', '333333', 4, 'ooooooo', 'cccccc', 'Si', 'iiiii', 'ooooooo', 1),
(3, 11923757, 'Omar ', 'Figueroa', '1974-01-05', 'La Vega', '0212-4710498', '04168386846', 4, 'Programador', 'ojf74@hotmail.com', 'Si', '', 'Programador', 1),
(4, 33333, 'aaaaaa', 'aaaaa', '2019-07-11', 'aaaaa', '33333', '33333', 13, 'wwww', 'ssssss', 'No', 'wwwwww', 'wwww', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesion`
--

CREATE TABLE `profesion` (
  `id_profesion` int(11) NOT NULL,
  `denominacion_profesion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `profesion`
--

INSERT INTO `profesion` (`id_profesion`, `denominacion_profesion`) VALUES
(4, 'Abogado'),
(2, 'Administrador de Recursos Humanos'),
(1, 'Analista de Sistemas'),
(13, 'otra mas'),
(11, 'Otra prueba'),
(3, 'Prueba');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL,
  `denominacion_rol` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id_rol`, `denominacion_rol`) VALUES
(1, 'Administrador'),
(2, 'Usuario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `nombre_usuario` varchar(100) NOT NULL,
  `apellido_usuario` varchar(100) NOT NULL,
  `login_usuario` varchar(50) NOT NULL,
  `contrasena_usuario` varchar(255) NOT NULL,
  `correo_usuario` varchar(50) DEFAULT NULL,
  `last_session_usuario` datetime DEFAULT '0000-00-00 00:00:00',
  `id_rol_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nombre_usuario`, `apellido_usuario`, `login_usuario`, `contrasena_usuario`, `correo_usuario`, `last_session_usuario`, `id_rol_usuario`) VALUES
(1, 'Omar', 'Figueroa', 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'ojf74@hotmail.com', '2019-07-31 23:54:25', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visitantes`
--

CREATE TABLE `visitantes` (
  `id_visitantes` int(11) NOT NULL,
  `nombre_visitantes` varchar(255) NOT NULL,
  `apellido_visitantes` varchar(255) NOT NULL,
  `tlf_visitantes` varchar(20) NOT NULL,
  `tlf2_visitantes` varchar(20) NOT NULL,
  `correo_visitantes` varchar(50) NOT NULL,
  `tipo_visitantes` varchar(20) NOT NULL,
  `observacion_visitantes` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `hijos`
--
ALTER TABLE `hijos`
  ADD PRIMARY KEY (`id_hijos`);

--
-- Indices de la tabla `iglesia`
--
ALTER TABLE `iglesia`
  ADD PRIMARY KEY (`id_iglesia`);

--
-- Indices de la tabla `ministerio`
--
ALTER TABLE `ministerio`
  ADD PRIMARY KEY (`id_ministerio`),
  ADD UNIQUE KEY `denominacion_ministerio_UNIQUE` (`denominacion_ministerio`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id_persona`),
  ADD UNIQUE KEY `cedula_persona_UNIQUE` (`cedula_persona`),
  ADD KEY `id_profesion_idx` (`id_profesion_persona`),
  ADD KEY `id_ministerio_idx` (`id_ministerio_persona`);

--
-- Indices de la tabla `profesion`
--
ALTER TABLE `profesion`
  ADD PRIMARY KEY (`id_profesion`),
  ADD UNIQUE KEY `denominacion_profesion_UNIQUE` (`denominacion_profesion`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id_rol`),
  ADD UNIQUE KEY `denominacion_rol_UNIQUE` (`denominacion_rol`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `login_usuario_UNIQUE` (`login_usuario`),
  ADD KEY `id_rol_idx` (`id_rol_usuario`);

--
-- Indices de la tabla `visitantes`
--
ALTER TABLE `visitantes`
  ADD PRIMARY KEY (`id_visitantes`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `hijos`
--
ALTER TABLE `hijos`
  MODIFY `id_hijos` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `iglesia`
--
ALTER TABLE `iglesia`
  MODIFY `id_iglesia` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ministerio`
--
ALTER TABLE `ministerio`
  MODIFY `id_ministerio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id_persona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `profesion`
--
ALTER TABLE `profesion`
  MODIFY `id_profesion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `visitantes`
--
ALTER TABLE `visitantes`
  MODIFY `id_visitantes` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `persona`
--
ALTER TABLE `persona`
  ADD CONSTRAINT `fk_id_ministerio` FOREIGN KEY (`id_ministerio_persona`) REFERENCES `ministerio` (`id_ministerio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_profesion` FOREIGN KEY (`id_profesion_persona`) REFERENCES `profesion` (`id_profesion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `id_rol` FOREIGN KEY (`id_rol_usuario`) REFERENCES `rol` (`id_rol`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
