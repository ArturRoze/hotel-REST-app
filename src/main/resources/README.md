1. **About**\
Demo REST application for booking room in hotel by user.\
Application has API, it allows you to work with the application using JSON\
2. **API:**\
**2.1**\
**`GET`** **`/rooms/all/{date}`** returns list of available rooms for specified dates.\
**2.2**\
**`GET`** **`/rooms/category/{nameCategory}`** returns rooms filtered by category.
(single, double, apartment)\
**2.3**\
**`POST`** **`/user/create`** with a user object as JSON creates a new user.\
{
"name":"name",
"surname":"surname"
}\
**2.4**\
**`POST`** **`/rooms/booking`** book the room for specified days.\
{
"userId": 1,
"category":"name",
"startDate":"2018.03.02",
"endDate":"2018.03.03"
}\
**2.5**\
**`GET`** **`/user/booking/{userId}`** view user booking with userId.\
**2.6**\
**`GET`** **`/user/booking/total_price/{userId}`** get the total price of the booking user with userId\
**2.7**\
**`POST`** **`/hotel/rooms/allBooking`** get all bookings for the hotel.\
{
"startDate":"2018.03.02",
"endDate":"2018.03.02"
}

**3. Requirements**\
Java 8.

**4. How to run**\
