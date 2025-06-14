# Todo Application

A REST API built with Spring Boot for managing todos and user profiles with JWT-based authentication.

## 1. Technologies Used
- **Spring Boot**: Framework for creating REST API with minimal configuration.
- **Spring Security**: For authentication and authorization using JWT tokens.
- **JWT (JSON Web Token)**: Mechanism for stateless authentication.
- **Hibernate/JPA**: ORM for database operations (using H2 by default).
- **Maven**: Build and dependency management system.
- **Postman**: Tool for API testing (via `generated-requests.http`).
- **Java**: Primary programming language.

## 2. Features and Requirements
This project meets the following requirements for a REST API with authentication and CRUD functionality:

### 2.1 User Authentication and Registration Module
- User registration and login functionality (`POST /auth/register`, `POST /auth/login`).
- Authentication using JWT (JSON Web Tokens).
- Protection of private routes (e.g., access to `/todos` and `/profile` endpoints only after login).
- Optional: Support for access/refresh token flow (can be implemented if needed).

### 2.2 Task Management System
- CRUD operations (Create, Read, Update, Delete) for tasks:
  - Create: `POST /todos`
  - Read: `GET /todos`, `GET /todos/{id}`
  - Update: `PUT /todos/{id}`
  - Delete: `DELETE /todos/{id}`
- Each task includes the following fields:
  - `id`
  - `title`
  - `description`
  - `status`
  - `createdAt`
  - `updatedAt`
- Filtering tasks by status (e.g., `todo`, `in_progress`, `done`) via query parameter: `GET /todos?status=todo`.

## 3. How to Run the Project

### Requirements
- Java 17 or higher.
- Maven.
- IDE (e.g., IntelliJ IDEA) or command line.
- Postman: Tool for API testing (via `generated-requests.http`).

### Steps
1. **Open the Project**:
   - In IntelliJ IDEA: `File -> Open` and select the project folder.
2. **Set Up Dependencies**:
   - Run `mvn clean install` in the terminal.
3. **Configure the Project**:
   - Copy `src/main/resources/application-example.properties` to `src/main/resources/application.properties`.
   - Edit `src/main/resources/application.properties`:
     - Set port: `server.port=8080`
     - Configure database (e.g., H2): `spring.datasource.url=jdbc:h2:mem:testdb`
     - Set JWT secret: `jwt.secret=your-very-long-secret-key` (or use environment variable `JWT_SECRET`)
4. **Run the Application**:
   - In IntelliJ: Click "Run" on `TodoApplication`.
   - Or in terminal: `mvn spring-boot:run`
5. **Test the API**:
   - **Note**: This is a REST API without a user interface, so it’s not designed to work directly in a browser.
   - Use Postman to test the API (via `generated-requests.http`).
   - Import the file `postman/generated-requests.http` into Postman.
   - Execute requests in order:
     - `POST /auth/register` — Register a user.
     - `POST /auth/login` — Login to get a JWT token.
     - Use the token for secured endpoints (`/todos`, `/profile`).
     - Use the `dev` environment to  `generated-requests.http`.

## 4. Testing
All API endpoints are tested using Postman. The `postman/generated-requests.http` file contains a collection of requests for:
- User registration and login.
- Todo creation, retrieval, update, and deletion.
- Profile management.

### Example Workflow
1. Register a user: `POST /auth/register`
2. Login: `POST /auth/login` (save the token).
3. Use the token to access endpoints like `GET /todos` or `GET /profile`.

## 5. Notes
- The project is not intended for direct browser access due to its REST API nature.

## 6. About the Development
This project marks my first experience with the Java + Spring Boot stack. Previously, I worked primarily with Python and Django, where I built several applications. I decided to challenge myself by exploring a new technology stack to expand my skills and gain hands-on experience with Spring Boot, Spring Security, and JWT-based authentication. This todo application was created specifically as a learning exercise and portfolio piece for job interviews, reflecting my ability to adapt and learn new frameworks quickly.
