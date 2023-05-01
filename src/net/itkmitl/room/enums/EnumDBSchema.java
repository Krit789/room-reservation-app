package net.itkmitl.room.enums;

import net.itkmitl.room.libs.jarukrit.ConfigManager;

public enum EnumDBSchema {

    USER(String.format("CREATE TABLE IF NOT EXISTS `%s`.`user` ( `id` int(255) NOT NULL AUTO_INCREMENT, `email` TEXT NOT NULL , `password_hash` varchar(255) NOT NULL, `firstname` TEXT NOT NULL , `lastname` TEXT NOT NULL , `tel_num` TEXT NOT NULL , `is_active` tinyint(1) NOT NULL DEFAULT 1 , `created_on` DATETIME NOT NULL DEFAULT current_timestamp() , `role` int(1) NOT NULL DEFAULT '0' , PRIMARY KEY (`id`), UNIQUE `email` (`email`) ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;", ConfigManager.getConnectionConfig().get(1))),
    ROOM(String.format("CREATE TABLE IF NOT EXISTS `%s`.`room` ( `id` int(255) NOT NULL AUTO_INCREMENT, `name` text NOT NULL, `capacity` int(4) NOT NULL, `floor` varchar(4) NOT NULL, `building` text NOT NULL, `open_time` datetime DEFAULT NULL, `close_time` datetime DEFAULT NULL, `state` enum('AVAILABLE','UNAVAILABLE','MAINTENANCE') NOT NULL DEFAULT 'UNAVAILABLE', PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;", ConfigManager.getConnectionConfig().get(1))),
    RESERVATION(String.format("CREATE TABLE IF NOT EXISTS `%s`.`reservation` ( `id` int(255) NOT NULL AUTO_INCREMENT , `user_id` int(255) NOT NULL , `room_id` int(255) NOT NULL , `reason` TEXT NOT NULL , `start_time` DATETIME NOT NULL , `end_time` DATETIME NOT NULL , `reservation_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP , `is_cancelled` BOOLEAN NOT NULL DEFAULT FALSE , PRIMARY KEY (`id`) ) ENGINE = InnoDB;", ConfigManager.getConnectionConfig().get(1))),
    FEEDBACK(String.format("CREATE TABLE IF NOT EXISTS `%s`.`feedback` ( `id` int(255) NOT NULL AUTO_INCREMENT , `room_id` int(255) NOT NULL , `user_id` int(255) NOT NULL , `created_on` DATETIME NOT NULL DEFAULT current_timestamp() , `comment` TEXT NOT NULL , `rating` float NOT NULL , PRIMARY KEY (`id`) ) ENGINE = InnoDB;", ConfigManager.getConnectionConfig().get(1))),
    FEEDBACK_RELATIONSHIP("ALTER TABLE `feedback` ADD CONSTRAINT `room_relation_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION, ADD CONSTRAINT `user_relation_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"),
    RESERVATION_RELATIONSHIP("ALTER TABLE `reservation` ADD CONSTRAINT `room_relation` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION, ADD CONSTRAINT `user_relation` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"),
    FEEDBACK_INDEX("ALTER TABLE `feedback` ADD KEY `user_relation_1` (`user_id`), ADD KEY `room_relation_1` (`room_id`);"),
    RESERVATION_INDEX("ALTER TABLE `reservation` ADD KEY `user_relation` (`user_id`), ADD KEY `room_relation` (`room_id`)");

    private String raw;

    EnumDBSchema(String raw) {
        this.raw = raw;
    }

    public String getRaw() {
        return raw;
    }
}
