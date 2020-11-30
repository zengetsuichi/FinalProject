create schema "pricecheckerdummy";
set search_path = 'pricecheckerdummy';

/*define domains for Users*/
CREATE DOMAIN d_username AS varchar(20);
CREATE DOMAIN d_email AS varchar(50);

CREATE DOMAIN d_password AS varchar(20);

CREATE DOMAIN d_dob AS date;
ALTER DOMAIN d_dob ADD CONSTRAINT d_con_dob
CHECK (VALUE >= '01-JAN-1920');

CREATE DOMAIN d_type AS char(11);
ALTER DOMAIN d_type ADD CONSTRAINT d_con_type
CHECK (VALUE IN ('User', 'ShopManager', 'Admin'));

CREATE DOMAIN d_isSubscribed AS char(1);
ALTER DOMAIN d_isSubscribed ADD CONSTRAINT d_con_isSubscribed
CHECK (VALUE = 'T' OR VALUE = 'F');

/*create table User*/
CREATE TABLE users(
    userId SERIAL PRIMARY KEY,
    username d_username NOT null UNIQUE,
    email d_email NOT null UNIQUE,
    password d_password NOT null,
    dob d_dob NOT null,
    type d_type NOT null
);

CREATE TABLE defaultUser(
	userId INT PRIMARY KEY,
    isSubscribed d_isSubscribed  DEFAULT 'F' NOT null,
	FOREIGN KEY (userId) REFERENCES users(userId)
);

/*define domains for Product and Category*/

CREATE DOMAIN d_productName AS varchar(40);
CREATE DOMAIN d_productDescription AS varchar(200);
CREATE DOMAIN d_categoryName AS varchar(40);

/*create table category and product*/
CREATE TABLE category(
	categoryName d_categoryName  PRIMARY KEY
);

CREATE TABLE product(
	productId SERIAL PRIMARY KEY,
    productName d_productName NOT null,
	productDescription d_productDescription,
	categoryName d_categoryName REFERENCES category(categoryName) NOT null
);


/*create table price*/
CREATE TABLE price(
	productId INT NOT null,
    	userId INT REFERENCES users(userId) NOT null,
	price NUMERIC NOT null,
	FOREIGN KEY (productid) REFERENCES product(productid) ON DELETE CASCADE,
	PRIMARY KEY (productId, userId)
);

/*create domain for tag*/
CREATE DOMAIN d_tagName AS varchar(20);

/*create table tag*/
CREATE TABLE tag(
	tagName d_tagName  PRIMARY KEY
);

/*create table productTag*/
CREATE TABLE productTag(
	productId INT NOT null,
    tagName d_tagName REFERENCES tag(tagName) NOT null,
    FOREIGN KEY (productId)
    REFERENCES product(productId)
    ON DELETE CASCADE
);

/*create table shoppingList*/
CREATE TABLE shoppingList(
	shopListId SERIAL PRIMARY KEY,
    userId INT REFERENCES defaultUser(userId) NOT null
);

/*create domain and table shoppingListItem*/

CREATE DOMAIN d_quantity AS INT;
ALTER DOMAIN d_quantity ADD CONSTRAINT d_con_quantity
CHECK (VALUE BETWEEN 0 AND 100);

CREATE TABLE shoppingListItem(
	shopListId INT NOT null,
  productId INT NOT null,
	quantity d_quantity DEFAULT 1,
    FOREIGN KEY (productId)
    REFERENCES product(productId)
    ON DELETE CASCADE
);

/*INSERTING DUMMY DATA*/
INSERT INTO category
VALUES ('Beverages'),
('Meat'),
('Sweets'),
('Coffee');

INSERT INTO tag
VALUES ('drink'),
('beer'),
('coffee'),
('cola');

INSERT INTO product (productName, productDescription, categoryName)
VALUES ('Cola 330', 'Cola 330 ml, can, best for parties','Beverages'),
('Fanta 1l', 'Fanta, bottle, german engineering','Beverages'),
('Nescafe 1kg', 'Nescafe, worst coffe for even worser times','Coffee');

INSERT INTO users ( username, email, password, dob, type)
VALUES ( 'Admin', 'admin@gmail.com','admin1','01-02-2003','Admin'),
( 'Netto', 'netto@gmail.com','admin1','01-02-2003','ShopManager'),
( 'Lidl', 'lidl@gmail.com','lidl1','01-02-2003','ShopManager'),
( 'User1', 'user1@gmail.com','user1','01-02-2003','User'),
('User2', 'user2@gmail.com','user2','01-02-2003','User');


INSERT INTO price (productId, userId, price)
VALUES (1, 2, 105),
(2, 2, 155),
(3, 2, 55),
(1, 3, 100),
(2, 3, 150),
(3, 3, 50);

INSERT INTO productTag(productId, tagName)
VALUES (1, 'cola' ),
(1, 'drink' ),
(2,'drink'),
(3,'coffee'),
(3,'drink');

INSERT INTO defaultUser
VALUES (4, 'F'),
(5, 'F');

INSERT INTO shoppingList(userId)
VALUES (4),
(5);

INSERT INTO shoppingListItem(shopListId, productId, quantity)
VALUES (1, 3, 1),
(1,1,1),
(2,2,1),
(2,3,1);




