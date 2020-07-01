DROP TABLE IF EXISTS "offers_sell_buy_limit" CASCADE;
CREATE TABLE "offers_sell_buy_limit" (
                                         "id" SERIAL PRIMARY KEY,
                                         "amount" integer,
                                         "price" numeric(10, 2),
                                         "limit" numeric(10, 2),
                                         "company_id" integer,
                                         "type" varchar
);

DROP TABLE IF EXISTS "companies" CASCADE;
CREATE TABLE "companies" (
                             "id" SERIAL PRIMARY KEY,
                             "name" varchar,
                             "industry" varchar,
                             "revenue" numeric(16, 2),
                             "capital" numeric(16, 2)
);

DROP TABLE IF EXISTS "transactions" CASCADE;
CREATE TABLE "transactions" (
                                "id" SERIAL PRIMARY KEY,
                                "offer_sell_buy_id" integer,
                                "offer_sell_buy_limit_id" integer,
                                "date" timestamp
);

DROP TABLE IF EXISTS "companies_statistics" CASCADE;
CREATE TABLE "companies_statistics" (
                                        "id" SERIAL PRIMARY KEY,
                                        "price" numeric(10, 2),
                                        "date" timestamp,
                                        "volume" integer,
                                        "max_price" numeric(10, 2),
                                        "min_price" numeric(10, 2),
                                        "trend_value" numeric(3, 2),
                                        "companie_id" integer
);

DROP TABLE IF EXISTS "deposits" CASCADE;
CREATE TABLE "deposits" (
                            "id" SERIAL PRIMARY KEY,
                            "cash" numeric(10, 2),
                            "transaction_id" integer
);

DROP TABLE IF EXISTS "users" CASCADE;
CREATE TABLE "users" (
                         "id" SERIAL PRIMARY KEY,
                         "name" varchar,
                         "lastname" varchar,
                         "account_number" numeric(26, 0),
                         "email" varchar,
                         "created_at" timestamp,
                         "birthday" timestamp,
                         "country" varchar,
                         "login" varchar,
                         "password" varchar,
                         "security_code" integer,
                         "deposit_id" int
);

DROP TABLE IF EXISTS "offers_sell_buy" CASCADE;
CREATE TABLE "offers_sell_buy" (
                                   "id" SERIAL PRIMARY KEY,
                                   "amount" integer,
                                   "price" real,
                                   "company_id" integer,
                                   "type" varchar
);


ALTER TABLE "users" ADD FOREIGN KEY ("deposit_id") REFERENCES "deposits" ("id");

ALTER TABLE "offers_sell_buy" ADD FOREIGN KEY ("company_id") REFERENCES "companies" ("id");

ALTER TABLE "transactions" ADD FOREIGN KEY ("offer_sell_buy_id") REFERENCES "offers_sell_buy" ("id");

ALTER TABLE "transactions" ADD FOREIGN KEY ("offer_sell_buy_limit_id") REFERENCES "offers_sell_buy_limit" ("id");

ALTER TABLE "offers_sell_buy_limit" ADD FOREIGN KEY ("company_id") REFERENCES "companies" ("id");

ALTER TABLE "deposits" ADD FOREIGN KEY ("transaction_id") REFERENCES "transactions" ("id");

ALTER TABLE "companies_statistics" ADD FOREIGN KEY ("companie_id") REFERENCES "companies" ("id");