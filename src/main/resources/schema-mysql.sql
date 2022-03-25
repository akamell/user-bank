-- DROP TABLE IF EXISTS public.user;
CREATE TABLE IF NOT EXISTS user(
    id INT AUTO_INCREMENT PRIMARY KEY,
    username varchar(100),
    name varchar(80),
    lastname varchar(80),
    password varchar(255),
    created_at timestamp,
    updated_at timestamp
);
