-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: db_partner_manager
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_auth_menu`
--

DROP TABLE IF EXISTS `t_auth_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_auth_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `level` int(11) DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT '0',
  `route` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_37kqlovsqqo3o8hmbdkwm24ho` (`name`),
  UNIQUE KEY `UK_rgxbxwiifdj5q8v9dc3ahnr14` (`route`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_auth_menu`
--

LOCK TABLES `t_auth_menu` WRITE;
/*!40000 ALTER TABLE `t_auth_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_auth_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_auth_permission`
--

DROP TABLE IF EXISTS `t_auth_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_auth_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(63) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_q1owjt6c9689s8uh1hc5fulso` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_auth_permission`
--

LOCK TABLES `t_auth_permission` WRITE;
/*!40000 ALTER TABLE `t_auth_permission` DISABLE KEYS */;
INSERT INTO `t_auth_permission` VALUES (1,'2021-03-07 16:15:40','2021-03-07 16:15:40','authIndex'),(2,'2021-03-07 16:15:40','2021-03-07 16:15:40','getAllUserByPageVO'),(3,'2021-03-07 16:15:40','2021-03-07 16:15:40','addUser');
/*!40000 ALTER TABLE `t_auth_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_auth_role`
--

DROP TABLE IF EXISTS `t_auth_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_auth_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(63) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_la0owuvku85ddfflejmfys05j` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_auth_role`
--

LOCK TABLES `t_auth_role` WRITE;
/*!40000 ALTER TABLE `t_auth_role` DISABLE KEYS */;
INSERT INTO `t_auth_role` VALUES (1,'2021-03-07 16:16:10','2021-03-07 16:16:10','admin'),(2,'2021-03-07 16:16:10','2021-03-07 16:16:10','visitor'),(3,'2021-03-07 16:16:10','2021-03-07 16:16:10','user');
/*!40000 ALTER TABLE `t_auth_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_auth_role_menu`
--

DROP TABLE IF EXISTS `t_auth_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_auth_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `menu_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXr1dlal2omdf36ivxcsrlhd309` (`role_id`),
  KEY `IDX5pmmreoc18m97738qny58cvbx` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_auth_role_menu`
--

LOCK TABLES `t_auth_role_menu` WRITE;
/*!40000 ALTER TABLE `t_auth_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_auth_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_auth_role_permission`
--

DROP TABLE IF EXISTS `t_auth_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_auth_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `permission_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXhftrjkd8uam9qr2msako8ir31` (`role_id`),
  KEY `IDXqtgjfrhjdgam5uq6ivmiawakm` (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_auth_role_permission`
--

LOCK TABLES `t_auth_role_permission` WRITE;
/*!40000 ALTER TABLE `t_auth_role_permission` DISABLE KEYS */;
INSERT INTO `t_auth_role_permission` VALUES (1,'2021-03-07 16:17:09','2021-03-07 16:17:09',1,1),(2,'2021-03-07 16:17:09','2021-03-07 16:17:09',2,1),(3,'2021-03-07 16:17:09','2021-03-07 16:17:09',3,1),(4,'2021-03-07 16:17:09','2021-03-07 16:17:09',1,2),(5,'2021-03-07 16:17:09','2021-03-07 16:17:09',1,3),(6,'2021-03-07 16:17:09','2021-03-07 16:17:09',2,3);
/*!40000 ALTER TABLE `t_auth_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_auth_user`
--

DROP TABLE IF EXISTS `t_auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `email` varchar(255) DEFAULT NULL,
  `login_try_times` int(11) DEFAULT '0',
  `name` varchar(63) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `username` varchar(63) DEFAULT NULL,
  `eid` varchar(15) DEFAULT NULL,
  `locale` varchar(63) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_aajmqymsrauoh7el1pa2nk0t7` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_auth_user`
--

LOCK TABLES `t_auth_user` WRITE;
/*!40000 ALTER TABLE `t_auth_user` DISABLE KEYS */;
INSERT INTO `t_auth_user` VALUES (1,'2021-03-07 08:17:27','2021-03-07 08:40:15',NULL,0,NULL,'$2a$10$ZHsCyNjZU3A4S.9emXNr/ebMT5ZQM0dh.JdqWLIW9QHgPvsjLhqVi',0,'test',NULL,NULL),(6,'2021-03-07 08:27:32','2021-03-31 17:05:01',NULL,0,NULL,'$2a$10$cI6cmvUn2DO1nnyUTxVgNeK69DNlijuqUUlxvNh3Go2JxAHt4UUt6',0,'admin',NULL,NULL),(8,'2021-03-07 08:41:20','2021-03-07 08:41:53',NULL,0,NULL,'$2a$10$5Sb4Srog99jyol0EYrLeNOcdpIZdSZMBXVTSumcss0fCFXKmFwcdi',0,'test2',NULL,NULL),(9,'2021-03-07 08:43:06','2021-03-07 08:43:06',NULL,0,NULL,'$2a$10$0k7ivjUPxoWNuvKPCBCd/u1.hMmpInxxzfvcPmY67FpmwxZP8FHZi',0,'test3',NULL,NULL),(11,'2021-03-31 10:28:15','2021-03-31 10:28:15',NULL,0,NULL,'$2a$10$lx.p.XsTfwA1zVUfnQOeFOvH2WkBGDZE.PqLlzwy2c8IcAfduuPkO',0,'testNormalCondition',NULL,'zh-cn');
/*!40000 ALTER TABLE `t_auth_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_auth_user_role`
--

DROP TABLE IF EXISTS `t_auth_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_auth_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `role_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXg17din2wb1xlnahcd80vq64gy` (`user_id`),
  KEY `IDXewgx28hmmmekwjmmy28361ury` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_auth_user_role`
--

LOCK TABLES `t_auth_user_role` WRITE;
/*!40000 ALTER TABLE `t_auth_user_role` DISABLE KEYS */;
INSERT INTO `t_auth_user_role` VALUES (1,'2021-03-07 08:17:27','2021-03-07 08:17:27',2,1),(2,'2021-03-07 08:17:27','2021-03-07 16:39:26',3,1),(3,'2021-03-07 08:27:32','2021-03-07 08:27:32',2,6),(4,'2021-03-07 08:27:32','2021-03-07 16:39:26',3,6),(5,'2021-03-07 08:41:20','2021-03-07 08:41:20',2,8),(6,'2021-03-07 08:41:20','2021-03-07 16:47:16',3,8),(7,'2021-03-07 08:43:36','2021-03-07 08:43:36',2,9),(8,'2021-03-07 08:46:10','2021-03-07 16:47:16',3,9),(9,'2021-03-07 16:48:00','2021-03-07 16:48:00',1,6),(10,'2021-03-07 09:06:10','2021-03-07 09:06:10',2,10),(11,'2021-03-07 09:06:10','2021-03-07 09:06:10',3,10),(12,'2021-03-31 10:28:15','2021-03-31 10:28:15',2,11),(13,'2021-03-31 10:28:15','2021-03-31 10:28:15',3,11);
/*!40000 ALTER TABLE `t_auth_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_prm_bank`
--

DROP TABLE IF EXISTS `t_prm_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_prm_bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `account` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `partner_id` int(11) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXcw6emphrixif7cjf8ad2kgcmn` (`partner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_prm_bank`
--

LOCK TABLES `t_prm_bank` WRITE;
/*!40000 ALTER TABLE `t_prm_bank` DISABLE KEYS */;
INSERT INTO `t_prm_bank` VALUES (1,'2021-03-31 17:19:41','2021-03-31 17:19:41','string','string','string','string','string',0,'string');
/*!40000 ALTER TABLE `t_prm_bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_prm_check_record`
--

DROP TABLE IF EXISTS `t_prm_check_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_prm_check_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `check_code` int(11) DEFAULT NULL,
  `check_state` int(11) DEFAULT NULL,
  `check_time` datetime DEFAULT NULL,
  `check_uid` int(11) DEFAULT NULL,
  `check_user_name` varchar(255) DEFAULT NULL,
  `out_id` int(11) DEFAULT NULL,
  `reject_reason` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX2f2n2qo87mwtr00hkuys8ej4m` (`out_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_prm_check_record`
--

LOCK TABLES `t_prm_check_record` WRITE;
/*!40000 ALTER TABLE `t_prm_check_record` DISABLE KEYS */;
INSERT INTO `t_prm_check_record` VALUES (1,'2021-03-31 17:43:37','2021-03-31 17:43:37',0,0,'2021-03-31 17:43:37',6,'admin',0,'string');
/*!40000 ALTER TABLE `t_prm_check_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_prm_contract`
--

DROP TABLE IF EXISTS `t_prm_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_prm_contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `content` varchar(2047) DEFAULT NULL,
  `contract_no` varchar(255) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `partner_id` int(11) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `value` decimal(15,3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXo14ogahjb4jsho1rt82wcqq5y` (`partner_id`),
  KEY `IDXoxwmswhrca34rue11j4lah8ig` (`start_time`),
  KEY `IDXmxj4bhplkm5f8bkqsbqvdunbl` (`end_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_prm_contract`
--

LOCK TABLES `t_prm_contract` WRITE;
/*!40000 ALTER TABLE `t_prm_contract` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_prm_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_prm_partner`
--

DROP TABLE IF EXISTS `t_prm_partner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_prm_partner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `bank_id` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `market_scope` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `payment_address_id` int(11) DEFAULT NULL,
  `phone_country_code` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_prm_partner`
--

LOCK TABLES `t_prm_partner` WRITE;
/*!40000 ALTER TABLE `t_prm_partner` DISABLE KEYS */;
INSERT INTO `t_prm_partner` VALUES (1,'2021-03-31 17:22:04','2021-03-31 17:43:37',0,'string','string','string',0,'string','string',2,0),(2,'2021-03-31 17:30:32','2021-03-31 17:30:32',0,'string','string','string',0,'string','string',1,0),(3,'2021-03-31 17:36:41','2021-03-31 17:36:41',0,'string','string','string',0,'string','string',1,0);
/*!40000 ALTER TABLE `t_prm_partner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_prm_payment_address`
--

DROP TABLE IF EXISTS `t_prm_payment_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_prm_payment_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `partner_id` int(11) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXqc0om6ai72dii3reygvo9y0yt` (`partner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_prm_payment_address`
--

LOCK TABLES `t_prm_payment_address` WRITE;
/*!40000 ALTER TABLE `t_prm_payment_address` DISABLE KEYS */;
INSERT INTO `t_prm_payment_address` VALUES (1,'2021-03-31 17:19:41','2021-03-31 17:19:41','string','string','string',0,'string');
/*!40000 ALTER TABLE `t_prm_payment_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_prm_site`
--

DROP TABLE IF EXISTS `t_prm_site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_prm_site` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `partner_id` int(11) DEFAULT NULL,
  `site_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXcb1tnvo35johxsfralv7nf4ts` (`partner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_prm_site`
--

LOCK TABLES `t_prm_site` WRITE;
/*!40000 ALTER TABLE `t_prm_site` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_prm_site` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-15 22:58:05
