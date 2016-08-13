SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS sequence;




/* Create Tables */

CREATE TABLE `sequence` (
  `key` varchar(20) NOT NULL,
  `prefix` varchar(20) DEFAULT NULL,
  `curr_value` int(8) NOT NULL,
  `suffix` varchar(20) DEFAULT NULL,
  `increment` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



