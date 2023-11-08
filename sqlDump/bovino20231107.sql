-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bovinoapp
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `animals`
--

DROP TABLE IF EXISTS `animals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animals` (
  `id` int NOT NULL AUTO_INCREMENT,
  `actual_weight` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `race` varchar(255) DEFAULT NULL,
  `ox_id_fk` int NOT NULL,
  `birth` date DEFAULT NULL,
  `dtcreated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `sexo` enum('M','F') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd02eg2sm7gtxq80h5mdqxc9jy` (`ox_id_fk`),
  CONSTRAINT `FKd02eg2sm7gtxq80h5mdqxc9jy` FOREIGN KEY (`ox_id_fk`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animals`
--

LOCK TABLES `animals` WRITE;
/*!40000 ALTER TABLE `animals` DISABLE KEYS */;
INSERT INTO `animals` VALUES (2,555,'1544','bulgarian',7,'2020-09-21',NULL,NULL),(3,200,'1721','bulgarian',7,'2020-11-27','2023-09-28 14:01:57',NULL),(4,150,'884','bulgarian',8,'2016-08-30','2023-09-28 14:01:57',NULL),(5,150,'1544','bulgarian',8,'2015-01-09','2023-09-28 14:01:57',NULL),(9,150,'1933','bulgarian',7,'2021-01-09','2023-09-28 14:01:57',NULL),(10,256,'4332','bulgarian',7,'2022-01-17','2023-09-28 14:01:57',NULL),(11,355,'8888','bulgarian',7,'2022-07-15','2023-09-28 14:01:57',NULL),(15,715,'9129','bulgarian',7,'2022-01-17',NULL,NULL),(21,555,'3221','bulgarian',7,'2022-01-17',NULL,NULL),(23,333,'8876','servio',7,'2022-02-26','2023-09-28 14:01:57',NULL),(26,155,'1722','bulgarian',7,'2022-03-11','2023-10-04 22:31:41',NULL),(27,155,'1723','bulgarian',7,'2022-05-11','2023-10-04 22:32:27',NULL),(28,155,'1724','bulg',7,'2022-06-11','2023-10-04 22:33:06',NULL),(29,323,'1727','bugr',7,'2022-08-15','2023-10-04 22:33:32',NULL),(30,452,'1729','rdf',7,'2022-11-18','2023-10-04 22:34:09',NULL),(31,111,'1751','rtgfd',7,'2022-12-24','2023-10-04 22:34:27',NULL);
/*!40000 ALTER TABLE `animals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dashboard`
--

DROP TABLE IF EXISTS `dashboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dashboard` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id_fk` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdseir6x9if3k0pf6mtoy46s5u` (`user_id_fk`),
  CONSTRAINT `FKdseir6x9if3k0pf6mtoy46s5u` FOREIGN KEY (`user_id_fk`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dashboard`
--

LOCK TABLES `dashboard` WRITE;
/*!40000 ALTER TABLE `dashboard` DISABLE KEYS */;
/*!40000 ALTER TABLE `dashboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evolution`
--

DROP TABLE IF EXISTS `evolution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evolution` (
  `id` int NOT NULL AUTO_INCREMENT,
  `weight` int DEFAULT NULL,
  `animal_id_fk` int NOT NULL,
  `registry_date` date DEFAULT NULL,
  `dtcreated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK2p5vgkeauef3nn6vx703lpe7b` (`animal_id_fk`),
  CONSTRAINT `FK2p5vgkeauef3nn6vx703lpe7b` FOREIGN KEY (`animal_id_fk`) REFERENCES `animals` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evolution`
--

LOCK TABLES `evolution` WRITE;
/*!40000 ALTER TABLE `evolution` DISABLE KEYS */;
INSERT INTO `evolution` VALUES (1,150,9,'2021-01-09','2023-10-07 22:33:17'),(2,256,10,'0022-07-15','2023-10-07 22:33:17'),(3,355,11,'0022-07-15','2023-10-07 22:33:17'),(6,515,15,'2022-01-17','2023-10-07 22:33:17'),(11,545,15,'2022-01-17','2023-10-07 22:33:17'),(14,687,15,'2023-10-13','2023-10-07 22:33:17'),(19,545,21,'2022-01-17','2023-10-07 22:33:17'),(23,555,2,'2021-04-21','2023-10-07 22:33:17'),(25,333,23,'2022-02-26','2023-10-07 22:33:17'),(31,155,26,'2022-03-11','2023-10-07 22:33:17'),(32,155,27,'2022-05-11','2023-10-07 22:33:17'),(33,155,28,'2022-06-11','2023-10-07 22:33:17'),(34,323,29,'2022-08-15','2023-10-07 22:33:17'),(35,452,30,'2022-11-18','2023-10-07 22:33:17'),(36,111,31,'2022-12-24','2023-10-07 22:33:17'),(40,715,15,'2023-10-22','2023-10-23 02:15:26'),(41,555,21,'2023-10-22','2023-10-23 02:17:23');
/*!40000 ALTER TABLE `evolution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precos`
--

DROP TABLE IF EXISTS `precos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `precos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dia` datetime(6) NOT NULL,
  `valor` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precos`
--

LOCK TABLES `precos` WRITE;
/*!40000 ALTER TABLE `precos` DISABLE KEYS */;
INSERT INTO `precos` VALUES (1,'2023-10-16 00:00:00.000000',100),(2,'2023-10-17 00:00:00.000000',110),(3,'2023-10-16 00:00:00.000000',161),(9,'2023-10-22 00:00:00.000000',241),(10,'2023-10-23 00:00:00.000000',241),(11,'2023-10-24 00:00:00.000000',237),(12,'2023-10-26 00:00:00.000000',240);
/*!40000 ALTER TABLE `precos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) DEFAULT NULL,
  `dtcreated` datetime(6) DEFAULT NULL,
  `paymentmethod` varchar(255) DEFAULT NULL,
  `total` int DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1,'vitin@gmail.com','2023-10-28 17:08:34.717000','card',132,'BILL-1698523714692'),(2,'vitin@gmail.com','2023-10-28 17:22:16.802000','card',132,'BILL-1698524536801'),(3,'vitin@gmail.com','2023-10-28 17:23:59.053000','card',132,'BILL-1698524639022'),(4,'vitin@gmail.com','2023-10-28 17:25:16.117000','card',132,'BILL-1698524716072'),(5,'vitin@gmail.com','2023-10-28 17:26:34.404000','card',132,'BILL-1698524794379'),(6,'vitin@gmail.com','2023-10-28 17:27:45.345000','card',132,'BILL-1698524865322'),(7,'vitin@gmail.com','2023-10-28 17:29:10.487000','card',132,'BILL-1698524950433'),(8,'vitin@gmail.com','2023-10-28 17:36:34.021000','card',132,'BILL-1698525394000'),(9,'vitin@gmail.com','2023-10-28 17:39:51.854000','card',132,'BILL-1698525591829'),(10,'vitin@gmail.com','2023-10-28 17:40:35.713000','card',132,'BILL-1698525635690'),(11,'vitin@gmail.com','2023-10-28 17:42:01.620000','card',132,'BILL-1698525721595');
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'saurav','1234567890','vitor.bovino@gmail.com','123456','user','false'),(7,'vitin','1234567890','vitin@gmail.com','123456','admin','true'),(8,'indian','1234567890','indian.bovino@gmail.com','123456','user','true');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `wbois_ano`
--

DROP TABLE IF EXISTS `wbois_ano`;
/*!50001 DROP VIEW IF EXISTS `wbois_ano`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `wbois_ano` AS SELECT 
 1 AS `quantidade`,
 1 AS `mes`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `wbois_ano`
--

/*!50001 DROP VIEW IF EXISTS `wbois_ano`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `wbois_ano` AS select count(0) AS `quantidade`,month(`animals`.`birth`) AS `mes` from `animals` where (year(`animals`.`birth`) = 2022) group by month(`animals`.`birth`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-07 22:23:44
