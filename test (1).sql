-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 30-06-2017 a las 02:39:03
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
  `IdDNIAmigos` int(11) NOT NULL AUTO_INCREMENT,
  `DNI` int(11) NOT NULL,
  `IdAmigo` int(11) NOT NULL,
  PRIMARY KEY (`IdDNIAmigos`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Volcar la base de datos para la tabla `amigos`
--

INSERT INTO `amigos` (`IdDNIAmigos`, `DNI`, `IdAmigo`) VALUES
(1, 42225260, 42586854),
(2, 42225260, 11111111),
(3, 42225260, 22222222),
(4, 42225260, 33333333),
(5, 42586854, 11111111),
(6, 42586854, 22222222),
(7, 22222222, 33333333),
(8, 11111111, 33333333);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dias`
--

CREATE TABLE IF NOT EXISTS `dias` (
  `IdDia` int(11) NOT NULL AUTO_INCREMENT,
  `Dia` varchar(11) NOT NULL,
  PRIMARY KEY (`IdDia`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Volcar la base de datos para la tabla `dias`
--

INSERT INTO `dias` (`IdDia`, `Dia`) VALUES
(1, 'Lunes'),
(2, 'Martes'),
(3, 'Miercoles'),
(4, 'Jueves'),
(5, 'Viernes');

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
  `Horario` varchar(45) NOT NULL,
  PRIMARY KEY (`IdHorario`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Volcar la base de datos para la tabla `horarios`
--

INSERT INTO `horarios` (`IdHorario`, `Horario`) VALUES
(1, '7:45'),
(2, '9:05'),
(3, '10:40'),
(4, '12:15'),
(5, '14:30'),
(6, '16:00'),
(7, '17:30');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transportes`
--

CREATE TABLE IF NOT EXISTS `transportes` (
  `IdTransporte` int(11) NOT NULL AUTO_INCREMENT,
  `TipoTransporte` varchar(15) NOT NULL,
  PRIMARY KEY (`IdTransporte`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Volcar la base de datos para la tabla `transportes`
--

INSERT INTO `transportes` (`IdTransporte`, `TipoTransporte`) VALUES
(1, 'Auto'),
(2, 'Colectivo'),
(3, 'Subte'),
(4, 'Bicicleta'),
(5, 'Caminando');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `DNI` int(11) NOT NULL,
  `Nombre` varchar(60) NOT NULL,
  `Anio` varchar(1) NOT NULL,
  `Curso` varchar(5) NOT NULL,
  `Contrasenia` varchar(50) NOT NULL,
  `Imagen` varchar(11) NOT NULL,
  `PrimeraEdicion` tinyint(1) NOT NULL,
  PRIMARY KEY (`DNI`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`DNI`, `Nombre`, `Anio`, `Curso`, `Contrasenia`, `Imagen`, `PrimeraEdicion`) VALUES
(42225260, 'Rocio Tarda', '6', 'IC', 'rocio123', '', 0),
(42586854, 'Micaela Jalife', '6', 'IC', 'micaela123', '', 0),
(11111111, 'Felipe Sasiain', '5', 'KC', 'felipe123', '', 1),
(22222222, 'Simon Oliva', '4', 'IB', 'simon123', '', 0),
(33333333, 'Enrico Martella', '5', 'IA', 'enrico123', '', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viajes`
--

CREATE TABLE IF NOT EXISTS `viajes` (
  `IdViaje` int(11) NOT NULL AUTO_INCREMENT,
  `DNI` int(11) NOT NULL,
  `IdHorario` int(11) NOT NULL,
  `IdTransporte` int(11) NOT NULL,
  `IdDia` int(11) NOT NULL,
  `DesdeHasta` tinyint(1) NOT NULL,
  `DetalleTransporte` varchar(140) NOT NULL,
  `DireccionLatitud` varchar(45) NOT NULL,
  `DireccionLongitud` varchar(45) NOT NULL,
  PRIMARY KEY (`IdViaje`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Volcar la base de datos para la tabla `viajes`
--

INSERT INTO `viajes` (`IdViaje`, `DNI`, `IdHorario`, `IdTransporte`, `IdDia`, `DesdeHasta`, `DetalleTransporte`, `DireccionLatitud`, `DireccionLongitud`) VALUES
(1, 42225260, 1, 2, 0, 1, 'Uso el colectivo 132', '', ''),
(3, 42225260, 7, 1, 1, 0, 'Tengo auto, y manejo porque soy cra', '', ''),
(4, 42586854, 1, 3, 2, 1, 'Toma el subte A en puan ;)', '', ''),
(5, 42586854, 4, 5, 5, 0, 'Caminando por rivadavia', '', '');
