CREATE DATABASE tender_olmero;

CREATE TABLE tender_olmero.offers (
    id INT NOT NULL PRIMARY KEY auto_increment,
    isAccepted INT,
    tender_id INT DEFAULT NULL ,
    bidder_id INT DEFAULT NULL
);

CREATE TABLE tender_olmero.issuers (
    id INT NOT NULL PRIMARY KEY auto_increment,
	issuer_name VARCHAR(255)
);

CREATE TABLE tender_olmero.tenders (
    id INT NOT NULL PRIMARY KEY auto_increment,
	work_description VARCHAR(255),
	issuer_id INT
);

CREATE TABLE tender_olmero.bidders (
    id INT NOT NULL PRIMARY KEY auto_increment,
	bidder_name VARCHAR(255)
);

INSERT INTO issuers (id, issuer_name) values (1, "issuer 1");
INSERT INTO issuers (id, issuer_name) values (2, "issuer 2");

INSERT INTO bidders (id, bidder_name) values (1, "bidder 1");
INSERT INTO bidders (id, bidder_name) values (2, "bidder 2");

INSERT INTO tenders (id, work_description, issuer_id) values (1, "Work description 1", 1);
