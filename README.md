# Hotel Manager Project

## Overview
A Java-based hotel management system designed for managing rooms, bookings, and guests. The project utilizes file-based storage for room data (`hotel_data.xlsx`) and adheres to best practices, including unit testing and custom data structures.

## Features
- **Room Management**: Add, view, and list room details.
- **Guest Management**: Check-in and check-out guests with billing functionality.
- **Data Management**: Read and save room data to an Excel file.
- **Commands**: Supports operations like `checkin`, `checkout`, `view`, `prices`, `list`, `save`, and `exit`.

## How to Run
1. Place the `hotel_data.xlsx` file in the project directory.
2. Compile and run the program:
   ```java -cp target/hotelmanager.jar pz.hotelmanager.Main```
3. Use the provided commands in the application interface.

## Testing
To execute unit tests, use Maven:
   ```mvn test```

## Tools and Technologies
- Java: Primary programming language.
- Apache POI: For Excel file operations.
- JUnit 5: For writing and running unit tests.
- SonarQube: For code quality analysis and maintaining high coding standards.
