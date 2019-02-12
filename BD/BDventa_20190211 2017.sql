-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.17-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema venta
--

CREATE DATABASE IF NOT EXISTS venta;
USE venta;

--
-- Definition of table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE `categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `denominacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categoria`
--

/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` (`id`,`denominacion`) VALUES 
 (1,'BEBIDAS'),
 (3,'LACTEOS'),
 (4,'ULTILES ESCOLARES'),
 (5,'LIMPIEZA'),
 (7,'EMBUTIDOS');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;


--
-- Definition of table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(30) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `direccion` varchar(40) NOT NULL,
  `dni` char(8) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cliente`
--

/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`id`,`nombres`,`apellidos`,`direccion`,`dni`) VALUES 
 (1,'Santiago','Cacha','Huaraz 1','00000001'),
 (2,'Marquinho','Moya Rivera','Jr Ricardo Palma 412','72437799'),
 (16,'Simba','Jean Pier','Hz','12345678');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;


--
-- Definition of table `detalle`
--

DROP TABLE IF EXISTS `detalle`;
CREATE TABLE `detalle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `precio` decimal(10,2) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `subtotal` decimal(9,2) DEFAULT NULL,
  `producto_id` int(11) NOT NULL,
  `factura_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_detalle_producto1_idx` (`producto_id`),
  KEY `fk_detalle_factura1_idx` (`factura_id`),
  CONSTRAINT `fk_detalle_factura1_idx` FOREIGN KEY (`factura_id`) REFERENCES `factura` (`id`),
  CONSTRAINT `fk_detalle_producto1_idx` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `detalle`
--

/*!40000 ALTER TABLE `detalle` DISABLE KEYS */;
INSERT INTO `detalle` (`id`,`precio`,`cantidad`,`subtotal`,`producto_id`,`factura_id`) VALUES 
 (6,'10.00',10,'100.00',1,1),
 (8,'5.00',20,'20.00',34,1),
 (12,'5.00',2,'10.00',42,2),
 (13,'2.50',2,'5.00',39,2),
 (14,'10.00',3,'30.00',44,2),
 (15,'2.50',1,'2.50',39,2),
 (16,'10.00',4,'40.00',44,2),
 (17,'2.50',2,'5.00',39,3),
 (18,'2.20',2,'4.40',34,3),
 (19,'1.00',1,'1.00',43,3);
/*!40000 ALTER TABLE `detalle` ENABLE KEYS */;


--
-- Definition of table `documento`
--

DROP TABLE IF EXISTS `documento`;
CREATE TABLE `documento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `documento`
--

/*!40000 ALTER TABLE `documento` DISABLE KEYS */;
INSERT INTO `documento` (`id`,`nombre`) VALUES 
 (1,'BOLETA'),
 (2,'FACTURA');
/*!40000 ALTER TABLE `documento` ENABLE KEYS */;


--
-- Definition of table `factura`
--

DROP TABLE IF EXISTS `factura`;
CREATE TABLE `factura` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nrofactura` int(7) NOT NULL,
  `fecha` date DEFAULT NULL,
  `subtotal` decimal(9,2) DEFAULT NULL,
  `igv` decimal(9,2) DEFAULT NULL,
  `total` decimal(9,2) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `cliente_id` int(11) NOT NULL,
  `documento_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_factura_1` (`documento_id`),
  KEY `FK_factura_2` (`cliente_id`),
  CONSTRAINT `FK_factura_1` FOREIGN KEY (`documento_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FK_factura_2` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `factura`
--

/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
INSERT INTO `factura` (`id`,`nrofactura`,`fecha`,`subtotal`,`igv`,`total`,`estado`,`cliente_id`,`documento_id`) VALUES 
 (1,500,'2019-02-14','101.69','18.31','120.00',1,1,2),
 (2,501,'1970-01-01','74.15','13.35','87.50',1,1,1),
 (3,502,'1970-01-01','8.81','1.59','10.40',1,16,1);
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;


--
-- Definition of table `producto`
--

DROP TABLE IF EXISTS `producto`;
CREATE TABLE `producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `stock` int(11) NOT NULL,
  `categoria_id` int(11) NOT NULL,
  `precio` decimal(9,2) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_producto_1` (`categoria_id`),
  CONSTRAINT `FK_producto_1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `producto`
--

/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` (`id`,`nombre`,`stock`,`categoria_id`,`precio`,`estado`) VALUES 
 (1,'INCA KOLA',24,1,'2.20',1),
 (34,'LECHE GLORIA',24,3,'2.20',1),
 (39,'COCA COLA',24,1,'2.50',1),
 (40,'producto_X',20,7,'12.00',0),
 (41,'Producto0',20,5,'20.00',0),
 (42,'YOGURT LAIVE',20,3,'5.00',1),
 (43,'BETUN SAPOLIO',12,5,'1.00',1),
 (44,'TRAPEADOR',20,5,'10.00',1),
 (45,'JAMON DE CHANCHO',500,7,'120.00',0);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
