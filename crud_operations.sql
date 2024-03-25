-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 25, 2024 at 07:46 AM
-- Server version: 10.10.2-MariaDB
-- PHP Version: 8.1.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `crud_operations`
--

-- --------------------------------------------------------

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
CREATE TABLE IF NOT EXISTS `classes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`id`, `created_at`, `created_by`, `name`, `updated_at`, `updated_by`) VALUES
(4, NULL, NULL, NULL, NULL, NULL),
(5, NULL, NULL, NULL, NULL, NULL),
(6, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `class_teacher`
--

DROP TABLE IF EXISTS `class_teacher`;
CREATE TABLE IF NOT EXISTS `class_teacher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `class_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfh695osd6ugijlg3hb6nd50` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `class_teacher`
--

INSERT INTO `class_teacher` (`id`, `created_at`, `created_by`, `name`, `updated_at`, `updated_by`, `class_id`) VALUES
(2, NULL, NULL, 'krishna', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
CREATE TABLE IF NOT EXISTS `students` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `about_me` varchar(255) DEFAULT NULL,
  `address_line1` varchar(255) DEFAULT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `date_of_birth` datetime(6) DEFAULT NULL,
  `gender` int(11) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `class_id` bigint(20) DEFAULT NULL,
  `class_teacher_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhnslh0rm5bthlble8vjunbnwe` (`class_id`),
  KEY `FK6upcnk3uxoquk7ddg06aahfvf` (`class_teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`id`, `about_me`, `address_line1`, `address_line2`, `city`, `created_at`, `created_by`, `date_of_birth`, `gender`, `is_active`, `name`, `updated_at`, `updated_by`, `class_id`, `class_teacher_id`) VALUES
(2, 'student', '132 ecr', 'trunk road', 'chennai', NULL, NULL, '2024-03-01 09:16:31.000000', 1, b'0', 'krishna', NULL, NULL, NULL, NULL);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `class_teacher`
--
ALTER TABLE `class_teacher`
  ADD CONSTRAINT `FKfh695osd6ugijlg3hb6nd50` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`);

--
-- Constraints for table `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `FK6upcnk3uxoquk7ddg06aahfvf` FOREIGN KEY (`class_teacher_id`) REFERENCES `class_teacher` (`id`),
  ADD CONSTRAINT `FKhnslh0rm5bthlble8vjunbnwe` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
