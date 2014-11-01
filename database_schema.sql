-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 01, 2014 at 10:17 PM
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
('jmmodi@indiana.edu', 'ROLE_USER'),
('shah_tejas92@yahoo.co.in', 'ROLE_USER');

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
(1, 'tejas');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `taskid` int(10) NOT NULL AUTO_INCREMENT,
  `TaskDescription` varchar(100) NOT NULL,
  `points` int(11) NOT NULL,
  `Start_Date` date NOT NULL,
  `End_Date` date NOT NULL,
  `completed` tinyint(5) NOT NULL,
  `useremail` varchar(50) DEFAULT NULL,
  `room` varchar(50) NOT NULL,
  PRIMARY KEY (`taskid`),
  KEY `userid` (`useremail`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`taskid`, `TaskDescription`, `points`, `Start_Date`, `End_Date`, `completed`, `useremail`, `room`) VALUES
(1, 'Cleaning Dishes', 20, '2014-10-08', '2014-10-16', 0, 'shah_tejas92@yahoo.co.in', '910N'),
(2, 'Cooking', 40, '2014-10-14', '2014-10-13', 0, 'shah_tejas92@yahoo.co.in', '910N'),
(3, 'Vacuum Cleaning', 10, '2014-10-15', '2014-10-08', 0, 'shah_tejas92@yahoo.co.in', '910N'),
(4, 'Trash', 30, '0000-00-00', '0000-00-00', 0, NULL, '986N'),
(5, 'Cleaning bathroom', 40, '0000-00-00', '0000-00-00', 0, NULL, '986N'),
(6, 'Clean the Car', 30, '2014-10-31', '2014-11-08', 0, 'jmmodi@indiana.edu', '910N');

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
  PRIMARY KEY (`userid`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `userinfo`
--

INSERT INTO `userinfo` (`userid`, `fname`, `lname`, `sex`, `dob`, `email`, `phone`, `room`) VALUES
(1, 'Tejas', 'Shah', 'male', '1992-07-09', 'shah_tejas92@yahoo.co.in', 8123696631, '910N'),
(7, 'Jay', 'Modi', 'male', '1992-05-28', 'jmmodi@indiana.edu', 8123696652, '910N'),
(8, 'Abhinav', 'Vijaykumar', 'male', '2014-10-14', 'abhino@gmail.com', 234, '986N');

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
('abhino@gmail.com', 'ABCD', 1),
('jmmodi@indiana.edu', 'xyz', 1),
('shah_tejas92@yahoo.co.in', 'abcd', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `authority`
--
ALTER TABLE `authority`
  ADD CONSTRAINT `authority_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
