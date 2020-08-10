CREATE TABLE CUSTOMERS (
  ID int(11) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  active boolean NOT NULL DEFAULT true
);


CREATE TABLE IP_BLACKLIST (
  IP bigint(11) unsigned NOT NULL PRIMARY KEY
);

CREATE TABLE USER_AGENT_BLACKLIST (
  userAgent varchar(255) NOT NULL PRIMARY KEY
);

CREATE TABLE HOURLY_STATS (
  ID int(11) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  customer_id int(11) unsigned NOT NULL,
  time timestamp NOT NULL,
  request_count bigint(20) unsigned NOT NULL DEFAULT '0',
  invalid_count bigint(20) unsigned NOT NULL DEFAULT '0'
);

CREATE TABLE REQUESTS (
  ID int(11)  NOT NULL AUTO_INCREMENT,
  customer_id int(11) NOT NULL,
  tag_id int(11) NOT NULL,
  user_id VARCHAR(255),
  IP varchar(255),
  time int(11),
  valid boolean
);