-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 07, 2014 at 10:23 PM
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
('jmmodi@indiana.edu', 'ROLE_USER'),
('shah_tejas92@yahoo.co.in', 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `roominfo`
--

CREATE TABLE IF NOT EXISTS `roominfo` (
  `room` varchar(50) NOT NULL,
  `totalpoints` int(30) NOT NULL,
  `members` int(10) NOT NULL,
  PRIMARY KEY (`room`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roominfo`
--

INSERT INTO `roominfo` (`room`, `totalpoints`, `members`) VALUES
('910N', 198, 3);

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
  `recurrence` varchar(20) DEFAULT ' no',
  PRIMARY KEY (`taskid`),
  KEY `userid` (`useremail`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=56 ;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`taskid`, `TaskDescription`, `points`, `Start_Date`, `End_Date`, `completed`, `useremail`, `room`, `recurrence`) VALUES
(2, 'Cooking', 40, '2014-10-14', '2014-10-15', 1, 'abhino@gmail.com', '910N', 'no'),
(3, 'Vacuum Cleaning', 10, '2014-10-15', '2014-10-08', 1, 'shah_tejas92@yahoo.co.in', '910N', 'no'),
(7, 'Clean the coffee pot', 55, '2014-10-29', '2014-11-21', 1, 'abhino@gmail.com', '910N', 'no'),
(23, 'Pay the utilities bill', 10, '2014-11-07', '2014-11-18', 0, 'shah_tejas92@yahoo.co.in', '910N', 'no'),
(43, 'Get mail from mailbox', 44, '2015-01-10', '2015-01-17', 1, 'abhino@gmail.com', '910N', 'no'),
(52, 'Take out the trash', 15, '2014-11-08', '2014-11-15', 1, 'abhino@gmail.com', '910N', 'weekly'),
(54, 'Pay the Rent', 55, '2014-11-01', '2014-11-30', 1, 'abhino@gmail.com', '910N', 'monthly');

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
  `score` int(20) NOT NULL,
  `pending_score` int(20) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `userinfo`
--

INSERT INTO `userinfo` (`userid`, `fname`, `lname`, `sex`, `dob`, `email`, `phone`, `room`, `score`, `pending_score`) VALUES
(1, 'Tejas', 'Shah', 'male', '1992-07-09', 'shah_tejas92@yahoo.co.in', 8123696631, '910N', 84, 0),
(7, 'Jay', 'Modi', 'male', '1992-05-28', 'jmmodi@indiana.edu', 8123696652, '910N', 0, 0),
(8, 'Abhinav', 'Vijaykumar', 'male', '2014-10-14', 'abhino@gmail.com', 234, '910N', 209, 0);

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
