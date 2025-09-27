DROP SCHEMA IF EXISTS pharmacy CASCADE;

CREATE SCHEMA pharmacy;

CREATE TYPE pharmacy.transaction_type AS ENUM ('IN', 'OUT');

CREATE TABLE pharmacy.categories (id BIGSERIAL PRIMARY KEY,
                                 name VARCHAR(255) NOT NULL UNIQUE,
                                 description VARCHAR(255) NOT NULL
);

CREATE TABLE pharmacy.medicines (id BIGSERIAL PRIMARY KEY,
                                name VARCHAR(255) NOT NULL,
                                code VARCHAR(255) NOT NULL UNIQUE,
                                price DOUBLE PRECISION NOT NULL,
                                stock INT NOT NULL,
                                category_id BIGINT,
                                CONSTRAINT fk_category
                                    FOREIGN KEY (category_id)
                                        REFERENCES pharmacy.categories(id)
);

CREATE TABLE pharmacy.transactions (id BIGSERIAL PRIMARY KEY,
                                   type pharmacy.transaction_type NOT NULL,
                                   quantity INT NOT NULL,
                                   date TIMESTAMP NOT NULL,
                                   description VARCHAR(255),
                                   medicine_id BIGINT NOT NULL,
                                   CONSTRAINT fk_medicine
                                       FOREIGN KEY (medicine_id)
                                           REFERENCES pharmacy.medicines(id)
);
