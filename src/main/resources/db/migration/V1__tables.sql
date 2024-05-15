CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    firstname VARCHAR(55) NOT NULL,
    lastname VARCHAR(55) NOT NULL,
    age INT,
    charge VARCHAR(40),
    birthdate DATE,
    checkInDate DATE,
    ci VARCHAR(20) UNIQUE,
    email VARCHAR(20) UNIQUE,
    phoneNumber VARCHAR(20) UNIQUE
);


CREATE TABLE IF NOT EXISTS patients(
    id SERIAL PRIMARY KEY,
    ptFirstname VARCHAR(55) NOT NULL,
    ptLastname VARCHAR (55) NOT NULL,
    ptOccupation VARCHAR (55) NOT NULL,
    ptAddress VARCHAR(255) NOT NULL,
    ptPhone VARCHAR(20) NOT NULL,
    ptAge INT,
    ptCi VARCHAR(20) UNIQUE NOT NULL,
    ptCity VARCHAR (55) NOT NULL,
    ptEmail VARCHAR UNIQUE NOT NULL,
    ptConsultationReason VARCHAR (100),
    ptRecommendations VARCHAR (100)
);


CREATE TABLE IF NOT EXISTS frame(
    id SERIAL PRIMARY KEY,
    brand  VARCHAR (100),
    referenceBrand VARCHAR(40),
    size INT,
    bridge VARCHAR (55),  -- puente
    model VARCHAR (100), --model
    color  VARCHAR (20),
    price DECIMAL (10, 2),
    frameStock INT
);

--asdasd