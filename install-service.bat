@echo off
REM Requires admin privileges
echo Installing Eye Candy as a Windows service...
sc create EyeCandySvc binPath= "%~dp0target\eyecandy.exe" start= auto DisplayName= "Eye Candy Service"

echo Starting service...
sc start EyeCandySvc
sc query EyeCandySvc

echo Installation complete! Service should now be running.
pause