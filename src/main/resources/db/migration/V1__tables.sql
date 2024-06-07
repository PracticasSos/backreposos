CREATE TABLE IF NOT EXISTS branch(
  id SERIAL PRIMARY KEY,
  name_branch VARCHAR(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS role(
    id SERIAL PRIMARY KEY,
    role_name VARCHAR(40) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    firstname VARCHAR(55) NOT NULL,
    lastname VARCHAR(55) NOT NULL,
    age INT,
    charge VARCHAR(40),
    birthdate DATE,
    check_in_date DATE,
    ci VARCHAR(25) UNIQUE,
    email VARCHAR(50) UNIQUE,
    phone_number VARCHAR(25) UNIQUE,
    password VARCHAR(200)  NOT NULL,
    locked BOOLEAN NOT NULL DEFAULT FALSE,
    disabled BOOLEAN NOT NULL DEFAULT FALSE,
    branch_id INT,
    role_id INT,
    FOREIGN KEY (branch_id) REFERENCES branch(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);





CREATE TABLE IF NOT EXISTS patients(
    id SERIAL PRIMARY KEY,
    user_id INT,
    pt_firstname VARCHAR(55) NOT NULL,
    pt_lastname VARCHAR (55) NOT NULL,
    pt_occupation VARCHAR (55) NOT NULL,
    pt_address VARCHAR(255) NOT NULL,
    pt_phone VARCHAR(25) NOT NULL,
    pt_age INT,
    pt_ci VARCHAR(25) UNIQUE NOT NULL,
    pt_city VARCHAR (55) NOT NULL,
    pt_email VARCHAR UNIQUE NOT NULL,
    pt_consultation_reason VARCHAR (100),
    pt_recommendations VARCHAR (100),
    FOREIGN KEY (user_id) REFERENCES users(id)
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

CREATE TABLE IF NOT EXISTS lens(
    lens_type VARCHAR(100), --tipo de lente (monofocal,bifocal, etc)
    lens_material VARCHAR(100), -- material del lente (plastico, policarbonato, cristal, etc)
    lens_coating VARCHAR(100), -- recubrimiento del lente(antireflejo, antirrayado)
    lens_color  VARCHAR(100), --color de la lente (si aplica)
    lens_price DECIMAL (10,2), --precio de la lente
    lens_stock INT --cantidad de stock disponible
);

CREATE VIEW user_view AS
SELECT
    users.*,
    branch.name_branch
FROM
    users
        INNER JOIN
    branch ON users.branch_id = branch.id;

