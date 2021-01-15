INSERT INTO companies(name, industry, revenue, capital)
VALUES ('Asseco', 'Computer software', 77150000, 2150000000);

INSERT INTO companies(name, industry, revenue, capital)
VALUES ('Google', 'Computer software', 66001000000, 131133000000);

INSERT INTO companies(name, industry, revenue, capital)
VALUES ('Tesla', 'Automotive', 24578000000, 34309000000);

INSERT INTO companies(name, industry, revenue, capital)
VALUES ('Amazon', 'E-commerce, cloud computing', 280522000000, 225248000000);

INSERT INTO companies(name, industry, revenue, capital)
VALUES ('CD Projekt', 'Video games', 521200000, 1404000000);

INSERT INTO companies(name, industry, revenue, capital)
VALUES ('Pfizer', 'Pharmaceutical', 51750000000, 167489000000);

INSERT INTO companies(name, industry, revenue, capital)
VALUES ('Apple', 'Computer software and hardware', 274515000000, 323888000000);

INSERT INTO companies(name, industry, revenue, capital)
VALUES ('Toyota', 'Automotive', 275400000000, 492700000000);

INSERT INTO companies(name, industry, revenue, capital)
VALUES ('Samsung', 'Conglomerate', 211200000000, 305500000000);

INSERT INTO companies(name, industry, revenue, capital)
VALUES ('Twitter', 'Internet', 3460000000, 10160000000);


INSERT INTO users(name, lastname, email, created_at, birthday, login, password, cash)
VALUES ('admin', 'admin', 'admin@com', CURRENT_DATE, CURRENT_DATE, 'admin', 'admin', 1000000);

INSERT INTO users(name, lastname, email, created_at, birthday, login, password, cash)
VALUES ('Piotr', 'Kwasek', 'pkwasek@com', CURRENT_DATE, CURRENT_DATE, 'login', 'password', 1000000);


INSERT INTO offers_sell_buy_limit(amount, price, company_id , type, limit_price, date, user_id, active)
VALUES (100 , 200 , 1, 'Sell', 200, CURRENT_DATE, null, true);

INSERT INTO offers_sell_buy_limit(amount, price, company_id , type, limit_price, date, user_id, active)
VALUES (100 , 100 , 2, 'Sell', 100, CURRENT_DATE, null, true);

INSERT INTO offers_sell_buy_limit(amount, price, company_id , type, limit_price, date, user_id, active)
VALUES (100 , 200 , 3, 'Sell', 200, CURRENT_DATE, null, true);

INSERT INTO offers_sell_buy_limit(amount, price, company_id , type, limit_price, date, user_id, active)
VALUES (100 , 100 , 4, 'Sell', 100, CURRENT_DATE, null, true);

INSERT INTO offers_sell_buy_limit(amount, price, company_id , type, limit_price, date, user_id, active)
VALUES (100 , 200 , 5, 'Sell', 200, CURRENT_DATE, null, true);

INSERT INTO offers_sell_buy_limit(amount, price, company_id , type, limit_price, date, user_id, active)
VALUES (100 , 100 , 6, 'Sell', 100, CURRENT_DATE, null, true);

INSERT INTO offers_sell_buy_limit(amount, price, company_id , type, limit_price, date, user_id, active)
VALUES (100 , 200 , 7, 'Sell', 200, CURRENT_DATE, null, true);

INSERT INTO offers_sell_buy_limit(amount, price, company_id , type, limit_price, date, user_id, active)
VALUES (100 , 100 , 8, 'Sell', 100, CURRENT_DATE, null, true);

INSERT INTO offers_sell_buy_limit(amount, price, company_id , type, limit_price, date, user_id, active)
VALUES (100 , 200 , 9, 'Sell', 200, CURRENT_DATE, null, true);

INSERT INTO offers_sell_buy_limit(amount, price, company_id , type, limit_price, date, user_id, active)
VALUES (100 , 100 , 10, 'Sell', 100, CURRENT_DATE, null, true);