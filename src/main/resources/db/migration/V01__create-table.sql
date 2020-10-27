CREATE TABLE tb_image
(
    id_image      uuid         NOT NULL,
    nm_image      varchar(255) NOT NULL,
    ds_image_type varchar(100) NOT NULL,
    "file"        text         NOT NULL,
    PRIMARY KEY (id_image)
);
