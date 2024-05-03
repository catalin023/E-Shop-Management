CREATE TABLE `E-Shop`.`PRODUCT` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `distributorId` INT,
    `name` VARCHAR(255),
    `category` VARCHAR(255),
    `priceBuy` INT
);

CREATE TABLE `E-Shop`.`DISTRIBUTOR`(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(255)
);

create table `E-Shop`.`SHOP_PRODUCT`(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `productId` INT,
    `distributorId` INT,
    `name` VARCHAR(255),
    `category` VARCHAR(255),
    `priceBuy` INT,
    `priceSell` INT,
    `quantity` INT
);

create table `E-Shop`.`ADMIN`(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(255),
    `email` VARCHAR(255) UNIQUE,
    `password` VARCHAR(255)
);

create table `E-Shop`.`USER`(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(255),
    `email` VARCHAR(255) UNIQUE,
    `password` VARCHAR(255),
    `balance` FLOAT
);


create table `E-Shop`.`WISHLIST`(
    `userId` INT,
    `productId` INT,
    `quantity` INT,
    PRIMARY KEY (`userId`, `productId`),
    FOREIGN KEY (`userId`) REFERENCES  `E-Shop`.`USER`(`id`),
    FOREIGN KEY (`productId`) REFERENCES `E-Shop`.`SHOP_PRODUCT`(`id`)
);

create table `E-Shop`.`SHOP` (
    `balance` FLOAT
);

ALTER TABLE `E-Shop`.`PRODUCT`
ADD FOREIGN KEY (distributorId) REFERENCES `E-Shop`.`DISTRIBUTOR`(id);

DROP table WISHLIST;
drop table SHOP_PRODUCT


