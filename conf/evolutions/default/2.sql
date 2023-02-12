# --- !Ups
-- ROLE
INSERT INTO `role` (`id`,`name`) VALUES (1,'Participant');
INSERT INTO `role` (`id`,`name`) VALUES (2,'Manager');
INSERT INTO `role` (`id`,`name`) VALUES (3,'Admin');

-- USER
INSERT INTO `user` (`id`,`first_name`,`last_name`,`email`,`password`,`status`,`balance`,`city`) VALUES (1,'António','Lopes','antoniolopes@gmail.com','$2a$10$/1SnG/SioykI9XpdQLwSK.iAUgXrYfyNDQDd2sjHCi1BvzxEhXu6O','Approved',NULL,NULL);
INSERT INTO `user` (`id`,`first_name`,`last_name`,`email`,`password`,`status`,`balance`,`city`) VALUES (2,'Adriano','Fernandes','adrianofernandes@gmail.com','$2a$10$9ybO/9XzrKUcEw1HIfcaUu7jqEJlRe4PKZHZG4U0CSCnpDYyzAO9i','Approved',NULL,NULL);
INSERT INTO `user` (`id`,`first_name`,`last_name`,`email`,`password`,`status`,`balance`,`city`) VALUES (3,'Susana','Nascimento','susananascimento@gmail.com','$2a$10$GSivrEBja/.ggbBQUXBDP.LlLT1.PtpRde14K0djrDMiDPy0B6Bue','Approved',650,'Lisboa');
INSERT INTO `user` (`id`,`first_name`,`last_name`,`email`,`password`,`status`,`balance`,`city`) VALUES (4,'João','Silva','joaosilva@hotmail.com','$2a$10$uig6gzTEF3LeFiP5VfeQUOUO3Cufp7VSH5W1yJQq5Lj9wkpy.KgtK','Rejected',1000,'Almada');
INSERT INTO `user` (`id`,`first_name`,`last_name`,`email`,`password`,`status`,`balance`,`city`) VALUES (5,'Rute','Marlene','rutemarlene@outlook.com','$2a$10$eiY0U7W1bdEZ5FFa8tKtuOzyNxXiMcmlSHSl9QBF8JXyzsNk9Y68W','Approved',860,'Sintra');
INSERT INTO `user` (`id`,`first_name`,`last_name`,`email`,`password`,`status`,`balance`,`city`) VALUES (6,'Adriano','Fernandes','ola123@gmail.com','$2a$10$b2r5TTSb1PcceU06t9YFnOMqGWhw/vn/Cg5pMA5IYlRVXxuJaXhg.','Approved',995,'Lisboa');
INSERT INTO `user` (`id`,`first_name`,`last_name`,`email`,`password`,`status`,`balance`,`city`) VALUES (7,'André','Moreira','andremoreira@gmail.com','$2a$10$qjpgOPf08kPd9NNMfb8O7unuca/yGlKtC2vikK0zBCwrltiZxpg3S','Approved',345,'Sintra');
INSERT INTO `user` (`id`,`first_name`,`last_name`,`email`,`password`,`status`,`balance`,`city`) VALUES (8,'Pedro','Vasconcelos','pedrovasconcelos@email.com','$2a$10$Q19P76bOGj0Y9caCq29WeuDUqaohXYNVHuwPnNqikf8OaQtjatbIy','Pending',1000,'Beja');
INSERT INTO `user` (`id`,`first_name`,`last_name`,`email`,`password`,`status`,`balance`,`city`) VALUES (9,'Luís','Costa','luiscosta@gmail.com','$2a$10$qW5wmvtB7elYRnugOatyTO0.1MNmI/ANXhT8e.6zwHWs8ID6POjEa','Pending',1000,'Braga');
INSERT INTO `user` (`id`,`first_name`,`last_name`,`email`,`password`,`status`,`balance`,`city`) VALUES (10,'Joana','Ferro','joanaferro92@gmail.com','$2a$10$BH/fr79JW2L1SsR6Q4oQXOKejYz3Q2O4qP/nQhVYzIAAs2Qqa0ghW','Approved',470,'Beja');
INSERT INTO `user` (`id`,`first_name`,`last_name`,`email`,`password`,`status`,`balance`,`city`) VALUES (11,'Francisco','Coisas','franciscocoisas@gmail.com','$2a$10$RP.Qxe6xAm/tG2Ms9h53i.prE9aR/xuzW4wg8r2HaWFqUVMVQi5tm','Approved',1000,'Lisboa');
INSERT INTO `user` (`id`,`first_name`,`last_name`,`email`,`password`,`status`,`balance`,`city`) VALUES (12,'Gonçalo','Miguel','goncalomiguel@gmail.com','$2a$10$qxXgQK4X8LzJqWaJKBrtUO8R0JLzq5316wLmWr/3IYgfM1NkHbjUe','Approved',950,'Lisbon');
INSERT INTO `user` (`id`,`first_name`,`last_name`,`email`,`password`,`status`,`balance`,`city`) VALUES (13,'Margarida','Fonseca','margaridafonseca@hotmail.com','$2a$10$NbCij1qgmG03IGwYCF5HsuFMHi.D34GqnXGUZlcCg6HoBnYfEgZYq','Rejected',1000,'Almada');
INSERT INTO `user` (`id`,`first_name`,`last_name`,`email`,`password`,`status`,`balance`,`city`) VALUES (14,'Francisco','Atanásio','franciscoatanasio@gmail.com','$2a$10$HtbIDzFxcQJivyYR8Rc4MOqA/.Sd0B1XiCDURwKT8LPKNTkN2jbZu','Rejected',1000,'Alverca');

