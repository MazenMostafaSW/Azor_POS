@echo off
REM AZOR POS Executable Packaging Script using Launch4j
REM Developed by Mazen Mostafa

setlocal

echo ========================================
echo AZOR POS Executable Packaging (Launch4j)
echo ========================================
echo.

REM Check if Launch4j is installed
set LAUNCH4J_DIR=C:\Program Files (x86)\Launch4j
if not exist "%LAUNCH4J_DIR%\launch4jc.exe" (
    set LAUNCH4J_DIR=C:\Program Files\Launch4j
)

if not exist "%LAUNCH4J_DIR%\launch4jc.exe" (
    echo ERROR: Launch4j is not installed!
    echo.
    echo Please download and install Launch4j from:
    echo https://launch4j.sourceforge.net/
    echo.
    echo After installation, run this script again.
    echo.
    pause
    exit /b 1
)

REM Check if build exists
if not exist "target\azorpos.jar" (
    echo ERROR: Build not found!
    echo.
    echo Please run build.bat first to compile the application.
    echo.
    pause
    exit /b 1
)

echo Launch4j found at: %LAUNCH4J_DIR%
echo.
echo Creating Windows executable...
echo.

REM Run Launch4j
"%LAUNCH4J_DIR%\launch4jc.exe" launch4j-config.xml

if %errorlevel% neq 0 (
    echo.
    echo ERROR: Launch4j failed!
    echo.
    pause
    exit /b 1
)

echo.
echo Creating distribution folder...
echo.

REM Create distribution folder
set DIST_DIR=target\AZOR_POS_Distribution
if exist "%DIST_DIR%" rmdir /s /q "%DIST_DIR%"
mkdir "%DIST_DIR%"

REM Copy executable
copy /Y target\AZOR_POS.exe "%DIST_DIR%\"

REM Copy JAR
copy /Y target\azorpos.jar "%DIST_DIR%\"

REM Copy libraries
xcopy /E /I /Y lib "%DIST_DIR%\lib\"

REM Copy resources
xcopy /E /I /Y locales "%DIST_DIR%\locales\"
xcopy /E /I /Y images "%DIST_DIR%\images\"
xcopy /E /I /Y cssStyles "%DIST_DIR%\cssStyles\"
xcopy /E /I /Y fonts "%DIST_DIR%\fonts\"
xcopy /E /I /Y iconsets "%DIST_DIR%\iconsets\"
xcopy /E /I /Y licensing "%DIST_DIR%\licensing\"

REM Copy sound files
copy /Y *.wav "%DIST_DIR%\" 2>nul

REM Copy configuration template
copy /Y chromisposconfig.properties "%DIST_DIR%\chromisposconfig.properties.template"

REM Copy JRE if exists
if exist "jre" (
    echo Copying bundled JRE...
    xcopy /E /I /Y jre "%DIST_DIR%\jre\"
)

REM Create README
echo AZOR POS - Point of Sale System > "%DIST_DIR%\README.txt"
echo Developed by Mazen Mostafa >> "%DIST_DIR%\README.txt"
echo. >> "%DIST_DIR%\README.txt"
echo To run AZOR POS: >> "%DIST_DIR%\README.txt"
echo 1. Double-click AZOR_POS.exe >> "%DIST_DIR%\README.txt"
echo. >> "%DIST_DIR%\README.txt"
echo Requirements: >> "%DIST_DIR%\README.txt"
echo - Java 11 or higher (bundled JRE included if available) >> "%DIST_DIR%\README.txt"
echo - MySQL database configured >> "%DIST_DIR%\README.txt"
echo - chromisposconfig.properties configured >> "%DIST_DIR%\README.txt"

echo.
echo ========================================
echo SUCCESS!
echo ========================================
echo.
echo Distribution package created at:
echo %DIST_DIR%
echo.
echo Contents:
echo - AZOR_POS.exe (Windows executable)
echo - azorpos.jar (Main application)
echo - lib\ (Dependencies)
echo - locales\ (Language files including Arabic)
echo - images\, fonts\, cssStyles\, iconsets\ (Resources)
echo - jre\ (Bundled Java Runtime if available)
echo.
echo You can now distribute the entire folder!
echo.
pause

endlocal

