CREATE TABLE mst_order (
       id SERIAL PRIMARY KEY,
       payment_id INT REFERENCES mst_list_payment(id) NULL,
       is_paid BOOLEAN NOT NULL DEFAULT FALSE,
       amount INT NOT NULL,
       product_id INT NOT NULL,
       user_id INT NOT NULL,

       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
       created_by VARCHAR(255) NULL,
       updated_at TIMESTAMP,
       updated_by VARCHAR(255),
       is_active BOOLEAN DEFAULT TRUE,
       is_delete BOOLEAN NOT NULL DEFAULT FALSE,
       deleted_by VARCHAR(36),
       deleted_at TIMESTAMP
);