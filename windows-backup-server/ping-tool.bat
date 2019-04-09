echo off

set LOG_FILE=c:\backup\ping.log

FOR /F "delims=" %%I IN ('powershell get-date -format "{yyyy-MM-dd_HH.mm.ss}"') DO SET DATESTR=%%I

:LOOPSTART
echo %DATESTR% >> %LOG_FILE%
ping 8.8.8.8 -n 1 >> %LOG_FILE%
timeout /t 10 > NUL
GOTO LOOPSTART

