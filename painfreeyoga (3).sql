-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 06, 2025 at 10:56 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `painfreeyoga`
--

-- --------------------------------------------------------

--
-- Table structure for table `admindetail`
--

CREATE TABLE `admindetail` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `age` text NOT NULL,
  `gender` text NOT NULL,
  `mobilenumber` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `adminsignup`
--

CREATE TABLE `adminsignup` (
  `adminname` varchar(25) NOT NULL DEFAULT '""',
  `number` varchar(15) NOT NULL DEFAULT '""',
  `email` varchar(25) NOT NULL DEFAULT '""',
  `password` varchar(255) NOT NULL DEFAULT '""',
  `adminid` varchar(25) NOT NULL DEFAULT '""'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `adminsignup`
--

INSERT INTO `adminsignup` (`adminname`, `number`, `email`, `password`, `adminid`) VALUES
('samatha', '9587231456', 'sam@gmail.com', '$2y$10$LsbGGKnbM12x7SKaiGkg9.Olh1q1GrNwQZ8rpN84T..s7okvFntji', '25243'),
('samatha', '9587230456', 'sam1@gmail.com', '$2y$10$SLW3U5E9Per87EBzRlJ4cO/fi.KIBNQ8f5791lSxzjNOnQaiKiR7q', '252434'),
('samatha', '9380712345', 'chi1@gmail.com', '$2y$10$GlA9yYri1olc/9LuQ44thON6YqjaSfcaIpC96.m8KLYzIMhPT36ke', '67451'),
('samatha', '9445513384', 'ice@gmail.com', '$2y$10$n4Nd6/eyNCV2pijjmot74emU4xHdhDsi.u6ZVUysgpVtEIkh0pW.G', '74567'),
('sachin', '9865230147', 'schin@gmail.com', '$2y$10$2ASb0Z0OYaxtUmonF0gvk.VqHUiQwUYijTmiB75h8PBr3t35kiFkS', '6666'),
('sageetha', '989706612', 'sageetha@gmail.com', '$2y$10$DznGUQU80DQWZAXK2ppx4eb4Yo9OT4.DWgBGFKst5YNIPl/2BdnOO', '7890'),
('Chittagong', '9597877217', 'chitra@gmail.com', '$2y$10$L6yz1BA2SpmKPfTCaOepbOGzelXPQql/yqgPaFr.omfjbm089rcFO', '12345'),
('normal', '956780032', 'nirmal@gmail.com', '$2y$10$qUkVD7SwFOfZ1HQDZaGZhOITHfmY7puKIesm59eYVZ8UfOE6atZo.', '678'),
('samatha', '9587600032', 'samatha@gmail.com', '$2y$10$ktCWTkejVtBjHJ31yYKd3O5BkPw6aE6hqmLA2/1ckZdtr89oHKgxy', '6789'),
('samatha', '9587600032', 'samatha@gmail.com', '$2y$10$2Jts.8T3weyhPFn2ZIZsLOW96zghInEaHPsx6OVKuaysUGZXCRa3S', '6789'),
('samatha', '9587600032', 'samatha@gmail.com', '$2y$10$pdupgFJ1xlI/Vyj7TEr9seF57Epciq6ZZVhtBMKTnXzN3VLM9thku', '6789');

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE `answer` (
  `email` varchar(15) NOT NULL DEFAULT '""',
  `answer` varchar(300) NOT NULL DEFAULT '""'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`email`, `answer`) VALUES
('test@gmail.com', 'the'),
('test@gmail.com', 'the'),
('test@gmail.com', 'yessss'),
('test@gmail.com', 'yesssss'),
('janedoe@example', 'kj'),
('test@gmail.com', 'yes'),
('test@gmail.com', 'yess'),
('test@gmail.com', 'yess'),
('test@gmail.com', 'yes');

-- --------------------------------------------------------

--
-- Table structure for table `disease`
--

CREATE TABLE `disease` (
  `id` int(11) NOT NULL,
  `diseasename` varchar(255) NOT NULL DEFAULT '""',
  `yogaimage` varchar(255) NOT NULL DEFAULT '""',
  `yoganame` varchar(255) NOT NULL DEFAULT '""',
  `yoga_video` text NOT NULL DEFAULT '""'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `disease`
--

INSERT INTO `disease` (`id`, `diseasename`, `yogaimage`, `yoganame`, `yoga_video`) VALUES
(2, 'THYROID', 'https://media.istockphoto.com/id/1388470396/vector/yoga-plow-pose-or-halasana-woman-silhouette-practicing-stretching-yoga-pose-vector.jpg?s=612x612&w=0&k=20&c=lUqSeYt2sYarqNWFj6ssGecjJy1O6tz0aWGw6-OCOK0=', 'Sarvangasana', 'uploads/sarvangasana.mp4'),
(8, 'THYROID', 'https://c8.alamy.com/comp/KYB9WE/yoga-woman-in-a-pose-halasana-silhouette-vector-outline-portrait-gymnast-KYB9WE.jpg', 'halasana', ''),
(9, 'THYROID', 'https://c8.alamy.com/comp/KT10KE/beautiful-woman-doing-matsyasana-pose-on-yoga-class-studio-shot-KT10KE.jpg', 'matsyasana', 'uploads/matayasana.mp4'),
(10, 'THYROID', 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.alamy.com%2Fstock-photo%2Fchakrasana.html%3Fblackwhite%3D1&psig=AOvVaw1x-DC4sFvLgkOQT1Mf-TWV&ust=1736501313772000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCMij5e6p6IoDFQAAAAAdAAAAABAE', 'chakrasana', ''),
(11, 'THYROID', 'https://l450v.alamy.com/450v/byxj37/woman-paschimottanasana-yoga-pose-posture-position-in-silouhette-on-byxj37.jpg', 'paschimottanasana', ''),
(12, 'THYROID', 'https://media.istockphoto.com/id/533962793/vector/silhouette-girl-in-the-yoga-bridge-pose.jpg?s=612x612&w=0&k=20&c=EB-Ag5DKyR6-SkMQYeSoBhdFutwOdbB4K8M1ZIPwG5Y=', 'sethubandhasana', ''),
(13, 'SYNES', 'https://c8.alamy.com/comp/KT10KE/beautiful-woman-doing-matsyasana-pose-on-yoga-class-studio-shot-KT10KE.jpg', 'matsyasana', 'uploads/matayasana.mp4'),
(14, 'SYNES', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMAAAAEHCAMAAADPmLmNAAAAgVBMVEX///8AAAD8/Py2trby8vKDg4NLS0vt7e3d3d2AgID5+fnz8/Pv7+/j4+Ovr6+1tbVaWlrMzMzX19fBwcHPz88QEBCKioqamppgYGBycnIyMjJERERqamo5OTkWFhapqamTk5MmJiZQUFAcHByioqJ5eXkrKys8PDxmZmYjIyMTExNb6PvWA', 'vajrasana', ''),
(15, 'SYNES', 'https://c8.alamy.com/comp/2DAE963/silhouette-young-smiling-woman-practicing-yoga-doing-padmasana-2DAE963.jpg', 'padmasana', ''),
(16, 'SYNES', 'https://www.shutterstock.com/image-photo/woman-paschimottanasana-yoga-pose-posture-260nw-70618294.jpg', 'paschimottanasana', ''),
(17, 'SYNES', 'https://www.shutterstock.com/image-vector/silhouette-woman-maintaining-yoga-butterfly-260nw-1445318087.jpg', 'butterfly pose', ''),
(18, 'JOINPAIN', 'https://thumbs.dreamstime.com/b/silhouette-awkward-pose-utkatasana-flat-black-silhouette-young-woman-practicing-yoga-doing-awkward-pose-utkatasana-standing-280981048.jpg', 'utkatasana', ''),
(19, 'JOINPAIN', 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.shutterstock.com%2Fimage-photo%2Fyoga-asana-trikonasana-black-figure-on-182268683&psig=AOvVaw2PC6SrhTYy8lpfx1sRP71K&ust=1736505025525000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCOCq-Ly36IoDF', 'trikonasana', ''),
(20, 'JOINPAIN', 'https://media.istockphoto.com/id/533962793/vector/silhouette-girl-in-the-yoga-bridge-pose.jpg?s=612x612&w=0&k=20&c=EB-Ag5DKyR6-SkMQYeSoBhdFutwOdbB4K8M1ZIPwG5Y=', 'sethubandhasana', ''),
(21, 'JOINPAIN', 'https://www.shutterstock.com/image-vector/woman-doing-virabhadrasana-warrior-one-260nw-2474019311.jpg', 'veerabhadhrasana', ''),
(22, 'PCOD', 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fstock.adobe.com%2Fimages%2Fcamel-yoga-pose-vector-silhouette-illustration-isolated-on-white-background-man-exercises-yoga-zen-mantra-wellness-ustrasana-pose-gymnastic-bridge-figure-on-the-floor%2F169208489', 'ustrasana', ''),
(23, 'PCOD', 'https://c8.alamy.com/comp/KT10KE/beautiful-woman-doing-matsyasana-pose-on-yoga-class-studio-shot-KT10KE.jpg', 'matsyasana', ''),
(24, 'PCOD', 'https://media.istockphoto.com/id/1388470396/vector/yoga-plow-pose-or-halasana-woman-silhouette-practicing-stretching-yoga-pose-vector.jpg?s=612x612&w=0&k=20&c=lUqSeYt2sYarqNWFj6ssGecjJy1O6tz0aWGw6-OCOK0=', 'halasana', ''),
(25, 'PCOD', 'https://l450v.alamy.com/450v/2ybd51k/young-woman-practicing-yoga-pose-wind-removing-with-head-tucked-pose-pavanamuktasana-apanasana-isolated-vector-illustration-2ybd51k.jpg', 'pavanamtasana', ''),
(26, 'PCOD', 'https://c8.alamy.com/comp/W5AX2R/person-doing-shoulder-stand-W5AX2R.jpg', 'sarvangasana', ''),
(27, 'BLOODPRESSURE', 'https://c8.alamy.com/comp/2HM73A5/relaxing-yoga-pose-woman-silhouette-in-balasana-or-extended-childs-pose-vector-illustration-isolated-in-white-background-2HM73A5.jpg', 'childpose', ''),
(28, 'BLOODPRESSURE', 'https://www.shutterstock.com/image-vector/silhouette-woman-doing-yoga-janu-260nw-2428241093.jpg', 'janusirsasana', ''),
(29, 'BLOODPRESSURE', 'https://www.shutterstock.com/image-photo/woman-paschimottanasana-yoga-pose-posture-260nw-70618294.jpg', 'paschimottanasana', ''),
(30, 'BLOODPRESSURE', 'https://thumbs.dreamstime.com/z/flat-black-silhouette-young-woman-practicing-yoga-doing-butterfly-pose-bound-angle-pose-baddha-konasana-bhadrasana-seated-279817384.jpg', 'baddhakanasana', '');

-- --------------------------------------------------------

--
-- Table structure for table `homescreen`
--

CREATE TABLE `homescreen` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `age` text NOT NULL,
  `problem` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `patientdetail`
--

CREATE TABLE `patientdetail` (
  `id` int(11) NOT NULL,
  `patientname` varchar(250) NOT NULL DEFAULT '""',
  `age` varchar(250) NOT NULL DEFAULT '""',
  `gender` varchar(250) NOT NULL DEFAULT '""',
  `mobilenumber` varchar(200) NOT NULL DEFAULT '""',
  `email` varchar(200) NOT NULL DEFAULT '""',
  `selectdisease` varchar(200) NOT NULL DEFAULT '""'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patientdetail`
--

INSERT INTO `patientdetail` (`id`, `patientname`, `age`, `gender`, `mobilenumber`, `email`, `selectdisease`) VALUES
(1, 'John Doe', '30', 'Male', '9876543210', 'john.doe@example.com', 'Back Pain');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `email` varchar(50) NOT NULL,
  `question` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`email`, `question`) VALUES
('test@gmail.com', '6y6'),
('test@gmail.com', '6y6'),
('test@gmail.com', '6y6'),
('test@gmail.com', 'yoga pose that help support kidney health');

-- --------------------------------------------------------

--
-- Table structure for table `signup`
--

CREATE TABLE `signup` (
  `name` varchar(25) NOT NULL DEFAULT '""',
  `number` varchar(15) NOT NULL DEFAULT '""',
  `email` varchar(25) NOT NULL DEFAULT '""',
  `password` varchar(255) NOT NULL DEFAULT '""',
  `age` varchar(15) NOT NULL DEFAULT '""',
  `gender` varchar(25) NOT NULL DEFAULT '""',
  `problem` varchar(300) NOT NULL DEFAULT '""',
  `video` varchar(255) NOT NULL DEFAULT '""'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `signup`
--

INSERT INTO `signup` (`name`, `number`, `email`, `password`, `age`, `gender`, `problem`, `video`) VALUES
('chitra', '8254799145', 'c5678@gmail.com', '$2y$10$7ECI6mcGi6R/Q86EMI/.p.phfdYzaCWhmOouToFqrq0se/PbLaKAu', '\"\"', '\"\"', '\"\"', '\"\"'),
('chitra', '1234567890', 'test@gmail.com', '$2y$10$YfWuO/RgAHHEpuffYv7dYuWVK5tEUD046bSHQSOZdKrfqlC7oZRDa', '\"\"', '\"\"', '\"\"', 'yes'),
('John Doe', '1234567899', 'johndoe@example.com', '$2y$10$y1RXtqnSI8sw.Fz1aVXDNeEYPBLBmGzgiczKMQF3EFKcqXQqS2QE.', '\"\"', '\"\"', '\"\"', '\"\"'),
('Jane Doe', '9876543210', 'janedoe@example.com', '$2y$10$T/CNIHHn/i2/f.WFVWbyO.0AAEqNbOqJIdWBxlfQPWxIU4XJ8qWUC', '\"\"', '\"\"', '\"\"', '\"\"'),
('siva', '1234567898', 'siva@example.com', '12345', '\"\"', '\"\"', '\"\"', '\"\"'),
('chitra', '95872877217', 'chi@gmail.com', '$2y$10$c515KpP9EubybHE27vYJSeyMZaV2pJqlV9GbM756rjv2GzvBmkAYK', '23', 'female', 'back pain', '\"\"'),
('harini', '9445513384', 'harini@gmail.com', '$2y$10$0w9k.3D9D6jAjJhymTh4B.mrcOBgg3A1D8d1vdrbJDzUfRLAh7zpy', '20', 'female', 'throid', '\"\"'),
('rathika', '9449597877', 'rathika@gmail.com', '$2y$10$DZaEQIUFxPJ2v30ntVaE4OyZf7pxYGN/bnTKD3xhGdVKywt3X/VUK', '35', 'female', 'throid', '\"\"'),
('sahanna', '7024587651', 'samatha@gmail.com', '$2y$10$MNYEJi0PPotvYSlyKo.p8OLDcj.UZI/WMP8MsGNiSftMdfIUp1CFu', '34', 'female', 'back pain', '\"\"');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userId` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `number` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admindetail`
--
ALTER TABLE `admindetail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `disease`
--
ALTER TABLE `disease`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `homescreen`
--
ALTER TABLE `homescreen`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `patientdetail`
--
ALTER TABLE `patientdetail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userId`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admindetail`
--
ALTER TABLE `admindetail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `disease`
--
ALTER TABLE `disease`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `homescreen`
--
ALTER TABLE `homescreen`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `patientdetail`
--
ALTER TABLE `patientdetail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
