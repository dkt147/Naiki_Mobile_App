-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 03, 2021 at 05:16 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `naiki_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `donate`
--

CREATE TABLE `donate` (
  `d_id` int(11) NOT NULL,
  `item_name` varchar(255) NOT NULL,
  `category` varchar(200) NOT NULL,
  `quantity` int(11) NOT NULL,
  `note` text NOT NULL,
  `item_image` varchar(255) NOT NULL,
  `r_id` int(11) NOT NULL,
  `status` varchar(100) NOT NULL DEFAULT 'Pending',
  `recv_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `donate`
--

INSERT INTO `donate` (`d_id`, `item_name`, `category`, `quantity`, `note`, `item_image`, `r_id`, `status`, `recv_id`) VALUES
(6, '$_POST[\"it\"];', '', 2, '$_POST[\"ds\"];', '$_POST[\"im\"];', 2, 'Pending', 0),
(7, 'shoes', '', 2, '', '', 2, 'Pending', 0);

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE `register` (
  `r_id` int(100) NOT NULL,
  `uname` varchar(255) NOT NULL,
  `upass` varchar(255) NOT NULL,
  `uphone` int(11) NOT NULL,
  `uaddress` varchar(255) NOT NULL,
  `uemail` varchar(255) NOT NULL,
  `utype` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`r_id`, `uname`, `upass`, `uphone`, `uaddress`, `uemail`, `utype`) VALUES
(1, 'ali', 'ali123', 13123, 'adas', 'adas', 'dsf'),
(2, 'ali', 'ali123', 3123, 'adas', 'adas', 'dsf'),
(3, 'asdasd', 'asdasd', 213213, 'asdas', 'sadfasd', ''),
(4, 'bilal', 'asdas', 1313123, 'sadad', 'sdasd', 'Donor'),
(5, 'sada', 'aasd', 721, 'Address : 1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA', 'asd', 'Donor');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `donate`
--
ALTER TABLE `donate`
  ADD PRIMARY KEY (`d_id`),
  ADD KEY `r_id` (`r_id`);

--
-- Indexes for table `register`
--
ALTER TABLE `register`
  ADD PRIMARY KEY (`r_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `donate`
--
ALTER TABLE `donate`
  MODIFY `d_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `register`
--
ALTER TABLE `register`
  MODIFY `r_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `donate`
--
ALTER TABLE `donate`
  ADD CONSTRAINT `donate_ibfk_1` FOREIGN KEY (`r_id`) REFERENCES `register` (`r_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
