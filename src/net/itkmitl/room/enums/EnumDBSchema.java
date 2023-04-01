package net.itkmitl.room.enums;

public enum EnumDBSchema {

    USER("CREATE TABLE `user` ( `id` INT(255) NOT NULL AUTO_INCREMENT , `email` TEXT NOT NULL , `firstname` TEXT NOT NULL , `lastname` TEXT NOT NULL , `tel_num` TEXT NOT NULL , `is_active` BOOLEAN NOT NULL DEFAULT TRUE , `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP , `role` INT(1) NOT NULL DEFAULT '0' , PRIMARY KEY (`id`), UNIQUE `email` (`email`) ) ENGINE = InnoDB;"),
    ROOM("CREATE TABLE `room` ( `id` int(255) NOT NULL, `name` text NOT NULL, `capacity` int(4) NOT NULL, `floor` varchar(4) NOT NULL, `building` text NOT NULL, `state` enum('AVAILABLE','UNAVAILABLE','MAINTENANCE') NOT NULL DEFAULT 'UNAVAILABLE' ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;"),
    RESERVATION("CREATE TABLE `reservation` ( `id` INT(255) NOT NULL AUTO_INCREMENT , `user_id` INT(255) NOT NULL , `room_id` INT(255) NOT NULL , `reason` TEXT NOT NULL , `start_time` DATETIME NOT NULL , `end_time` DATETIME NOT NULL , `reservation_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP , `is_cancelled` BOOLEAN NOT NULL DEFAULT FALSE , PRIMARY KEY (`id`) ) ENGINE = InnoDB; ALTER TABLE `reservation` ADD CONSTRAINT `user_relation` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION; ALTER TABLE `reservation` ADD CONSTRAINT `room_relation` FOREIGN KEY (`room_id`) REFERENCES `room`(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"),
    FEEDBACK("CREATE TABLE `feedback` ( `id` INT(255) NOT NULL AUTO_INCREMENT , `room_id` INT(255) NOT NULL , `user_id` INT(255) NOT NULL , `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP , `comment` TEXT NOT NULL , `rating` DECIMAL(1,1) NOT NULL , PRIMARY KEY (`id`) ) ENGINE = InnoDB; ALTER TABLE `feedback` ADD CONSTRAINT `user_relation_1` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION; ALTER TABLE `feedback` ADD CONSTRAINT `room_relation_1` FOREIGN KEY (`room_id`) REFERENCES `room`(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"),
    ;

    private String raw;

    EnumDBSchema(String raw) {
        this.raw = raw;
    }

    public String getRaw() {
        return raw;
    }
}
