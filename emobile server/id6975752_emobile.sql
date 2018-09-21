-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Sep 20, 2018 at 07:07 PM
-- Server version: 10.2.12-MariaDB
-- PHP Version: 7.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id6975752_emobile`
--

-- --------------------------------------------------------

--
-- Table structure for table `banner`
--

CREATE TABLE `banner` (
  `banner_id` int(11) NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `banner`
--

INSERT INTO `banner` (`banner_id`, `image`) VALUES
(1, 'b1.jpg'),
(2, 'b2.jpg'),
(3, 'b3.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

CREATE TABLE `brand` (
  `brand_id` int(11) NOT NULL,
  `brand` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`brand_id`, `brand`) VALUES
(1, 'Apple'),
(2, 'Huawei'),
(5, 'Nokia'),
(7, 'Samsung'),
(8, 'Xiaomi');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `cart_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `ordered` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`cart_id`, `user_id`, `product_id`, `ordered`) VALUES
(2, 4, 1, 1),
(3, 2, 1, 1),
(4, 2, 7, 1),
(5, 5, 1, 1),
(6, 5, 5, 1),
(7, 5, 7, 1),
(8, 4, 9, 1),
(9, 5, 1, 1),
(10, 5, 2, 1),
(12, 4, 9, 0),
(13, 1, 1, 1),
(14, 1, 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `category` varchar(255) CHARACTER SET utf8 NOT NULL,
  `image` varchar(255) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category_id`, `category`, `image`) VALUES
(1, 'Apple', 'apple.png'),
(3, 'Huawei', 'huawei.png'),
(4, 'Nokia', 'nokia.png'),
(5, 'Samsung', 'samsung.png'),
(6, 'Xiaomi', 'xiaomi.png');

-- --------------------------------------------------------

--
-- Table structure for table `commen1`
--

CREATE TABLE `commen1` (
  `comment_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `comment` text CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `commen1`
--

INSERT INTO `commen1` (`comment_id`, `user_id`, `product_id`, `rating`, `comment`) VALUES
(1, 5, 2, 4, 'Its quite good, but iPhone X is better.'),
(2, 4, 2, 5, 'Very good product.'),
(3, 4, 1, 5, 'Very nice phone!');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `comment` text CHARACTER SET utf8 NOT NULL,
  `rating_scale` varchar(255) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`comment_id`, `user_id`, `comment`, `rating_scale`) VALUES
(3, 5, 'Bad', 'Very bad'),
(4, 4, 'Partizan champion!', 'Awesome. I love it'),
(5, 5, 'Bas ste kul', 'Very bad'),
(6, 5, 'hhbb', 'Awesome. I love it'),
(7, 4, 'xhsbn', 'Awesome. I love it'),
(8, 4, 'xhsbn', 'Awesome. I love it');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `cart_id` int(11) NOT NULL,
  `city` varchar(255) CHARACTER SET utf8 NOT NULL,
  `zip_code` int(11) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 NOT NULL,
  `quantity` int(11) NOT NULL,
  `show_order` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `cart_id`, `city`, `zip_code`, `address`, `phone`, `quantity`, `show_order`) VALUES
(1, 2, 'Beograd', 11000, 'Moja adresa', '0635555555', 1, 0),
(2, 3, 'Novi Sad', 21000, 'Laze Teleckog', '0637777777', 1, 1),
(4, 5, 'Beograd', 11000, 'Blagajska 2', '0647777777', 1, 1),
(5, 6, 'Beograd', 11000, 'Blagajska 2', '0647777777', 1, 0),
(6, 7, 'Beograd', 11000, 'Terazije 2', '0631111111', 1, 0),
(7, 9, 'Beograd', 11000, 'Terazije 2', '0631111111', 1, 0),
(8, 10, 'Beogras', 11000, 'Terazije 3', '0631111111', 1, 1),
(9, 8, 'Beograd', 11000, 'Terazije 2', '063111111', 1, 1),
(10, 13, 'Beograd', 11000, 'Jove Ilica 154', '0631111111', 1, 1),
(11, 14, 'Beograd', 11000, 'Jove Ilica 154', '0631111111', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `brand_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `image` varchar(255) CHARACTER SET utf8 NOT NULL,
  `description` text CHARACTER SET utf8 NOT NULL,
  `featured` tinyint(4) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_id`, `title`, `price`, `brand_id`, `category_id`, `image`, `description`, `featured`, `quantity`) VALUES
