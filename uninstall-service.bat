@echo off
echo Stopping and removing Eye Candy service...
sc stop EyeCandySvc > nul 2>&1
sc delete EyeCandySvc > nul 2>&1

echo Service has been removed
timeout /t 2 > nul