drop database pothole;
create database pothole;

CREATE TABLE pothole.geotab (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	place_name VARCHAR(128) NOT NULL,
	area POLYGON NULL,
	reg_dt DATETIME NOT NULL,
	mod_dt DATETIME NOT NULL
);

CREATE TABLE pothole.pothole (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	gid BIGINT REFERENCES pothole.geotab (id),
	x_acc DECIMAL NOT NULL,
	y_acc DECIMAL NOT NULL,
	z_acc DECIMAL NOT NULL,
-- 	video_url VARCHAR(256) NOT NULL,
	image_url VARCHAR(256) NOT NULL,
	point POINT NULL,
	state VARCHAR(2) NOT NULL,
	reg_dt DATETIME NOT NULL,
	mod_dt DATETIME NOT NULL
);