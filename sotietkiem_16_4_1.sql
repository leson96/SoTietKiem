CREATE DATABASE  IF NOT EXISTS `sotietkiem` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `sotietkiem`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sotietkiem
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `idGroup` int(2) NOT NULL,
  `ten_group` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idGroup`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `khachhang` (
  `IDKhachHang` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenKhachHang` varchar(50) CHARACTER SET utf8 NOT NULL,
  `DiaChi` varchar(45) CHARACTER SET ucs2 COLLATE ucs2_unicode_ci NOT NULL,
  `CMND` varchar(10) CHARACTER SET ucs2 COLLATE ucs2_unicode_ci NOT NULL,
  `NgaySinh` date NOT NULL,
  `QuocTich` varchar(45) CHARACTER SET ucs2 COLLATE ucs2_unicode_ci DEFAULT NULL,
  `QueQuan` varchar(15) CHARACTER SET utf8 DEFAULT NULL,
  `NgheNghiep` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `IsActive` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`IDKhachHang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES ('1','mot','mot','123','2018-04-28',NULL,NULL,NULL,1),('12','123','123','123','2018-04-19','','','',0),('123','mot hai ba','123','3','2018-04-16','','','',1),('4','4','4','4','2018-04-28','','','',0);
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaitietkiem`
--

DROP TABLE IF EXISTS `loaitietkiem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loaitietkiem` (
  `IDLoaiTietKiem` int(1) NOT NULL,
  `TenLTK` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `SoNgayRut` int(3) NOT NULL,
  `LaiSuat` float NOT NULL,
  `LaiSuatTruocHan` float DEFAULT NULL,
  `KyHan` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Don vi thang',
  `GuiThem` tinyint(4) NOT NULL COMMENT 'Gui them - khong thoi han',
  `TienGuiToiThieu` float NOT NULL,
  `IsActive` tinyint(4) NOT NULL DEFAULT '1',
  `IDThamSo` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`IDLoaiTietKiem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaitietkiem`
--

LOCK TABLES `loaitietkiem` WRITE;
/*!40000 ALTER TABLE `loaitietkiem` DISABLE KEYS */;
INSERT INTO `loaitietkiem` VALUES (0,'Không thời hạn',15,1,1,'0',1,100000,1,''),(3,'3 tháng',30,5.5,1,'3',0,500000,1,''),(6,'6 tháng',30,6.2,1,'6',0,500000,1,''),(9,'9 tháng',30,6.4,1,'9',0,500000,1,''),(12,'12 tháng',30,7,1,'12',0,500000,1,''),(18,'18 tháng',30,7.2,1,'18',0,500000,1,''),(24,'24 tháng',30,7.4,1,'24',0,500000,1,'');
/*!40000 ALTER TABLE `loaitietkiem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nhanvien` (
  `id` varchar(20) CHARACTER SET utf8 NOT NULL,
  `pass` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `age` int(11) DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idGroup` int(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk-idGroup_idx` (`idGroup`),
  CONSTRAINT `fk-idGroup` FOREIGN KEY (`idGroup`) REFERENCES `group` (`idGroup`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieugui`
--

DROP TABLE IF EXISTS `phieugui`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phieugui` (
  `IDPhieuGui` int(11) NOT NULL AUTO_INCREMENT,
  `IDSoTK` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `SoTienGui` float NOT NULL,
  `NgayGui` date NOT NULL,
  `NguoiGuiTien` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`IDPhieuGui`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieugui`
--

LOCK TABLES `phieugui` WRITE;
/*!40000 ALTER TABLE `phieugui` DISABLE KEYS */;
INSERT INTO `phieugui` VALUES (11,'1',500000,'2018-04-17','nvq'),(12,'1',500000,'2018-04-16','sd');
/*!40000 ALTER TABLE `phieugui` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `sotietkiem`.`phieugui_AFTER_INSERT` AFTER INSERT ON `phieugui` FOR EACH ROW
BEGIN
	UPDATE sotietkiem 
    SET TongTien = TongTien + NEW.SoTienGui WHERE IDSTK = NEW.IDSoTK;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `phieurut`
--

DROP TABLE IF EXISTS `phieurut`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phieurut` (
  `IDPhieuRut` int(11) NOT NULL AUTO_INCREMENT,
  `IDSoTK` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Mã sổ tk',
  `SoTienRut` float NOT NULL,
  `NgayRut` date NOT NULL,
  `NguoiRut` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`IDPhieuRut`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieurut`
--

LOCK TABLES `phieurut` WRITE;
/*!40000 ALTER TABLE `phieurut` DISABLE KEYS */;
INSERT INTO `phieurut` VALUES (1,'1',100000,'2018-04-10','112'),(2,'1',123456,'2018-05-01',''),(3,'1',5000000,'2018-04-08','454'),(4,'1',500000,'2018-04-04','ád'),(5,'1',1000000,'2018-04-18','ád');
/*!40000 ALTER TABLE `phieurut` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `sotietkiem`.`phieurut_AFTER_INSERT` AFTER INSERT ON `phieurut` FOR EACH ROW
BEGIN
	UPDATE sotietkiem 
    SET TongTien = TongTien - NEW.SoTienRut WHERE IDSTK = NEW.IDSoTK;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `sotietkiem`
--

DROP TABLE IF EXISTS `sotietkiem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sotietkiem` (
  `IDSTK` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `IDLoaiTietKiem` int(1) NOT NULL,
  `IDKH` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `NgayMo` date DEFAULT NULL,
  `DongSo` tinyint(1) NOT NULL DEFAULT '0',
  `LoaiTien` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `NgayTinhLai` date DEFAULT NULL COMMENT 'Ngày tính lãi suất',
  `NgayDenHan` date DEFAULT NULL COMMENT 'Ngày đến hạn rút lãi',
  `TongTien` float NOT NULL,
  `IsActive` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`IDSTK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sotietkiem`
--

LOCK TABLES `sotietkiem` WRITE;
/*!40000 ALTER TABLE `sotietkiem` DISABLE KEYS */;
INSERT INTO `sotietkiem` VALUES ('1',0,'1','2018-04-28',0,'VND','2018-04-28','2018-05-28',100,1),('2',3,'123','2018-04-30',0,'USSD','2018-04-30','2018-05-30',5000000,1);
/*!40000 ALTER TABLE `sotietkiem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thamso`
--

DROP TABLE IF EXISTS `thamso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thamso` (
  `IDQuyDinhSoTien` int(11) NOT NULL,
  `TenQuyDinh` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `SoTienGuiToiThieu` float NOT NULL,
  `SoTienGuiToiDa` float NOT NULL,
  PRIMARY KEY (`IDQuyDinhSoTien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thamso`
--

LOCK TABLES `thamso` WRITE;
/*!40000 ALTER TABLE `thamso` DISABLE KEYS */;
INSERT INTO `thamso` VALUES (1,'TH',500000,50000000),(2,'KTH',500000,50000000),(3,'Tiền rút',100000,20000000);
/*!40000 ALTER TABLE `thamso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tinhlai`
--

DROP TABLE IF EXISTS `tinhlai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tinhlai` (
  `idTinhlai` int(11) NOT NULL,
  `IDSTK` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `SoTienGoc` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `TongLaiSuat` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idTinhlai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tinhlai`
--

LOCK TABLES `tinhlai` WRITE;
/*!40000 ALTER TABLE `tinhlai` DISABLE KEYS */;
/*!40000 ALTER TABLE `tinhlai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sotietkiem'
--

--
-- Dumping routines for database 'sotietkiem'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-16 22:16:52
