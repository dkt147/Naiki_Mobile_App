-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 29, 2021 at 11:17 PM
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
  `recv_id` int(11) NOT NULL,
  `type` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `donate`
--

INSERT INTO `donate` (`d_id`, `item_name`, `category`, `quantity`, `note`, `item_image`, `r_id`, `status`, `recv_id`, `type`) VALUES
(5, 'shoes', 'School Item', 2, 'shoes', 'IMG1361196666.jpeg', 7, 'Pending', 0, 'Donate'),
(6, 'coloths', 'Clothes', 3, 'clothe', 'IMG1748977064.jpeg', 7, 'Pending', 0, 'Donate');

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
  `utype` varchar(100) DEFAULT NULL,
  `profile_image` mediumtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`r_id`, `uname`, `upass`, `uphone`, `uaddress`, `uemail`, `utype`, `profile_image`) VALUES
(7, 'mubeen', 'abc123', 2147483647, '1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA', 'mubeen@gmail.com', 'Donor', 'IMG238152165.jpeg');

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
  MODIFY `d_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `register`
--
ALTER TABLE `register`
  MODIFY `r_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

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
