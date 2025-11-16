@echo off
REM AZOR POS Build Script
REM Developed by Mazen Mostafa

echo ========================================
echo AZOR POS Build Script
echo ========================================
echo.

REM Check if Maven is installed
where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo ERROR: Maven is not installed or not in PATH!
    echo.
    echo Please install Maven from: https://maven.apache.org/download.cgi
    echo.
    pause
    exit /b 1
)

echo Maven found. Starting build...
echo.

REM Clean previous build
echo [1/3] Cleaning previous build...
call mvn clean
if %errorlevel% neq 0 (
    echo ERROR: Clean failed!
    pause
    exit /b 1
)

echo.
echo [2/3] Compiling and packaging...
call mvn package
if %errorlevel% neq 0 (
    echo ERROR: Build failed!
    pause
    exit /b 1
)

echo.
echo [3/3] Build completed successfully!
echo.
echo Output location: target\AZOR_POS_1.5.5
echo.
echo To create Windows executable, run: package-exe.bat
echo.
pause