-- USER ROLE
INSERT INTO `user_role` (`user_id`,`role_id`,`is_active`) VALUES (1,3,0);
INSERT INTO `user_role` (`user_id`,`role_id`,`is_active`) VALUES (2,2,0);
INSERT INTO `user_role` (`user_id`,`role_id`,`is_active`) VALUES (3,1,0);
INSERT INTO `user_role` (`user_id`,`role_id`,`is_active`) VALUES (4,1,0);
INSERT INTO `user_role` (`user_id`,`role_id`,`is_active`) VALUES (5,1,0);
INSERT INTO `user_role` (`user_id`,`role_id`,`is_active`) VALUES (2,1,1);
INSERT INTO `user_role` (`user_id`,`role_id`,`is_active`) VALUES (2,3,1);
INSERT INTO `user_role` (`user_id`,`role_id`,`is_active`) VALUES (6,1,0);
INSERT INTO `user_role` (`user_id`,`role_id`,`is_active`) VALUES (7,1,0);
INSERT INTO `user_role` (`user_id`,`role_id`,`is_active`) VALUES (8,1,0);
INSERT INTO `user_role` (`user_id`,`role_id`,`is_active`) VALUES (9,1,0);
INSERT INTO `user_role` (`user_id`,`role_id`,`is_active`) VALUES (10,1,0);
INSERT INTO `user_role` (`user_id`,`role_id`,`is_active`) VALUES (11,1,0);
INSERT INTO `user_role` (`user_id`,`role_id`,`is_active`) VALUES (10,2,1);
INSERT INTO `user_role` (`user_id`,`role_id`,`is_active`) VALUES (10,3,1);
INSERT INTO `user_role` (`user_id`,`role_id`,`is_active`) VALUES (12,1,0);
INSERT INTO `user_role` (`user_id`,`role_id`,`is_active`) VALUES (13,1,0);
INSERT INTO `user_role` (`user_id`,`role_id`,`is_active`) VALUES (14,1,0);

