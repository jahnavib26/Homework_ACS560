DROP TABLE IF EXISTS sales;

CREATE TABLE sales (
    sale_date DATE NOT NULL,
    item_name VARCHAR(255) NOT NULL,
    transaction_type VARCHAR(255) NOT NULL,
    item_type VARCHAR(255),
    item_price DOUBLE,
    quantity INT,
    transaction_amount DOUBLE,
    staff_gender VARCHAR(10),
    time_of_sale VARCHAR(255),
    year_month VARCHAR(255),
    PRIMARY KEY (sale_date, item_name, transaction_type)
);
