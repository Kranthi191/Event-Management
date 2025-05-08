# Event Management System

This is a Spring Boot application for managing events. Users can register, log in, create/edit events, RSVP to events, and view their RSVP history.

---

## Features

1. User Management (Register, Login, Profile Management)
2. Event Management (Create, Edit,  Filter, RSVP)
3. Responsive web pages built with Thymeleaf.

## Setting Up the Application

### 1. Clone the Repository
```bash
git clone https://github.com/[your-repo]/event-management-system.git
cd event-management-system
```

### 2. Configure the Database
- Create a MySQL database (e.g., `event_management`).
- Update the database credentials in the `application.properties` file:
```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/event_management
spring.datasource.username=your_database_username
spring.datasource.password=your_database_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Install Dependencies
Here are the dependencies you need to add to the pom.xml file:

1.Spring Boot Starter Web
2.Spring Boot Starter Thymeleaf
3.Spring Boot Starter JPA
4.MySQL Connector
5.Spring Boot DevTools
6.Lombok


Run the following command to download and install all dependencies:
```bash
mvn clean install
```

### 4. Run the Application
Run the application using Maven:
```bash
mvn spring-boot:run
```

The application will start on **http://localhost:8080**.

## Usage

### 1. Register a User
- Open **http://localhost:8080/users/register** in your browser.
- Fill out the registration form and submit.

### 2. Log in
- Navigate to **http://localhost:8080/users/login**.
- Enter your email and password to log in.

### 3. Manage Events
- Create an Event: **http://localhost:8080/events/create**
- View Events: **http://localhost:8080/events**
- RSVP to an Event: Open the event details and submit your RSVP.

---

## Customization

### 1. Modify Frontend
- Frontend templates are located in the `src/main/resources/templates` directory. You can customize the HTML and CSS.

### 2. Add New Features
- Add new REST endpoints in the `controller` package.
- Implement business logic in the `service` package.

---

## Troubleshooting

### Common Issues
1. **Database Connection Error**:
   - Ensure that MySQL is running and the credentials in `application.properties` are correct.

2. **Port Already in Use**:
   - Change the default port in `application.properties`:
   ```properties
   server.port=8081
   ```

3. **Missing Dependencies**:
   - Run `mvn clean install` to resolve dependency issues.

---
