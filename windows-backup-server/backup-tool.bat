echo off

set SEVENZIP="c:\Program Files\7-Zip\7z.exe"
set LOG_FILE=c:\backup\backup.log

FOR /F "delims=" %%I IN ('powershell get-date -format "{yyyy-MM-dd_HH.mm.ss}"') DO SET DATESTR=%%I

rem 3. create backups on network driverquery
echo "#***************************************" >> %LOG_FILE%
%SEVENZIP% a -tzip X:\%DATESTR%_backup-tool.zip c:\backup >> %LOG_FILE% 
%SEVENZIP% a -tzip X:\%DATESTR%_backup-downloads.zip c:\users\username\Downloads >> %LOG_FILE% 

rem 5. shutdown computer when backup is done
echo "%DATESTR% shutting down" >> %LOG_FILE%
shutdown -s -t 5

echo "%DATESTR% backup done." >> %LOG_FILE%
echo "" >> %LOG_FILE%
echo "" >> %LOG_FILE%
