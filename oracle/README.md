
## Check DB server setup
```
SELECT * FROM v$version;
SELECT * FROM DBA_DIRECTORIES;
SELECT * FROM dba_users;
SELECT * FROM DBA_TABLES WHERE owner='USERNAME';
SELECT count(*) FROM DBA_TABLES WHERE owner='USERNAME';
```
