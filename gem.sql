-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 26, 2021 at 06:34 PM
-- Server version: 8.0.21
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gem`
--

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `adresse` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tel` varchar(20) NOT NULL,
  `fax` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `contact` varchar(255) NOT NULL,
  `telcontact` varchar(20) NOT NULL,
  `valsync` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`id`, `nom`, `adresse`, `tel`, `fax`, `email`, `contact`, `telcontact`, `valsync`) VALUES
(1, 'Client 1', 'Ariana', '77250250', 'fax 23456', 'client@gmail.com', 'Contact@contact.com', '55205050', 1);

-- --------------------------------------------------------

--
-- Table structure for table `contrats`
--

DROP TABLE IF EXISTS `contrats`;
CREATE TABLE IF NOT EXISTS `contrats` (
  `id` int NOT NULL AUTO_INCREMENT,
  `datedebut` date NOT NULL,
  `datefin` date NOT NULL,
  `redevence` decimal(10,0) NOT NULL,
  `client_id` int NOT NULL,
  `valsync` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employes`
--

DROP TABLE IF EXISTS `employes`;
CREATE TABLE IF NOT EXISTS `employes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `actif` int NOT NULL,
  `valsync` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `employes`
--

INSERT INTO `employes` (`id`, `login`, `pwd`, `prenom`, `nom`, `email`, `actif`, `valsync`) VALUES
(1, 'test', 'test', 'nour', 'mahdi', 'nour.mahdi@gmail.com', 1, 1),
(2, 'login', 'pwd', 'test', 'test', 'test', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `employes_interventions`
--

DROP TABLE IF EXISTS `employes_interventions`;
CREATE TABLE IF NOT EXISTS `employes_interventions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employe_id` int NOT NULL,
  `intervention_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `employes_interventions`
--

INSERT INTO `employes_interventions` (`id`, `employe_id`, `intervention_id`) VALUES
(1, 1, 1),
(2, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
CREATE TABLE IF NOT EXISTS `images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` text NOT NULL,
  `img` longblob NOT NULL,
  `dateCapture` date NOT NULL,
  `intervention_id` int NOT NULL,
  `valsync` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `interventions`
--

DROP TABLE IF EXISTS `interventions`;
CREATE TABLE IF NOT EXISTS `interventions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) NOT NULL,
  `datedebut` varchar(255) NOT NULL,
  `datefin` varchar(255) NOT NULL,
  `heuredebutplan` varchar(250) NOT NULL,
  `heurefinplan` varchar(250) NOT NULL,
  `commentaires` text NOT NULL,
  `dateplanification` varchar(255) NOT NULL,
  `heuredebuteffect` time NOT NULL,
  `heurefineffect` time NOT NULL,
  `terminee` tinyint(1) NOT NULL,
  `dateterminaison` varchar(255) NOT NULL,
  `validee` tinyint(1) NOT NULL,
  `datevalidation` varchar(255) NOT NULL,
  `priorite_id` int NOT NULL,
  `site_id` int NOT NULL,
  `valsync` int NOT NULL DEFAULT '0',
  `id_user` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `interventions`
--

INSERT INTO `interventions` (`id`, `titre`, `datedebut`, `datefin`, `heuredebutplan`, `heurefinplan`, `commentaires`, `dateplanification`, `heuredebuteffect`, `heurefineffect`, `terminee`, `dateterminaison`, `validee`, `datevalidation`, `priorite_id`, `site_id`, `valsync`, `id_user`) VALUES
(2, 'Societe 2', '15/12/2021', '30/12/2021', '20:20:53', '22:20:53', 'Commentaire 2', '01/05/2021', '21:41:25', '21:41:25', 2, '01/08/2021', 1, '20/05/2021', 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `priorites`
--

DROP TABLE IF EXISTS `priorites`;
CREATE TABLE IF NOT EXISTS `priorites` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `valsync` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sites`
--

DROP TABLE IF EXISTS `sites`;
CREATE TABLE IF NOT EXISTS `sites` (
  `id` int NOT NULL AUTO_INCREMENT,
  `longitude` decimal(10,0) NOT NULL,
  `latitude` decimal(10,0) NOT NULL,
  `adresse` text NOT NULL,
  `rue` varchar(255) NOT NULL,
  `codepostal` int NOT NULL,
  `ville` varchar(255) NOT NULL,
  `contact` varchar(255) NOT NULL,
  `telcontact` varchar(20) NOT NULL,
  `client_id` int NOT NULL,
  `valsync` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `taches`
--

DROP TABLE IF EXISTS `taches`;
CREATE TABLE IF NOT EXISTS `taches` (
  `id` int NOT NULL AUTO_INCREMENT,
  `refernce` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `duree` varchar(255) NOT NULL,
  `prixheure` varchar(255) NOT NULL,
  `dateaction` varchar(250) NOT NULL,
  `intervention_id` int NOT NULL,
  `valsync` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `taches`
--

INSERT INTO `taches` (`id`, `refernce`, `nom`, `duree`, `prixheure`, `dateaction`, `intervention_id`, `valsync`) VALUES
(1, 'true', 'Task1', '8 heures', '15', '2021-12-28', 2, 1),
(2, 'false', 'Task2', '4 heures', '13', '2021-12-31', 2, 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
