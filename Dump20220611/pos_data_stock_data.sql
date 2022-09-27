-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: pos_data
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `stock_data`
--

DROP TABLE IF EXISTS `stock_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_data` (
  `item_id` int NOT NULL,
  `count` int NOT NULL,
  `stock_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DSC` varchar(255) DEFAULT '',
  PRIMARY KEY (`item_id`,`stock_date`),
  CONSTRAINT `stock_data_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `item_data` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_data`
--

LOCK TABLES `stock_data` WRITE;
/*!40000 ALTER TABLE `stock_data` DISABLE KEYS */;
INSERT INTO `stock_data` VALUES (1,100,'2022-06-11 03:09:06','입고'),(1,73,'2022-06-11 04:20:07','입고'),(1,1,'2022-06-11 04:30:46','환불'),(1,0,'2022-06-11 06:20:33',''),(1,100,'2022-06-11 06:54:25','관리자 수정'),(1,200,'2022-06-11 06:54:36','관리자 수정'),(1,100,'2022-06-11 07:26:52','관리자 수정'),(1,400,'2022-06-11 07:34:20',''),(1,3,'2022-06-11 17:24:54','시범용'),(1,3,'2022-06-11 17:29:35','시범용'),(1,-5,'2022-06-11 17:37:49','판매'),(2,100,'2022-06-11 03:09:12','입고'),(2,0,'2022-06-11 04:23:12',''),(2,35,'2022-06-11 04:23:17',''),(2,35,'2022-06-11 04:23:23',''),(2,1,'2022-06-11 04:30:57','환불'),(2,28,'2022-06-11 05:49:34','환불'),(2,-3,'2022-06-11 17:24:04','판매'),(2,-3,'2022-06-11 17:28:45','판매'),(3,20,'2022-06-11 07:38:14','관리자 수정'),(3,-1,'2022-06-11 17:24:04','판매'),(3,-1,'2022-06-11 17:28:45','판매'),(4,20,'2022-06-11 12:57:43','관리자 수정'),(4,0,'2022-06-11 16:44:42','관리자 수정'),(4,0,'2022-06-11 16:44:59','관리자 수정'),(4,0,'2022-06-11 17:31:28','관리자 수정'),(5,5,'2022-06-11 16:45:54','관리자 수정'),(6,10,'2022-06-11 17:31:48','관리자 수정');
/*!40000 ALTER TABLE `stock_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-11 17:50:01
