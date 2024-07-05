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
    age INT NOT NULL,
    charge VARCHAR(40) NOT NULL,
    birthdate DATE NOT NULL,
    check_in_date DATE NOT NULL,
    ci VARCHAR(25) UNIQUE NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    phone_number VARCHAR(25) UNIQUE NOT NULL,
    password VARCHAR(200)  NOT NULL,
    locked BOOLEAN NOT NULL DEFAULT FALSE,
    disabled BOOLEAN NOT NULL DEFAULT FALSE,
    branch_id INT,
    role_id INT,
    FOREIGN KEY (branch_id) REFERENCES branch(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);





CREATE TABLE IF NOT EXISTS patients(
    user_id INT,
    id SERIAL PRIMARY KEY,
    pt_firstname VARCHAR(55) NOT NULL,
    pt_lastname VARCHAR (55) NOT NULL,
    pt_occupation VARCHAR (55) NOT NULL,
    pt_address VARCHAR(255) NOT NULL,
    pt_phone VARCHAR(25) NOT NULL,
    pt_age INT NOT NULL,
    pt_ci VARCHAR(25) UNIQUE NOT NULL,
    pt_city VARCHAR (55) NOT NULL,
    pt_email VARCHAR UNIQUE NOT NULL,
    pt_consultation_reason VARCHAR (100),
    pt_recommendations VARCHAR (100),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS frame(
    id SERIAL PRIMARY KEY,
    brand  VARCHAR (100) NOT NULL,
    referenceBrand VARCHAR(40) NOT NULL ,
    size INT NOT NULL,
    bridge VARCHAR (55) NOT NULL,  -- puente
    model VARCHAR (100) NOT NULL, --model
    color  VARCHAR (20) NOT NULL,
    price DECIMAL (10, 2)NOT NULL,
    frameStock INT NOT NULL
);

CREATE TABLE IF NOT EXISTS lens(
    id SERIAL PRIMARY KEY,
    lens_type VARCHAR(100), --tipo de lente (monofocal,bifocal, etc)
    lens_material VARCHAR(100), -- material del lente (plastico, policarbonato, cristal, etc)
    lens_coating VARCHAR(100), -- recubrimiento del lente(antireflejo, antirrayado)
    lens_color  VARCHAR(100), --color de la lente (si aplica)
    lens_price DECIMAL (10,2), --precio de la lente
    lens_stock INT --cantidad de stock disponible
);


CREATE TABLE IF NOT EXISTS rx_uso(
    id SERIAL PRIMARY KEY,
    id_user Int,
    FOREIGN KEY (id_user) REFERENCES users(id) ON DELETE CASCADE,
    sphere_r VARCHAR(15) NOT NULL,
    cylinder_r VARCHAR(15) NOT NULL,
    axis_r VARCHAR(15) NOT NULL,
    prism_r VARCHAR(15) NOT NULL,
    add_r VARCHAR(15) NOT NULL,
    av_vl_r VARCHAR(15) NOT NULL,
    dnp_r VARCHAR(15) NOT NULL,
    alt_r VARCHAR(15) NOT NULL,
    sphere_l VARCHAR(15) NOT NULL,
    cylinder_l VARCHAR(15) NOT NULL,
    axis_l VARCHAR(15) NOT NULL,
    prism_l VARCHAR(15) NOT NULL,
    add_l VARCHAR(15) NOT NULL,
    av_vl_l VARCHAR(15) NOT NULL,
    dnp_l VARCHAR(15) NOT NULL,
    alt_l VARCHAR(15) NOT NULL
);

CREATE VIEW user_view AS
SELECT
    users.*,
    branch.name_branch,
    role.role_name
FROM
    users
        INNER JOIN
    branch ON users.branch_id = branch.id
        INNER JOIN
    role ON users.role_id = role.id;

CREATE VIEW user_view_pat_register AS
SELECT
    patients.*,
    users.username
FROM
    patients
           INNER JOIN
    users ON patients.user_id = users.id
