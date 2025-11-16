@echo off
REM AZOR POS Executable Packaging Script
REM Creates a Windows executable using jpackage
REM Developed by Mazen Mostafa

setlocal enabledelayedexpansion

echo ========================================
echo AZOR POS Executable Packaging
echo ========================================
echo.

REM Check if jpackage is available (Java 14+)
where jpackage >nul 2>nul
if %errorlevel% neq 0 (
    echo ERROR: jpackage is not available!
    echo.
    echo jpackage requires Java 14 or higher.
    echo Your current Java version:
    java -version
    echo.
    echo Please install JDK 14+ or use the alternative Launch4j method.
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

echo jpackage found. Creating Windows executable...
echo.

REM Set variables
set APP_NAME=AZOR POS
set APP_VERSION=1.5.5
set MAIN_JAR=target\azorpos.jar
set MAIN_CLASS=uk.chromis.pos.forms.StartPOS
set OUTPUT_DIR=target\installer
set ICON_FILE=src-beans\uk\chromis\fixedimages\azoricon.png

REM Convert PNG icon to ICO if needed (requires ImageMagick or manual conversion)
if not exist "azoricon.ico" (
    echo WARNING: azoricon.ico not found. Using default icon.
    echo To use custom icon, convert azoricon.png to azoricon.ico
    set ICON_ARG=
) else (
    set ICON_ARG=--icon azoricon.ico
)

REM Clean output directory
if exist "%OUTPUT_DIR%" rmdir /s /q "%OUTPUT_DIR%"
mkdir "%OUTPUT_DIR%"

echo Creating application image...
echo.

REM Create application image with jpackage
jpackage ^
    --type app-image ^
    --name "AZOR POS" ^
    --app-version %APP_VERSION% ^
    --input target ^
    --main-jar azorpos.jar ^
    --main-class %MAIN_CLASS% ^
    --dest "%OUTPUT_DIR%" ^
    %ICON_ARG% ^
    --java-options "-Xmx512m" ^
    --java-options "-Dfile.encoding=UTF-8" ^
    --runtime-image jre

if %errorlevel% neq 0 (
    echo.
    echo ERROR: jpackage failed!
    echo.
    pause
    exit /b 1
)

echo.
echo Copying additional resources...

REM Copy additional resources to the app image
xcopy /E /I /Y lib "%OUTPUT_DIR%\AZOR POS\lib\"
xcopy /E /I /Y locales "%OUTPUT_DIR%\AZOR POS\locales\"
xcopy /E /I /Y images "%OUTPUT_DIR%\AZOR POS\images\"
xcopy /E /I /Y cssStyles "%OUTPUT_DIR%\AZOR POS\cssStyles\"
xcopy /E /I /Y fonts "%OUTPUT_DIR%\AZOR POS\fonts\"
xcopy /E /I /Y iconsets "%OUTPUT_DIR%\AZOR POS\iconsets\"
xcopy /E /I /Y licensing "%OUTPUT_DIR%\AZOR POS\licensing\"
copy /Y *.wav "%OUTPUT_DIR%\AZOR POS\" 2>nul
copy /Y chromisposconfig.properties "%OUTPUT_DIR%\AZOR POS\" 2>nul

echo.
echo ========================================
echo SUCCESS!
echo ========================================
echo.
echo Application package created at:
echo %OUTPUT_DIR%\AZOR POS
echo.
echo Executable: %OUTPUT_DIR%\AZOR POS\AZOR POS.exe
echo.
echo You can now distribute the entire "AZOR POS" folder.
echo.
pause

endlocal

