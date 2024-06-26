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
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservations` (
  `ConfirmationNumber` varchar(6) NOT NULL DEFAULT '',
  `FirstName` varchar(25) DEFAULT NULL,
  `LastName` varchar(25) DEFAULT NULL,
  `FromAirport` varchar(25) DEFAULT NULL,
  `ToAirport` varchar(25) DEFAULT NULL,
  `OutDeparture` datetime DEFAULT NULL,
  `OutArrival` datetime DEFAULT NULL,
  `ReturnDeparture` datetime DEFAULT NULL,
  `ReturnArrival` datetime DEFAULT NULL,
  `OutSeat` varchar(4) DEFAULT NULL,
  `ReturnSeat` varchar(4) DEFAULT NULL,
  `OutFlightNumber` varchar(10) DEFAULT NULL,
  `ReturnFlightNumber` varchar(10) DEFAULT NULL,
  `Price` decimal(10,0) NOT NULL DEFAULT '0',
  `OutPrice` decimal(10,0) NOT NULL,
  `RetPrice` decimal(10,0) NOT NULL,
  `Created` datetime NOT NULL,
  PRIMARY KEY (`ConfirmationNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES ('0DIBMA','Charles','Wilson','Barcelona','San Francisco','2024-06-20 00:00:00','2024-06-20 15:00:00','2024-06-21 16:30:00','2024-06-22 10:30:00','10A','10A','NR 1301','NR 1300',1000,500,500,'2024-05-19 00:00:00'),('1G2U61','Mary','Brown','Barcelona','Seattle','2024-05-23 14:30:00','2024-05-23 16:00:00','2024-05-26 16:00:00','2024-05-27 11:45:00','10A','10A','NR 1401','NR 1400',1000,500,500,'2024-05-19 00:00:00'),('1PQZ3S','Barbara','White','Dublin','San Francisco','2024-06-21 13:00:00','2024-06-21 16:00:00','2024-06-24 17:00:00','2024-06-25 11:00:00','10A','10A','NR 701','NR 700',1100,550,550,'2024-05-19 00:00:00'),('21TARS','Amy','Jones','Dublin','Seattle','2024-05-23 13:00:00','2024-05-23 15:00:00','2024-05-26 18:00:00','2024-05-27 11:00:00','10A','10A','NR 1201','NR 1200',1000,500,500,'2024-05-19 00:00:00'),('3O7KS7','Jim','Wilson','San Francisco','Portland','2024-06-05 19:00:00','2024-06-05 20:30:00','2024-06-08 21:30:00','2024-06-08 23:00:00','10A','10A','NR 300','NR 301',400,200,200,'2024-05-19 00:00:00'),('49ENS9','Jane','Anderson','San Francisco','Dublin','2024-06-24 17:00:00','2024-06-25 11:00:00','2024-06-27 13:00:00','2024-06-27 16:00:00','10B','10A','NR 700','NR 701',1100,550,550,'2024-05-19 00:00:00'),('75R0NH','Sam','Wilson','Barcelona','Portland','2024-05-23 14:00:00','2024-05-23 16:00:00','2024-05-26 15:30:00','2024-05-27 11:15:00','10A','10A','NR 1501','NR 1500',1000,500,500,'2024-05-19 00:00:00'),('7T1KRJ','Patricia','Williams','San Francisco','Dublin','2024-06-01 17:00:00','2024-06-02 11:00:00','2024-06-04 13:00:00','2024-06-04 16:00:00','10A','10A','NR 700','NR 701',1100,550,550,'2024-05-19 00:00:00'),('A5H3PL','Doug','White','Dublin','Portland','2024-05-26 13:00:00','2024-05-26 15:30:00','2024-05-29 18:00:00','2024-05-30 11:30:00','10A','10A','NR 1101','NR 1100',1000,500,500,'2024-05-19 00:00:00'),('BEK01O','Linda','Smith','Seattle','Dublin','2024-06-24 18:00:00','2024-06-25 11:00:00','2024-06-27 13:00:00','2024-06-27 15:00:00','10A','10A','NR 1200','NR 1201',1000,500,500,'2024-05-19 00:00:00'),('BI23YV','Amy','Smith','Seattle','Portland','2024-06-29 22:00:00','2024-06-29 23:00:00','2024-07-02 13:00:00','2024-07-02 14:00:00','10A','10A','NR 1001','NR 900',300,150,150,'2024-05-19 00:00:00'),('BT42G7','James','Johnson','Barcelona','Dublin','2024-06-23 13:15:00','2024-06-23 13:30:00','2024-06-26 10:00:00','2024-06-27 00:15:00','10A','10A','NR 1601','NR 1600',400,200,200,'2024-05-19 00:00:00'),('CURJBI','Sam','Taylor','Portland','Seattle','2024-06-06 20:00:00','2024-06-06 21:00:00','2024-06-09 15:00:00','2024-06-09 16:00:00','10A','10A','NR 1000','NR 901',300,150,150,'2024-05-19 00:00:00'),('DPZRJL','James','Davis','San Francisco','Seattle','2024-05-23 17:00:00','2024-05-23 19:00:00','2024-05-26 20:00:00','2024-05-26 22:00:00','10A','10A','NR 600','NR 601',400,200,200,'2024-05-19 00:00:00'),('EDDG1P','David','Williams','Barcelona','Dublin','2024-05-22 13:15:00','2024-05-22 13:30:00','2024-05-25 10:00:00','2024-05-26 00:15:00','10A','10A','NR 1601','NR 1600',400,200,200,'2024-05-19 00:00:00'),('EHUBXT','Doug','White','Barcelona','Seattle','2024-06-24 14:30:00','2024-06-24 16:00:00','2024-06-27 16:00:00','2024-06-28 11:45:00','10A','10A','NR 1401','NR 1400',1000,500,500,'2024-05-19 00:00:00'),('EKHXMK','Jim','Martin','San Francisco','Dublin','2024-06-20 17:00:00','2024-06-21 11:00:00','2024-06-23 13:00:00','2024-06-23 16:00:00','10A','10A','NR 700','NR 701',1100,550,550,'2024-05-19 00:00:00'),('FBJGB9','Jim','Miller','Barcelona','San Francisco','2024-05-29 00:00:00','2024-05-29 15:00:00','2024-05-30 16:30:00','2024-05-31 10:30:00','10A','10A','NR 1301','NR 1300',1000,500,500,'2024-05-19 00:00:00'),('GH0IUA','Elizabeth','Jackson','Seattle','San Francisco','2024-06-25 16:00:00','2024-06-25 18:00:00','2024-06-30 00:30:00','2024-06-30 14:30:00','10A','10A','NR 501','NR 500',400,200,200,'2024-05-19 00:00:00'),('H9M5SP','Barbara','Miller','San Francisco','Seattle','2024-06-14 08:30:00','2024-06-14 10:30:00','2024-06-17 20:00:00','2024-06-17 22:00:00','10A','10A','NR 400','NR 601',400,200,200,'2024-05-19 00:00:00'),('HJUUIS','Jim','Wilson','Dublin','San Francisco','2024-05-23 13:00:00','2024-05-23 16:00:00','2024-05-26 17:00:00','2024-05-27 11:00:00','10A','10A','NR 701','NR 700',1100,550,550,'2024-05-19 00:00:00'),('IHT1QE','Jane','Anderson','Dublin','Seattle','2024-06-22 13:00:00','2024-06-22 15:00:00','2024-06-25 18:00:00','2024-06-26 11:00:00','10A','10A','NR 1201','NR 1200',1000,500,500,'2024-05-19 00:00:00'),('K171L5','James','Martin','Barcelona','Seattle','2024-06-01 14:30:00','2024-06-01 16:00:00','2024-06-04 16:00:00','2024-06-05 11:45:00','10A','10A','NR 1401','NR 1400',1000,500,500,'2024-05-19 00:00:00'),('LAAJZL','Barbara','Anderson','Barcelona','San Francisco','2024-06-10 00:00:00','2024-06-10 15:00:00','2024-06-11 16:30:00','2024-06-12 10:30:00','10A','10A','NR 1301','NR 1300',1000,500,500,'2024-05-19 00:00:00'),('MSUI24','Jane','Johnson','Portland','San Francisco','2024-05-23 10:30:00','2024-05-23 11:45:00','2024-05-26 14:30:00','2024-05-26 16:00:00','10A','10A','NR 101','NR 110',400,200,200,'2024-05-19 00:00:00'),('NRRT44','David','Brown','Barcelona','San Francisco','2024-07-01 00:00:00','2024-07-01 15:00:00','2024-07-02 16:30:00','2024-07-03 10:30:00','10A','10A','NR 1301','NR 1300',1000,500,500,'2024-05-19 00:00:00'),('O4BRVQ','Robert','Williams','Seattle','Barcelona','2024-06-27 16:00:00','2024-06-28 11:45:00','2024-06-30 14:30:00','2024-06-30 16:00:00','10B','10A','NR 1400','NR 1401',1000,500,500,'2024-05-19 00:00:00'),('QBEIA6','Amy','Smith','Portland','San Francisco','2024-06-11 17:00:00','2024-06-11 18:30:00','2024-06-16 00:00:00','2024-06-16 13:30:00','10A','10A','NR 111','NR 200',400,200,200,'2024-05-19 00:00:00'),('R64YMM','William','Jackson','San Francisco','Portland','2024-06-28 19:00:00','2024-06-28 20:30:00','2024-07-01 17:00:00','2024-07-01 18:30:00','10A','10A','NR 300','NR 111',400,200,200,'2024-05-19 00:00:00'),('SAV4IG','Jim','Jackson','Dublin','San Francisco','2024-06-12 13:00:00','2024-06-12 16:00:00','2024-06-15 17:00:00','2024-06-16 11:00:00','10A','10A','NR 701','NR 700',1100,550,550,'2024-05-19 00:00:00'),('UI2NSN','Barbara','Brown','Portland','Barcelona','2024-06-22 15:30:00','2024-06-23 11:15:00','2024-06-25 14:00:00','2024-06-25 16:00:00','10A','10A','NR 1500','NR 1501',1000,500,500,'2024-05-19 00:00:00'),('UP42XF','John','White','Seattle','Portland','2024-06-21 15:00:00','2024-06-21 16:00:00','2024-06-24 20:00:00','2024-06-24 21:00:00','10A','10A','NR 901','NR 1000',300,150,150,'2024-05-19 00:00:00'),('UWZPP3','Mary','Taylor','Barcelona','San Francisco','2024-07-04 00:00:00','2024-07-04 15:00:00','2024-07-05 16:30:00','2024-07-06 10:30:00','10A','10A','NR 1301','NR 1300',1000,500,500,'2024-05-19 00:00:00'),('VARHAN','James','Moore','San Francisco','Seattle','2024-06-08 08:30:00','2024-06-08 10:30:00','2024-06-11 20:00:00','2024-06-11 22:00:00','10A','10A','NR 400','NR 601',400,200,200,'2024-05-19 00:00:00'),('VUZ4LJ','Mary','Moore','Dublin','Seattle','2024-06-07 13:00:00','2024-06-07 15:00:00','2024-06-10 18:00:00','2024-06-11 11:00:00','10A','10A','NR 1201','NR 1200',1000,500,500,'2024-05-19 00:00:00'),('VY5WQ4','Doug','Smith','San Francisco','Barcelona','2024-06-15 16:30:00','2024-06-16 10:30:00','2024-06-20 00:00:00','2024-06-20 15:00:00','10A','10A','NR 1300','NR 1301',1000,500,500,'2024-05-19 00:00:00'),('X07THT','Charles','Taylor','San Francisco','Seattle','2024-06-23 17:00:00','2024-06-23 19:00:00','2024-06-26 20:00:00','2024-06-26 22:00:00','10A','10A','NR 600','NR 601',400,200,200,'2024-05-19 00:00:00'),('X4OU4T','Mary','Anderson','San Francisco','Barcelona','2024-05-19 16:30:00','2024-05-20 10:30:00','2024-05-24 00:00:00','2024-05-24 15:00:00','10A','10A','NR 1300','NR 1301',1000,500,500,'2024-05-19 00:00:00'),('XJMAZB','Doug','White','Seattle','San Francisco','2024-06-08 16:00:00','2024-06-08 18:00:00','2024-06-11 17:00:00','2024-06-11 19:00:00','10A','10A','NR 501','NR 600',400,200,200,'2024-05-19 00:00:00'),('XWUR08','Tom','Davis','Portland','Seattle','2024-05-24 13:00:00','2024-05-24 14:00:00','2024-05-27 22:00:00','2024-05-27 23:00:00','10A','10A','NR 900','NR 1001',300,150,150,'2024-05-19 00:00:00'),('YNO5NF','Doug','Miller','Seattle','San Francisco','2024-05-20 16:00:00','2024-05-20 18:00:00','2024-05-23 08:30:00','2024-05-23 10:30:00','10A','10A','NR 501','NR 400',400,200,200,'2024-05-19 00:00:00'),('ZYTJX8','Elizabeth','Wilson','Portland','Seattle','2024-05-22 20:00:00','2024-05-22 21:00:00','2024-05-25 09:30:00','2024-05-25 10:30:00','10A','10A','NR 1000','NR 801',300,150,150,'2024-05-19 00:00:00');
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-20  7:19:18
