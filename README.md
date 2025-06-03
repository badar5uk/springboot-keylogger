# Spring Boot Keylogger Application

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.5-brightgreen)
![License](https://img.shields.io/badge/License-MIT-orange)

This is a simple Windows keylogger implementation using Spring Boot and JNA (Java Native Access) that can log and capture keyboard inputs, The application also utilizes Launch4j to wrap the jar as an .exe file that can e executed, this was named as eyecandy.exe this will not work during UAC prompts unless you elevate the privelege first.

You can simply run the jar file using the java -jar command and the output will be stored as a text file in the same directory where you ran it, you can also run the .exe file that is generated

## Important Notice!!

### PLEASE USE THIS ETHICALLY AND RESPONSIBLY

- Obtain explicit consent before monitoring any system

- Check local regulations

- Never deploy on systems you don't own or have permission to monitor

This project was done just as a practice and for me to learn new conecpts

## Features

- **Shift/Caps Lock awareness** for proper character case detection
- **Spring Boot integration** for easy deployment
- **Admin privilege detection** with proper error handling
- **Real-time output** of captured keystrokes

## Implementation (how to make it work)

You can generate the jar and .exe file using the following

```
mvn clean package
```
or using the maven wrapper like so
```
mvnw clean package
```
The file would then be generated in the /target folder, you can run the jar file using the following:
```
java -jar target/eyecandy.jar
```
Or directly run the .exe file
