# Signature App

This is the backend for the Signature App. The frontend can be found [here](https://github.com/HyperDarkmoon/signaturefrontend).
The backend is built with Spring Boot and uses a MySQL database to store users and their signatures.

## Features

- Create a new user
- Retrieve a user by username
- Retrieve a user by email
- Store user's signature
- Store user's ID card image
- Retrieve user's information for contract generation by the frontend

## Installation

1. Clone the repository: `git clone https://github.com/hyperdarkmoon/signatureapp.git`
2. Navigate to the project directory: `cd signatureapp`
3. Launch your MySQL server and create a new database called `signatureapp`

## Usage

1. Run the application: `./mvnw spring-boot:run` or with your IDE
2. The server is now running on `http://localhost:8085` by default, you can change the port in `application.properties`

