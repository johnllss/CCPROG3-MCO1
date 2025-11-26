@echo off
REM Batch script to run a single test class

if "%1"=="" (
    echo Usage: run-single-test.bat TestClassName
    echo Example: run-single-test.bat Product_Model_Test
    pause
    exit /b 1
)

set PROJECT_ROOT=%~dp0
set BIN_MAIN=%PROJECT_ROOT%bin\main
set BIN_TEST=%PROJECT_ROOT%bin\test
set LIB=%PROJECT_ROOT%lib

echo Running test: StoreApp.Models.%1
echo.

java -jar "%LIB%\junit-platform-console-standalone-1.10.0.jar" ^
    --class-path "%BIN_MAIN%;%BIN_TEST%;%LIB%\*" ^
    --select-class StoreApp.Models.%1

pause
