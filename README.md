Project Description
Assume that we are building a street parking spot reservation service. Each parking spot is identified by
its location (lat, lng). Users should be able to view street parking spots, reserve and pay for the parking
spots or cancel their reservations. Build REST API's for the following and share the github repository
with us. You can populate your database with any dummy data you want.
Requirements
• See available parking spots on a map
• Search for an address and find nearby parking spot. (input: lat, lng, radius in meters. Output - list of
parking spots within the radius).
• Reserve a parking spot
• View existing reservations
• Cancel an existing reservation
• Show the user the cost of the reservation
• Please write Unit tests

To Run this project :
DB requirements -
Install Mysql - Create database with name test.

run with mvn clean spring-boot:run

1.GET  http://localhost:8080/parkings/available
- 200 OK
[
    {
        "latitude": 18.5963,
        "longitude": 73.7719,
        "address": "Wakad",
        "id": 4
    },
    {
        "latitude": 18.5172,
        "longitude": 73.8534,
        "address": "Shanipar",
        "id": 5
    }
]
2. GET http://localhost:8080/parkings/reserved
3. POST http://localhost:8080/parkings/search
Request {
	    "latitude": 18.562120,
        "longitude": 73.802544,
		"radius" : 50
}

Response - 200 OK

[
    {
        "latitude": 18.5963,
        "longitude": 73.7719,
        "address": "Wakad",
        "id": 4
    },
    {
        "latitude": 18.5172,
        "longitude": 73.8534,
        "address": "Shanipar",
        "id": 5
    }
]
4. View User's reservation - GET http://localhost:8080/users/2/reservations
Response - 200 OK 
[
    {
        "latitude": 18.4895,
        "longitude": 73.8519,
        "address": "Sahakar Nagar",
        "id": 6
    }
]
5. User reservation - POST http://localhost:8080/users/2/reservations/6
2 - user id
4 - parking spot id
Request { "cost" : 100.0 } 
Response 200 OK {
    "latitude": 18.4895,
    "longitude": 73.8519,
    "address": "Sahakar Nagar",
    "id": 6
}
