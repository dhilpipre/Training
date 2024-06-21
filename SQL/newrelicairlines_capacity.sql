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
-- Table structure for table `capacity`
--

DROP TABLE IF EXISTS `capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `capacity` (
  `FlightNumber` varchar(10) NOT NULL DEFAULT '',
  `FlightDate` date NOT NULL DEFAULT '0000-00-00',
  `SeatsAvailable` int(11) DEFAULT NULL,
  `AvailableSeats` varchar(512) DEFAULT NULL,
  `TotalSeats` int(11) NOT NULL DEFAULT '80',
  PRIMARY KEY (`FlightNumber`,`FlightDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `capacity`
--

LOCK TABLES `capacity` WRITE;
/*!40000 ALTER TABLE `capacity` DISABLE KEYS */;
INSERT INTO `capacity` VALUES ('NR 100','2024-05-28',80,'10A,10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 1000','2024-05-22',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 1000','2024-06-06',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 1000','2024-06-24',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 1001','2024-05-27',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 1001','2024-06-29',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 101','2024-05-23',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 110','2024-05-26',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 1100','2024-05-29',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1100','2024-06-02',100,'10A,10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1101','2024-05-26',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1101','2024-05-30',100,'10A,10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 111','2024-06-11',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 111','2024-07-01',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 1200','2024-05-26',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1200','2024-06-10',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1200','2024-06-20',100,'10A,10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1200','2024-06-24',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1200','2024-06-25',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1201','2024-05-23',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1201','2024-06-07',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1201','2024-06-17',100,'10A,10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1201','2024-06-22',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1201','2024-06-27',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1300','2024-05-19',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1300','2024-05-30',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1300','2024-06-11',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1300','2024-06-15',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1300','2024-06-21',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1300','2024-07-02',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1300','2024-07-05',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1301','2024-05-23',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1301','2024-05-28',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1301','2024-06-09',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1301','2024-06-19',98,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1301','2024-06-30',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1301','2024-07-03',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1400','2024-05-26',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 1400','2024-06-04',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 1400','2024-06-27',78,'10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 1401','2024-05-23',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 1401','2024-06-01',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 1401','2024-06-24',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 1401','2024-06-30',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 1500','2024-05-26',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1500','2024-06-22',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1501','2024-05-23',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1501','2024-06-25',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 1600','2024-05-25',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 1600','2024-06-26',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 1601','2024-05-22',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 1601','2024-06-23',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 200','2024-06-15',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 201','2024-05-31',80,'10A,10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 300','2024-06-05',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 300','2024-06-28',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 301','2024-06-08',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 400','2024-05-23',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 400','2024-06-08',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 400','2024-06-14',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 500','2024-06-29',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 501','2024-05-20',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 501','2024-06-08',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 501','2024-06-25',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 600','2024-05-23',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 600','2024-06-07',80,'10A,10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 600','2024-06-11',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 600','2024-06-23',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 601','2024-05-26',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 601','2024-06-04',80,'10A,10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 601','2024-06-11',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 601','2024-06-17',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 601','2024-06-26',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 700','2024-05-26',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 700','2024-06-01',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 700','2024-06-15',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 700','2024-06-20',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 700','2024-06-24',98,'10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 701','2024-05-23',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 701','2024-06-04',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 701','2024-06-12',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 701','2024-06-21',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 701','2024-06-23',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 701','2024-06-27',99,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,21A,21B,21C,21D,22A,22B,22C,22D,23A,23B,23C,23D,24A,24B,24C,24D,25A,25B,25C,25D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',100),('NR 801','2024-05-25',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 900','2024-05-24',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 900','2024-07-02',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 901','2024-06-09',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80),('NR 901','2024-06-21',79,'10B,10C,10D,11A,11B,11C,11D,12A,12B,12C,12D,13A,13B,13C,13D,14A,14B,14C,14D,15A,15B,15C,15D,16A,16B,16C,16D,17A,17B,17C,17D,18A,18B,18C,18D,19A,19B,19C,19D,1A,1B,1C,1D,20A,20B,20C,20D,2A,2B,2C,2D,3A,3B,3C,3D,4A,4B,4C,4D,5A,5B,5C,5D,6A,6B,6C,6D,7A,7B,7C,7D,8A,8B,8C,8D,9A,9B,9C,9D',80);
/*!40000 ALTER TABLE `capacity` ENABLE KEYS */;
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
