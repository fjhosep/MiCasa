-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-09-2019 a las 17:18:09
-- Versión del servidor: 5.6.16
-- Versión de PHP: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `micasa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hijos`
--

CREATE TABLE IF NOT EXISTS `hijos` (
  `id_hijos` int(11) NOT NULL AUTO_INCREMENT,
  `cedula_hijos` int(11) DEFAULT NULL,
  `nombre_hijos` varchar(255) NOT NULL,
  `apellido_hijos` varchar(255) NOT NULL,
  `fechaNac_hijos` date NOT NULL,
  PRIMARY KEY (`id_hijos`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `iglesia`
--

CREATE TABLE IF NOT EXISTS `iglesia` (
  `id_iglesia` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_iglesia` varchar(255) NOT NULL,
  `pastor_iglesia` varchar(45) NOT NULL,
  `direccion_iglesia` varchar(255) NOT NULL,
  `ciudad_iglesia` varchar(40) DEFAULT NULL,
  `estado_iglesia` varchar(40) DEFAULT NULL,
  `pais_iglesia` varchar(40) DEFAULT NULL,
  `tlf_iglesia` varchar(20) DEFAULT NULL,
  `tlf1_iglesia` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_iglesia`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `iglesia`
--

INSERT INTO `iglesia` (`id_iglesia`, `nombre_iglesia`, `pastor_iglesia`, `direccion_iglesia`, `ciudad_iglesia`, `estado_iglesia`, `pais_iglesia`, `tlf_iglesia`, `tlf1_iglesia`) VALUES
(2, 'COmunidad de Amor', 'Jhonny Quintero', 'Avenida Libertador', 'Caracas', 'DC', 'Venezuela', '98989898', ''),
(3, 'Las Acacias', 'Samuel Olson', 'El Cementerio', 'Caracas', 'DC', 'Venezuela', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ministerio`
--

CREATE TABLE IF NOT EXISTS `ministerio` (
  `id_ministerio` int(11) NOT NULL AUTO_INCREMENT,
  `denominacion_ministerio` varchar(255) NOT NULL,
  `pastor_ministerio` varchar(255) NOT NULL,
  PRIMARY KEY (`id_ministerio`),
  UNIQUE KEY `denominacion_ministerio_UNIQUE` (`denominacion_ministerio`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `ministerio`
--

INSERT INTO `ministerio` (`id_ministerio`, `denominacion_ministerio`, `pastor_ministerio`) VALUES
(1, 'Musica y Adoración', 'Omar Figueroa'),
(2, 'Danza', 'Lisneidy Figueroa'),
(3, 'Relove', 'Jhonny Quintero');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE IF NOT EXISTS `persona` (
  `id_persona` int(11) NOT NULL AUTO_INCREMENT,
  `cedula_persona` int(11) NOT NULL,
  `nombre_persona` varchar(255) NOT NULL,
  `apellido_persona` varchar(255) NOT NULL,
  `fechaNac_persona` date DEFAULT NULL,
  `dirhab_persona` varchar(255) DEFAULT NULL,
  `telfijo_persona` varchar(20) DEFAULT NULL,
  `telcel_persona` varchar(20) DEFAULT NULL,
  `id_profesion_persona` int(11) DEFAULT NULL,
  `ocupacion_persona` varchar(255) NOT NULL,
  `correo_persona` varchar(50) DEFAULT NULL,
  `bautizado_persona` char(2) NOT NULL,
  `iglesiaProv_persona` varchar(100) DEFAULT NULL,
  `observacion_persona` varchar(255) DEFAULT NULL,
  `id_ministerio_persona` int(11) DEFAULT NULL,
  `id_hijos_persona` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_persona`),
  UNIQUE KEY `cedula_persona_UNIQUE` (`cedula_persona`),
  KEY `id_profesion_idx` (`id_profesion_persona`),
  KEY `id_ministerio_idx` (`id_ministerio_persona`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id_persona`, `cedula_persona`, `nombre_persona`, `apellido_persona`, `fechaNac_persona`, `dirhab_persona`, `telfijo_persona`, `telcel_persona`, `id_profesion_persona`, `ocupacion_persona`, `correo_persona`, `bautizado_persona`, `iglesiaProv_persona`, `observacion_persona`, `id_ministerio_persona`, `id_hijos_persona`) VALUES
(1, 11923757, 'Omar', 'Figueroa', '2019-08-07', 'La Vega', '0212-4710498', '04168386846', 1, 'Programador', 'ojf74@hotmail.com', 'Si', 'Las Acacias', 'Programador', 1, NULL),
(3, 222222, 'Juan Hilario', 'Colmenarez', '2019-08-19', 'La Bandera', '7777777', '777777', 2, 'zxzxzx', 'dddddd', 'No', 'xzxzxzxzx', 'zxzxzx', 2, NULL),
(4, 123456789, 'Pedro ', 'Perez', '2019-07-24', 'Los Rosales', '22222', '33333', 1, 'wwww', 'wwww', 'Si', 'wwwwww', 'wwww', 1, NULL),
(5, 999999, 'rrrrrrr', 'rrrrrrr', '2019-08-19', 'rrrrrrrr', '999999', '99999', 3, 'rrrrrr', 'rrrrrrrr', 'Si', 'rrrrr', 'rrrrrr', 3, NULL),
(7, 555555, 'dfddfdfdf', 'dfdfdfdfdf', '2019-08-13', 'dfdfdfdf', '2323232323', '232323232', 2, 'sdsdsdsdsds', 'ffffff', 'Si', 'dsdsdsdsds', 'sdsdsdsdsds', 2, NULL),
(9, 33333, 'ddfdfdfd', 'fdfdfdfdf', '2019-08-12', 'fdfdfdfdff', '5454545', '5454545', 2, '', '', 'Si', '', '', 2, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesion`
--

CREATE TABLE IF NOT EXISTS `profesion` (
  `id_profesion` int(11) NOT NULL AUTO_INCREMENT,
  `denominacion_profesion` varchar(100) NOT NULL,
  PRIMARY KEY (`id_profesion`),
  UNIQUE KEY `denominacion_profesion_UNIQUE` (`denominacion_profesion`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `profesion`
--

INSERT INTO `profesion` (`id_profesion`, `denominacion_profesion`) VALUES
(2, 'Abogado'),
(1, 'Analista de Sistema'),
(3, 'Contador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE IF NOT EXISTS `rol` (
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

CREATE TABLE IF NOT EXISTS `usuario` (
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
(1, 'Omar', 'Figueroa', 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'ojf74@hotmail.com', '2019-08-16 08:23:22', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visitantes`
--

CREATE TABLE IF NOT EXISTS `visitantes` (
  `id_visitantes` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_visitantes` varchar(200) NOT NULL,
  `apellido_visitantes` varchar(200) NOT NULL,
  `correo_visitantes` varchar(100) NOT NULL,
  `tipo_visitantes` varchar(100) NOT NULL,
  PRIMARY KEY (`id_visitantes`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `persona`
--
ALTER TABLE `persona`
  ADD CONSTRAINT `id_ministerio` FOREIGN KEY (`id_ministerio_persona`) REFERENCES `ministerio` (`id_ministerio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_profesion` FOREIGN KEY (`id_profesion_persona`) REFERENCES `profesion` (`id_profesion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
