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
**`POST`** **`/rooms/book`** book the room for specified days. Available values for test: name = (clean, wash, breakfast);\
{
	"userId": 1,
	"roomId": 4,
	"startDate": "2018-03-28 12:00:10",
	"endDate": "2018-03-30 13:00:00",
	"additionalOptions": [{
		"name": "breakfast"
	}]
}\
**response:**\
{
    "userId": 1,
    "roomId": 4,
    "bookingId": 2,
    "totalPrice": 300.0,
    "category": "SINGLE",
    "startDate": "yyyy-MM-dd hh:mm:ss",
    "endDate": "yyyy-MM-dd hh:mm:ss",
    "additionalOptions": [{
    		"name": "breakfast"
    }]
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
Java 1.8 https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html\
Apache Maven 3.5.3 https://maven.apache.org/install.html\
For manage the application need 
Rest client(for example "Postman"). https://www.getpostman.com/

**4. How to run**\
Download project from Git.
Open commandLine go to directory with pom.xml (root project for example C:\Users\Artur\IdeaProjects\hotel-app)

**mvn spring-boot:run**