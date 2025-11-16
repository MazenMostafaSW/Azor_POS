@echo off
REM AZOR POS Debug Launcher Script
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

REM Set JVM options with debug logging
set JVM_OPTS=-Xmx512m -Dfile.encoding=UTF-8

REM Change to application directory
cd /d "%APP_DIR%"

REM Create logs directory
if not exist "logs" mkdir logs

REM Launch AZOR POS in debug mode
echo Starting AZOR POS in DEBUG mode...
echo Logs will be saved to: logs\POSstd.log and logs\POSerror.log
"%JAVA_CMD%" %JVM_OPTS% -cp "%CLASSPATH%" uk.chromis.pos.forms.StartPOS -debug

if errorlevel 1 (
    echo.
    echo ERROR: AZOR POS failed to start!
    echo Check logs folder for details.
    echo.
    pause
)

endlocal

