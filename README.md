Task Management Application
This is a simple Task Management application built with Spring Boot. The application provides endpoints for user
authentication, task management, and more.

In this project I have used java 17. 

## Database Configuration
   The application uses MySQL as its database. Configure the database connection in the application.properties file: 
    change the database url and the database name


## Endpoints

1. User Authentication
   Login

URL: /login
Method: GET
Description: Navigate to the login page.
Sign Up

URL: /signup
Method: GET
Description: Navigate to the sign-up page.
Do Sign Up

URL: /do-signUp
Method: POST
Description: Register a new user.
Parameters:
email: User's email
password: User's password
name: User's name

2. Task Management
   Home Page
   URL: /home
   Method: GET
   Description: Navigate to the home page.
   Note
   User roles are set to "ADMIN" by default for simplicity. You can customize this behavior based on your application's
   requirements.
