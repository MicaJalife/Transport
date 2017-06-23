-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-06-2017 a las 14:37:40
-- Versión del servidor: 5.7.9
-- Versión de PHP: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `transport`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `amigos`
--

DROP TABLE IF EXISTS `amigos`;
CREATE TABLE IF NOT EXISTS `amigos` (
  `IdAmigos` int(11) NOT NULL,
  `DNI` int(11) NOT NULL,
  `IdAmigo` int(11) NOT NULL,
  PRIMARY KEY (`IdAmigos`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dias`
--

DROP TABLE IF EXISTS `dias`;
CREATE TABLE IF NOT EXISTS `dias` (
  `IdDia` int(11) NOT NULL AUTO_INCREMENT,
  `Dia` int(11) NOT NULL,
  PRIMARY KEY (`IdDia`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `diasviajes`
--

DROP TABLE IF EXISTS `diasviajes`;
CREATE TABLE IF NOT EXISTS `diasviajes` (
  `IdDiasViajes` int(11) NOT NULL AUTO_INCREMENT,
  `IdViaje` int(11) NOT NULL,
  `IdDia` int(11) NOT NULL,
  PRIMARY KEY (`IdDiasViajes`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horarios`
--

DROP TABLE IF EXISTS `horarios`;
CREATE TABLE IF NOT EXISTS `horarios` (
  `IdHorario` int(11) NOT NULL AUTO_INCREMENT,
  `Horario` time NOT NULL,
  PRIMARY KEY (`IdHorario`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transportes`
--

DROP TABLE IF EXISTS `transportes`;
CREATE TABLE IF NOT EXISTS `transportes` (
  `IdTransporte` int(11) NOT NULL AUTO_INCREMENT,
  `TipoTransporte` varchar(15) NOT NULL,
  PRIMARY KEY (`IdTransporte`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `DNI` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(60) NOT NULL,
  `Año` varchar(1) NOT NULL,
  `Curso` varchar(5) NOT NULL,
  `Contraseña` varchar(50) NOT NULL,
  `Imagen` int(11) NOT NULL,
  `PrimeraEdicion` tinyint(1) NOT NULL,
  `IdAmigos` int(11) NOT NULL,
  PRIMARY KEY (`DNI`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viajes`
--

DROP TABLE IF EXISTS `viajes`;
CREATE TABLE IF NOT EXISTS `viajes` (
  `IdViaje` int(11) NOT NULL AUTO_INCREMENT,
  `DNI` int(11) NOT NULL,
  `IdHorario` int(11) NOT NULL,
  `IdTransporte` int(11) NOT NULL,
  `DesdeHasta` tinyint(1) NOT NULL,
  `DetalleTransporte` varchar(140) NOT NULL,
  `DireccionLatitud` varchar(45) NOT NULL,
  `DireccionLonguitud` varchar(45) NOT NULL,
  PRIMARY KEY (`IdViaje`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
