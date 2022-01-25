# Tool-Box cheat sheet

## Network testing
Usual hacker's swiss army knife tools like:
* telnet, nmap, ping, dig, nslookup, nc, tracepath, stress, ifconfig
* python3

### PostgreSQL commands
```
psql -h <hostname> -p <port> -U <username>
  SELECT * FROM information_schema.schemata; #show schemas
  SELECT * FROM pg_database; #show databases
  SELECT * FROM pg_shadow; #list users 
  SELECT * FROM pg_roles;
  SELECT * FROM pg_catalog.pg_tables;
```

### Redis commands
```
cd redis-6.2.5/src
./redis-cli -c -h <redis-cluster-endpoint> --tls -a <password> -p <port>
ping
info 
info keyspace
keys *
type <key-id>
get <key-id>
```

### MongoDB Shell
```
cd mongosh-1.0.5-linux-x64/bin
./mongosh 
```

### Start Web Server
```
/usr/sbin/nginx -g "daemon off;error_log /dev/stdout debug;"
curl http://localhost:8080
```