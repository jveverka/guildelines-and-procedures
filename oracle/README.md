# Oracle Tips

## Check DB server setup
```
SELECT * FROM v$version;
SELECT * FROM DBA_DIRECTORIES;
SELECT * FROM dba_users;
SELECT * FROM DBA_TABLES WHERE owner='USERNAME';
SELECT count(*) FROM DBA_TABLES WHERE owner='USERNAME';
```

## Export schema (database)
Using commandline.
```
expdp SYSTEM/<password>@<hostname>:<port>/<SID> SCHEMAS=<SCHEMA_NAME> DIRECTORY=DATA_PUMP_DIR DUMPFILE=<DUMP_FILE>.dmp LOGFILE=<LOG_FILE>.log
```
SQL export command using SQL developer
```
DECLARE
hdnl NUMBER;
BEGIN
hdnl := DBMS_DATAPUMP.OPEN( operation => 'EXPORT', job_mode => 'SCHEMA', job_name=>null);
DBMS_DATAPUMP.ADD_FILE( handle => hdnl, filename => '<DUMP_FILE>.dmp', directory => 'DATA_PUMP_DIR', filetype => dbms_datapump.ku$_file_type_dump_file);
DBMS_DATAPUMP.ADD_FILE( handle => hdnl, filename => '<LOG_FILE>.log',  directory => 'DATA_PUMP_DIR', filetype => dbms_datapump.ku$_file_type_log_file);
DBMS_DATAPUMP.METADATA_FILTER(hdnl,'SCHEMA_EXPR','IN (''<SCHEMA_NAME>'')');
DBMS_DATAPUMP.START_JOB(hdnl);
END;
/  
```

## Import schema (database)
Copy schema dump file into ``DATA_PUMP_DIR`` directory.
Prepare users and roles for import.
```
DROP USER <SCHEMA_NAME> CASCADE;
CREATE USER <SCHEMA_NAME> IDENTIFIED BY <password>;
GRANT DBA to <SCHEMA_NAME>;
CREATE ROLE <ROLE_NAME>
```
Using commandline 
```
DECLARE
hdnl NUMBER;
BEGIN
hdnl := DBMS_DATAPUMP.OPEN( operation => 'IMPORT', job_mode => 'SCHEMA', job_name=>null);
DBMS_DATAPUMP.ADD_FILE( handle => hdnl, filename => '<DUMP_FILE>.dmp', directory => 'DATA_PUMP_DIR', filetype => dbms_datapump.ku$_file_type_dump_file);
DBMS_DATAPUMP.ADD_FILE( handle => hdnl, filename => '<LOG_FILE>.log',  directory => 'DATA_PUMP_DIR', filetype => dbms_datapump.ku$_file_type_log_file);
DBMS_DATAPUMP.METADATA_FILTER(hdnl,'SCHEMA_EXPR','IN (''<SCHEMA_NAME>'')');
DBMS_DATAPUMP.START_JOB(hdnl);
END;
/
```
SQL import command using SQL developer
```
impdp ...
```