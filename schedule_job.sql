CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(100) NOT NULL,
  `corn_expr` varchar(100) NOT NULL,
  `job_group_name` varchar(100) DEFAULT NULL,
  `job_class` varchar(200) DEFAULT NULL,
  `trigger_name` varchar(100) DEFAULT NULL,
  `trigger_group_name` varchar(100) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `app_url` varchar(1000) DEFAULT NULL,
  `http_mehtod` varchar(200) DEFAULT NULL,
  `successful_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

