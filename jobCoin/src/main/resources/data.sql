DROP TABLE IF EXISTS addressStore;
DROP TABLE IF EXISTS depositAddress;


CREATE TABLE addressStore (
  depositAddress INT AUTO_INCREMENT  PRIMARY KEY,
  adresses VARCHAR(250) NOT NULL
);

CREATE TABLE addressStore (
  address_id INT AUTO_INCREMENT  PRIMARY KEY,
  adressKey VARCHAR(250) NOT NULL,
  adresses VARCHAR(250) NOT NULL
);

INSERT INTO addressStore (adressKey, adresses) VALUES
  ('address', 'address1'),
  ('abcd', 'cds1'),
  ('fdsafd', 'fdsafds');

INSERT INTO addressStore (adressKey, adresses) VALUES
  ('address', 'address1'),
  ('abcd', 'cds1'),
  ('fdsafd', 'fdsafds');