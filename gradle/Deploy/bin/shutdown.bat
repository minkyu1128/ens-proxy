@echo off

for /F "delims=" %%i in (proxyens.pid) do set "PROC_ID=%%i"
taskkill /F /PID %PROC_ID%