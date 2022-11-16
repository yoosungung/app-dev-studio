@echo off

chcp 65001
cls

setlocal

set APP_HOME=%~dp0..
set JAVA_HOME=%APP_HOME%\..\..\..\bin\java\openjdk-12.0.1-x64
set MAVEN_HOME=%APP_HOME%\..\..\..\bin\build\apache-maven-3.6.3\bin
set POM_FILE=%APP_HOME%\pom.xml
set PROFILES=%~1

if "!%PROFILES%" == "!" (
    set /p PROFILES=프로파일 ID : 
) else (
    echo 프로파일 ID : %PROFILES%
)

call %MAVEN_HOME%\mvn.cmd -f "%POM_FILE%" -e -P %PROFILES% -Dhttps.protocols=TLSv1,TLSv1.1,TLSv1.2 -DskipTests

endlocal

pause
