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

## 2. How to Run the Project

### Requirements
- Java 17 or higher.
- Maven.
- IDE (e.g., IntelliJ IDEA) or command line.
- Postman: Tool for API testing (via generated-requests.http).

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
   - Use Postman to test the API(via generated-requests.http).
   - Import the file `postman/generated-requests.http` into Postman.
   - Execute requests in order:
     - `POST /auth/register` — Register a user.
     - `POST /auth/login` — Login to get a JWT token.
     - Use the token for secured endpoints (`/todos`, `/profile`).
     - Use the env dev.

## 3. Testing
All API endpoints are tested using Postman. The `postman/generated-requests.http` file contains a collection of requests for:
- User registration and login.
- Todo creation, retrieval, update, and deletion.
- Profile management.

### Example Workflow
1. Register a user: `POST /auth/register`
2. Login: `POST /auth/login` (save the token).
3. Use the token to access endpoints like `GET /todos` or `GET /profile`.

## 4. Notes
- The project is not intended for direct browser access due to its REST API nature.
