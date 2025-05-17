# COMP 2013 OOP Project - Classroom Booking System
## Run
Excute: `java -jar ClassroomBookingSystem.jar`

## Structure
### Packages
* db: Store database action methods and interfaces
* exceptions: As its name, exceptions
* gui: GUI component with 5 pages, these are used to make them
* main: Main.java have static method, so Main.java is in package `main` to be call

### Lib
* lib - sqlite-jdbc-3.42.0.0.jar: I think the use of MySQL on other environments are difficult, so I selected SQLite to make a local file to store informations

### Tests
* tests - listDatabase.java: This is not included in the program, it is used for debug - to list every data in `data.db`

## Build
### Temporary
```bash
./build.sh
```
Start automatically, and after close program it will clean the environment

### Pack to JAR
```bash
./pack.sh
```
Final jar package is `ClassroomBookingSystem.jar`. I'm already built it for you. Just run `java -jar ClassroomBookingSystem.jar`