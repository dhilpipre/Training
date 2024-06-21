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
-- Table structure for table `flights`
--

DROP TABLE IF EXISTS `flights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flights` (
  `FlightNumber` varchar(10) NOT NULL DEFAULT '',
  `Depart` varchar(25) DEFAULT NULL,
  `Arrive` varchar(25) DEFAULT NULL,
  `Aircraft` varchar(10) NOT NULL,
  `FromAirport` varchar(25) DEFAULT NULL,
  `ToAirport` varchar(25) DEFAULT NULL,
  `Price` decimal(10,0) NOT NULL,
  PRIMARY KEY (`FlightNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flights`
--

LOCK TABLES `flights` WRITE;
/*!40000 ALTER TABLE `flights` DISABLE KEYS */;
INSERT INTO `flights` VALUES ('NR 100','8:00 AM','9:30 AM','737','San Francisco','Portland',200),('NR 1000','8:00 PM','9:00 PM','A320','Portland','Seattle',150),('NR 1001','10:00 PM','11:00 PM','A320','Seattle','Portland',150),('NR 101','10:30 AM','11:45 AM','737','Portland','San Francisco',200),('NR 110','2:30 PM','4:00 PM','737','San Francisco','Portland',200),('NR 1100','6:00 PM','11:30 AM+1','A330','Portland','Dublin',500),('NR 1101','1:00 PM','3:30 PM','A330','Dublin','Portland',500),('NR 111','5:00 PM','6:30 PM','737','Portland','San Francisco',200),('NR 1200','6:00 PM','11:00 AM+1','A330','Seattle','Dublin',500),('NR 1201','1:00 PM','3:00 PM','A330','Dublin','Seattle',500),('NR 1300','4:30 PM','10:30 AM+1','777','San Francisco','Barcelona',500),('NR 1301','12:00 PM','3:00 PM','777','Barcelona','San Francisco',500),('NR 1400','4:00 PM','11:45 AM+1','767','Seattle','Barcelona',500),('NR 1401','2:30 PM','4:00 PM','767','Barcelona','Seattle',500),('NR 1500','3:30 PM','11:15 AM+1','777','Portland','Barcelona',500),('NR 1501','2:00 PM','4:00 PM','777','Barcelona','Portland',500),('NR 1600','10:00 AM','12:15 PM','737','Dublin','Barcelona',200),('NR 1601','1:15 PM','1:30 PM','737','Barcelona','Dublin',200),('NR 200','12:00 PM','1:30 PM','737','San Francisco','Portland',200),('NR 201','2:15 PM','4:00 PM','737','Portland','San Francisco',200),('NR 300','7:00 PM','8:30 PM','A320','San Francisco','Portland',200),('NR 301','9:30 PM','11:00 PM','A320','Portland','San Francisco',200),('NR 400','8:30 AM','10:30 AM','737','San Francisco','Seattle',200),('NR 401','11:45 AM','1:00 PM','737','Seattle','San Francisco',200),('NR 500','12:30 PM','2:30 PM','737','San Francisco','Seattle',200),('NR 501','4:00 PM','6:00 PM','737','Seattle','San Francisco',200),('NR 600','5:00 PM','7:00 PM','A320','San Francisco','Seattle',200),('NR 601','8:00 PM','10:00 PM','A320','Seattle','San Francisco',200),('NR 700','5:00 PM','11:00 AM+1','A330','San Francisco','Dublin',550),('NR 701','1:00 PM','4:00 PM','A330','Dublin','San Francisco',550),('NR 800','8:30 AM','7:30 AM','737','Portland','Seattle',150),('NR 801','9:30 AM','10:30 AM','737','Seattle','Portland',150),('NR 900','1:00 PM','2:00 PM','737','Portland','Seattle',150),('NR 901','3:00 PM','4:00 PM','737','Seattle','Portland',150);
/*!40000 ALTER TABLE `flights` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-20  7:19:19