-- COMPANY
INSERT INTO `company` (`id`,`name`,`image_id`,`bio`) VALUES (1,'Ecstatic Dance Lisboa',NULL,NULL);
INSERT INTO `company` (`id`,`name`,`image_id`,`bio`) VALUES (2,'Based in Lisbon',NULL,NULL);
INSERT INTO `company` (`id`,`name`,`image_id`,`bio`) VALUES (3,'Wine Book Magazine',NULL,NULL);
INSERT INTO `company` (`id`,`name`,`image_id`,`bio`) VALUES (4,'ISCTE',NULL,NULL);
INSERT INTO `company` (`id`,`name`,`image_id`,`bio`) VALUES (5,'SafeJourney',NULL,NULL);
INSERT INTO `company` (`id`,`name`,`image_id`,`bio`) VALUES (6,'Based in Lisbon',NULL,NULL);
INSERT INTO `company` (`id`,`name`,`image_id`,`bio`) VALUES (7,'The Rock Orchestra',NULL,NULL);
INSERT INTO `company` (`id`,`name`,`image_id`,`bio`) VALUES (8,'Concerts in Lisbon',NULL,NULL);
INSERT INTO `company` (`id`,`name`,`image_id`,`bio`) VALUES (9,'DoTerra Global Limited',NULL,NULL);
INSERT INTO `company` (`id`,`name`,`image_id`,`bio`) VALUES (10,'Câmara Municipal de Sintra',NULL,NULL);
INSERT INTO `company` (`id`,`name`,`image_id`,`bio`) VALUES (11,'Trumps Lda.',NULL,NULL);
INSERT INTO `company` (`id`,`name`,`image_id`,`bio`) VALUES (12,'Universidade Nova de Lisboa',NULL,NULL);


-- CATEGORY
INSERT INTO `category` (`id`,`name`) VALUES (1,'Music');
INSERT INTO `category` (`id`,`name`) VALUES (2,'Cinema');
INSERT INTO `category` (`id`,`name`) VALUES (3,'Conference');
INSERT INTO `category` (`id`,`name`) VALUES (4,'Dance');
INSERT INTO `category` (`id`,`name`) VALUES (5,'Sports');
INSERT INTO `category` (`id`,`name`) VALUES (6,'Festival');
INSERT INTO `category` (`id`,`name`) VALUES (7,'Food & Drinks');
INSERT INTO `category` (`id`,`name`) VALUES (8,'Theater');

-- VENUE
INSERT INTO `venue` (`id`,`name`,`address`,`city`) VALUES (1,'Art Kaizen','Rua do Açúcar','Lisbon');
INSERT INTO `venue` (`id`,`name`,`address`,`city`) VALUES (2,'Tiles Bar','Rua de São Paulo','Lisbon');
INSERT INTO `venue` (`id`,`name`,`address`,`city`) VALUES (3,'Hotel Lisboa Palácio','Rua de Algo','Lisbon');
INSERT INTO `venue` (`id`,`name`,`address`,`city`) VALUES (4,'ISCTE','Av. das Forças Armadas','Lisbon');
INSERT INTO `venue` (`id`,`name`,`address`,`city`) VALUES (5,'Pavilhão Atlântico','Av. dos Oceanos','Lisbon');
INSERT INTO `venue` (`id`,`name`,`address`,`city`) VALUES (6,'Lux','Rua de Santa Apolónia','Lisbon');
INSERT INTO `venue` (`id`,`name`,`address`,`city`) VALUES (7,'Sítio do Costume','Rua de Algo','Almada');
INSERT INTO `venue` (`id`,`name`,`address`,`city`) VALUES (8,'Casa Pavão','Avenida do Mar','Sintra');
INSERT INTO `venue` (`id`,`name`,`address`,`city`) VALUES (9,'Pavilhão Multiusos','Rua Carlos Paredes','Sintra');
INSERT INTO `venue` (`id`,`name`,`address`,`city`) VALUES (10,'Trumps Club','Rua do Rato','Lisbon');
INSERT INTO `venue` (`id`,`name`,`address`,`city`) VALUES (11,'Nova School of Business and Economics','Rua da Holanda','Carcavelos');

