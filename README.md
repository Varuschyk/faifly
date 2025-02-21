# Messenger Project

### Author is *Alexander Varushchyk*

## Table of Contents

---
- [MySQL](#MySQL)
- [Database initialization](#)
- [API Endpoints](#api-endpoints)


## MySQL
You need the active MySQL server on localhost and port `3306`.
To manage configuration follow to the `application.yml` in `faifly-app` module.

## Database initialization
MySQL database has initial records of `Patients` and `Doctors`. If you want to edit
data, follow the `data.sql` file in `faifly-api-core` module in `resource` folder.

## API Endpoints
To check the completed task here the api URLs where you can check correct execution your requirements:


[GET endpoint](http://localhost:8080/v1/visit?doctorIds=1,2&search=Angelo)

[POST endpoint](http://localhost:8080/v1/visit)

Here request body:
````
{
    "patientId": "1",
    "doctorId": "1",
    "startDateTime": 4126541731,
    "endDateTime": 8754986367
}
````
`dateTime` fields is `Instant` java object in Unix TimeStamp (millis) representation.
