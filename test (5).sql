-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 30-06-2017 a las 00:34:24
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


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
-- Volcado de datos para la tabla `amigos`
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
-- Volcado de datos para la tabla `dias`
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
-- Volcado de datos para la tabla `horarios`
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
-- Volcado de datos para la tabla `transportes`
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
-- Volcado de datos para la tabla `usuarios`
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
  `DireccionLonguitud` varchar(45) NOT NULL,
  PRIMARY KEY (`IdViaje`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `viajes`
--

INSERT INTO `viajes` (`IdViaje`, `DNI`, `IdHorario`, `IdTransporte`, `IdDia`, `DesdeHasta`, `DetalleTransporte`, `DireccionLatitud`, `DireccionLonguitud`) VALUES
(1, 42225260, 1, 2, 0, 1, 'Uso el colectivo 132', '', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