(1, 'iPhone X', 1599, 1, 1, 'apple/iphone_x.png', 'Screen diagonal: 5.8\r\nNumber of processor cores: Hexa Core\r\nRam memory: 3 GB\r\nBattery: 2716 mAh\r\nColor: Black\r\nCamera: 20 megapixels', 1, 30),
(2, 'iPhone 8', 1190, 1, 1, 'apple/iphone_8.png', 'Screen diagonal: 5.5\r\nNumber of processor cores: Hexa Core \r\nRam memory: 3 GB\r\nBattery:  2691 mAh\r\nColor: Black\r\nCamera: 17 megapixels', 1, 39),
(3, 'Huawei P20 Pro', 690, 2, 3, 'huawei/huawei_p20.png', 'Screen diagonal: 6.1\r\nNumber of processor cores: Octa Core\r\nRam Memory: 6GB\r\nBattery: 3400 mAh\r\nColor: Black\r\nCamera: 22 MP ', 1, 15),
(4, 'Huawei Mate 10', 220, 2, 3, 'huawei/huawei_mate10.png', 'Screen diagonal: 6.1\r\nNumber of processor cores: Octa Core\r\nRam Memory: 6GB\r\nBattery: 3340 mAh\r\nColor: Black\r\nCamera: 20 MP ', 0, 17),
(5, 'Nokia 8 Sirocco', 610, 5, 4, 'nokia/nokia_8.png', 'Screen diagonal: 5.5\r\nNumber of processor cores: Octa Core\r\nRam Memory: 6GB\r\nBattery: 2800 mAh\r\nColor: Black\r\nCamera: 16 MP ', 0, 20),
(6, 'Nokia 7 Plus', 350, 5, 4, 'nokia/nokia_7.png', 'Screen diagonal: 5.3\r\nNumber of processor cores: Octa Core\r\nRam Memory: 4GB\r\nBattery: 3000 mAh\r\nColor: Black\r\nCamera: 15 MP ', 0, 8),
(7, 'Samsung Galaxy S9', 810, 7, 5, 'samsung/samsung_gs9.png', 'Screen diagonal: 5.8\r\nNumber of processor cores: Octa Core\r\nRam Memory: 4GB\r\nBattery: 3560 mAh\r\nColor: Gold\r\nCamera: 20 MP ', 1, 22),
(8, 'Samsung Galaxy A6+', 360, 7, 5, 'samsung/samsung_ga6.png', 'Screen diagonal: 6.0\r\nNumber of processor cores: Octa Core\r\nRam Memory: 63GB\r\nBattery: 4000 mAh\r\nColor: Black\r\nCamera: 16 MP ', 0, 16),
(9, 'Xiaomi Mi A2', 290, 8, 6, 'xiaomi/xiaomi_mi_a2.png', 'Screen diagonal: 5.99\r\nNumber of processor cores: Octa Core\r\nRam Memory: 4GB\r\nBattery: 3200 mAh\r\nColor: White\r\nCamera: 20 MP ', 1, 32),
(10, 'Xiaomi Redmi Note 5', 280, 8, 6, 'xiaomi/xiaomi_note5.png', 'Screen diagonal: 5.99\r\nNumber of processor cores: Octa Core\r\nRam Memory: 4GB\r\nBattery: 4000 mAh\r\nColor: White\r\nCamera: 12 MP ', 0, 26);

-- --------------------------------------------------------

--
-- Table structure for table `shipping_address`
--

CREATE TABLE `shipping_address` (
  `shipping_address_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `city` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `zip_code` int(11) NOT NULL DEFAULT 0,
  `address` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `phone` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `shipping_address`
--

INSERT INTO `shipping_address` (`shipping_address_id`, `user_id`, `city`, `zip_code`, `address`, `phone`) VALUES
(1, 1, '', 0, '', ''),
(2, 2, '', 0, '', ''),
(3, 4, 'Beograd', 11000, '', ''),
(4, 5, '', 0, '', '');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `full_name` varchar(30) CHARACTER SET utf8 NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `full_name`, `email`, `password`) VALUES
(1, 'Djordje Vukicevic', 'vukicevic.djordje@gmail.com', 'djole123'),
(2, 'Pera Peric', 'pera@gmail.com', 'pera123'),
(4, 'Mateja Vukicevic', 'maki@gmail.com', 'maki321'),
(5, 'Jelena Novovic', 'jeca@gmail.com', 'jeca123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `banner`
--
ALTER TABLE `banner`
  ADD PRIMARY KEY (`banner_id`);

--
-- Indexes for table `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`brand_id`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cart_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `commen1`
--
ALTER TABLE `commen1`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `cart_id` (`cart_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `brand_id` (`brand_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `shipping_address`
--
ALTER TABLE `shipping_address`
  ADD PRIMARY KEY (`shipping_address_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `banner`
--
ALTER TABLE `banner`
  MODIFY `banner_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `brand`
--
ALTER TABLE `brand`
  MODIFY `brand_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `commen1`
--
ALTER TABLE `commen1`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `shipping_address`
--
ALTER TABLE `shipping_address`
  MODIFY `shipping_address_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `ProductID` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  ADD CONSTRAINT `UserID` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `commen1`
--
ALTER TABLE `commen1`
  ADD CONSTRAINT `commen1_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `commen1_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `CartID` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `BrandID` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`brand_id`),
  ADD CONSTRAINT `CategoryID` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);

--
-- Constraints for table `shipping_address`
--
ALTER TABLE `shipping_address`
  ADD CONSTRAINT `shipping_address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
