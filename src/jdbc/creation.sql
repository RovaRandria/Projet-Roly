CREATE TABLE sport (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40)
);

CREATE TABLE user_info (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(40), 
    user_password VARCHAR (40),
    last_name VARCHAR (40),
    first_name VARCHAR (40),
    birthday DATE,
    gender ENUM('male', 'female'),
    registration_date DATE
);

CREATE TABLE exercise (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR (40),
    description VARCHAR (100),
    area_of_effect VARCHAR (40)
);

CREATE TABLE practice (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    practice_date DATE,
    place VARCHAR(100),
    results VARCHAR(100),
    duration TIME,
    id_sport INT, 
    id_user INT,
    id_exercise INT,
    FOREIGN KEY (id_sport) REFERENCES sport(id),
    FOREIGN KEY (id_user) REFERENCES user_info(id),
    FOREIGN KEY (id_exercise) REFERENCES exercise(id)
);

CREATE TABLE physics_datas (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    measure_date DATE,
    weight FLOAT,
    waist_size INT, 
    hip_size INT, 
    id_user INT,
    FOREIGN KEY (id_user) REFERENCES user_info(id)
);

