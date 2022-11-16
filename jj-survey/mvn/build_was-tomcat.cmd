@echo off

chcp 65001
cls

setlocal

set PROFILES0=%~nx0
set PROFILES1=%PROFILES0:build_=%
set PROFILES2=%PROFILES1:.cmd=%

call %~dp0build.cmd "%PROFILES2%"

endlocal
