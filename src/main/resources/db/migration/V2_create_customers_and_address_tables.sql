-- Table des customers
CREATE TABLE customers (
                           id SERIAL PRIMARY KEY,
                           first_name VARCHAR,
                           last_name VARCHAR,
                           address VARCHAR,
                           phone VARCHAR,
                           user_id VARCHAR REFERENCES users(id) ON DELETE CASCADE
);

-- Table des billing_addresses
CREATE TABLE billing_addresses (
                                   id SERIAL PRIMARY KEY,
                                   street VARCHAR,
                                   city VARCHAR,
                                   province VARCHAR,
                                   postal_code VARCHAR,
                                   country VARCHAR,
                                   customer_id BIGINT REFERENCES customers(id) ON DELETE CASCADE
);

-- Table des shipping_addresses
CREATE TABLE shipping_addresses (
                                    id SERIAL PRIMARY KEY,
                                    street VARCHAR,
                                    city VARCHAR,
                                    province VARCHAR,
                                    postal_code VARCHAR,
                                    country VARCHAR,
                                    customer_id BIGINT REFERENCES customers(id) ON DELETE CASCADE
);
