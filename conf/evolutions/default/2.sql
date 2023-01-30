# --- !Ups
-- CATEGORY
INSERT INTO `category` (`id`,`name`) VALUES (1,'Music');
INSERT INTO `category` (`id`,`name`) VALUES (2,'Cinema');
INSERT INTO `category` (`id`,`name`) VALUES (3,'Conference');
INSERT INTO `category` (`id`,`name`) VALUES (4,'Dance');
INSERT INTO `category` (`id`,`name`) VALUES (5,'Sports');
INSERT INTO `category` (`id`,`name`) VALUES (6,'Festival');
INSERT INTO `category` (`id`,`name`) VALUES (7,'Food & Drinks');
INSERT INTO `category` (`id`,`name`) VALUES (8,'Theater');

-- COMPANY
INSERT INTO `company` (`id`,`name`,`image_id`,`bio`) VALUES (1,'Ecstatic Dance Lisboa',NULL,NULL);
INSERT INTO `company` (`id`,`name`,`image_id`,`bio`) VALUES (2,'Based in Lisbon',NULL,NULL);
INSERT INTO `company` (`id`,`name`,`image_id`,`bio`) VALUES (3,'Wine Book Magazine',NULL,NULL);
INSERT INTO `eventhub`.`company` (`name`) VALUES ('Ecstatic Partiers');
INSERT INTO `eventhub`.`company` (`name`) VALUES ('Long Nights');
INSERT INTO `eventhub`.`company` (`name`) VALUES ('ISCTE');
INSERT INTO `eventhub`.`company` (`name`) VALUES ('Undefined');


-- VENUE
INSERT INTO `venue` (`id`,`name`,`address`,`city`) VALUES (1,'Art Kaizen','Rua do Açúcar','Lisbon');
INSERT INTO `venue` (`id`,`name`,`address`,`city`) VALUES (2,'Tiles Bar','Rua de São Paulo','Lisbon');
INSERT INTO `venue` (`id`,`name`,`address`,`city`) VALUES (3,'Hotel Lisboa Palácio','Rua de Algo','Lisbon');
INSERT INTO `eventhub`.`venue` (`name`, `address`, `city`) VALUES ('Bus', 'Rua dos Anjos', 'Lisbon');
INSERT INTO `eventhub`.`venue` (`name`, `address`, `city`) VALUES ('Lux', 'Rua de Santa Apolónia', 'Lisbon');
INSERT INTO `eventhub`.`venue` (`name`, `address`, `city`) VALUES ('ISCTE', 'Av. das Forças Armadas', 'Lisbon');

-- EVENT
INSERT INTO `event` (`id`,`title`,`description`,`image_id`,`start_date_time`,`end_date_time`,`company_id`,`category_id`,`venue_id`) VALUES (1,'Ecstatic Dance Lisboa','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.',NULL,'2022-12-17 17:45','2022-12-17 22:00',1,4,1);
INSERT INTO `event` (`id`,`title`,`description`,`image_id`,`start_date_time`,`end_date_time`,`company_id`,`category_id`,`venue_id`) VALUES (2,'Degens Hangout','Imagine a scenario where you just arrived in Lisbon from the nomad capitals of the world (Thailand, Indonesia, Germany, USA, Australia, etc..) and want to connect with like minded-people. Here you can network and have a nice Wednesday night with people from all over the world.',NULL,'2023-01-11 18:00','2023-01-11 21:30',2,7,2);
INSERT INTO `event` (`id`,`title`,`description`,`image_id`,`start_date_time`,`end_date_time`,`company_id`,`category_id`,`venue_id`) VALUES (3,'Wine Tasting Lisboa','Coisas coisas coisas e coisas',NULL,'2022-11-26 15:00','2022-11-26 22:00',3,7,3);
INSERT INTO `event` (`title`, `description`, `start_date_time`, `end_date_time`, `company_id`, `category_id`, `venue_id`) VALUES ('Ecstatic Dance Lisboa', 'Teste', '2022-01-21 17:45', '2022-01-21 22:00', '1', '4', '1');
INSERT INTO `eventhub`.`event` (`title`, `description`, `start_date_time`, `end_date_time`, `company_id`, `category_id`, `venue_id`) VALUES ('Ecstatic Dance Party', 'ewfsgdfgrgdfgr', '2023-02-23 18:00', '2023-02-23 21:30', '4', '4', '4');
INSERT INTO `eventhub`.`event` (`title`, `description`, `start_date_time`, `end_date_time`) VALUES ('All Night Long', 'A party that will last all night long', '2023-04-04 14:00', '2023-04-06 5:00');
UPDATE `eventhub`.`event` SET `company_id` = '5', `category_id` = '4', `venue_id` = '5' WHERE (`id` = '6');
INSERT INTO `event` (`id`,`title`,`description`,`image_id`,`start_date_time`,`end_date_time`,`company_id`,`category_id`,`venue_id`) VALUES (7,'Festa no Escritório','Festa de ARROMBA no GDSI com piquenique',NULL,'2023-01-13 16:36:00.000000','2023-01-13 19:30:00.000000',6,7,6);

-- SECTION
INSERT INTO `eventhub`.`section` (`event_id`, `name`, `capacity`, `price`) VALUES ('1', 'General Admission', '50', '25');
INSERT INTO `eventhub`.`section` (`event_id`, `name`, `description`, `capacity`, `price`) VALUES ('1', 'Premium', 'Includes 1 free drink', '20', '30');
INSERT INTO `eventhub`.`section` (`event_id`, `name`, `description`, `capacity`, `price`) VALUES ('1', 'VIP', 'Includes 3 free drinks', '10', '40');

-- USER
INSERT INTO `eventhub`.`user` (`first_name`, `last_name`, `email`, `password`, `status`, `balance`, `city`) VALUES ('Joana', 'Ferro', 'joanaferro92@gmail.com', '123456', 'Approved', '1000', 'Lisbon');
INSERT INTO `eventhub`.`user` (`first_name`, `last_name`, `email`, `password`, `status`, `balance`, `city`) VALUES ('André', 'Moreira', 'andremoreira@gmail.com', '123456', 'Approved', '1000', 'Lisbon');
INSERT INTO `eventhub`.`user` (`first_name`, `last_name`, `email`, `password`, `status`, `balance`, `city`) VALUES ('Susana', 'Nascimento', 'susananascimento@gmail.com', '123456', 'Approved', '1000', 'Lisbon');

-- TICKET
INSERT INTO `eventhub`.`ticket` (`id`, `section_id`, `user_id`) VALUES ('1589002001', '1', '1');