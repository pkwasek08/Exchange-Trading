INSERT INTO companies(id, name, industry, revenue, capital)
VALUES (1, 'Asseco', 'Computer Software', 2150000000, 77150000);

INSERT INTO companies(id,
                      name, industry, revenue, capital)
VALUES (2, 'Google', 'Computer Software', 66001000000, 131133000000);

INSERT INTO users(id, name, lastname, email, created_at, birthday, country, login, password)
VALUES (1, 'admin', 'admin', 'admin@com', CURRENT_DATE, CURRENT_DATE, 'Poland', 'admin', 'admin');

INSERT INTO users(id, name, lastname, email, created_at, birthday, country, login, password)
VALUES (2, 'Piotr', 'Kwasek', 'pkwasek@com', CURRENT_DATE, CURRENT_DATE, 'Poland', 'login', 'password');

INSERT INTO deposits(id, cash, user_id)
VALUES (1, 1000000, 1);

INSERT INTO deposits(id, cash, user_id)
VALUES (2, 300000, 2);

INSERT INTO companies_statistics(id, price, date, volume, max_price, min_price, trend_value, companie_id)
VALUES (1, 100, current_date, 1000, 120, 80, 0.0, 1);

INSERT INTO companies_statistics(id, price, date, volume, max_price, min_price, trend_value, companie_id)
VALUES (2, 500, CURRENT_DATE, 2000, 530, 480, 0.0, 2);