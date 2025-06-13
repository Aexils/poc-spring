CREATE TABLE products (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR NOT NULL,
                          category VARCHAR NOT NULL,
                          serial_number VARCHAR,
                          price NUMERIC(10, 2) NOT NULL,
                          on_sale BOOLEAN DEFAULT false,
                          sale_price NUMERIC(10, 2),
                          description TEXT,
                          image_url VARCHAR,
                          active BOOLEAN DEFAULT true,
                          created_at TIMESTAMP,
                          updated_at TIMESTAMP
);
