@echo off
tasklist /FI "IMAGENAME eq eyecandy.exe" | find "eyecandy.exe" > nul
if %errorlevel% equ 0 (
    echo Eye Candy is running!
    echo Process info:
    tasklist /FI "IMAGENAME eq eyecandy.exe"
) else (
    echo Eye Candy is not running
)
pause