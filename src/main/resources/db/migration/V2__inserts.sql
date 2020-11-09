INSERT INTO companies(id, name, industry, revenue, capital)
VALUES (1, 'Asseco', 'Computer Software', 77150000, 2150000000);

INSERT INTO companies(id,
                      name, industry, revenue, capital)
VALUES (2, 'Google', 'Computer Software', 66001000000, 131133000000);

INSERT INTO users(id, name, lastname, email, created_at, birthday, login, password, cash)
VALUES (1, 'admin', 'admin', 'admin@com', CURRENT_DATE, CURRENT_DATE, 'admin', 'admin', 1000000);

INSERT INTO users(id, name, lastname, email, created_at, birthday, login, password, cash)
VALUES (2, 'Piotr', 'Kwasek', 'pkwasek@com', CURRENT_DATE, CURRENT_DATE, 'login', 'password', 1000000);

INSERT INTO offers_sell_buy_limit(id, amount, price, company_id , type, limit_price, date, user_id, active)
VALUES (1, 1000 , 200 , 1, 'Sell', 200, CURRENT_DATE, null, true);

INSERT INTO offers_sell_buy_limit(id, amount, price, company_id , type, limit_price, date, user_id, active)
VALUES (2, 1000 , 100 , 2, 'Sell', 100, CURRENT_DATE, null, true);