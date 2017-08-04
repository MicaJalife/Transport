-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:50075
-- Generation Time: Aug 04, 2017 at 08:02 AM
-- Server version: 5.7.9
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `amigos`
--

CREATE TABLE `amigos` (
  `IdDNIAmigos` int(11) NOT NULL,
  `DNI` int(11) NOT NULL,
  `IdAmigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `amigos`
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
-- Table structure for table `dias`
--

CREATE TABLE `dias` (
  `IdDia` int(11) NOT NULL,
  `Dia` varchar(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dias`
--

INSERT INTO `dias` (`IdDia`, `Dia`) VALUES
(1, 'Lunes'),
(2, 'Martes'),
(3, 'Miercoles'),
(4, 'Jueves'),
(5, 'Viernes');

-- --------------------------------------------------------

--
-- Table structure for table `diasviajes`
--

CREATE TABLE `diasviajes` (
  `IdDiasViajes` int(11) NOT NULL,
  `IdViaje` int(11) NOT NULL,
  `IdDia` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `horarios`
--

CREATE TABLE `horarios` (
  `IdHorario` int(11) NOT NULL,
  `Horario` varchar(45) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `horarios`
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
-- Table structure for table `transportes`
--

CREATE TABLE `transportes` (
  `IdTransporte` int(11) NOT NULL,
  `TipoTransporte` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transportes`
--

INSERT INTO `transportes` (`IdTransporte`, `TipoTransporte`) VALUES
(1, 'Auto'),
(2, 'Colectivo'),
(3, 'Subte'),
(4, 'Bicicleta'),
(5, 'Caminando');

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE `usuarios` (
  `DNI` int(11) NOT NULL,
  `Nombre` varchar(60) NOT NULL,
  `Anio` varchar(1) NOT NULL,
  `Curso` varchar(5) NOT NULL,
  `Contrasenia` varchar(50) NOT NULL,
  `Imagen` varchar(30) NOT NULL,
  `PrimeraEdicion` tinyint(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`DNI`, `Nombre`, `Anio`, `Curso`, `Contrasenia`, `Imagen`, `PrimeraEdicion`) VALUES
(42225260, 'Rocio Tarda', '6', 'IC', 'rocio123', 't42225260.jpg', 1),
(42586854, 'Micaela Jalife', '6', 'IC', 'micaela123', 't42586854.jpg', 1),
(11111111, 'Felipe Sasiain', '5', 'KC', 'felipe123', 't11111111.jpg', 1),
(22222222, 'Simon Oliva', '4', 'IB', 'simon123', 't22222222.jpg', 1),
(33333333, 'Enrico Martella', '5', 'IA', 'enrico123', 't33333333.jpg', 0),
(1, 'alumno a', '6', 'I', '1', 't1.jpg', 1);

-- --------------------------------------------------------

--
-- Table structure for table `viajes`
--

CREATE TABLE `viajes` (
  `IdViaje` int(11) NOT NULL,
  `DNI` int(11) NOT NULL,
  `IdHorario` int(11) NOT NULL,
  `IdTransporte` int(11) NOT NULL,
  `IdDia` int(11) NOT NULL,
  `DesdeHasta` tinyint(1) NOT NULL,
  `DetalleTransporte` varchar(140) NOT NULL,
  `DireccionLatitud` varchar(45) NOT NULL,
  `DireccionLongitud` varchar(45) NOT NULL,
  `Direccion` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `viajes`
--

INSERT INTO `viajes` (`IdViaje`, `DNI`, `IdHorario`, `IdTransporte`, `IdDia`, `DesdeHasta`, `DetalleTransporte`, `DireccionLatitud`, `DireccionLongitud`, `Direccion`) VALUES
(20, 0, 7, 1, 2, 0, '', '-34.6330017', '-58.4684348', 'Avenida San Pedrito 184'),
(14, 42225260, 1, 2, 1, 1, 'me tomo el 132 en carabobo y primera junta', '-34.6357067', '-58.4537327', 'Lautaro 791'),
(19, 22222222, 6, 2, 3, 1, 'subte A', '-34.6330017', '-58.4684348', 'Avenida San Pedrito 184'),
(18, 22222222, 1, 1, 5, 1, '', '-34.6263474', '-58.4466183', 'Valle 1352'),
(15, 42225260, 7, 2, 2, 0, 'me tomo el 132 en La Plata y Rivadavia', '-34.6357067', '-58.4537327', 'Lautaro 791'),
(16, 42586854, 0, 3, 4, 1, 'voy en subte A', '-34.6275953', '-58.4466156', 'Avenida Pedro Goyena 1378'),
(17, 42586854, 6, 5, 1, 1, 'voy caminando', '-34.5979814', '-58.4243076', 'Avenida Estado de Israel 4156'),
(21, 42225260, 4, 1, 3, 1, '', '-34.4873049', '-58.563743', 'Warnes 123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `amigos`
--
ALTER TABLE `amigos`
  ADD PRIMARY KEY (`IdDNIAmigos`);

--
-- Indexes for table `dias`
--
ALTER TABLE `dias`
  ADD PRIMARY KEY (`IdDia`);

--
-- Indexes for table `diasviajes`
--
ALTER TABLE `diasviajes`
  ADD PRIMARY KEY (`IdDiasViajes`);

--
-- Indexes for table `horarios`
--
ALTER TABLE `horarios`
  ADD PRIMARY KEY (`IdHorario`);

--
-- Indexes for table `transportes`
--
ALTER TABLE `transportes`
  ADD PRIMARY KEY (`IdTransporte`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`DNI`);

--
-- Indexes for table `viajes`
--
ALTER TABLE `viajes`
  ADD PRIMARY KEY (`IdViaje`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `amigos`
--
ALTER TABLE `amigos`
  MODIFY `IdDNIAmigos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `dias`
--
ALTER TABLE `dias`
  MODIFY `IdDia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `diasviajes`
--
ALTER TABLE `diasviajes`
  MODIFY `IdDiasViajes` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `horarios`
--
ALTER TABLE `horarios`
  MODIFY `IdHorario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `transportes`
--
ALTER TABLE `transportes`
  MODIFY `IdTransporte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `viajes`
--
ALTER TABLE `viajes`
  MODIFY `IdViaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
