INSERT INTO companies(name, industry, revenue, capital)
VALUES ('Asseco', 'Computer Software', 77150000, 2150000000);

INSERT INTO companies(name, industry, revenue, capital)
VALUES ('Google', 'Computer Software', 66001000000, 131133000000);

INSERT INTO users(name, lastname, email, created_at, birthday, login, password, cash)
VALUES ('admin', 'admin', 'admin@com', CURRENT_DATE, CURRENT_DATE, 'admin', 'admin', 1000000);

INSERT INTO users(name, lastname, email, created_at, birthday, login, password, cash)
VALUES ('Piotr', 'Kwasek', 'pkwasek@com', CURRENT_DATE, CURRENT_DATE, 'login', 'password', 1000000);

INSERT INTO offers_sell_buy_limit(amount, price, company_id , type, limit_price, date, user_id, active)
VALUES (100 , 200 , 1, 'Sell', 200, CURRENT_DATE, null, true);

INSERT INTO offers_sell_buy_limit(amount, price, company_id , type, limit_price, date, user_id, active)
VALUES (100 , 100 , 2, 'Sell', 100, CURRENT_DATE, null, true);