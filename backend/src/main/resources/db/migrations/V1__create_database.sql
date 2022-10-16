-- intcomex.suppliers definition

CREATE TABLE `suppliers` (
  `supplier_id` int NOT NULL AUTO_INCREMENT,
  `company_name` varchar(100) DEFAULT NULL,
  `contact_name` varchar(100) DEFAULT NULL,
  `contact_title` varchar(100) DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `region` varchar(100) DEFAULT NULL,
  `postal_code` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `phone` int DEFAULT NULL,
  `fax` int DEFAULT NULL,
  `home_page` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`supplier_id`),
  KEY `suppliers_company_name_IDX` (`company_name`) USING BTREE,
  KEY `suppliers_postal_code_IDX` (`postal_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- intcomex.categories definition

CREATE TABLE `categories` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `picture` text,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `categories_UN` (`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- intcomex.employees definition

CREATE TABLE `employees` (
  `employee_id` int NOT NULL AUTO_INCREMENT,
  `last_name` varchar(100) NOT NULL,
  `first_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `title_of_courtesy` varchar(100) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `hire_date` date DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `region` varchar(100) DEFAULT NULL,
  `postal_code` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `home_phone` int DEFAULT NULL,
  `extension` varchar(100) DEFAULT NULL,
  `photo` text,
  `notes` varchar(100) DEFAULT NULL,
  `reports_to` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `employees_last_name_IDX` (`last_name`) USING BTREE,
  KEY `employees_postal_code_IDX` (`postal_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- intcomex.shippers definition

CREATE TABLE `shippers` (
  `shipper_id` int NOT NULL AUTO_INCREMENT,
  `company_name` varchar(100) NOT NULL,
  `phone` int DEFAULT NULL,
  PRIMARY KEY (`shipper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- intcomex.customers definition

CREATE TABLE `customers` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `company_name` varchar(100) NOT NULL,
  `contact_name` varchar(100) DEFAULT NULL,
  `contact_title` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `region` varchar(100) DEFAULT NULL,
  `postal_code` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `phone` int DEFAULT NULL,
  `fax` int DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  KEY `customers_city_IDX` (`city`) USING BTREE,
  KEY `customers_region_IDX` (`region`) USING BTREE,
  KEY `customers_postal_code_IDX` (`postal_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- intcomex.products definition

CREATE TABLE `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `supplier_id` int NOT NULL,
  `category_id` int NOT NULL,
  `quantity_per_unit` int DEFAULT NULL,
  `unit_price` int DEFAULT NULL,
  `units_in_stock` int DEFAULT NULL,
  `units_on_order` int DEFAULT NULL,
  `reorder_level` int DEFAULT NULL,
  `discontinued` tinyint DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `products_FK` (`supplier_id`),
  KEY `products_FK_1` (`category_id`),
  CONSTRAINT `products_FK` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`supplier_id`),
  CONSTRAINT `products_FK_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- intcomex.orders definition

CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `employee_id` int NOT NULL,
  `order_date` date DEFAULT NULL,
  `required_date` date DEFAULT NULL,
  `shipped_date` date DEFAULT NULL,
  `ship_via` int NOT NULL,
  `freight` varchar(100) DEFAULT NULL,
  `ship_name` varchar(100) DEFAULT NULL,
  `ship_address` varchar(100) DEFAULT NULL,
  `ship_city` varchar(100) DEFAULT NULL,
  `ship_region` varchar(100) DEFAULT NULL,
  `ship_postal_code` varchar(100) DEFAULT NULL,
  `ship_country` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `orders_FK_2` (`ship_via`),
  KEY `orders_customer_id_IDX` (`customer_id`) USING BTREE,
  KEY `orders_employee_id_IDX` (`employee_id`) USING BTREE,
  KEY `orders_order_date_IDX` (`order_date`) USING BTREE,
  KEY `orders_shipped_date_IDX` (`shipped_date`) USING BTREE,
  KEY `orders_ship_postal_code_IDX` (`ship_postal_code`) USING BTREE,
  CONSTRAINT `orders_FK` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  CONSTRAINT `orders_FK_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`),
  CONSTRAINT `orders_FK_2` FOREIGN KEY (`ship_via`) REFERENCES `shippers` (`shipper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- intcomex.order_datails definition

CREATE TABLE `order_datails` (
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `unit_price` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `discount` int DEFAULT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `order_datails_FK_1` (`product_id`),
  CONSTRAINT `order_datails_FK` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `order_datails_FK_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;