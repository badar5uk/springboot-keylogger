@echo off
echo Stopping Eye Candy...
taskkill /IM eyecandy.exe /F > nul 2>&1

REM Verify it's stopped
tasklist /FI "IMAGENAME eq eyecandy.exe" | find "eyecandy.exe" > nul
if %errorlevel% equ 1 (
    echo Eye Candy has been stopped successfully!
) else (
    echo Failed to stop Eye Candy!
)
timeout /t 2 > nul