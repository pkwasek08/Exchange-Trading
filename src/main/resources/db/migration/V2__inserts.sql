DROP TABLE IF EXISTS "companies" CASCADE;
CREATE TABLE "companies"(
                             "id" SERIAL PRIMARY KEY,
                             "name" varchar,
                             "industry" varchar,
                             "revenue" numeric(16, 2),
                             "capital" numeric(16, 2));

INSERT INTO companies(id, name, industry, revenue, capital)
VALUES (1, 'Asseco', 'Computer Software', 2150000000, 77150000);

INSERT INTO companies(id,
                      name, industry, revenue, capital)
VALUES (2, 'Google', 'Computer Software', 66001000000, 131133000000);

INSERT INTO users(
    id, name, lastname, account_number, email, created_at, birthday, country, login, password, security_code, deposit_id)
VALUES (1, 'admin', 'admin', null, 'admin@com', CURRENT_DATE, CURRENT_DATE, 'Poland', 'admin', 'admin', 1234, null);