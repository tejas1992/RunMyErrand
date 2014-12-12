-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 12, 2014 at 10:43 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `runmyerrand`
--

-- --------------------------------------------------------

--
-- Table structure for table `authority`
--

CREATE TABLE IF NOT EXISTS `authority` (
  `username` varchar(50) NOT NULL,
  `role` varchar(20) NOT NULL,
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authority`
--

INSERT INTO `authority` (`username`, `role`) VALUES
('abhino@gmail.com', 'ROLE_USER'),
('admin@admin.com', 'ROLE_ADMIN'),
('jmodi@indiana.edu', 'ROLE_USER'),
('njagasia@indiana.edu', 'ROLE_USER'),
('pvijaya@indiana.edu', 'ROLE_USER'),
('shah_tejas92@yahoo.co.in', 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `mastertask`
--

CREATE TABLE IF NOT EXISTS `mastertask` (
  `masterid` int(20) NOT NULL AUTO_INCREMENT,
  `mastertaskdesc` varchar(50) NOT NULL,
  `room` varchar(50) NOT NULL,
  `points` double NOT NULL,
  `Defaultdays` int(10) NOT NULL,
  PRIMARY KEY (`masterid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=97 ;

--
-- Dumping data for table `mastertask`
--

INSERT INTO `mastertask` (`masterid`, `mastertaskdesc`, `room`, `points`, `Defaultdays`) VALUES
(93, 'Clean the Car', '910N', 15, 16),
(94, 'Dishes', '910N', 20, 2),
(95, 'Pay the Rent', '910N', 20, 11),
(96, 'Take out the trash', '910N', 10, 16);

-- --------------------------------------------------------

--
-- Table structure for table `roominfo`
--

CREATE TABLE IF NOT EXISTS `roominfo` (
  `room` varchar(50) NOT NULL,
  `totalpoints` float NOT NULL,
  `members` int(10) NOT NULL,
  PRIMARY KEY (`room`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roominfo`
--

INSERT INTO `roominfo` (`room`, `totalpoints`, `members`) VALUES
('910N', 0, 5);

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `taskid` int(10) NOT NULL AUTO_INCREMENT,
  `masterid` int(50) NOT NULL,
  `TaskDescription` varchar(100) NOT NULL,
  `points` float NOT NULL,
  `Start_Date` date NOT NULL,
  `End_Date` date NOT NULL,
  `completed` tinyint(5) NOT NULL,
  `useremail` varchar(50) DEFAULT NULL,
  `room` varchar(50) NOT NULL,
  `recurrence` varchar(20) DEFAULT ' no',
  `recurrenable` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`taskid`),
  KEY `userid` (`useremail`),
  KEY `masterid` (`masterid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=148 ;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`taskid`, `masterid`, `TaskDescription`, `points`, `Start_Date`, `End_Date`, `completed`, `useremail`, `room`, `recurrence`, `recurrenable`) VALUES
(144, 93, 'Clean the Car', 15, '2014-12-13', '2014-12-29', 0, NULL, '910N', 'no', 0),
(145, 94, 'Dishes', 20, '2014-12-13', '2014-12-15', 0, NULL, '910N', 'no', 0),
(146, 95, 'Pay the Rent', 20, '2014-12-13', '2014-12-24', 0, NULL, '910N', 'no', 0),
(147, 96, 'Take out the trash', 10, '2014-12-13', '2014-12-29', 0, NULL, '910N', 'weekly', 0);

-- --------------------------------------------------------

--
-- Table structure for table `timebox`
--

CREATE TABLE IF NOT EXISTS `timebox` (
  `current` date NOT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL,
  PRIMARY KEY (`current`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `timebox`
--

INSERT INTO `timebox` (`current`, `start`, `end`) VALUES
('2014-12-11', '2014-12-07', '2014-12-14');

-- --------------------------------------------------------

--
-- Table structure for table `userinfo`
--

CREATE TABLE IF NOT EXISTS `userinfo` (
  `userid` int(20) NOT NULL AUTO_INCREMENT,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `dob` date NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` bigint(10) NOT NULL,
  `room` varchar(10) NOT NULL,
  `score` float NOT NULL DEFAULT '0',
  `pending_score` float NOT NULL DEFAULT '0',
  `weeklygoal` float NOT NULL DEFAULT '25',
  PRIMARY KEY (`userid`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `userinfo`
--

INSERT INTO `userinfo` (`userid`, `fname`, `lname`, `sex`, `dob`, `email`, `phone`, `room`, `score`, `pending_score`, `weeklygoal`) VALUES
(1, 'Tejas', 'Shah', 'male', '2014-12-08', 'shah_tejas92@yahoo.co.in', 8123696631, '910N', 0, 11.7, 25),
(2, 'Abhinav', 'Vijaykumar', 'male', '2014-12-09', 'abhino@gmail.com', 124235244, '910N', 0, 11.7, 25),
(3, 'Namrata', 'Jagasia', 'female', '1991-12-01', 'njagasia@indiana.edu', 8329047753, '910N', 0, 11.7, 25),
(4, 'Purnima', 'Vijaya', 'female', '1990-12-01', 'pvijaya@indiana.edu', 8326664453, '910N', 0, 11.7, 25),
(5, 'Jai', 'Modi', 'male', '2014-12-17', 'jmodi@indiana.edu', 8326664453, '910N', 0, 11.7, 25);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
('abhino@gmail.com', 'abcd', 1),
('admin@admin.com', 'abcd', 1),
('jmodi@indiana.edu', 'abcd', 1),
('njagasia@indiana.edu', 'abcd', 1),
('pvijaya@indiana.edu', 'abcd', 1),
('shah_tejas92@yahoo.co.in', 'abcd', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