-- EVENT
INSERT INTO `event` (`id`,`title`,`description`,`image_id`,`start_date_time`,`end_date_time`,`company_id`,`category_id`,`venue_id`) VALUES (1,'Def Leppard & Mötley Crüe','There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don\'t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn\'t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.',NULL,'2023-02-02 18:00:00.000000','2023-02-02 21:00:00.000000',8,1,5);
INSERT INTO `event` (`id`,`title`,`description`,`image_id`,`start_date_time`,`end_date_time`,`company_id`,`category_id`,`venue_id`) VALUES (3,'Ecstatic Dance Lisboa','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.',NULL,'2022-12-17 17:45:00.000000','2022-12-17 22:00:00.000000',1,4,1);
INSERT INTO `event` (`id`,`title`,`description`,`image_id`,`start_date_time`,`end_date_time`,`company_id`,`category_id`,`venue_id`) VALUES (4,'Degens Hangout','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet',NULL,'2023-01-11 18:00:00.000000','2023-01-11 21:30:00.000000',2,7,2);
INSERT INTO `event` (`id`,`title`,`description`,`image_id`,`start_date_time`,`end_date_time`,`company_id`,`category_id`,`venue_id`) VALUES (5,'Wine Tasting Lisboa','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet. Why do we use it? Something else and some other words have been added here. Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum',NULL,'2022-11-26 15:00:00.000000','2022-11-26 22:00:00.000000',3,7,3);
INSERT INTO `event` (`id`,`title`,`description`,`image_id`,`start_date_time`,`end_date_time`,`company_id`,`category_id`,`venue_id`) VALUES (6,'Ecstatic Dance Lisboa','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet',NULL,'2023-01-21 17:45:00.000000','2023-01-21 22:00:00.000000',1,4,1);
INSERT INTO `event` (`id`,`title`,`description`,`image_id`,`start_date_time`,`end_date_time`,`company_id`,`category_id`,`venue_id`) VALUES (7,'Ecstatic Dance Party','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet',NULL,'2023-02-23 18:00:00.000000','2023-02-23 21:30:00.000000',4,4,4);
INSERT INTO `event` (`id`,`title`,`description`,`image_id`,`start_date_time`,`end_date_time`,`company_id`,`category_id`,`venue_id`) VALUES (8,'All Night Long','A party that will last all night long. Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet',NULL,'2023-04-04 14:00:00.000000','2023-04-06 05:00:00.000000',2,1,6);
INSERT INTO `event` (`id`,`title`,`description`,`image_id`,`start_date_time`,`end_date_time`,`company_id`,`category_id`,`venue_id`) VALUES (9,'Aprender a Programar em Java','It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using \'Content here, content here\', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for \'lorem ipsum\' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).',NULL,'2023-02-01 14:00:00.000000','2023-02-01 19:30:00.000000',4,3,4);
INSERT INTO `event` (`id`,`title`,`description`,`image_id`,`start_date_time`,`end_date_time`,`company_id`,`category_id`,`venue_id`) VALUES (10,'DoTerra Europe Convention 2021','We are pleased to announce that dōTERRA Europe Convention will be held at the Altice Arena on 5th-6th May 2023. We have an exciting programme planned that is packed with education and inspiration. You don’t want to miss it!\r\n\r\nAll tickets will be charged in euros and are inclusive of VAT.\r\n\r\nPlease note:\r\n\r\n• Last day for refunds: 13th April 2023\r\n\r\n• Last day for transfers: 13th April 2023\r\n\r\nContact: Europeconvention@doterra.com',NULL,'2023-02-01 12:16:00.000000','2023-02-01 15:00:00.000000',9,3,7);
INSERT INTO `event` (`id`,`title`,`description`,`image_id`,`start_date_time`,`end_date_time`,`company_id`,`category_id`,`venue_id`) VALUES (11,'Jornadas do Desporto','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\r\n\r\nWhy do we use it?\r\nIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using \'Content here, content here\', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for \'lorem ipsum\' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).',NULL,'2023-02-02 14:00:00.000000','2023-02-02 15:00:00.000000',10,5,9);
INSERT INTO `event` (`id`,`title`,`description`,`image_id`,`start_date_time`,`end_date_time`,`company_id`,`category_id`,`venue_id`) VALUES (12,'Demo do projeto','Apresentação do projeto de estágio da Joana',NULL,'2023-02-01 15:45:00.000000','2023-02-01 16:45:00.000000',4,3,4);

