CREATE TABLE member
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_at   TIMESTAMP(6)    NOT NULL,
    social_id    BIGINT          NOT NULL,
    avatar_url   VARCHAR(255)    NOT NULL,
    display_name VARCHAR(255),
    email        VARCHAR(255),
    username     VARCHAR(255)    NOT NULL,
    provider     ENUM ('GITHUB') NOT NULL
) ENGINE = InnoDB;

CREATE TABLE form
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_at  TIMESTAMP(6) NOT NULL,
    member_id   BIGINT       NOT NULL,
    description TEXT         NOT NULL,
    title       VARCHAR(255) NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member (id)
) ENGINE = InnoDB;

CREATE TABLE question
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    sequence INT                                                                         NOT NULL,
    form_id  BIGINT                                                                      NOT NULL,
    text     VARCHAR(255)                                                                NOT NULL,
    type     ENUM ('CHECKBOX', 'DROPDOWN', 'LONG_TEXT', 'MULTIPLE_CHOICE', 'SHORT_TEXT') NOT NULL,
    FOREIGN KEY (form_id) REFERENCES form (id)
) ENGINE = InnoDB;

CREATE TABLE answer
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id   BIGINT       NOT NULL,
    question_id BIGINT       NOT NULL,
    text        VARCHAR(255) NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member (id),
    FOREIGN KEY (question_id) REFERENCES question (id)
) ENGINE = InnoDB;

CREATE TABLE question_option
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    sequence    INT          NOT NULL,
    question_id BIGINT,
    text        VARCHAR(255) NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question (id)
) ENGINE = InnoDB;

CREATE TABLE choice
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    question_option_id BIGINT NOT NULL,
    member_id          BIGINT NOT NULL,
    FOREIGN KEY (question_option_id) REFERENCES question_option (id),
    FOREIGN KEY (member_id) REFERENCES member (id)
) ENGINE = InnoDB;

CREATE TABLE mission
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    description TEXT                        NOT NULL,
    thumbnail   TEXT                        NOT NULL,
    title       VARCHAR(255)                NOT NULL,
    url         VARCHAR(255)                NOT NULL,
    language    ENUM ('JAVA', 'JAVASCRIPT') NOT NULL
) ENGINE = InnoDB;

CREATE TABLE tag
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE mission_tag
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    mission_id BIGINT NOT NULL,
    tag_id     BIGINT NOT NULL,
    FOREIGN KEY (mission_id) REFERENCES mission (id),
    FOREIGN KEY (tag_id) REFERENCES tag (id)
) ENGINE = InnoDB;

CREATE TABLE solution
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_at   TIMESTAMP(6) NOT NULL,
    member_id    BIGINT       NOT NULL,
    mission_id   BIGINT       NOT NULL,
    submitted_at TIMESTAMP(6),
    description  TEXT,
    title        VARCHAR(255),
    url          VARCHAR(255),
    FOREIGN KEY (member_id) REFERENCES member (id),
    FOREIGN KEY (mission_id) REFERENCES mission (id)
) ENGINE = InnoDB;

CREATE TABLE solution_comment
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_at        TIMESTAMP(6) NOT NULL,
    deleted_at        TIMESTAMP(6),
    member_id         BIGINT       NOT NULL,
    parent_comment_id BIGINT,
    solution_id       BIGINT       NOT NULL,
    content           TEXT         NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member (id),
    FOREIGN KEY (solution_id) REFERENCES solution (id)
) ENGINE = InnoDB;
