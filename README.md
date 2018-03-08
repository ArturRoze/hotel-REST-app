1. **About**\
Demo REST application for management rooms in hotel by user.\
Application has API, it allows you to work with the application using JSON
2. **API:**\
**2.1**\
**`POST`** **`/rooms/all`** returns list of available rooms for specified dates.\
{
"startDate": "yyyy-MM-dd hh:mm:ss",
"endDate": "yyyy-MM-dd hh:mm:ss"
}\
**2.2**\
**`GET`** **`/rooms/category?nameCategory=SINGLE`** returns rooms filtered by category.
(available values SINGLE, DOUBLE, APARTMENT)\
**2.3**\
**`POST`** **`/user/create`** with a user object as JSON creates a new user.\
{
"name": "name",
"login: "login",
"surname": "surname"
}\
**2.4**\
**`POST`** **`/rooms/booking`** book the room for specified days.\
{
"userId": 1,
"roomId": 1,
"category": "name",
"startDate": "yyyy-MM-dd hh:mm:ss",
"endDate": "yyyy-MM-dd hh:mm:ss"
}\
**2.5**\
**`GET`** **`/user/all`** return all users from database.\
**2.6**\
**`GET`** **`/user/{userId}/booking`** view bookings of user with userId (for example test userId = 1, 2,...5)\
**2.7**\
**`GET`** **`/user/{userId}/bookings/total_price`** get the total price of the user's bookings with userId (for example test userId = 4)\
**2.8**\
**`GET`** **`/user/{userId}/booking/{bookingId}/total_price`** get the total price of the user's booking with userId and bookingId(for example test userId = 1, 2,...5; bookingId = 1,2...3)\
**2.9**\
**`GET`** **`/hotel/{idHotel}/bookings/all`** get all bookings for the hotel.(for example test idHotel = 1, 2)

**3. Requirements**\
Java 1.8\
Apache Maven 3.3.9 or higher\
Rest client(for example "Postman").

**4. How to run**

mvn spring-boot:run