DROP TABLE IF EXISTS t_file_info;
CREATE TABLE t_file_info (
id INT AUTO_INCREMENT  PRIMARY KEY,
file_name VARCHAR(50) NOT NULL,
file_extension VARCHAR(50) ,
file_path VARCHAR(100) ,
file_size INT(8) NOT NULL
);