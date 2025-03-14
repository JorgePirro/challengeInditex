SET REFERENTIAL_INTEGRITY FALSE;
DELETE FROM brand WHERE id = 1;
SET REFERENTIAL_INTEGRITY TRUE;

-- Ensure the tables exist
CREATE TABLE IF NOT EXISTS brand (
                                     id INT PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS price (
                                     id INT PRIMARY KEY,
                                     brand_id INT,
                                     start_date TIMESTAMP,
                                     end_date TIMESTAMP,
                                     price_list INT,
                                     product_id BIGINT,
                                     priority INT,
                                     price DECIMAL(10, 2),
    currency VARCHAR(3),
    FOREIGN KEY (brand_id) REFERENCES brand(id)
    );

-- Delete existing records to avoid primary key violations
DELETE FROM price WHERE brand_id = 1;
DELETE FROM brand WHERE id = 1;

-- Insert new test data
INSERT INTO brand (id, name) VALUES (1, 'ZARA');

INSERT INTO price (id, brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
VALUES (1, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR');