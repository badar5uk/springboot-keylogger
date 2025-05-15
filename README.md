# Spring Boot Keylogger Application

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.5-brightgreen)
![License](https://img.shields.io/badge/License-MIT-orange)

This is a simple Windows keylogger implementation using Spring Boot and JNA (Java Native Access) that can log and capture keyboard inputs.

You can simply run the jar file using the java -jar command and the output will be stored as a text file ( You will need to change the path of the file in the GlobalKeyListenerService)

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

For you to compile and run the jar, you first need to adjust the GlobalKeyListenerService file, you need to change FILE_PATH and include the path of where you want to generate the file ( including the file_name.txt)
```
File file = new File(System.getenv("FILE_PATH"));
```

You can generate a jar file using the following

```
mvn clean install
```

or using the maven wrapper like so
```
./mvnw clean install
```


Then you can run using the following command:
```
java -jar target/keylogger-0.0.1-SNAPSHOT.jar
```
