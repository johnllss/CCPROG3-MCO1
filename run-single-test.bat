@echo off
setlocal enabledelayedexpansion
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

REM Build the classpath with all JAR files
set CLASSPATH=%BIN_MAIN%;%BIN_TEST%
for %%i in ("%LIB%\*.jar") do (
    if not "%%i"=="%LIB%\junit-platform-console-standalone-1.10.0.jar" (
        set CLASSPATH=!CLASSPATH!;%%i
    )
)

echo Running test: StoreApp.Models.%1
echo.

java -jar "%LIB%\junit-platform-console-standalone-1.10.0.jar" ^
    --class-path "!CLASSPATH!" ^
    --select-class StoreApp.Models.%1

pause
