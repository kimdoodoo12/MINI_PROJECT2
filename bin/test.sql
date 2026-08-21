CREATE TABLE menu (
    menu_id INT AUTO_INCREMENT PRIMARY KEY,
    menu_name VARCHAR(50) NOT NULL,
    menu_price INT NOT NULL
);

INSERT INTO menu (menu_name, menu_price) VALUES
('치즈버거', 5000),
('불고기버거', 5500),
('더블불고기버거', 7500),
('새우버거', 6000),
('베이컨치즈버거', 7000),
('클래식버거', 6500),
('더블치즈버거', 8000),
('스파이시버거', 7000),
('치킨버거', 6500),
('프리미엄버거', 10000);

CREATE TABLE product (
    product_no INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(50) NOT NULL,
    product_qty INT NOT NULL,
    product_price INT NOT NULL
);

INSERT INTO product
(product_name, product_qty, product_price)
VALUES
('햄버거빵', 500, 500),
('소고기패티', 300, 1200),
('불고기패티', 300, 1000),
('치즈', 300, 300),
('양상추', 500, 200),
('토마토', 300, 300),
('피클', 500, 100),
('베이컨', 300, 500),
('새우패티', 300, 1300),
('치킨패티', 300, 1100),
('양파', 500, 150),
('스파이시소스', 300, 200);


CREATE TABLE recipe (
    menu_id INT NOT NULL,
    product_no INT NOT NULL,
    recipe_order INT NOT NULL,
    PRIMARY KEY (menu_id, recipe_order),
    FOREIGN KEY (menu_id) REFERENCES menu(menu_id),
    FOREIGN KEY (product_no) REFERENCES product(product_no)
);INSERT INTO recipe (menu_id, product_no, recipe_order) VALUES
-- 치즈버거
(1,1,1),
(1,4,2),
(1,2,3),
(1,5,4),
(1,1,5),

-- 불고기버거
(2,1,1),
(2,3,2),
(2,11,3),
(2,5,4),
(2,1,5),

-- 더블불고기버거
(3,1,1),
(3,3,2),
(3,4,3),
(3,3,4),
(3,11,5),
(3,5,6),
(3,7,7),
(3,1,8),

-- 새우버거
(4,1,1),
(4,5,2),
(4,9,3),
(4,7,4),
(4,1,5),

-- 베이컨치즈버거
(5,1,1),
(5,8,2),
(5,4,3),
(5,2,4),
(5,6,5),
(5,5,6),
(5,1,7),

-- 클래식버거
(6,1,1),
(6,5,2),
(6,6,3),
(6,7,4),
(6,11,5),
(6,2,6),
(6,1,7),

-- 더블치즈버거
(7,1,1),
(7,4,2),
(7,2,3),
(7,4,4),
(7,2,5),
(7,5,6),
(7,7,7),
(7,1,8),

-- 스파이시버거
(8,1,1),
(8,12,2),
(8,10,3),
(8,11,4),
(8,5,5),
(8,12,6),
(8,1,7),

-- 치킨버거
(9,1,1),
(9,5,2),
(9,10,3),
(9,6,4),
(9,7,5),
(9,1,6),

-- 프리미엄버거
(10,1,1),
(10,4,2),
(10,2,3),
(10,8,4),
(10,4,5),
(10,2,6),
(10,6,7),
(10,11,8),
(10,5,9),
(10,7,10),
(10,1,11);


CREATE TABLE GameLog (
    gameLog_no INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(20) NOT NULL,
    current_gold INT,
    max_date INT UNSIGNED,
    date DATE DEFAULT (CURRENT_DATE)
);

INSERT INTO GameLog
(gameLog_no, user_name, current_gold, max_date, date)
VALUES
(1, 'burgerking', 150000, 12, '2026-08-01'),
(2, 'cheesemaster', 98000, 8, '2026-08-02'),
(3, 'pattyboss', 230000, 18, '2026-08-03'),
(4, 'lettuce123', 45000, 5, '2026-08-04'),
(5, 'tomatoking', 320000, 22, '2026-08-05'),
(6, 'shrimpman', 175000, 14, '2026-08-06'),
(7, 'baconchef', 80000, 7, '2026-08-07'),
(8, 'burgergod', 410000, 30, '2026-08-08'),
(9, 'pickleboy', 60000, 6, '2026-08-09'),
(10, 'burgergirl', 210000, 15, '2026-08-10');



CREATE TABLE GameState (
    gameState_id INT PRIMARY KEY,
    current_day INT,
    current_gold INT,
    restaurant_state BOOLEAN NOT NULL,
    gameState_date DATETIME DEFAULT NOW()
);

INSERT INTO GameState
(gameState_id, current_day, current_gold, restaurant_state, gameState_date)
VALUES
(1, 7, 135000, TRUE, '2026-08-20 10:00:00');

CREATE TABLE customer (
    customer_no INT AUTO_INCREMENT PRIMARY KEY,
    menu_id INT,
    entered_at TIME,
    customer_state VARCHAR(10),
    customerLog_day INT,
    current_gold INT,
    FOREIGN KEY (menu_id) REFERENCES menu(menu_id)
);

INSERT INTO customer
(customer_no, menu_id, entered_at, customer_state, customerLog_day, current_gold)
VALUES
(1, 2, '10:00:00', 'served', 3, 5000),
(2, 5, '10:00:07', 'waiting', 3, 0),
(3, 1, '10:00:15', 'served', 3, 7000),
(4, 8, '10:00:21', 'served', 3, 9000),
(5, 4, '10:00:30', 'left', 3, 0),
(6, 10, '10:00:37', 'waiting', 3, 0),
(7, 3, '10:00:43', 'served', 3, 4500),
(8, 7, '10:00:51', 'served', 3, 8000),
(9, 9, '10:01:02', 'waiting', 3, 0),
(10, 6, '10:01:10', 'served', 3, 6000);



CREATE TABLE cook (
    cook_id INT AUTO_INCREMENT PRIMARY KEY,
    menu_id INT,
    cook_state BOOLEAN NOT NULL,
    FOREIGN KEY (menu_id) REFERENCES menu(menu_id)
);

INSERT INTO cook
(cook_id, menu_id, cook_state)
VALUES
(1, 2, TRUE),
(2, 5, FALSE),
(3, 1, TRUE),
(4, 8, TRUE),
(5, 10, FALSE),
(6, 3, TRUE),
(7, 7, TRUE),
(8, 9, FALSE),
(9, 4, TRUE),
(10, 6, TRUE);

-- 재고 로그 테이블 생성
CREATE TABLE productLog (
    productLog_no INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    product_qty INT NOT NULL,
    product_condition VARCHAR(20) NOT NULL,
    productLog_price INT NOT NULL,

    CONSTRAINT fk_productLog_product
        FOREIGN KEY (product_id)
        REFERENCES product(product_no)
);

-- 재고 로그 데이터 삽입
INSERT INTO productLog
(productLog_no, product_id, product_qty, product_condition, productLog_price)
VALUES
(1, 1, 200, '발주', 20000),
(2, 2, 100, '발주', 15000),
(3, 3, 100, '발주', 30000),
(4, 5, 150, '발주', 75000),
(5, 7, 50, '발주', 25000),
(6, 8, 30, '폐기', 0),
(7, 9, 100, '발주', 10000),
(8, 10, 80, '발주', 12000),
(9, 4, 40, '폐기', 0),
(10, 6, 120, '발주', 60000);
