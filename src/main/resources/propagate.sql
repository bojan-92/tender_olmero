CREATE DATABASE tender_olmero;

CREATE TABLE tender_olmero.offers (
    id INT NOT NULL PRIMARY KEY auto_increment,
    status VARCHAR (50) NOT NULL ,
    price INT NOT NULL ,
    tender_id INT DEFAULT NULL ,
    bidder_id INT DEFAULT NULL
);

CREATE TABLE tender_olmero.issuers (
    id INT NOT NULL PRIMARY KEY auto_increment,
	issuer_name VARCHAR(255)
);

CREATE TABLE tender_olmero.tenders (
    id INT NOT NULL PRIMARY KEY auto_increment,
	work_description VARCHAR(255) NOT NULL ,
	issuer_id INT
);

CREATE TABLE tender_olmero.bidders (
    id INT NOT NULL PRIMARY KEY auto_increment,
	bidder_name VARCHAR(255)
);

INSERT INTO issuers (id, issuer_name) values (1, "issuer 1");

INSERT INTO bidders (id, bidder_name) values (1, "bidder 1");

INSERT INTO tenders (id, work_description, issuer_id) values (1, "Work description 1", 1);
INSERT INTO tenders (id, work_description, issuer_id) values (2, "Work description 2", 1);


INSERT INTO offers (id, status, price, tender_id, bidder_id) values (1, "NON_SUBMITTED", 20000, 1, 1);
INSERT INTO offers (id, status, price, tender_id, bidder_id) values (2, "NON_SUBMITTED", 30000, 1, 1);
INSERT INTO offers (id, status, price, tender_id, bidder_id) values (3, "NON_SUBMITTED", 20000, 1, 1);

INSERT INTO offers (id, status, price, tender_id, bidder_id) values (4, "REJCETED", 50000, 2, 1);
INSERT INTO offers (id, status, price, tender_id, bidder_id) values (5, "SUBMITTED", 60000, 2, 1);

