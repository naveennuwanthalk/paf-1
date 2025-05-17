# Digital NUXARA Backend

Spring Boot backend server with Supabase integration for Digital NUXARA.

## Prerequisites

- Java 17 or higher
- Maven
- Supabase account and project

## Setup

1. Clone the repository
2. Copy the `.env` file to the root directory
3. Make sure your Supabase credentials are correctly set in the `.env` file

## Building the Project

```bash
mvn clean install
```

## Running the Application

```bash
mvn spring-boot:run
```

The server will start on `http://localhost:8080`

## API Endpoints

- `GET /api/test` - Test endpoint to verify server and Supabase integration

## Environment Variables

The following environment variables are required:

- `SUPABASE_URL` - Your Supabase project URL
- `SUPABASE_ANON_KEY` - Your Supabase anonymous key
- `SUPABASE_SERVICE_ROLE_KEY` - Your Supabase service role key
- `SUPABASE_STORAGE_URL` - Your Supabase storage URL

## Project Structure

```
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── nuxara/
│   │   │           └── backend/
│   │   │               ├── config/
│   │   │               ├── controller/
│   │   │               └── BackendApplication.java
│   │   └── resources/
│   │       └── application.yml
├── .env
├── pom.xml
└── README.md
``` 