-- Table: users
CREATE TABLE IF NOT EXISTS `users` (
  `id` varchar(40) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Table: roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` varchar(40) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Table for mapping user and roles: user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_id` varchar(40) NOT NULL DEFAULT '',
  `role_id` varchar(40) NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- table `manufacturers`
CREATE TABLE IF NOT EXISTS `manufacturers` (
  `id` varchar(40) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Table `products`
CREATE TABLE IF NOT EXISTS `products` (
  `id` varchar(40) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `manufacturer_id` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `mnf_id_pr` (`manufacturer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `users` (`id`, `email`, `password`, `firstName`, `lastName`) VALUES
  ('77a96737-810d-40fb-9fd5-a6e968a16747', 'test@email', '$2a$11$bZ7fOxRRr/DbEaJBmeH3pOW5tqjd5Ndy5PSDJU7Q6JDfmpPvKcaFq', 'fname', 'lname'),
  ('b0d161f2-cbab-4a8f-ad43-87b23407c876', 'admin@mail', '$2a$11$Dwq3lxWb/WpTNf2hXt10q.mQmsZBaLw5dYOx4DV54lRJBR7JKapdy', 'firstn', 'lastn');

INSERT INTO `roles` (`id`, `name`) VALUES
  ('c32e2bae-0f0c-4d86-802a-e755a477bd3d', 'ROLE_ADMIN'),
  ('e942aff1-4104-4280-99d5-0e078dac6a2c', 'ROLE_USER');

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
  ('b0d161f2-cbab-4a8f-ad43-87b23407c876', 'c32e2bae-0f0c-4d86-802a-e755a477bd3d'),
  ('77a96737-810d-40fb-9fd5-a6e968a16747', 'e942aff1-4104-4280-99d5-0e078dac6a2c');

INSERT INTO `manufacturers` (`id`, `name`) VALUES
  ('628466f9-b2e9-442e-91b4-f2809f66af1c', 'manufacturer1'),
  ('826cc322-d8e3-422e-8e18-487e2e01a9bb', 'manufacturer2');

INSERT INTO `products` (`id`, `name`, `price`, `manufacturer_id`) VALUES
  ('3911cf1b-e38e-4483-9388-3c373390f078', 'product1', '100000', '826cc322-d8e3-422e-8e18-487e2e01a9bb'),
  ('b0ba24f2-0a9c-4cfe-95fb-b55ce344942d', 'product2', '1000', '628466f9-b2e9-442e-91b4-f2809f66af1c');


ALTER TABLE `products`
  ADD CONSTRAINT `mnf_id_pr` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `user_roles`
  ADD CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
-- Insert data
/*
INSERT INTO user_roles VALUES (1, 2);*/