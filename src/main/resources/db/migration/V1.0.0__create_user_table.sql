CREATE TABLE `user` (
                        `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                        `username` VARCHAR(255) NOT NULL UNIQUE,
                        `password` VARCHAR(255) NOT NULL,
                        `level` INT NOT NULL,
                        `last_level_up_at` DATETIME,
                        CONSTRAINT `uc_username` UNIQUE (`username`)
);
