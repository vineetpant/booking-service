# Rest Service for Booking

## About

This repository contains coding task for booking service.

## Code Structure

This service is created using spring boot in visual studio code,

- src/main - folder contains source code
- src/test - folder contains unit tests for controller endpoints

### Source Code

Source code contains `BookingsController` which creates endpoint mappings and paths, `BookingService` contains bussines logic to perform operations related to endpoints.

For data structures code uses `HashMap`'s to store `Bookings` and `Transaction` Detials. Due to some time constraints and other occupancies the onchain transaction is currently mocked and it just return booking hash and a random transactionId which are stored in `HashMap` for resolving get queries in run time. 

### Swagger UI

Swagger UI can be accessed `http://localhost:8080/swagger-ui/index.html`


### Sample booking request
POST /bookingservice/bookings
Sample Body:

```
{"description": "Cool descripƟon!", "price": 50.00, "currency": "USD",
"subscription_start_date": 683124845000, "email": "valid@email.ok", "department": "ADMIN"}
```