-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 10, 2015 at 10:22 AM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `personalassistant`
--

-- --------------------------------------------------------

--
-- Table structure for table `budget`
--

CREATE TABLE IF NOT EXISTS `budget` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `TaskUserId` varchar(20) NOT NULL,
  `income` double NOT NULL,
  `totalExpenses` double NOT NULL,
  `household` double NOT NULL,
  `food` double NOT NULL,
  `credit` double NOT NULL,
  `clothes` double NOT NULL,
  `luxury` double NOT NULL,
  `contracts` double NOT NULL,
  `loans` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `budget`
--

INSERT INTO `budget` (`id`, `TaskUserId`, `income`, `totalExpenses`, `household`, `food`, `credit`, `clothes`, `luxury`, `contracts`, `loans`) VALUES
(1, '1', 10000, 7800, 2000, 3000, 1000, 500, 500, 300, 1000);

-- --------------------------------------------------------

--
-- Table structure for table `eventtable`
--

CREATE TABLE IF NOT EXISTS `eventtable` (
  `eventID` int(11) NOT NULL AUTO_INCREMENT,
  `eventOwnerID` varchar(20) NOT NULL,
  `eventName` varchar(20) NOT NULL,
  `eventDate` varchar(20) NOT NULL,
  `eventTime` varchar(20) NOT NULL,
  `eventDesc` varchar(50) NOT NULL,
  `eventRem` varchar(50) NOT NULL,
  PRIMARY KEY (`eventID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `eventtable`
--

INSERT INTO `eventtable` (`eventID`, `eventOwnerID`, `eventName`, `eventDate`, `eventTime`, `eventDesc`, `eventRem`) VALUES
(1, '1', 'Cool Event', '21-September', '14:00', 'wub wub wub', '10:00'),
(3, '1', 'Alloy Tech', '23-October', '17:00', 'Busincess Conference for metal ore', '15:00'),
(4, '1', 'Soccer match', '22-August', '10:00', 'Jonny''s National League match', '08:00'),
(7, '1', 'Guess', '29-September', '15:00', 'No really, guess', '12:00'),
(9, '1', 'Lunch', '29-September', '13:00', 'its a blind date', '11:00');

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE IF NOT EXISTS `tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `TaskUserId` int(11) NOT NULL,
  `taskName` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`id`, `TaskUserId`, `taskName`) VALUES
(1, 1, 'buy eggs'),
(2, 1, 'feed the dog'),
(3, 1, 'buy wedding gift'),
(4, 1, 'plant lettuce');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userId`, `username`, `password`, `email`) VALUES
(1, 'qwe', 'qwe', 'qwe@qwe.qwe');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
