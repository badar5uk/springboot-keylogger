# Spring Boot Keylogger Application

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.5-brightgreen)
![License](https://img.shields.io/badge/License-MIT-orange)

This is a simple Windows keylogger implementation using Spring Boot and JNA (Java Native Access) that can log and capture keyboard inputs.

You can simply run the jar file using the java -jar command and the output will be stored as a text file ( You will need to change the path of the file in the GlobalKeyListenerService)

## Important Notice!!

### PLEASE USE THIS ETHICALLY AND RESPONSIBLY

This project was done just as a practice and for me to learn new conecpts

## Features

- **Shift/Caps Lock awareness** for proper character case detection
- **Spring Boot integration** for easy deployment
- **Admin privilege detection** with proper error handling
- **Real-time output** of captured keystrokes

## Technical Implementation

You can generate a jar file using the following

```
mvn clean install
```

or using the maven wrapper like so
```
./mvnw clean install
```
