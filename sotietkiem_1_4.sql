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
  `IDKhachHang` varchar(10) COLLATE ucs2_unicode_ci NOT NULL,
  `TenKhachHang` varchar(50) CHARACTER SET utf8 NOT NULL,
  `DiaChi` varchar(45) COLLATE ucs2_unicode_ci NOT NULL,
  `CMND` varchar(10) COLLATE ucs2_unicode_ci NOT NULL,
  `NgaySinh` date DEFAULT NULL,
  `QuocTich` varchar(45) COLLATE ucs2_unicode_ci NOT NULL,
  `QueQuan` varchar(15) CHARACTER SET utf8 DEFAULT NULL,
  `NgheNghiep` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `IsActive` bit(1) DEFAULT b'1',
  PRIMARY KEY (`IDKhachHang`)
) ENGINE=InnoDB DEFAULT CHARSET=ucs2 COLLATE=ucs2_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES ('1','1','112','1','2018-03-22','','','',''),('2','','2','','2018-03-15','2','','',''),('3','2','2','','2018-03-21','2','','',''),('4','','4','','2018-03-14','','','','');
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
  `TenLTK` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Tên loại tiết kiệm',
  `SoNgayRut` int(3) NOT NULL COMMENT 'Số ngày được rút tiền',
  `LaiSuat` float NOT NULL,
  `LaiSuatTruocHan` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KyHan` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Đơn vị tháng',
  `GuiThem` bit(1) DEFAULT NULL COMMENT 'Gửi thêm - chế độ không thời hạn',
  `IsActive` bit(1) NOT NULL DEFAULT b'1',
  `IDThamSo` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`IDLoaiTietKiem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaitietkiem`
--

LOCK TABLES `loaitietkiem` WRITE;
/*!40000 ALTER TABLE `loaitietkiem` DISABLE KEYS */;
/*!40000 ALTER TABLE `loaitietkiem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `user` varchar(50) CHARACTER SET utf8 NOT NULL,
  `pass` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `idGroup` int(2) DEFAULT NULL COMMENT 'id nhóm quyền',
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('b','c',2),('c','c',1);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieugui`
--

DROP TABLE IF EXISTS `phieugui`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phieugui` (
  `IDPhieuGui` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `IDSoTK` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Mã sổ tiết kiệm',
  `SoTienGui` float NOT NULL,
  `NgayGui` date NOT NULL,
  `NguoiGuiTien` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT 'Người gửi thay',
  PRIMARY KEY (`IDPhieuGui`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieugui`
--

LOCK TABLES `phieugui` WRITE;
/*!40000 ALTER TABLE `phieugui` DISABLE KEYS */;
/*!40000 ALTER TABLE `phieugui` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieurut`
--

DROP TABLE IF EXISTS `phieurut`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phieurut` (
  `IDPhieuRut` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `IDSoTK` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Mã sổ tk',
  `SoTienRut` float NOT NULL,
  `NgayRut` date NOT NULL,
  `NguoiRut` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`IDPhieuRut`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieurut`
--

LOCK TABLES `phieurut` WRITE;
/*!40000 ALTER TABLE `phieurut` DISABLE KEYS */;
/*!40000 ALTER TABLE `phieurut` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sotietkiem`
--

DROP TABLE IF EXISTS `sotietkiem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sotietkiem` (
  `IDSTK` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '''Mã stk''',
  `SoTienGuiTK` float NOT NULL COMMENT 'Số tiền gửi ban đầu',
  `NgayMo` date NOT NULL COMMENT '''Ngày mở sổ''',
  `DongSo` tinyint(1) NOT NULL DEFAULT '0' COMMENT '''Kiểm tra sổ đã đóng hay chưa''',
  `IDKH` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '''Mã khách hàng''',
  `IDLoaiTien` varchar(5) COLLATE utf8_unicode_ci NOT NULL COMMENT '''Mã loại tiền''',
  `IDLoaiTietKiem` int(1) NOT NULL COMMENT '''Mã loại tiết kiệm''',
  `NgayTinhLai` date DEFAULT NULL COMMENT 'Ngày tính lãi suất',
  `NgayDenHan` date DEFAULT NULL COMMENT 'Ngày đến hạn rút lãi',
  `TongTien` float NOT NULL COMMENT 'Tổng lãi suất + gốc',
  `IsActive` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`IDSTK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sotietkiem`
--

LOCK TABLES `sotietkiem` WRITE;
/*!40000 ALTER TABLE `sotietkiem` DISABLE KEYS */;
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
  `TenQuyDinh` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SoTienGuiToiThieu` float NOT NULL,
  `SoTienGuiToiDa` float NOT NULL,
  `SoTienGuiThemToiThieu` float NOT NULL,
  PRIMARY KEY (`IDQuyDinhSoTien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thamso`
--

LOCK TABLES `thamso` WRITE;
/*!40000 ALTER TABLE `thamso` DISABLE KEYS */;
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-01 18:26:06
