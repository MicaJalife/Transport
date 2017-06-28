-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 28-06-2017 a las 00:28:07
-- Versión del servidor: 5.5.8
-- Versión de PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `test`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `amigos`
--

CREATE TABLE IF NOT EXISTS `amigos` (
  `DNI` int(11) NOT NULL,
  `IdAmigo` int(11) NOT NULL,
  PRIMARY KEY (`IdAmigo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `amigos`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dias`
--

CREATE TABLE IF NOT EXISTS `dias` (
  `IdDia` int(11) NOT NULL AUTO_INCREMENT,
  `Dia` int(11) NOT NULL,
  PRIMARY KEY (`IdDia`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `dias`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `diasviajes`
--

CREATE TABLE IF NOT EXISTS `diasviajes` (
  `IdDiasViajes` int(11) NOT NULL AUTO_INCREMENT,
  `IdViaje` int(11) NOT NULL,
  `IdDia` int(11) NOT NULL,
  PRIMARY KEY (`IdDiasViajes`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `diasviajes`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horarios`
--

CREATE TABLE IF NOT EXISTS `horarios` (
  `IdHorario` int(11) NOT NULL AUTO_INCREMENT,
  `Horario` time NOT NULL,
  PRIMARY KEY (`IdHorario`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `horarios`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transportes`
--

CREATE TABLE IF NOT EXISTS `transportes` (
  `IdTransporte` int(11) NOT NULL AUTO_INCREMENT,
  `TipoTransporte` varchar(15) NOT NULL,
  PRIMARY KEY (`IdTransporte`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `transportes`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `DNI` int(11) NOT NULL,
  `Nombre` varchar(60) NOT NULL,
  `Año` varchar(1) NOT NULL,
  `Curso` varchar(5) NOT NULL,
  `Contraseña` varchar(50) NOT NULL,
  `Imagen` varchar(11) NOT NULL,
  `PrimeraEdicion` tinyint(1) NOT NULL,
  PRIMARY KEY (`DNI`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `usuarios`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viajes`
--

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `viajes`
--

