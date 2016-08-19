SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS sflow_sequence;




/* Create Tables */

CREATE TABLE `sflow_sequence` (
  `sequence_name` varchar(20) NOT NULL,
  `prefix` varchar(20) DEFAULT NULL,
  `curr_value` bigint(20) NOT NULL,
  `suffix` varchar(20) DEFAULT NULL,
  `lpad_length` int(2) NOT NULL DEFAULT 1,
  `increment` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



/* Create Sequence Procedure */
DROP procedure IF EXISTS `nextval`;

DELIMITER $$
CREATE PROCEDURE nextval(IN in_sequence_name varchar(20),
 OUT out_prefix varchar(20),
 OUT out_curr_value bigint(20),
 OUT out_suffix varchar(20),
 OUT out_lpad_length varchar(20))
BEGIN
	-- get current sequence value
 	select prefix,curr_value,suffix,lpad_length 
      INTO out_prefix,out_curr_value,out_suffix,out_lpad_length
      from sflow_sequence 
	 WHERE sequence_name = in_sequence_name;
     
	-- update current sequence value
	UPDATE sflow_sequence t
	   SET t.curr_value = t.curr_value + t.increment  
	 WHERE t.sequence_name = in_sequence_name;
	
    commit;
END$$

DELIMITER ;


/*  Insert test data */
INSERT INTO sflow_sequence (`sequence_name`,`prefix`,`curr_value`,`suffix`,`lpad_length`,`increment`)
VALUES ('test','q',1,'a',8,1);


-- test sql:
CALL nextval('test',@prefix,@curr_value,@suffix,@lpad_length);
SELECT @prefix,@curr_value,@suffix,@lpad_length;








