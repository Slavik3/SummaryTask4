-- phpMyAdmin SQL Dump
-- version 3.2.3
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 24, 2015 at 05:12 PM
-- Server version: 5.1.40
-- PHP Version: 5.2.12

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `st4db`
--

-- --------------------------------------------------------

--
-- Table structure for table `discharged_patients`
--

CREATE TABLE IF NOT EXISTS `discharged_patients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `diagnosis` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=cp1251 AUTO_INCREMENT=50 ;

--
-- Dumping data for table `discharged_patients`
--

INSERT INTO `discharged_patients` (`id`, `first_name`, `last_name`, `birthday`, `doctor_id`, `diagnosis`) VALUES
(49, 'Дмитро', 'Тамаза', '2015-07-30', 2, 'Гайморит');

-- --------------------------------------------------------

--
-- Table structure for table `hospital_card`
--

CREATE TABLE IF NOT EXISTS `hospital_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `type_of_treatment_id` int(11) NOT NULL,
  `name_of_medication` varchar(40) NOT NULL,
  `done` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=cp1251 AUTO_INCREMENT=20 ;

--
-- Dumping data for table `hospital_card`
--

INSERT INTO `hospital_card` (`id`, `patient_id`, `type_of_treatment_id`, `name_of_medication`, `done`) VALUES
(1, 1, 2, 'Назол', 1),
(2, 1, 2, 'Назол', 0),
(3, 2, 2, 'Метионин', 0),
(4, 1, 3, 'прокол', 0),
(5, 26, 2, 'Диазалин', 0),
(6, 2, 2, 'Рибоксин', 0),
(7, 2, 2, 'Сирепар', 0),
(8, 2, 2, 'Тыквеол', 0),
(9, 28, 2, 'уголь активированный', 0),
(10, 28, 2, 'Линекс', 0),
(11, 28, 2, 'Регидрон', 0),
(12, 29, 1, 'Рентген', 0),
(13, 29, 2, 'Анальгин', 0),
(14, 30, 2, 'Йод', 0),
(15, 30, 1, 'Перевязка', 0),
(16, 31, 1, 'Вправление кисти', 0),
(17, 32, 2, 'Венитан', 0),
(18, 32, 2, 'Аэсцин Гель', 0),
(19, 33, 1, 'Занятие в тренажерном зале', 0);

-- --------------------------------------------------------

--
-- Table structure for table `patients`
--

CREATE TABLE IF NOT EXISTS `patients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `birthday` date DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `diagnosis` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=cp1251 AUTO_INCREMENT=34 ;

--
-- Dumping data for table `patients`
--

INSERT INTO `patients` (`id`, `first_name`, `last_name`, `birthday`, `doctor_id`, `diagnosis`) VALUES
(2, 'Олексій', 'Майор', '2015-07-08', 2, 'Желтуха'),
(26, 'Марья', 'Шахматова', '2015-08-13', 2, 'Аллергия'),
(1, 'Дмитро', 'Тамаза', '2015-07-30', 2, 'Гайморит'),
(28, 'Василий', 'Фидлер', '2015-08-04', 2, 'Отравление'),
(29, 'Александр', 'Петров', '2000-08-17', 8, 'Перелом носа'),
(30, 'Владимир', 'Фесенко', '2001-08-24', 8, 'Рана от гвоздя на руке'),
(31, 'Исаак', 'Варшавчик', '2000-08-04', 8, 'Вывих кисти'),
(32, 'Иван', 'Василенко', '1999-08-05', 3, 'Варикоз'),
(33, 'Мария', 'Абаза', '1998-08-20', 3, 'Скалеоз');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(0, 'admin'),
(1, 'doctor'),
(2, 'nurse');

-- --------------------------------------------------------

--
-- Table structure for table `specializations`
--

CREATE TABLE IF NOT EXISTS `specializations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=cp1251 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `specializations`
--

INSERT INTO `specializations` (`id`, `title`) VALUES
(1, 'Педиатр'),
(2, 'Травматолог'),
(3, 'Хирург');

-- --------------------------------------------------------

--
-- Table structure for table `type_of_treatment`
--

CREATE TABLE IF NOT EXISTS `type_of_treatment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=cp1251 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `type_of_treatment`
--

INSERT INTO `type_of_treatment` (`id`, `title`) VALUES
(1, 'Процедура'),
(2, 'Лекарство'),
(3, 'Операция');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(10) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `locale_name` varchar(20) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `specialization_id` int(11) DEFAULT NULL,
  `count_of_patients` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `first_name`, `last_name`, `locale_name`, `role_id`, `specialization_id`, `count_of_patients`) VALUES
(1, 'admin', 'admin', 'Ivan', 'Ivanov', NULL, 0, NULL, 0),
(2, 'doctor', 'doctor', 'Petr', 'Petrov', NULL, 1, 1, 4),
(3, 'so123', 'so123', 'Вячеслав', 'Соколов', NULL, 1, 3, 2),
(6, 'nurse', 'nurse', 'Светлана', 'Казакова', NULL, 2, NULL, NULL),
(7, 'nurse1', 'nurse2', 'Светлана', 'Казакова', NULL, 2, NULL, NULL),
(8, 'karl123', 'karl123', 'Карл', 'Шафранек', NULL, 1, 2, 3);
