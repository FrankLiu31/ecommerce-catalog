# E-Commerce Catalog Application

This is a Spring Boot application that provides a basic e-commerce product catalog with CRUD (Create, Read, Update, Delete) operations and user authentication using JWT (JSON Web Tokens). It features a RESTful API for product management and a Thymeleaf-based UI for browsing and managing products.

---

## ✨ Features

### ✅ Product Management

- Add new products
- View a single product by ID
- Update existing products
- Delete products
- Search and filter products by name, description, and price range

### 🔐 User Authentication

- Provides a login page for user authentication
- Uses JWT for secure API authentication

### 🗄️ Database Integration

- Uses PostgreSQL as the backend database
- Manages data persistence with JPA/Hibernate

### ⚠️ Error Handling

- Includes a global exception handler for `NotFoundException` and other generic exceptions

### 🌐 Responsive UI

- Uses Thymeleaf for server-side rendering
- Styled with Tailwind CSS for a clean, modern, and responsive design

---

## 🛠️ Technologies Used

### Backend

- **Spring Boot**: Application framework
- **Spring Data JPA**: For database interaction
- **PostgreSQL**: Relational database
- **Spring Security**: For authentication and authorization
- **JWT**: For secure API authentication

### Frontend

- **Thymeleaf**: Server-side templating engine
- **Tailwind CSS**: Utility-first CSS framework for styling

### Build Tool

- **Maven**: Dependency management and build tool

---

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- Docker and Docker Compose

### 🔧 Setup Steps

1. **Clone the repository:**

   ```bash
   git clone https://github.com/FrankLiu31/ecommerce-catalog.git
   cd ecommerce-catalog
    ```
2. **Create a `docker-compose.yml` file in the project root:**

   ```yaml 
   services:
     db:
       image: postgres:14-alpine
       container_name: pg-catalog
       environment:
         POSTGRES_PASSWORD: changemeinprod!
         POSTGRES_USER: postgres
         POSTGRES_DB: postgres
       ports:
         - "5432:5432"
       volumes:
         - ./data/db:/var/lib/postgresql/data
       restart: always
   ```

3. **Start the PostgreSQL database:**

   ```bash
    docker-compose up -d
   ```
- `db`: The name of the service defined in the `docker-compose.yml` file
- `pg-catalog`: The name of the Docker container
- `changemeinprod!`: Password for the `postgres` user
- `5432:5432`: Maps the container's port 5432 to your local machine's port 5432

> ⚠️ The `application.properties` file is pre-configured to connect to `localhost:5432` with username `postgres` and the specified password.

4**Build the application:**

   ```bash
     mvn clean install
   ```

5**Run the application:**

   ```bash
     mvn spring-boot:run
   ```
## 🧪 How to Use

### 1. Access the application

Open your browser and navigate to:  
[http://localhost:8080/login](http://localhost:8080/login)

### 2. Log in

A default admin user is created on application startup if it doesn't already exist:

- **Username**: `admin`
- **Password**: `Frankliu@12345!`

After a successful login, you will be redirected to the product catalog page: `/products/main`.

### 3. Product Catalog Page

- View a list of all products
- Use the search and price filters to find specific products
- Click **"Add New Product"** to create a new product
- Click **"Edit"** to modify a product's details
- Click **"Delete"** to remove a product

### 4. Product Form Page

- Fill in the product details (name, description, price)
- The form includes validation for invalid input
- Click **"Save Product"** to submit the form

### 5. Log out

Click the **"Logout"** button on the product catalog page to clear the JWT token and return to the login page.


## 🔐 Authentication Endpoint

**POST** `/api/authenticate`:  
Authenticate a user and receive a JWT token.

**Request Body:**

```json
{
  "username": "admin",
  "password": "Frankliu@12345!"
}
```

## 📝 Development Notes

### JWT Secret Key
- Defined in `application.properties`.
- In production, use a secure key managed via environment variables or a secrets manager.

### Password Encoding
- User passwords are encrypted using a `PasswordEncoder`.

### Database Schema
- The application uses `spring.jpa.hibernate.ddl-auto=update`, allowing Hibernate to manage the database schema automatically.

### UI Libraries
- Tailwind CSS and Font Awesome are included via CDN for simplicity.
- For larger projects, consider integrating them into your build process.

