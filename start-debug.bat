@echo off
echo [DEBUG] Starting Eye Candy debugging...
echo.

echo [1] Checking Java installation...
where java > java-path.txt 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java not found in PATH!
    type java-path.txt
    goto :end
)

echo [2] Checking EXE existence...
if not exist "target\eyecandy.exe" (
    echo ERROR: eyecandy.exe not found in target directory!
    dir /b target
    goto :end
)

echo [3] Attempting to start with visible console...
start "EyeCandyDebug" /wait cmd /c "target\eyecandy.exe --visible > eyecandy.log 2>&1"

echo [4] Checking process status...
timeout /t 5 > nul
tasklist /FI "IMAGENAME eq eyecandy.exe" > process-list.txt

findstr /m "eyecandy.exe" process-list.txt > nul
if %errorlevel% equ 0 (
    echo SUCCESS: Eye Candy is running!
    type process-list.txt
) else (
    echo ERROR: Failed to start process!
    echo Check eyecandy.log for details:
    type eyecandy.log
)

:end
echo.
echo [DEBUG] Troubleshooting completed
pause
timeout /t 2 > nul