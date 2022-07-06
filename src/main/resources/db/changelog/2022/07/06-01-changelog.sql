-- liquibase formatted sql

-- changeset artem:1657122432893-1
CREATE TABLE article
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    creator_id   BIGINT                                  NOT NULL,
    title        VARCHAR(255),
    author       VARCHAR(255),
    topic        VARCHAR(255),
    version_date date,
    content      TEXT,
    role_access  VARCHAR(255),
    CONSTRAINT pk_article PRIMARY KEY (id)
);

-- changeset artem:1657122432893-2
CREATE TABLE articles_groups
(
    group_role VARCHAR(255),
    article_id BIGINT NOT NULL,
    group_id   BIGINT NOT NULL,
    CONSTRAINT pk_articles_groups PRIMARY KEY (article_id, group_id)
);

-- changeset artem:1657122432893-3
CREATE TABLE articles_users
(
    user_role  VARCHAR(255),
    article_id BIGINT NOT NULL,
    user_id    BIGINT NOT NULL,
    CONSTRAINT pk_articles_users PRIMARY KEY (article_id, user_id)
);

-- changeset artem:1657122432893-4
CREATE TABLE "group"
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title       VARCHAR(255),
    description VARCHAR(255),
    CONSTRAINT pk_group PRIMARY KEY (id)
);

-- changeset artem:1657122432893-5
CREATE TABLE group_users
(
    groups_id BIGINT NOT NULL,
    users_id  BIGINT NOT NULL,
    CONSTRAINT pk_group_users PRIMARY KEY (groups_id, users_id)
);

-- changeset artem:1657122432893-6
CREATE TABLE namespace
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title      VARCHAR(255),
    creator_id BIGINT,
    CONSTRAINT pk_namespace PRIMARY KEY (id)
);

-- changeset artem:1657122432893-7
CREATE TABLE namespace_articles
(
    articles_id  BIGINT NOT NULL,
    namespace_id BIGINT NOT NULL,
    CONSTRAINT pk_namespace_articles PRIMARY KEY (articles_id, namespace_id)
);

-- changeset artem:1657122432893-8
CREATE TABLE namespace_users
(
    groups_id BIGINT NOT NULL,
    users_id  BIGINT NOT NULL,
    CONSTRAINT pk_namespace_users PRIMARY KEY (groups_id, users_id)
);

-- changeset artem:1657122432893-9
CREATE TABLE tag
(
    title VARCHAR(255) NOT NULL,
    CONSTRAINT pk_tag PRIMARY KEY (title)
);

-- changeset artem:1657122432893-10
CREATE TABLE tag_articles
(
    articles_id     BIGINT       NOT NULL,
    namespace_title VARCHAR(255) NOT NULL
);

-- changeset artem:1657122432893-11
CREATE TABLE users
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    email    VARCHAR(255),
    name     VARCHAR(255),
    password VARCHAR(255),
    role     VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

-- changeset artem:1657122432893-12
ALTER TABLE namespace
    ADD CONSTRAINT uc_4d839ce1fe71f41bdfe158dfb UNIQUE (title);

-- changeset artem:1657122432893-13
ALTER TABLE articles_groups
    ADD CONSTRAINT FK_ARTICLES_GROUPS_ON_ARTICLE FOREIGN KEY (article_id) REFERENCES article (id);

-- changeset artem:1657122432893-14
ALTER TABLE articles_groups
    ADD CONSTRAINT FK_ARTICLES_GROUPS_ON_GROUP FOREIGN KEY (group_id) REFERENCES "group" (id);

-- changeset artem:1657122432893-15
ALTER TABLE articles_users
    ADD CONSTRAINT FK_ARTICLES_USERS_ON_ARTICLE FOREIGN KEY (article_id) REFERENCES article (id);

-- changeset artem:1657122432893-16
ALTER TABLE articles_users
    ADD CONSTRAINT FK_ARTICLES_USERS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset artem:1657122432893-17
ALTER TABLE article
    ADD CONSTRAINT FK_ARTICLE_ON_CREATOR FOREIGN KEY (creator_id) REFERENCES users (id);

-- changeset artem:1657122432893-18
ALTER TABLE namespace
    ADD CONSTRAINT FK_NAMESPACE_ON_CREATOR FOREIGN KEY (creator_id) REFERENCES users (id);

-- changeset artem:1657122432893-19
ALTER TABLE group_users
    ADD CONSTRAINT fk_grouse_on_group FOREIGN KEY (groups_id) REFERENCES "group" (id);

-- changeset artem:1657122432893-20
ALTER TABLE group_users
    ADD CONSTRAINT fk_grouse_on_user FOREIGN KEY (users_id) REFERENCES users (id);

-- changeset artem:1657122432893-21
ALTER TABLE namespace_articles
    ADD CONSTRAINT fk_namart_on_article FOREIGN KEY (articles_id) REFERENCES article (id);

-- changeset artem:1657122432893-22
ALTER TABLE namespace_articles
    ADD CONSTRAINT fk_namart_on_namespace FOREIGN KEY (namespace_id) REFERENCES namespace (id);

-- changeset artem:1657122432893-23
ALTER TABLE namespace_users
    ADD CONSTRAINT fk_namuse_on_namespace FOREIGN KEY (groups_id) REFERENCES namespace (id);

-- changeset artem:1657122432893-24
ALTER TABLE namespace_users
    ADD CONSTRAINT fk_namuse_on_user FOREIGN KEY (users_id) REFERENCES users (id);

-- changeset artem:1657122432893-25
ALTER TABLE tag_articles
    ADD CONSTRAINT fk_tagart_on_article FOREIGN KEY (articles_id) REFERENCES article (id);

-- changeset artem:1657122432893-26
ALTER TABLE tag_articles
    ADD CONSTRAINT fk_tagart_on_tag FOREIGN KEY (namespace_title) REFERENCES tag (title);
