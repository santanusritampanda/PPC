# Payroll Processing System

## Overview

PPC is a Payroll solution provider company who manages the payroll of the various companies from
small scale to large scale company.
PPC accepts the employees data from the client in either plain text format (.txt) or csv (.csv) format to
manage the employee life cycle starting from date of onboarding to date of exit.

## Components

### Controller Layer

- **PPCController**: Handles HTTP POST requests to upload multiple files and redirect the request to PCCService for report generation and all.

### Service Layer

- **PPCService**: Service Layer for writing business logic
- **EventFactory**: Creates event objects based on the event type based on factory pattern.
- **DateParserUtil**: utility class for date realted convertions.
- **CommonUtil**: Common Util class for convertion into Class object and vice versa.

### Output
1. Total number of employees in an organization.
2. Month wise following details
a. Total number of employees joined the organization with employee details like emp id,designation, name, surname.
b. Total number of employees exit organization with employee details like name, surname.
3. Monthly salary report in following format
a. Month, Total Salary, Total employees
4. Employee wise financial report in the following format
a. Employee Id, Name, Surname, Total amount paid
5. Monthly amount released in following format
a. Month, Total Amount (Salary + Bonus + REIMBURSEMENT), Total employees
6. Yearly financial report in the following format
a. Event, Emp Id, Event Date, Event value
 

## How to Run the Application

1. Java 17 reqquired.
2. Clone this repo to local system..
3. Build and Run the Spring Boot application using your IDE :
4. The application will start on `http://localhost:8081`.
5. Use the following endpoint to upload files:
   - `POST /ppcapi/payroll/processFiles`
   - The uploaded files should be in CSV or TXT format.
   - You can use the file inside resource folder i.e testfile.txt.
6. Output reports will be displayed in the console.
