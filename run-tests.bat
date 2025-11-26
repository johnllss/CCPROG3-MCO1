@echo off
REM Batch script to compile and run JUnit tests for CCPROG3-MCO1

echo ========================================
echo CCPROG3 MCO1 - Test Runner
echo ========================================
echo.

REM Set project root
set PROJECT_ROOT=%~dp0

REM Set paths
set SRC_MAIN=%PROJECT_ROOT%src\main\java
set SRC_TEST=%PROJECT_ROOT%src\test\java
set BIN_MAIN=%PROJECT_ROOT%bin\main
set BIN_TEST=%PROJECT_ROOT%bin\test
set LIB=%PROJECT_ROOT%lib

REM Create bin directories if they don't exist
if not exist "%BIN_MAIN%" mkdir "%BIN_MAIN%"
if not exist "%BIN_TEST%" mkdir "%BIN_TEST%"

echo Step 1: Compiling main source files...
javac -cp "%LIB%\*" -d "%BIN_MAIN%" "%SRC_MAIN%\StoreApp\Models\*.java" "%SRC_MAIN%\StoreApp\Controllers\*.java"
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Failed to compile main source files
    pause
    exit /b 1
)
echo Main source compiled successfully!
echo.

echo Step 2: Compiling test files...
javac -cp "%LIB%\*;%BIN_MAIN%" -d "%BIN_TEST%" "%SRC_TEST%\StoreApp\Models\*Test.java" "%SRC_TEST%\StoreApp\Models\*.java" "%SRC_TEST%\StoreApp\Controllers\*Test.java" "%SRC_TEST%\StoreApp\*.java"
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Failed to compile test files
    pause
    exit /b 1
)
echo Test files compiled successfully!
echo.

echo Step 3: Running tests...
echo.
java -jar "%LIB%\junit-platform-console-standalone-1.10.0.jar" ^
    --class-path "%BIN_MAIN%;%BIN_TEST%;%LIB%\*" ^
    --scan-class-path ^
    --reports-dir="%PROJECT_ROOT%test-reports"

echo.
echo ========================================
echo Test execution completed!
echo Check test-reports folder for detailed results
echo ========================================
pause
