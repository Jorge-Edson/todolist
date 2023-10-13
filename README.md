# To-Do List API

This project is a personal study developed with Java 17, Spring Boot 3.0.11, utilizing Maven, Spring Data JPA, Bcrypt, and Lombok. The development process followed a free course provided by Rocketseat. The project implements a simple API for managing tasks with the following functionalities:

- **Register User**: Allows the registration of users.
- **Register Task**: Permits users to register tasks.
- **List Tasks**: Provides a list of tasks.
- **Update Task**: Allows users to update their tasks.

## Key Features

- Only registered users can create and list tasks.
- Users can only create tasks for themselves and can only list and update their own tasks.
- Passwords are securely encrypted using Bcrypt.
- User IDs are in UUID format for security reasons.
- Task titles are limited to a maximum of 50 characters.
- The project has been tested using an in-memory database known as H2 Database Engine.
- The project does not have a fully functional login system, although user registration is allowed.

## Endpoints

- `POST /users/`: Register users.
- `POST /tasks/`: Register tasks.
- `GET /tasks/`: List tasks.
- `PUT /tasks/{task-id}`: Update task information.

## Technologies Used

- Java 17
- Spring Boot 3.0.11
- Maven
- Spring Data JPA
- Bcrypt
- Lombok
- Docker

## Getting Started

1. Clone the repository.
2. Open the project in your preferred IDE.
3. Build and run the project.

## Usage

- Use an API testing tool like Postman to interact with the endpoints.
- Register users via `POST /users/`.
- Register tasks via `POST /tasks/`.
- List tasks via `GET /tasks/`.
- Update tasks via `PUT /tasks/{task-id}`.

## Notes

- Ensure you have Java 17 and Maven installed on your system.
- This project uses H2 Database Engine for testing purposes. For production, consider using a more robust database.

## Acknowledgments

- Rocketseat for providing the free course material.
