-- Drop tables if they exist
DROP TABLE IF EXISTS user_ticket CASCADE;
DROP TABLE IF EXISTS lottery CASCADE;

CREATE TABLE lottery (
    ticketid VARCHAR(6) PRIMARY KEY NOT NULL,
    price INT NOT NULL,
    amount INT NOT NULL
);

CREATE TABLE user_ticket (
    id SERIAL PRIMARY KEY,
    userid INT NOT NULL,
    ticketid VARCHAR(6) NOT NULL,
    price INT NOT NULL
);

-- Initialize lottery table
INSERT INTO lottery (ticketid, price, amount) VALUES ('153647', 100, 2);
INSERT INTO lottery (ticketid, price, amount) VALUES ('153648', 200, 3);
INSERT INTO lottery (ticketid, price, amount) VALUES ('153649', 150, 1);