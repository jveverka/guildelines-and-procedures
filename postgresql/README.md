# PostgreSQL guidelines
* [PostgreSQL and docker](postgresql-docker.md)
* [PostgreSQL 9.5 on Ubuntu 18.04](postgersql-9.5_on_ubuntu-18.04.md)
* [Transaction Isolation](https://pgdash.io/blog/postgres-transactions.html)
* [Multiple databases in single docker image](postgresql-multiple-dbs)

## Backup database using pg_dump
```
pg_dump --format=t --create --dbname=my-database --clean --schema=my-database --file=pg_dump_my-database.sql --username=my-user --host=localhost --port=5433
```

## Restore database using pg_restore
* Before restoring for the first time:
```
psql -h 127.0.0.1 -p 5433 -U postgres
  CREATE USER my-user WITH PASSWORD 'secret';
  CREATE DATABASE my-database OWNER my-user;
  GRANT ALL ON DATABASE my-database TO my-user;
  \c my-database;
  CREATE SCHEMA my-database;
  ALTER SCHEMA my-database OWNER TO my-user;
  GRANT ALL ON SCHEMA my-database TO my-user;
  GRANT USAGE ON SCHEMA my-database TO my-user;
  \q
```
* Restore data:
```
pg_restore --create --clean --format=t --if-exists --dbname=my-database --schema=my-database --username=my-user --host=localhost --port=5433 -U my-user pg_dump_my-database.sql
```

## Useful PostgreSQL queries
```
psql -h 127.0.0.1 -p 5433 -U postgres
  SELECT * FROM information_schema.schemata; #show schemas
  SELECT * FROM pg_database; #show databases
  SELECT * FROM pg_shadow; #list users 
  SELECT * FROM pg_roles;
  SELECT * FROM pg_catalog.pg_tables;
```
