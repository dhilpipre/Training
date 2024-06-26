-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: newrelicairlines
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `revenuebyday`
--

DROP TABLE IF EXISTS `revenuebyday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `revenuebyday` (
  `RevenueDate` date NOT NULL,
  `Revenue` decimal(10,0) NOT NULL,
  `Reservations` int(11) NOT NULL,
  `Refunds` float NOT NULL,
  PRIMARY KEY (`RevenueDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revenuebyday`
--

LOCK TABLES `revenuebyday` WRITE;
/*!40000 ALTER TABLE `revenuebyday` DISABLE KEYS */;
INSERT INTO `revenuebyday` VALUES ('2018-01-23',1550,3,0),('2018-01-24',3500,10,0),('2018-01-25',1000,4,500),('2018-01-26',1900,5,0),('2018-01-27',4000,11,0),('2018-01-28',2100,6,0),('2018-01-29',1700,4,0),('2018-01-30',1250,5,0),('2018-01-31',2100,8,500),('2018-02-01',2650,7,0),('2018-02-02',550,3,0),('2018-02-03',2350,7,0),('2018-02-04',1950,5,0),('2018-02-05',1950,5,0),('2018-02-06',4100,11,150),('2018-02-07',2250,5,0),('2018-02-08',2350,7,0),('2018-02-09',3900,10,150),('2018-02-10',1400,6,500),('2018-02-11',2150,8,0),('2018-02-12',1700,4,0),('2018-02-13',2100,6,0),('2018-02-14',3450,10,0),('2018-02-15',50,4,500),('2018-02-16',2600,7,0),('2018-02-17',3100,8,0),('2018-02-18',1350,4,0),('2018-02-19',3600,9,0),('2018-02-20',1900,5,0),('2018-02-21',3550,9,0),('2018-02-22',2650,7,0),('2018-02-23',3100,8,0),('2018-02-24',1600,5,0),('2018-02-25',5500,13,0),('2018-02-26',3050,8,0),('2018-02-27',3200,7,0),('2018-02-28',5200,12,0),('2018-03-01',1950,7,0),('2018-03-02',4200,9,0),('2018-03-03',2250,9,150),('2018-03-04',2550,7,0),('2018-03-05',3100,8,0),('2018-03-06',1350,8,700),('2018-03-07',1800,13,1450),('2018-03-08',4550,12,0),('2018-03-09',1150,5,550),('2018-03-10',1050,12,1450),('2018-03-11',2150,6,0),('2018-03-12',0,2,200),('2018-03-13',900,3,0),('2018-03-14',1250,3,0),('2018-03-15',650,2,0),('2018-03-16',400,2,0),('2018-03-17',1050,4,200),('2018-03-18',150,1,0),('2018-03-19',500,1,0),('2019-01-30',0,6,550),('2019-01-31',200,11,1250),('2019-02-01',-200,1,200),('2019-02-02',0,2,500),('2019-02-03',500,1,0),('2019-02-04',-500,1,500),('2019-02-08',0,2,500),('2019-02-09',0,4,1000),('2020-05-19',500,1,0),('2020-05-20',200,1,0),('2020-05-22',350,2,0),('2020-05-23',3150,8,0),('2020-05-24',150,1,0),('2020-05-25',350,2,0),('2020-05-26',2950,7,0),('2020-05-27',150,1,0),('2020-05-28',500,3,200),('2020-05-29',500,1,0),('2020-05-30',500,3,500),('2020-05-31',0,2,200),('2020-06-01',1050,2,0),('2020-06-02',0,2,500),('2020-06-04',1050,4,200),('2020-06-05',200,1,0),('2020-06-06',150,1,0),('2020-06-07',500,3,200),('2020-06-08',600,3,0),('2020-06-09',650,2,0),('2020-06-10',500,1,0),('2020-06-11',1100,4,0),('2020-06-12',550,1,0),('2020-06-14',200,1,0),('2020-06-15',1250,3,0),('2020-06-17',200,3,500),('2020-06-19',1000,2,0),('2020-06-20',550,3,500),('2020-06-21',1200,3,0),('2020-06-22',1000,2,0),('2020-06-23',950,3,0),('2020-06-24',2250,5,0),('2020-06-25',1200,3,0),('2020-06-26',400,2,0),('2020-06-27',2050,4,0),('2020-06-28',200,1,0),('2020-06-29',350,2,0),('2020-06-30',1000,2,0),('2020-07-01',200,1,0),('2020-07-02',650,2,0),('2020-07-03',500,1,0),('2020-07-05',500,1,0);
/*!40000 ALTER TABLE `revenuebyday` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-20  7:19:18
