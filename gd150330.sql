-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Sep 22, 2018 at 09:31 PM
-- Server version: 5.7.21
-- PHP Version: 5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `a`
--
CREATE DATABASE IF NOT EXISTS `a` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `a`;

-- --------------------------------------------------------

--
-- Table structure for table `adresa`
--

DROP TABLE IF EXISTS `adresa`;
CREATE TABLE IF NOT EXISTS `adresa` (
  `adresaId` int(11) NOT NULL AUTO_INCREMENT,
  `grad` varchar(30) NOT NULL,
  `opstina` varchar(30) NOT NULL,
  `ulica` varchar(30) NOT NULL,
  `broj` int(11) NOT NULL,
  PRIMARY KEY (`adresaId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `adresa`
--

INSERT INTO `adresa` (`adresaId`, `grad`, `opstina`, `ulica`, `broj`) VALUES
(1, 'Cacak', 'Cacak', 'Rajiceva', 17),
(7, 'Cacak', 'Cacak', 'Kneza Milosa', 100),
(8, 'Novi Pazar', 'Novi Pazar', 'Osmanagiceva', 23),
(10, 'Novi Sad', 'Novi Sad', 'Dimitrija Tucovica', 212),
(12, 'Beograd', 'Beograd', '1300 Kaplara', 1);

-- --------------------------------------------------------

--
-- Table structure for table `autobus`
--

DROP TABLE IF EXISTS `autobus`;
CREATE TABLE IF NOT EXISTS `autobus` (
  `autobusId` int(11) NOT NULL AUTO_INCREMENT,
  `marka` varchar(25) NOT NULL,
  `model` varchar(25) NOT NULL,
  `brojSedista` int(11) NOT NULL,
  PRIMARY KEY (`autobusId`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `autobus`
--

INSERT INTO `autobus` (`autobusId`, `marka`, `model`, `brojSedista`) VALUES
(17, 'Mercedes', 'M1000', 60),
(18, 'Mercedes', 'M1000', 60),
(19, 'Mercedes', 'M1000', 60),
(20, 'Mercedes', 'M1000', 60),
(21, 'Mercedes', 'M1000', 60),
(22, 'GreyHound', 'G202', 60),
(23, 'GreyHound', 'G202', 60),
(24, 'GreyHound', 'G202', 60),
(25, 'GreyHound', 'G202', 60),
(26, 'GreyHound', 'G202', 60),
(27, 'GreyHound', 'G202', 60),
(28, 'GreyHound', 'G202', 60),
(29, 'GreyHound', 'G500', 50),
(30, 'GreyHound', 'G500', 50),
(31, 'GreyHound', 'G500', 50),
(32, 'GreyHound', 'G500', 50),
(33, 'Solaris', 'S100', 50),
(34, 'Solaris', 'S100', 50),
(35, 'Solaris', 'S100', 50),
(36, 'Solaris', 'S100', 50),
(37, 'Solaris', 'S100', 50),
(38, 'Mercedes', 'MA12', 45),
(39, 'Mercedes', 'MA12', 45),
(40, 'Mercedes', 'MA12', 45);

-- --------------------------------------------------------

--
-- Table structure for table `autobus_slika`
--

DROP TABLE IF EXISTS `autobus_slika`;
CREATE TABLE IF NOT EXISTS `autobus_slika` (
  `autobus_slikaId` int(11) NOT NULL AUTO_INCREMENT,
  `autobusId` int(11) NOT NULL,
  `slikaId` int(11) NOT NULL,
  PRIMARY KEY (`autobus_slikaId`),
  KEY `fk_autobusId` (`autobusId`),
  KEY `fk_slikaId` (`slikaId`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `autobus_slika`
--

INSERT INTO `autobus_slika` (`autobus_slikaId`, `autobusId`, `slikaId`) VALUES
(15, 17, 7),
(16, 17, 8),
(17, 17, 9),
(18, 18, 7),
(19, 18, 8),
(20, 18, 9),
(21, 19, 7),
(22, 19, 8),
(23, 19, 9),
(24, 20, 7),
(25, 20, 8),
(26, 20, 9),
(27, 21, 7),
(28, 21, 8),
(29, 21, 9),
(30, 22, 14),
(31, 22, 15),
(32, 23, 14),
(33, 23, 15),
(34, 24, 14),
(35, 24, 15),
(36, 25, 14),
(37, 25, 15),
(38, 26, 14),
(39, 26, 15),
(40, 27, 14),
(41, 27, 15),
(42, 28, 14),
(43, 28, 15),
(44, 29, 16),
(45, 29, 17),
(46, 30, 16),
(47, 30, 17),
(48, 31, 16),
(49, 31, 17),
(50, 32, 16),
(51, 32, 17),
(52, 33, 18),
(53, 33, 18),
(54, 33, 19),
(55, 33, 19),
(56, 33, 20),
(57, 33, 20),
(58, 33, 21),
(59, 33, 21),
(60, 34, 18),
(61, 34, 18),
(62, 34, 19),
(63, 34, 19),
(64, 34, 20),
(65, 34, 20),
(66, 34, 21),
(67, 34, 21),
(68, 35, 18),
(69, 35, 18),
(70, 35, 19),
(71, 35, 19),
(72, 35, 20),
(73, 35, 20),
(74, 35, 21),
(75, 35, 21),
(76, 36, 18),
(77, 36, 18),
(78, 36, 19),
(79, 36, 19),
(80, 36, 20),
(81, 36, 20),
(82, 36, 21),
(83, 36, 21),
(84, 37, 18),
(85, 37, 18),
(86, 37, 19),
(87, 37, 19),
(88, 37, 20),
(89, 37, 20),
(90, 37, 21),
(91, 37, 21),
(92, 38, 10),
(93, 38, 11),
(94, 38, 12),
(95, 38, 13),
(96, 39, 10),
(97, 39, 11),
(98, 39, 12),
(99, 39, 13),
(100, 40, 10),
(101, 40, 11),
(102, 40, 12),
(103, 40, 13);

-- --------------------------------------------------------

--
-- Table structure for table `glinija`
--

DROP TABLE IF EXISTS `glinija`;
CREATE TABLE IF NOT EXISTS `glinija` (
  `glinijaId` int(11) NOT NULL AUTO_INCREMENT,
  `brojLinije` int(11) NOT NULL,
  `polaznoStajaliste` varchar(25) NOT NULL,
  `krajnjeStajaliste` varchar(25) NOT NULL,
  `aktivna` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`glinijaId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `glinija`
--

INSERT INTO `glinija` (`glinijaId`, `brojLinije`, `polaznoStajaliste`, `krajnjeStajaliste`, `aktivna`) VALUES
(1, 33, 'Kumodraz', 'Pancevacki most', 1),
(3, 27, 'Trg Republike', 'Mirijevo 3', 1),
(4, 4, 'Lion', 'Kalemegdan', 1),
(5, 9, 'Banjica', 'Blok 45', 1),
(6, 23, 'Vidikovac', 'Ada Ciganlija', 1),
(7, 54, 'Vukov spomenik', 'Mirijevo', 1),
(8, 66, 'Autokomanda', 'Bezanijska kosa', 1),
(9, 72, 'Slavija', 'Studentski grad', 1),
(10, 19, 'Banjica', 'Vidikovac', 1),
(11, 71, 'Vukov Spomenik', 'Cvetkova pijaca', 1),
(12, 39, 'Slavija', 'Konjarnik', 1),
(13, 42, 'Slavija', 'Banjica', 1),
(14, 40, 'Ada Huja', 'Zeleznicka', 1),
(15, 30, 'Miljakovac', 'Dusanovac', 1),
(16, 60, 'Blok 19', 'Blok 49', 1),
(17, 80, 'Blok 21', 'Zemun', 1),
(18, 81, 'Autokomanda', 'Lion', 1),
(19, 84, 'Slavija', 'Medakovic', 1),
(20, 90, 'Djeram', 'Tasmajdan', 1),
(21, 92, 'Slavija', 'Usce', 1),
(22, 100, 'Senjak', 'Banjica', 1);

-- --------------------------------------------------------

--
-- Table structure for table `glinija_redvoznje`
--

DROP TABLE IF EXISTS `glinija_redvoznje`;
CREATE TABLE IF NOT EXISTS `glinija_redvoznje` (
  `glinija_redvoznjeId` int(11) NOT NULL AUTO_INCREMENT,
  `glinijaId` int(11) NOT NULL,
  `redvoznjeId` int(11) NOT NULL,
  PRIMARY KEY (`glinija_redvoznjeId`),
  KEY `fk_glinijaId` (`glinijaId`),
  KEY `fk_redvoznjeId` (`redvoznjeId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `glinija_redvoznje`
--

INSERT INTO `glinija_redvoznje` (`glinija_redvoznjeId`, `glinijaId`, `redvoznjeId`) VALUES
(5, 1, 13),
(6, 3, 14),
(7, 4, 15),
(8, 5, 13),
(9, 6, 14),
(10, 7, 15),
(11, 8, 13),
(12, 9, 14),
(13, 10, 15),
(14, 11, 13),
(15, 12, 14),
(16, 13, 15),
(17, 17, 13),
(18, 18, 14),
(19, 19, 15),
(20, 20, 13),
(21, 21, 14),
(22, 22, 15);

-- --------------------------------------------------------

--
-- Table structure for table `glinija_stajaliste`
--

DROP TABLE IF EXISTS `glinija_stajaliste`;
CREATE TABLE IF NOT EXISTS `glinija_stajaliste` (
  `glinija_stajalisteId` int(11) NOT NULL AUTO_INCREMENT,
  `glinijaId` int(11) NOT NULL,
  `stajalisteId` int(11) NOT NULL,
  `redosled` int(11) NOT NULL,
  PRIMARY KEY (`glinija_stajalisteId`),
  KEY `fk_glinijaId` (`glinijaId`),
  KEY `fk_stajalisteId` (`stajalisteId`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `glinija_stajaliste`
--

INSERT INTO `glinija_stajaliste` (`glinija_stajalisteId`, `glinijaId`, `stajalisteId`, `redosled`) VALUES
(1, 1, 7, 1),
(2, 1, 6, 5),
(3, 1, 4, 2),
(4, 1, 12, 3),
(5, 1, 15, 4),
(6, 3, 9, 1),
(7, 3, 24, 2),
(8, 3, 23, 3),
(9, 3, 22, 4),
(10, 4, 21, 5),
(11, 4, 20, 1),
(12, 4, 23, 2),
(13, 4, 8, 3),
(14, 4, 10, 4),
(15, 5, 18, 1),
(16, 5, 4, 3),
(17, 5, 5, 4),
(18, 5, 19, 2),
(19, 5, 13, 6),
(20, 5, 14, 5),
(21, 6, 27, 1),
(22, 6, 28, 2),
(23, 6, 11, 3),
(24, 6, 29, 4),
(25, 7, 8, 1),
(26, 7, 24, 2),
(27, 7, 31, 3),
(28, 7, 32, 4),
(29, 8, 33, 1),
(30, 8, 34, 2),
(31, 8, 13, 3),
(32, 8, 35, 4),
(33, 9, 4, 1),
(34, 9, 5, 2),
(35, 9, 36, 3),
(36, 9, 14, 4),
(37, 9, 37, 5),
(38, 10, 18, 1),
(39, 10, 38, 2),
(40, 10, 39, 3),
(41, 11, 8, 1),
(42, 11, 23, 2),
(43, 11, 20, 3),
(44, 11, 40, 4),
(45, 12, 4, 1),
(46, 12, 43, 2),
(47, 12, 41, 3),
(48, 13, 4, 1),
(49, 13, 33, 2),
(50, 13, 42, 3),
(51, 13, 18, 4),
(52, 14, 30, 1),
(53, 14, 44, 2),
(54, 14, 45, 3),
(55, 14, 5, 4),
(56, 15, 38, 1),
(57, 15, 18, 2),
(58, 15, 47, 3),
(59, 15, 46, 4),
(60, 16, 34, 1),
(61, 16, 48, 2),
(62, 16, 13, 3),
(63, 16, 49, 4),
(64, 17, 50, 1),
(65, 17, 51, 2),
(66, 17, 52, 3),
(67, 17, 53, 4),
(68, 18, 33, 1),
(69, 18, 43, 2),
(70, 18, 20, 3),
(71, 19, 4, 1),
(72, 19, 33, 2),
(73, 19, 46, 3),
(74, 19, 54, 4),
(75, 20, 55, 1),
(76, 20, 8, 2),
(77, 20, 15, 3),
(78, 20, 56, 5),
(79, 21, 4, 1),
(80, 21, 5, 2),
(81, 21, 36, 3),
(82, 21, 14, 4),
(83, 22, 57, 1),
(84, 22, 58, 2),
(85, 22, 42, 3),
(86, 22, 18, 4);

-- --------------------------------------------------------

--
-- Table structure for table `glinija_vozac`
--

DROP TABLE IF EXISTS `glinija_vozac`;
CREATE TABLE IF NOT EXISTS `glinija_vozac` (
  `glinija_vozacId` int(11) NOT NULL AUTO_INCREMENT,
  `glinijaId` int(11) NOT NULL,
  `vozacId` int(11) NOT NULL,
  PRIMARY KEY (`glinija_vozacId`),
  KEY `fk_glinijaId` (`glinijaId`),
  KEY `fk_vozacId` (`vozacId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `glinija_vozac`
--

INSERT INTO `glinija_vozac` (`glinija_vozacId`, `glinijaId`, `vozacId`) VALUES
(1, 1, 2),
(2, 3, 24),
(3, 3, 24),
(4, 5, 25),
(5, 5, 25),
(6, 6, 26),
(7, 6, 26),
(8, 8, 27),
(9, 8, 27),
(10, 10, 28),
(11, 10, 28),
(12, 15, 29),
(13, 15, 29),
(14, 16, 30),
(15, 16, 30),
(16, 18, 31),
(17, 18, 31),
(18, 19, 32),
(19, 19, 32),
(20, 22, 33),
(21, 22, 33);

-- --------------------------------------------------------

--
-- Table structure for table `kartica`
--

DROP TABLE IF EXISTS `kartica`;
CREATE TABLE IF NOT EXISTS `kartica` (
  `karticaId` int(11) NOT NULL AUTO_INCREMENT,
  `korisnikId` int(11) NOT NULL,
  `oznaka` varchar(1) NOT NULL,
  `vaziOd` date NOT NULL,
  `vaziDo` date NOT NULL,
  `cena` decimal(10,0) NOT NULL,
  `stanje` tinyint(4) NOT NULL,
  PRIMARY KEY (`karticaId`),
  KEY `fk_korisnikId` (`korisnikId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
CREATE TABLE IF NOT EXISTS `korisnik` (
  `korisnikId` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(30) NOT NULL,
  `prezime` varchar(30) NOT NULL,
  `korisnickoIme` varchar(30) NOT NULL,
  `sifra` varchar(30) NOT NULL,
  `datumRodjenja` date NOT NULL,
  `telefon` varchar(20) NOT NULL,
  `kategorija` char(1) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  `odobren` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`korisnikId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`korisnikId`, `ime`, `prezime`, `korisnickoIme`, `sifra`, `datumRodjenja`, `telefon`, `kategorija`, `email`, `admin`, `odobren`) VALUES
(1, 'Radovan', 'Obradovic', 'radovanje', 'Raki123', '2001-01-03', '0669000192', 'z', 'rale00@gmail.com', 0, 1),
(2, 'Dimitrije', 'Glisic', 'kento', 'dimi96', '1996-11-08', '066/9000/192', 's', 'kento@gmail.com', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `korisnik_adresa`
--

DROP TABLE IF EXISTS `korisnik_adresa`;
CREATE TABLE IF NOT EXISTS `korisnik_adresa` (
  `korisnik_adresaId` int(11) NOT NULL AUTO_INCREMENT,
  `korisnikId` int(11) NOT NULL,
  `adresaId` int(11) NOT NULL,
  PRIMARY KEY (`korisnik_adresaId`),
  KEY `fk_korisnikId` (`korisnikId`),
  KEY `fk_adresaId` (`adresaId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `korisnik_adresa`
--

INSERT INTO `korisnik_adresa` (`korisnik_adresaId`, `korisnikId`, `adresaId`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `medjustanica`
--

DROP TABLE IF EXISTS `medjustanica`;
CREATE TABLE IF NOT EXISTS `medjustanica` (
  `medjustanicaId` int(11) NOT NULL AUTO_INCREMENT,
  `mlinijaId` int(11) NOT NULL,
  `mstanicaId` int(11) NOT NULL,
  `vremeDolaska` time NOT NULL,
  `redosled` int(11) NOT NULL,
  PRIMARY KEY (`medjustanicaId`),
  KEY `fk_mlinijaId` (`mlinijaId`),
  KEY `fk_mstanicaId` (`mstanicaId`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `medjustanica`
--

INSERT INTO `medjustanica` (`medjustanicaId`, `mlinijaId`, `mstanicaId`, `vremeDolaska`, `redosled`) VALUES
(1, 1, 1, '08:00:00', 1),
(2, 1, 11, '09:00:00', 3),
(3, 1, 14, '08:30:00', 2),
(4, 1, 6, '09:30:00', 4),
(5, 1, 5, '10:00:00', 5),
(6, 2, 3, '14:00:00', 2),
(7, 2, 9, '12:00:00', 1),
(8, 2, 1, '15:30:00', 3),
(9, 2, 2, '17:00:00', 4),
(10, 2, 4, '18:00:00', 5),
(11, 2, 10, '19:00:00', 6),
(24, 29, 16, '07:00:00', 1),
(25, 29, 17, '07:30:00', 2),
(26, 29, 18, '08:00:00', 3),
(27, 29, 27, '09:00:00', 4),
(28, 30, 4, '10:00:00', 1),
(29, 30, 8, '11:00:00', 2),
(30, 30, 26, '12:00:00', 3),
(31, 31, 6, '19:00:00', 1),
(32, 31, 7, '20:00:00', 2),
(33, 31, 28, '21:30:00', 3),
(34, 32, 1, '12:00:00', 1),
(35, 32, 15, '13:00:00', 2),
(36, 32, 20, '14:00:00', 3),
(37, 32, 21, '15:00:00', 4),
(38, 33, 5, '10:00:00', 1),
(39, 33, 29, '11:00:00', 2),
(40, 33, 30, '12:00:00', 3),
(41, 34, 1, '16:00:00', 1),
(42, 34, 2, '17:30:00', 2),
(43, 34, 7, '18:30:00', 3),
(44, 34, 23, '20:00:00', 4),
(45, 35, 32, '07:00:00', 1),
(46, 35, 33, '08:00:00', 2),
(47, 35, 31, '09:00:00', 3),
(48, 36, 3, '20:00:00', 1),
(49, 36, 34, '20:30:00', 2),
(50, 36, 35, '21:30:00', 3),
(51, 37, 2, '15:00:00', 1),
(52, 37, 18, '16:00:00', 2),
(53, 37, 22, '17:00:00', 3),
(54, 38, 33, '09:00:00', 1),
(55, 38, 31, '10:00:00', 2),
(56, 38, 5, '11:00:00', 3),
(57, 38, 2, '12:00:00', 4),
(58, 39, 1, '11:00:00', 1),
(59, 39, 3, '12:00:00', 2),
(60, 39, 36, '14:00:00', 3),
(61, 39, 9, '15:00:00', 4),
(62, 40, 1, '06:00:00', 1),
(63, 40, 13, '07:00:00', 2),
(64, 40, 37, '08:00:00', 3),
(65, 40, 2, '09:00:00', 4),
(66, 41, 26, '10:00:00', 1),
(67, 41, 8, '11:00:00', 2),
(68, 41, 10, '12:00:00', 3),
(69, 42, 24, '08:00:00', 1),
(70, 42, 23, '09:00:00', 2),
(71, 42, 7, '10:00:00', 3),
(72, 42, 5, '11:00:00', 4),
(73, 42, 1, '13:00:00', 5),
(74, 43, 5, '09:00:00', 1),
(75, 43, 6, '09:30:00', 2),
(76, 43, 37, '10:30:00', 3),
(77, 43, 13, '11:30:00', 4),
(78, 44, 1, '07:00:00', 1),
(79, 44, 31, '09:00:00', 2),
(80, 44, 29, '10:00:00', 3),
(81, 44, 30, '10:30:00', 4),
(82, 45, 1, '13:00:00', 1),
(83, 45, 15, '14:00:00', 2),
(84, 45, 19, '15:00:00', 3),
(85, 45, 20, '16:00:00', 4),
(86, 46, 1, '09:00:00', 1),
(87, 46, 34, '10:00:00', 2),
(88, 46, 38, '11:00:00', 3),
(89, 47, 4, '06:00:00', 1),
(90, 47, 27, '07:00:00', 2),
(91, 47, 7, '08:00:00', 3),
(92, 47, 5, '09:00:00', 4);

-- --------------------------------------------------------

--
-- Table structure for table `mlinija`
--

DROP TABLE IF EXISTS `mlinija`;
CREATE TABLE IF NOT EXISTS `mlinija` (
  `mlinijaId` int(11) NOT NULL AUTO_INCREMENT,
  `polaziste` varchar(30) NOT NULL,
  `odrediste` varchar(30) NOT NULL,
  `datum` date NOT NULL,
  `vremePolaska` time NOT NULL,
  `vremeDolaska` time NOT NULL,
  PRIMARY KEY (`mlinijaId`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mlinija`
--

INSERT INTO `mlinija` (`mlinijaId`, `polaziste`, `odrediste`, `datum`, `vremePolaska`, `vremeDolaska`) VALUES
(1, 'Beograd', 'Kraljevo', '2018-09-25', '08:00:00', '10:00:00'),
(2, 'Subotica', 'Pirot', '2018-09-29', '12:00:00', '19:00:00'),
(29, 'Jagodina', 'Krusevac', '2018-09-27', '07:00:00', '09:00:00'),
(30, 'Nis', 'Vranje', '2018-09-25', '10:00:00', '12:00:00'),
(31, 'Gornji Milanovac', 'Kopaonik', '2018-09-27', '19:00:00', '21:30:00'),
(32, 'Beograd', 'Negotin', '2018-09-28', '12:00:00', '15:00:00'),
(33, 'Cacak', 'Zlatibor', '2018-09-30', '10:00:00', '12:00:00'),
(34, 'Beograd', 'Raska', '2018-09-28', '16:00:00', '20:00:00'),
(35, 'Sabac', 'Valjevo', '2018-10-01', '07:00:00', '09:00:00'),
(36, 'Novi Sad', 'Vrsac', '2018-10-01', '20:00:00', '21:30:00'),
(37, 'Kragujevac', 'Zajecar', '2018-10-02', '15:00:00', '17:00:00'),
(38, 'Loznica', 'Kragujevac', '2018-09-26', '09:00:00', '12:00:00'),
(39, 'Beograd', 'Subotica', '2018-09-29', '11:00:00', '15:00:00'),
(40, 'Beograd', 'Kragujevac', '2018-09-26', '06:00:00', '09:00:00'),
(41, 'Vranje', 'Pirot', '2018-09-25', '10:00:00', '12:00:00'),
(42, 'Novi Pazar', 'Beograd', '2018-09-29', '08:00:00', '13:00:00'),
(43, 'Cacak', 'Mladenovac', '2018-10-02', '09:00:00', '11:30:00'),
(44, 'Beograd', 'Zlatibor', '2018-09-27', '07:00:00', '10:30:00'),
(45, 'Beograd', 'Majdanpek', '2018-09-29', '13:00:00', '16:00:00'),
(46, 'Beograd', 'Kikinda', '2018-10-02', '09:00:00', '11:00:00'),
(47, 'Nis', 'Cacak', '2018-09-30', '06:00:00', '09:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `mlinija_autobus`
--

DROP TABLE IF EXISTS `mlinija_autobus`;
CREATE TABLE IF NOT EXISTS `mlinija_autobus` (
  `mlinija_autobusId` int(11) NOT NULL AUTO_INCREMENT,
  `mlinijaId` int(11) NOT NULL,
  `autobusId` int(11) NOT NULL,
  PRIMARY KEY (`mlinija_autobusId`),
  KEY `fk_mlinijaId` (`mlinijaId`),
  KEY `fk_autobusId` (`autobusId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mlinija_autobus`
--

INSERT INTO `mlinija_autobus` (`mlinija_autobusId`, `mlinijaId`, `autobusId`) VALUES
(6, 1, 17),
(7, 2, 18),
(8, 29, 20),
(9, 30, 21),
(10, 31, 22),
(11, 32, 23),
(12, 33, 19),
(13, 35, 24),
(14, 36, 25),
(15, 37, 26),
(16, 38, 27),
(17, 39, 28),
(18, 40, 29),
(19, 41, 30),
(20, 42, 31),
(21, 43, 32),
(22, 44, 33),
(23, 45, 34),
(24, 46, 35),
(25, 47, 36);

-- --------------------------------------------------------

--
-- Table structure for table `mlinija_prevoznik`
--

DROP TABLE IF EXISTS `mlinija_prevoznik`;
CREATE TABLE IF NOT EXISTS `mlinija_prevoznik` (
  `mlinija_prevoznikId` int(11) NOT NULL AUTO_INCREMENT,
  `mlinijaId` int(11) NOT NULL,
  `prevoznikId` int(11) NOT NULL,
  PRIMARY KEY (`mlinija_prevoznikId`),
  KEY `fk_mlinijaId` (`mlinijaId`),
  KEY `fk_prevoznikId` (`prevoznikId`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `mlinija_vozac`
--

DROP TABLE IF EXISTS `mlinija_vozac`;
CREATE TABLE IF NOT EXISTS `mlinija_vozac` (
  `mlinija_vozacId` int(11) NOT NULL AUTO_INCREMENT,
  `mlinijaId` int(11) NOT NULL,
  `vozacId` int(11) NOT NULL,
  PRIMARY KEY (`mlinija_vozacId`),
  KEY `fk_mlinijaId` (`mlinijaId`),
  KEY `fk_vozacId` (`vozacId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mlinija_vozac`
--

INSERT INTO `mlinija_vozac` (`mlinija_vozacId`, `mlinijaId`, `vozacId`) VALUES
(1, 1, 1),
(2, 2, 3),
(6, 29, 2),
(7, 30, 4),
(8, 31, 5),
(9, 32, 6),
(10, 33, 7),
(11, 34, 11),
(12, 35, 12),
(13, 36, 13),
(14, 37, 14),
(15, 38, 15),
(16, 39, 16),
(17, 40, 17),
(18, 41, 18),
(19, 42, 19),
(20, 44, 20),
(21, 45, 21),
(22, 46, 22),
(23, 47, 23);

-- --------------------------------------------------------

--
-- Table structure for table `mstanica`
--

DROP TABLE IF EXISTS `mstanica`;
CREATE TABLE IF NOT EXISTS `mstanica` (
  `mstanicaId` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(30) NOT NULL,
  `gsirina` decimal(10,6) NOT NULL,
  `gduzina` decimal(10,6) NOT NULL,
  PRIMARY KEY (`mstanicaId`),
  UNIQUE KEY `naziv` (`naziv`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mstanica`
--

INSERT INTO `mstanica` (`mstanicaId`, `naziv`, `gsirina`, `gduzina`) VALUES
(1, 'Beograd', '44.776000', '20.501200'),
(2, 'Kragujevac', '44.001190', '20.909385'),
(3, 'Novi Sad', '45.246251', '19.848000'),
(4, 'Nis', '43.318419', '21.891816'),
(5, 'Cacak', '43.828721', '20.348911'),
(6, 'Gornji Milanovac', '44.025132', '20.458525'),
(7, 'Kraljevo', '43.726727', '20.689900'),
(8, 'Leskovac', '42.993704', '21.946240'),
(9, 'Subotica', '46.080656', '19.655550'),
(10, 'Pirot', '43.160220', '22.588772'),
(11, 'Ljig', '44.224400', '20.238000'),
(12, 'Vrnjacka Banja', '43.617512', '20.891329'),
(13, 'Mladenovac', '44.437950', '20.694195'),
(14, 'Stepojevac', '44.510978', '20.294175'),
(15, 'Smederevo', '44.657941', '20.929750'),
(16, 'Jagodina', '43.983598', '21.261297'),
(17, 'Ćuprija', '43.935023', '21.374228'),
(18, 'Paraćin', '43.866777', '21.405995'),
(19, 'Požarevac', '44.612172', '21.175363'),
(20, 'Majdanpek', '44.424023', '21.942828'),
(21, 'Negotin', '44.227436', '21.525621'),
(22, 'Zajecar', '43.911507', '22.277578'),
(23, 'Raska', '43.294692', '20.625350'),
(24, 'Novi Pazar', '43.147446', '20.518823'),
(25, 'Priština', '42.671679', '21.164126'),
(26, 'Vranje', '42.555195', '21.908154'),
(27, 'Kruševac', '43.587676', '21.340339'),
(28, 'Kopaonik', '43.275664', '20.787752'),
(29, 'Uzice', '43.855467', '19.835809'),
(30, 'Zlatibor', '43.729197', '19.707912'),
(31, 'Valjevo', '44.270000', '19.883725'),
(32, 'Sabac', '44.756776', '19.699871'),
(33, 'Loznica', '44.531347', '19.219592'),
(34, 'Zrenjanin', '45.394342', '20.369587'),
(35, 'Vrsac', '45.114358', '21.285919'),
(36, 'Sombor', '45.765165', '19.114145'),
(37, 'Topola', '44.253560', '20.683354'),
(38, 'Kikinda', '45.801432', '20.429143');

-- --------------------------------------------------------

--
-- Table structure for table `otkazane_linije`
--

DROP TABLE IF EXISTS `otkazane_linije`;
CREATE TABLE IF NOT EXISTS `otkazane_linije` (
  `otkazane_linijeId` int(11) NOT NULL AUTO_INCREMENT,
  `glinijaId` int(11) NOT NULL,
  `otkazanaDo` datetime NOT NULL,
  PRIMARY KEY (`otkazane_linijeId`),
  KEY `fk_glinijaId` (`glinijaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `prevoznik`
--

DROP TABLE IF EXISTS `prevoznik`;
CREATE TABLE IF NOT EXISTS `prevoznik` (
  `prevoznikId` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(25) NOT NULL,
  `telefon` varchar(25) NOT NULL,
  `logo` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`prevoznikId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `prevoznik`
--

INSERT INTO `prevoznik` (`prevoznikId`, `naziv`, `telefon`, `logo`) VALUES
(1, 'GMTrans', '032/711/456', 'gmg.jpg'),
(6, 'Autoprevoz Cacak', '063254901', NULL),
(7, 'Benko Tours', '062222333', NULL),
(8, 'NS Trans', '063333222', NULL),
(9, 'BG Prevoz', '066999222', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `prevoznik_adresa`
--

DROP TABLE IF EXISTS `prevoznik_adresa`;
CREATE TABLE IF NOT EXISTS `prevoznik_adresa` (
  `prevoznik_adresaId` int(11) NOT NULL AUTO_INCREMENT,
  `prevoznikId` int(11) NOT NULL,
  `adresaId` int(11) NOT NULL,
  PRIMARY KEY (`prevoznik_adresaId`),
  KEY `fk_prevoznikId` (`prevoznikId`),
  KEY `fk_adresaId` (`adresaId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `prevoznik_adresa`
--

INSERT INTO `prevoznik_adresa` (`prevoznik_adresaId`, `prevoznikId`, `adresaId`) VALUES
(3, 6, 7),
(4, 7, 8),
(5, 8, 10),
(6, 9, 12);

-- --------------------------------------------------------

--
-- Table structure for table `redvoznje`
--

DROP TABLE IF EXISTS `redvoznje`;
CREATE TABLE IF NOT EXISTS `redvoznje` (
  `redvoznjeId` int(11) NOT NULL AUTO_INCREMENT,
  `intervalrd` varchar(15) NOT NULL,
  `intervals` varchar(15) NOT NULL,
  `intervaln` varchar(15) NOT NULL,
  `predvidjenoVremeVoznje` int(11) NOT NULL,
  PRIMARY KEY (`redvoznjeId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `redvoznje`
--

INSERT INTO `redvoznje` (`redvoznjeId`, `intervalrd`, `intervals`, `intervaln`, `predvidjenoVremeVoznje`) VALUES
(13, '5-15', '10-20', '10-20', 20),
(14, '10-15', '15-25', '15-30', 15),
(15, '5-15', '10-20', '10-20', 30);

-- --------------------------------------------------------

--
-- Table structure for table `rezervacija`
--

DROP TABLE IF EXISTS `rezervacija`;
CREATE TABLE IF NOT EXISTS `rezervacija` (
  `rezervacijaId` int(11) NOT NULL AUTO_INCREMENT,
  `korisnikId` int(11) NOT NULL,
  `mlinijaId` int(11) NOT NULL,
  `stanje` tinyint(4) NOT NULL,
  PRIMARY KEY (`rezervacijaId`),
  KEY `fk_korisnikId` (`korisnikId`),
  KEY `fk_mlinijaId` (`mlinijaId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `slika`
--

DROP TABLE IF EXISTS `slika`;
CREATE TABLE IF NOT EXISTS `slika` (
  `slikaId` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(25) NOT NULL,
  PRIMARY KEY (`slikaId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `slika`
--

INSERT INTO `slika` (`slikaId`, `naziv`) VALUES
(7, 'a1.jpg'),
(8, 'a2.jpg'),
(9, 'a3.jpg'),
(10, 'a4.jpg'),
(11, 'a5.jpg'),
(12, 'a6.jpg'),
(13, 'a7.jpg'),
(14, 'g1.jpg'),
(15, 'g2.jpg'),
(16, 'g3.jpg'),
(17, 'g4.jpg'),
(18, 's1.jpg'),
(19, 's2.jpg'),
(20, 's3.jpg'),
(21, 's4.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `stajaliste`
--

DROP TABLE IF EXISTS `stajaliste`;
CREATE TABLE IF NOT EXISTS `stajaliste` (
  `stajalisteId` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(25) NOT NULL,
  PRIMARY KEY (`stajalisteId`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `stajaliste`
--

INSERT INTO `stajaliste` (`stajalisteId`, `naziv`) VALUES
(1, 'Zeleni venac'),
(2, 'Zemun'),
(3, 'Novi grad'),
(4, 'Slavija'),
(5, 'Zeleznicka stanica'),
(6, 'Pancevacki most'),
(7, 'Kumodraz'),
(8, 'Vukov spomenik'),
(9, 'Trg republike'),
(10, 'Terazije'),
(11, 'Banovo brdo'),
(12, 'Pravni fakultet'),
(13, 'Blok 45'),
(14, 'Usce'),
(15, '27. Marta'),
(16, 'Brankov most'),
(17, 'Ustanicka'),
(18, 'Banjica'),
(19, 'Hram Svetog Save'),
(20, 'Lion'),
(21, 'Kalemegdan'),
(22, 'Mirijevo 3'),
(23, 'Bulevar kralja Aleksandra'),
(24, 'Ruzveltova'),
(25, 'Sava Centar'),
(26, 'Brankov Most'),
(27, 'Vidikovac'),
(28, 'Zarkovo'),
(29, 'Ada ciganlija'),
(30, 'Ada Huja'),
(31, 'Karaburma'),
(32, 'Mirijevo'),
(33, 'Autokomanda'),
(34, 'Blok 19'),
(35, 'Bezanijska kosa'),
(36, 'Ekonomski fakultet'),
(37, 'Studentski grad'),
(38, 'Miljakovac'),
(39, 'Vidikovac'),
(40, 'Cvetkova pijaca'),
(41, 'Konjarnik'),
(42, 'Dedinje'),
(43, 'Crveni krst'),
(44, 'Dorcol'),
(45, 'Savamala'),
(46, 'Dusanovac'),
(47, 'Brace Jerkovic'),
(48, 'Blok 70'),
(49, 'Blok 49'),
(50, 'Blok 21'),
(51, 'Arena'),
(52, 'Tosin bunar'),
(53, 'Zemun'),
(54, 'Medakovic'),
(55, 'Djeram'),
(56, 'Tasmajdan'),
(57, 'Senjak'),
(58, 'Topcider');

-- --------------------------------------------------------

--
-- Table structure for table `vozac`
--

DROP TABLE IF EXISTS `vozac`;
CREATE TABLE IF NOT EXISTS `vozac` (
  `vozacId` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(25) NOT NULL,
  `prezime` varchar(25) NOT NULL,
  `datumRodjenja` date NOT NULL,
  `datumZaposlenja` date NOT NULL,
  PRIMARY KEY (`vozacId`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `vozac`
--

INSERT INTO `vozac` (`vozacId`, `ime`, `prezime`, `datumRodjenja`, `datumZaposlenja`) VALUES
(1, 'Mile', 'Krtica', '1965-09-12', '1985-01-01'),
(2, 'Mladen', 'Popovic', '1996-04-23', '2017-02-15'),
(3, 'Nemanja', 'Jovanovic', '1984-02-15', '2005-07-01'),
(4, 'Veljko', 'Petrovic', '1964-02-22', '1987-01-01'),
(5, 'Lazar', 'Smiljanic', '1988-08-12', '2010-09-01'),
(6, 'Nikola', 'Lazic', '1993-03-05', '2013-10-11'),
(7, 'Filip', 'Petrovic', '1995-04-10', '2014-01-03'),
(11, 'Nikola', 'Lukic', '1983-02-22', '2000-02-12'),
(12, 'Radoslav', 'Jovanovic', '1987-09-22', '2007-01-01'),
(13, 'Milan', 'Maric', '1991-12-03', '2011-01-03'),
(14, 'Petar', 'Petrovic', '1975-11-03', '1994-02-11'),
(15, 'Lazar', 'Lazic', '1978-09-08', '2000-04-13'),
(16, 'Veljko', 'Veljkovic', '1969-03-02', '1990-09-01'),
(17, 'Dimitrije', 'Dimitrijevic', '1993-05-05', '2014-03-02'),
(18, 'Aleksa', 'Aleksic', '1989-08-22', '2014-04-04'),
(19, 'Kosta', 'Lazic', '1983-03-03', '2009-12-01'),
(20, 'Andrija', 'Nikolic', '1979-02-04', '1999-01-01'),
(21, 'Bojan', 'Lazarevic', '1969-03-15', '1987-01-01'),
(22, 'Miloje', 'Milojevic', '1980-01-07', '2000-03-01'),
(23, 'Aleksandar', 'Aleksandric', '1990-09-12', '2010-01-02'),
(24, 'Djordje', 'Sarcevic', '1996-04-01', '2016-07-12'),
(25, 'Petar', 'Petrovic', '1994-10-02', '2012-03-12'),
(26, 'Pavle', 'Pavlovic', '1995-11-08', '2020-01-14'),
(27, 'Djordje', 'Djordjevic', '1993-05-05', '2013-02-02'),
(28, 'Marko', 'Markovic', '1992-03-01', '2015-01-22'),
(29, 'Djordje', 'Markovic', '1990-01-01', '2010-01-01'),
(30, 'Marko', 'Djordjevic', '1985-05-15', '2012-12-01'),
(31, 'Luka', 'Lukic', '1989-05-23', '2011-04-22'),
(32, 'Kosta', 'Kostic', '1980-01-02', '2001-03-04'),
(33, 'Luka', 'Kostic', '1970-07-17', '1989-11-01');

-- --------------------------------------------------------

--
-- Table structure for table `zahtev_kartica`
--

DROP TABLE IF EXISTS `zahtev_kartica`;
CREATE TABLE IF NOT EXISTS `zahtev_kartica` (
  `zahtev_karticaId` int(11) NOT NULL AUTO_INCREMENT,
  `karticaId` int(11) NOT NULL,
  PRIMARY KEY (`zahtev_karticaId`),
  KEY `fk_karticaId` (`karticaId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `zahtev_registracija`
--

DROP TABLE IF EXISTS `zahtev_registracija`;
CREATE TABLE IF NOT EXISTS `zahtev_registracija` (
  `zahtev_registracijaId` int(11) NOT NULL AUTO_INCREMENT,
  `korisnikId` int(11) NOT NULL,
  PRIMARY KEY (`zahtev_registracijaId`),
  KEY `fk_userId` (`korisnikId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `zahtev_rezervacija`
--

DROP TABLE IF EXISTS `zahtev_rezervacija`;
CREATE TABLE IF NOT EXISTS `zahtev_rezervacija` (
  `zahtev_rezervacijaId` int(11) NOT NULL AUTO_INCREMENT,
  `rezervacijaId` int(11) NOT NULL,
  PRIMARY KEY (`zahtev_rezervacijaId`),
  KEY `fk_rezervacijaId` (`rezervacijaId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `kartica`
--
ALTER TABLE `kartica`
  ADD CONSTRAINT `kartica_ibfk_1` FOREIGN KEY (`korisnikId`) REFERENCES `korisnik` (`korisnikId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `mlinija_vozac`
--
ALTER TABLE `mlinija_vozac`
  ADD CONSTRAINT `mlinija_vozac_ibfk_1` FOREIGN KEY (`mlinijaId`) REFERENCES `mlinija` (`mlinijaId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `mlinija_vozac_ibfk_2` FOREIGN KEY (`vozacId`) REFERENCES `vozac` (`vozacId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `otkazane_linije`
--
ALTER TABLE `otkazane_linije`
  ADD CONSTRAINT `otkazane_linije_ibfk_1` FOREIGN KEY (`glinijaId`) REFERENCES `glinija` (`glinijaId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `prevoznik_adresa`
--
ALTER TABLE `prevoznik_adresa`
  ADD CONSTRAINT `prevoznik_adresa_ibfk_1` FOREIGN KEY (`prevoznikId`) REFERENCES `prevoznik` (`prevoznikId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `prevoznik_adresa_ibfk_2` FOREIGN KEY (`adresaId`) REFERENCES `adresa` (`adresaId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rezervacija`
--
ALTER TABLE `rezervacija`
  ADD CONSTRAINT `rezervacija_ibfk_1` FOREIGN KEY (`korisnikId`) REFERENCES `korisnik` (`korisnikId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rezervacija_ibfk_2` FOREIGN KEY (`mlinijaId`) REFERENCES `mlinija` (`mlinijaId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `zahtev_kartica`
--
ALTER TABLE `zahtev_kartica`
  ADD CONSTRAINT `zahtev_kartica_ibfk_1` FOREIGN KEY (`karticaId`) REFERENCES `kartica` (`karticaId`);

--
-- Constraints for table `zahtev_registracija`
--
ALTER TABLE `zahtev_registracija`
  ADD CONSTRAINT `zahtev_registracija_ibfk_1` FOREIGN KEY (`korisnikId`) REFERENCES `korisnik` (`korisnikId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `zahtev_rezervacija`
--
ALTER TABLE `zahtev_rezervacija`
  ADD CONSTRAINT `zahtev_rezervacija_ibfk_1` FOREIGN KEY (`rezervacijaId`) REFERENCES `rezervacija` (`rezervacijaId`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
