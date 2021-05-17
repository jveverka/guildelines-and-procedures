# Test-Box cheat sheet

## Network testing
Usual hacker's swiss army knife tools like:
* telnet, nmap, ping, dig, nslookup, nc, tracepath

## Postgresql commands
```
psql -h <hostname> -p <port> -U <username>
  SELECT * FROM information_schema.schemata; #show schemas
  SELECT * FROM pg_database; #show databases
  SELECT * FROM pg_shadow; #list users 
  SELECT * FROM pg_roles;
  SELECT * FROM pg_catalog.pg_tables;
```