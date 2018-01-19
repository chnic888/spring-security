CREATE TABLE authorized_user (
	id INT PRIMARY KEY AUTO_INCREMENT,
	client_id VARCHAR(50) NOT NULL,
	client_secret VARCHAR(200) NOT NULL,
	authorized_grant_type VARCHAR(200) NOT NULL,
	scope VARCHAR(200) NOT NULL,
	enable BIT NOT NULL DEFAULT 1
)