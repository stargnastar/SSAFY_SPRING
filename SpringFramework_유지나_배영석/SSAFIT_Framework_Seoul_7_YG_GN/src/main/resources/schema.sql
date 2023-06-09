DROP DATABASE ssafit;
CREATE DATABASE IF NOT EXISTS ssafit;
USE ssafit;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user`(
    `no` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `id` VARCHAR(20)  ,
    `pw` VARCHAR(20) NOT NULL,
    `name` VARCHAR(20) NOT NULL,
    `nickName` VARCHAR(50) NOT NULL,
    `email` VARCHAR(50) ,
    `age` INT,
    `reg_date` timestamp default current_timestamp,
    `img` VARCHAR(2048) ,
    `orgImg` VARCHAR(200)
)ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `follow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `follow`(
    `follower` INT NOT NULL,
    `followed` INT NOT NULL,
    FOREIGN KEY (`follower`) REFERENCES user (`no`) ON DELETE CASCADE,
    FOREIGN KEY (`followed`) REFERENCES user (`no`)  ON DELETE CASCADE
)ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `article` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` VARCHAR(45) NOT NULL,
  `content` VARCHAR(200) NOT NULL,
  `writer` INT NOT NULL,
  `part` VARCHAR(45) NOT NULL, -- 운동부위
  `view_cnt` INT NULL DEFAULT 0,
  `created_at` TIMESTAMP NOT NULL  DEFAULT current_timestamp,
  `url` VARCHAR(2048) NOT NULL,
  `thumbnail` VARCHAR(200),
  FOREIGN KEY (`writer`) REFERENCES `user` (`no`) ON DELETE CASCADE
 )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `review` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `article_id` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `content` VARCHAR(45) NOT NULL,
  `writer` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT current_timestamp,
  `updated_at` TIMESTAMP ,
  FOREIGN KEY (`article_id`) REFERENCES article (`id`),
  FOREIGN KEY (`writer`) REFERENCES user (`no`) ON DELETE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `subscribe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `subscribe` (
  `id` INT NOT NULL , -- 영상번호
  `subscriber` INT NOT NULL, -- 구독자
FOREIGN KEY (`id`) REFERENCES article (`id`)  ON DELETE CASCADE,
FOREIGN KEY (`subscriber`) REFERENCES user (`no`)  ON DELETE CASCADE
)ENGINE = InnoDB;


    
INSERT INTO user(id, pw, name, nickName, email, age)
VALUES ("mini", "mmmm", "김미니", "미니마우스", "mini@ssafy.com", 15),
    ("gina", "1234", "유지나", "지나몬", "gina@ssafy.com", 23),
    ("yung", "4321", "배영석", "영석몬", "young@ssafy.com", 25);
    

    
INSERT INTO follow(follower, followed)
VALUES (1, 2),
        (1, 3),
        (2, 3);
        
        
INSERT INTO article(title, content, writer, part, url)
VALUES ("집에서도 하는 엉덩이운동", "하체운동을 위해서는 꾸준한 엉덩이 힘이 필요해요", 2,"둔근", "https://www.youtube.com/watch?v=xua8mUT0IFQ"),
("턱걸이 1개 성공하기", "어깨가 올라갔는지 먼저 확인을 해보세요", 3,"등","https://www.youtube.com/watch?v=50MQXdDOiKQ"),
("여름을 앞두고 두꺼운 팔 만들기", "마동석 같은 팔을 만드려면 약을 해야해요", 2,"팔", "https://www.youtube.com/watch?v=8gaVdMFi0t8");

INSERT INTO `review` (`article_id`, `title`, `content`, `writer`) VALUES (1, "정말 최고에요", "사실 거짓말임", 1),
(2, "영상이 너무 좋습니다.", "저도 이런 영상을 만들고 싶어요.", 2),
(3, "이건 좀", "좋아요", 3);

INSERT INTO `subscribe` VALUES (1, 1), (1, 2), (2, 3);
