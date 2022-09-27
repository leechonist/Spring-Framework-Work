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
-- Table structure for table `sale_info_data`
--

DROP TABLE IF EXISTS `sale_info_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale_info_data` (
  `sale_id` int NOT NULL,
  `sale_method` int NOT NULL,
  `sale_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `total_price` int DEFAULT '0',
  PRIMARY KEY (`sale_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_info_data`
--

LOCK TABLES `sale_info_data` WRITE;
/*!40000 ALTER TABLE `sale_info_data` DISABLE KEYS */;
INSERT INTO `sale_info_data` VALUES (2,2,'2022-03-17 17:35:42',3500),(3,1,'2022-03-18 17:35:42',28000),(4,1,'2022-03-21 17:35:42',39000),(5,2,'2022-04-06 17:35:42',2000),(6,1,'2022-04-10 17:35:42',21000),(7,2,'2022-04-27 17:35:42',3600),(8,2,'2022-05-06 17:35:42',27000),(9,2,'2022-05-27 17:35:42',14200),(10,2,'2022-06-01 17:35:42',3000),(11,2,'2022-06-11 09:55:04',2000),(12,1,'2022-06-11 13:28:13',9000),(13,2,'2022-06-11 16:33:41',21000),(14,1,'2022-06-11 17:24:04',4600),(15,2,'2022-06-12 02:28:45',4600),(16,1,'2022-06-11 17:37:49',15000);
/*!40000 ALTER TABLE `sale_info_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-11 17:50:02