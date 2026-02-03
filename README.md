# E-Commerce Microservice

A Spring Boot-based microservices application for e-commerce operations consisting of three core services: User Service, Product Service, and Order Service.

## Services Overview

- **User Service**: Manages user accounts, authentication, and profiles
- **Product Service**: Handles product catalog and inventory management
- **Order Service**: Manages order processing and fulfillment

## Prerequisites

- **Java 11+**: [Download JDK](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.6+**: [Download Maven](https://maven.apache.org/download.cgi)
- **Docker & Docker Compose**: [Download Docker Desktop](https://www.docker.com/products/docker-desktop)
- **MySQL 8.0+**: (Optional - services use databases)


## Project Structure

```
E-Commerce-Microservice/
├── User Service/          # User management and authentication
├── Product Service/       # Product catalog and inventory
├── Order Service/         # Order processing
└── docker-compose.yml     # Container orchestration
```

## Setup Instructions

### 1. Clone/Extract the Repository

```bash
cd e:\PBC Preparation\Backend\SpringBoot Project\E-Commerce-Microservice
```

### 2. Build All Services

From the root directory:

```bash
mvn clean install
```

Or build individual services:

```bash
cd "User Service" && mvn clean install
cd "Product Service" && mvn clean install
cd "Order Service" && mvn clean install
```

### 3. Configure Environment

Each service has an `application.yml` file in `src/main/resources/`. Review and update database connections, server ports, and other configurations as needed.

## Running the Services

### Option A: Using Docker Compose (Recommended)

```bash
docker-compose up -d
```

This starts all services and dependencies in containers.

### Option B: Run Services Locally

Start each service in a separate terminal:

```bash
# Terminal 1 - User Service
cd "User Service"
mvn spring-boot:run

# Terminal 2 - Product Service
cd "Product Service"
mvn spring-boot:run

# Terminal 3 - Order Service
cd "Order Service"
mvn spring-boot:run
```

