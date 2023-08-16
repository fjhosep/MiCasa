-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-01-2020 a las 17:19:59
-- Versión del servidor: 10.1.31-MariaDB
-- Versión de PHP: 7.2.4

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
-- Estructura de tabla para la tabla `visitantes`
--

CREATE TABLE `visitantes` (
  `id_visitantes` int(11) NOT NULL,
  `nombre_visitantes` varchar(255) NOT NULL,
  `apellido_visitantes` varchar(255) NOT NULL,
  `tlf_visitante` varchar(20) NOT NULL,
  `tlf2_visitante` varchar(20) NOT NULL,
  `correo_visitantes` varchar(50) NOT NULL,
  `tipo_visitante` varchar(20) NOT NULL,
  `observacion_visitante` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `visitantes`
--

INSERT INTO `visitantes` (`id_visitantes`, `nombre_visitantes`, `apellido_visitantes`, `tlf_visitante`, `tlf2_visitante`, `correo_visitantes`, `tipo_visitante`, `observacion_visitante`) VALUES
(2, 'Nohomy', 'Alamo', '04128111980', '04122958497', 'nahomyalamo@hotmail.com', 'Visitante', ''),
(3, 'Maybeth', 'Pabón', '04261995363', '04143297633', 'admp8110@hotmail.com', 'Visitante', ''),
(4, 'Sara Estefanía', '', '04129181332', '', 'saraestafaniamendoza2007@gmail.com', 'Visitante', 'Niño Invitado a la fiesta Infantil realizada en Diciembre 2019'),
(5, 'Yordania', '', '02123144421', '', '', '', 'Niño Invitado a la fiesta Infantil realizada en Diciembre 2019');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `visitantes`
--
ALTER TABLE `visitantes`
  ADD PRIMARY KEY (`id_visitantes`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `visitantes`
--
ALTER TABLE `visitantes`
  MODIFY `id_visitantes` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
