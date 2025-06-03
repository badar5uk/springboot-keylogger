@echo off
REM Start the application in visible mode
echo Starting Eye Candy in visible mode...
start "" "%~dp0target\eyecandy.exe" --visible
echo [Note: Application might take 10-15 seconds to initialize]
timeout /t 5 > nul
call :check-running
exit /b

:check-running
tasklist /FI "IMAGENAME eq eyecandy.exe" | find "eyecandy.exe" > nul
if %errorlevel% equ 0 (
    echo Eye Candy is running successfully!
    echo Process ID: 
    for /f "tokens=2" %%a in ('tasklist /FI "IMAGENAME eq eyecandy.exe" ^| find "eyecandy.exe"') do echo %%a
) else (
    echo Failed to start Eye Candy!
)
pause