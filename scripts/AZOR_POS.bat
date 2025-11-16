@echo off
REM AZOR POS Launcher Script
REM Developed by Mazen Mostafa

setlocal

REM Set the application directory
set APP_DIR=%~dp0

REM Check if bundled JRE exists
if exist "%APP_DIR%jre\bin\java.exe" (
    set JAVA_CMD=%APP_DIR%jre\bin\java.exe
    echo Using bundled Java Runtime...
) else (
    REM Use system Java
    set JAVA_CMD=java
    echo Using system Java...
)

REM Set classpath
set CLASSPATH=%APP_DIR%azorpos.jar;%APP_DIR%lib\*

REM Set JVM options
set JVM_OPTS=-Xmx512m -Dfile.encoding=UTF-8

REM Change to application directory
cd /d "%APP_DIR%"

REM Launch AZOR POS
echo Starting AZOR POS...
"%JAVA_CMD%" %JVM_OPTS% -cp "%CLASSPATH%" uk.chromis.pos.forms.StartPOS

if errorlevel 1 (
    echo.
    echo ERROR: AZOR POS failed to start!
    echo.
    echo Please check:
    echo 1. Java 11 or higher is installed
    echo 2. Database is running and accessible
    echo 3. Configuration file exists: chromisposconfig.properties
    echo.
    pause
)

endlocal

