CREATE TABLE IF NOT EXISTS "Sales"(
    id SERIAL PRIMARY KEY ,
    patient_id SERIAL,
    clinical_history_id SERIAL,
    lens_id SERIAL,
    frame_id SERIAL,
    user_id SERIAL,
    lead_time DATE,
    discount DECIMAL (10,2),
    total_price DECIMAL (10,2),
    advance_payment DECIMAL (10,2),
    balance DECIMAL (10,2),
    FOREIGN KEY (patient_id) REFERENCES patients(id),
    FOREIGN KEY (clinical_history_id) REFERENCES rx_uso(id),
    FOREIGN KEY (frame_id) REFERENCES "frame"(id),
    FOREIGN KEY (lens_id) REFERENCES lens(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
)