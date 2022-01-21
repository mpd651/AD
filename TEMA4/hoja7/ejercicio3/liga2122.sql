-- MariaDB dump 10.19  Distrib 10.5.9-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: liga2122
-- ------------------------------------------------------
-- Server version	10.5.9-MariaDB-1:10.5.9+maria~focal

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `equipos`
--

DROP TABLE IF EXISTS `equipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipos` (
  `codigo` char(3) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `localidad` varchar(30) NOT NULL,
  `estadio` varchar(30) DEFAULT NULL,
  `escudo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipos`
--

LOCK TABLES `equipos` WRITE;
/*!40000 ALTER TABLE `equipos` DISABLE KEYS */;
INSERT INTO `equipos` VALUES ('ALA','Deportivo Alavés','Vitoria','Mendizorroza',NULL),('ATH','Athletic Club','Bilbao','San Mamés',NULL),('ATM','Atlético de Madrid','Madrid','Wanda Metropolitano',NULL),('BAR','Barcelona','Barcelona','Camp Nou',NULL),('BET','Real Betis',' Sevilla','Benito Villamarín',NULL),('CAD','Cádiz','Cádiz','Estadio Nuevo Mirandilla',NULL),('CEL','Celta de Vigo','Vigo','Abanca-Balaídos',NULL),('ELC','Elche',' Elche','Martínez Valero',NULL),('ESP','Espanyol',' BarcelonaA​','RCDE Stadium',NULL),('GET','Getafe',' Getafe','Coliseum Alfonso Pérez',NULL),('GRA','Granada',' Granada','Nuevo Los Cármenes',NULL),('LEV','Levante',' Valencia','Ciutat de València',NULL),('MAL','Mallorca',' Palma de Mallorca','Son Moix',NULL),('OSA','Osasuna',' Pamplona','El Sadar',NULL),('RAY','Rayo Vallecano',' Madrid','Estadio de Vallecas',NULL),('RMA','Real Madrid',' Madrid','Santiago Bernabéu',NULL),('RSO','Real Sociedad',' San Sebastián','Reale Arena',NULL),('SEV','Sevilla',' Sevilla','Estadio Ramón Sánchez-Pizjuán',NULL),('VAL','Valencia',' Valencia','Mestalla',NULL),('VIL','Villarreal',' Villarreal','Estadio de la Cerámica',NULL);
/*!40000 ALTER TABLE `equipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partidos`
--

DROP TABLE IF EXISTS `partidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partidos` (
  `numero_jornada` tinyint(4) NOT NULL,
  `numero_partido` tinyint(4) NOT NULL,
  `equipo_local` char(3) DEFAULT NULL,
  `equipo_visitante` char(3) DEFAULT NULL,
  `goles_local` tinyint(4) DEFAULT NULL,
  `goles_visitante` tinyint(4) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`numero_jornada`,`numero_partido`),
  KEY `fk_local_equipos` (`equipo_local`) USING BTREE,
  KEY `fk_vist_equipos` (`equipo_visitante`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partidos`
--

LOCK TABLES `partidos` WRITE;
/*!40000 ALTER TABLE `partidos` DISABLE KEYS */;
INSERT INTO `partidos` VALUES (1,1,'VAL','GET',NULL,NULL,NULL),(1,2,'MAL','BET',NULL,NULL,NULL),(1,3,'CAD','LEV',NULL,NULL,NULL),(1,4,'ALA','RMA',NULL,NULL,NULL),(1,5,'OSA','ESP',NULL,NULL,NULL),(1,6,'CEL','ATM',NULL,NULL,NULL),(1,7,'BAR','RSO',NULL,NULL,NULL),(1,8,'SEV','RAY',NULL,NULL,NULL),(1,9,'VIL','GRA',NULL,NULL,NULL),(1,10,'ELC','ATH',NULL,NULL,NULL);
/*!40000 ALTER TABLE `partidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'liga2122'
--
/*!50003 DROP PROCEDURE IF EXISTS `siguienteJornada` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `siguienteJornada`(IN nj_ant INT)
BEGIN
		
DECLARE nj INT;
DECLARE i INT;
DECLARE eq CHAR(3);

IF nj_ant>0 AND nj_ant<19 THEN
	IF (SELECT count(*) FROM partidos WHERE numero_jornada=nj_ant) = 10 THEN
		SET nj=nj_ant+1;
        SET i=1;
        WHILE i<=10 DO
			INSERT INTO partidos SET numero_jornada=nj, numero_partido=i;
			SET i=i+1;
		END WHILE;
		
		IF nj_ant % 2 = 1 THEN
			SELECT equipo_local INTO eq FROM partidos WHERE numero_jornada=nj_ant AND numero_partido=10;
			UPDATE partidos SET equipo_visitante=eq WHERE numero_jornada=nj AND numero_partido=1;
 			SET i=2;
       		WHILE i <=10 DO
				SELECT equipo_local INTO eq FROM partidos WHERE numero_jornada=nj_ant AND numero_partido=i-1;
				UPDATE partidos SET equipo_visitante=eq WHERE numero_jornada=nj AND numero_partido=i;
				SET i=i+1;
       		END WHILE;
       		SET i=1;
       		WHILE i <=10 DO
				SELECT equipo_visitante INTO eq FROM partidos WHERE numero_jornada=nj_ant AND numero_partido=i;
				UPDATE partidos SET equipo_local=eq WHERE numero_jornada=nj AND numero_partido=i;
				SET i=i+1;
			END WHILE;
		
		ELSE
			SELECT equipo_visitante INTO eq FROM partidos WHERE numero_jornada=nj_ant AND numero_partido=10;
			UPDATE partidos SET equipo_visitante=eq WHERE numero_jornada=nj AND numero_partido=10;
            
			SELECT equipo_visitante INTO eq FROM partidos WHERE numero_jornada=nj_ant AND numero_partido=1;
			UPDATE partidos SET equipo_local=eq WHERE numero_jornada=nj AND numero_partido=10;
            
			SELECT equipo_local INTO eq FROM partidos WHERE numero_jornada=nj_ant AND numero_partido=1;
			UPDATE partidos SET equipo_local=eq WHERE numero_jornada=nj AND numero_partido=1;

			SET i=1;
			WHILE i <=10 DO
				SELECT equipo_local INTO eq FROM partidos WHERE numero_jornada=nj_ant AND numero_partido=i+1;
				UPDATE partidos SET equipo_visitante=eq WHERE numero_jornada=nj AND numero_partido=i;
				IF i>1 THEN
					SELECT equipo_visitante INTO eq FROM partidos WHERE numero_jornada=nj_ant AND numero_partido=i;
					UPDATE partidos SET equipo_local=eq WHERE numero_jornada=nj AND numero_partido=i;
				END IF;
				SET i=i+1;
			END WHILE;
		END IF;
    ELSE 
		SELECT 'ERROR, no se puede generar nuava jornada ya que la anterior no tiene 10 partidos';
	END IF;
ELSE 
	SELECT 'ERROR, el número de jornada anterior debe estar comprendido entre 1 y 18';
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-22 12:48:54
