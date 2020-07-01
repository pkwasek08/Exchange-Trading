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
