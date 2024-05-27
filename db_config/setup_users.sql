INSERT INTO users (
    identification_type,
    identification_number,
    first_name,
    last_name,
    age,
    city_of_residence,
    nationality,
    phone_number,
    civil_status,
    email,
    password,
    gender,
    data_processing_agreement
) VALUES
('CC', '12345691', 'Alex', 'Jons', 30, 'Bogotá', 'Colombian', '3001234567', 'Single', 'alex.jons@example.com', crypt('Password3!', gen_salt('bf')), 'MALE', true),
('CC', '12345692', 'Maria', 'Lopez', 28, 'Medellín', 'Colombian', '3001234568', 'Married', 'maria.lopez@example.com', crypt('Password4!', gen_salt('bf')), 'FEMALE', true),
('CC', '12345693', 'Carlos', 'Smith', 35, 'Cali', 'Colombian', '3001234569', 'Divorced', 'carlos.smith@example.com', crypt('Password5!', gen_salt('bf')), 'MALE', true);
