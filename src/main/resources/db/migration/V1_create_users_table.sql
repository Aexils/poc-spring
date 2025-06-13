CREATE TABLE users (
                       id VARCHAR PRIMARY KEY,
                       email VARCHAR NOT NULL,
                       name VARCHAR,
                       picture_url VARCHAR,
                       email_verified BOOLEAN DEFAULT false,
                       active BOOLEAN DEFAULT true,
                       role VARCHAR NOT NULL DEFAULT 'GUEST',
                       created_at TIMESTAMP,
                       updated_at TIMESTAMP
);
