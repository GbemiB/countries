DROP TABLE IF EXISTS COUNTRIES;
 
CREATE TABLE COUNTRIES (
  id INT AUTO_INCREMENT PRIMARY KEY,
  country_name VARCHAR(250)  NOT NULL,
  country_code VARCHAR(250) NOT NULL
);