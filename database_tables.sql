-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 09, 2014 at 09:12 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `circle`
--

CREATE TABLE IF NOT EXISTS `circle` (
  `id` int(10) NOT NULL,
  `name` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `circle`
--

INSERT INTO `circle` (`id`, `name`) VALUES
(1, 'namrata'),
(2, 'purnima');

-- --------------------------------------------------------

--
-- Table structure for table `relation`
--

CREATE TABLE IF NOT EXISTS `relation` (
  `person_id` int(50) NOT NULL,
  `taskid` int(50) DEFAULT NULL,
  PRIMARY KEY (`person_id`),
  KEY `taskid` (`taskid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `roomies`
--

CREATE TABLE IF NOT EXISTS `roomies` (
  `rm_id` int(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `contact` int(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`rm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `room_number`
--

CREATE TABLE IF NOT EXISTS `room_number` (
  `room_id` int(50) DEFAULT NULL,
  `rm_id` int(50) DEFAULT NULL,
  KEY `rm_id` (`rm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `taskid` int(10) NOT NULL AUTO_INCREMENT,
  `TaskDescription` varchar(100) NOT NULL,
  `Score` int(11) NOT NULL,
  `assignedto` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`taskid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`taskid`, `TaskDescription`, `Score`, `assignedto`) VALUES
(1, 'Cleaning Dishes', 20, 'Tejas'),
(2, 'cooking', 40, NULL),
(3, 'Vacuum Cleaning', 10, NULL),
(4, 'trash', 30, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE IF NOT EXISTS `tasks` (
  `task_id` int(50) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `points` int(50) DEFAULT NULL,
  `rm_id` int(50) DEFAULT NULL,
  KEY `rm_id` (`rm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `userinfo`
--

INSERT INTO `userinfo` (`userid`, `fname`, `lname`, `sex`, `dob`, `email`, `phone`, `room`, `password`) VALUES
(1, 'Tejas', 'Shah', 'male', '1992-07-09', 'shah_tejas92@yahoo.co.in', 8123696631, '910N', 'abcd'),
(2, 'Abhinav', 'Vijaykumar', 'male', '1991-09-09', 'abhivij@indiana.edu', 999999999, '910N', 'abcd'),
(3, 'Namrata', 'Jagasia', 'female', '1992-09-09', 'njagasia@indiana.edu', 9999999, '910N', 'abcd'),
(4, 'baba', 'blacksheep', 'male', '1991-12-02', 'baba@gmail.com', 8328328321, '910N', 'baba');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `relation`
--
ALTER TABLE `relation`
  ADD CONSTRAINT `relation_ibfk_1` FOREIGN KEY (`taskid`) REFERENCES `task` (`taskid`);

--
-- Constraints for table `room_number`
--
ALTER TABLE `room_number`
  ADD CONSTRAINT `room_number_ibfk_1` FOREIGN KEY (`rm_id`) REFERENCES `roomies` (`rm_id`);

--
-- Constraints for table `tasks`
--
ALTER TABLE `tasks`
  ADD CONSTRAINT `tasks_ibfk_1` FOREIGN KEY (`rm_id`) REFERENCES `roomies` (`rm_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
