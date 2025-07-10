# Meeting Room Booking API

## Overview
This project is a Spring Boot backend for managing users, meeting rooms, and reservations.

## Features
- User management with roles (admin, user)
- CRUD operations for meeting rooms (admin-only for create/update/delete)
- Reservation system with conflict detection
- Secure login with BCrypt (in-memory, JWT planned)
- Email confirmation (planned)

## Getting Started

### Prerequisites
- Java 17+
- PostgreSQL database
- Maven or Gradle

### Setup
1. Clone this repo: https://github.com/Jodjod-tcodi/Application-de-gestion-de-r-servation-de-salles-de-r-union-rattach-au-Microsoft-TEAMS
2. Configure `application.properties` with your DB credentials.
3. Build and run:
   mvn clean install
   mvn spring-boot:run

yaml


### API Endpoints

| Method | Endpoint                      | Description                      | Auth          |
|--------|-------------------------------|---------------------------------|---------------|
| POST   | `/api/v1/login`               | Login user                      | Public        |
| GET    | `/api/v1/rooms`               | List all rooms                 | Authenticated |
| POST   | `/api/v1/rooms`               | Create a room (admin only)      | Admin         |
| POST   | `/api/v1/reservations`        | Book a room                    | Authenticated |
| DELETE | `/api/v1/reservations/{id}`   | Cancel reservation             | Authenticated |

### Testing
Use the `test-requests.http` file in the root to test APIs with HTTP client extensions (e.g., VSCode REST Client).

---

## Contribution
Feel free to open issues or submit PRs.

---

## License
License.