-- SECTION
INSERT INTO `section` (`id`,`event_id`,`name`,`description`,`capacity`,`price`) VALUES (1,1,'General Admission','Standing',249,100);
INSERT INTO `section` (`id`,`event_id`,`name`,`description`,`capacity`,`price`) VALUES (3,1,'Premium','Front Row Seated',49,250);
INSERT INTO `section` (`id`,`event_id`,`name`,`description`,`capacity`,`price`) VALUES (4,9,'General Admission','',49,5);
INSERT INTO `section` (`id`,`event_id`,`name`,`description`,`capacity`,`price`) VALUES (5,10,'General Admission','Includes 1 free drink',50,10);
INSERT INTO `section` (`id`,`event_id`,`name`,`description`,`capacity`,`price`) VALUES (6,10,'Golden Entrance','Includes 3 free drinks',10,20);
INSERT INTO `section` (`id`,`event_id`,`name`,`description`,`capacity`,`price`) VALUES (8,10,'Premium Pass','Full access to open bar',10,50);
INSERT INTO `section` (`id`,`event_id`,`name`,`description`,`capacity`,`price`) VALUES (9,6,'Premium','Includes 1 free drink',20,30);
INSERT INTO `section` (`id`,`event_id`,`name`,`description`,`capacity`,`price`) VALUES (10,6,'VIP','Includes 3 free drinks',9,40);
INSERT INTO `section` (`id`,`event_id`,`name`,`description`,`capacity`,`price`) VALUES (11,11,'General Admission','Free entrance',50,0);
INSERT INTO `section` (`id`,`event_id`,`name`,`description`,`capacity`,`price`) VALUES (12,5,'Premium Pass','Connect with all of the wine sellers',64,165);
INSERT INTO `section` (`id`,`event_id`,`name`,`description`,`capacity`,`price`) VALUES (13,5,'Golden Pass','Includes 50 years top quality Port bottle',99,280);
INSERT INTO `section` (`id`,`event_id`,`name`,`description`,`capacity`,`price`) VALUES (14,5,'Diamond Pass','Access to private areas + Port bottle 100 vintage',49,350);
INSERT INTO `section` (`id`,`event_id`,`name`,`description`,`capacity`,`price`) VALUES (15,12,'General Admission','Free entrance',9,0);

-- TICKET
INSERT INTO `ticket` (`id`,`section_id`,`user_id`) VALUES (437422329,1,5);
INSERT INTO `ticket` (`id`,`section_id`,`user_id`) VALUES (591850633,10,5);
INSERT INTO `ticket` (`id`,`section_id`,`user_id`) VALUES (659057984,3,10);
INSERT INTO `ticket` (`id`,`section_id`,`user_id`) VALUES (669869903,4,6);
INSERT INTO `ticket` (`id`,`section_id`,`user_id`) VALUES (823463472,13,10);
INSERT INTO `ticket` (`id`,`section_id`,`user_id`) VALUES (885081152,12,7);
INSERT INTO `ticket` (`id`,`section_id`,`user_id`) VALUES (899337845,14,3);
INSERT INTO `ticket` (`id`,`section_id`,`user_id`) VALUES (982193161,15,10);


-- REVIEW
INSERT INTO `review` (`id`,`ticket_id`,`rate`,`privacy`,`title`,`comment`,`date`) VALUES (4,899337845,5,'PUBLIC','Very elegant and unique','The wine tasting event was truly a luxurious experience. From the moment I stepped into the venue, I was struck by the elegance and sophistication of the decor. The chandeliers sparkled overhead, casting a warm glow over the room, while the soft background music set the perfect ambiance for a night of indulgence.\r  \r  The highlight of the evening was, of course, the wine. The selection was truly impressive, with a range of reds, whites, and bubblies to suit every taste.','2023-02-01 12:43:23.575740');
INSERT INTO `review` (`id`,`ticket_id`,`rate`,`privacy`,`title`,`comment`,`date`) VALUES (5,885081152,4,'PUBLIC','Rich but overwhelming experience','I found the wine tasting event to be a bit overwhelming. While it was a great opportunity to try a variety of wines, the large crowd and social atmosphere made me feel uncomfortable. However, I appreciated the knowledgeable sommelier who was able to guide us through the tasting and provide insightful information about each wine.','2023-02-01 12:54:58.719895');
INSERT INTO `review` (`id`,`ticket_id`,`rate`,`privacy`,`title`,`comment`,`date`) VALUES (6,823463472,2,'PUBLIC','Terrible event','It was the worst experience ever. Do not recommend.','2023-02-01 14:42:16.274228');
