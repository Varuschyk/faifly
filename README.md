# Messenger Project

## Table of Contents

---
- [MySQL](#MySQL)


## MySQL
You need the active MySQL server on localhost and port `3306`.
To manage configuration follow to the `application.yml` in `faifly-app` module.

## API endpoints
To check the completed task here the api URLs where you can check correct execution your requirements:
````
http://localhost:8080/v1/visit?search=Angelo&doctorIds=1
http://localhost:8080/v1/visit
````
````
{
    "patientId": "1",
    "doctorId": "1",
    "startDateTime": 4126541731,
    "endDateTime": 8754986367
}
````
