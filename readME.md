# Statistics service

This application calculates the total valid request and the total invalid request according to the requested customer ID and from a JSON data file that contains requests.

There are two endpoints  to display them:
http://localhost:8080/statistics?customerID=1&&tagID=2 to get the statistics (invalid and valid requests separately) about the customerID #1 for the day#2
http://localhost:8080/total?customerID=1&&tagID=2 to get the total request(sum of invalid + valid requests) about the customerID #1 for the day#2

The application contains a H2 database that can be seen on localhost:8080/h2-console. The username and password are initialized in the application properties file(please see below).

## Installation
Please go to statistics/src/main/resources/application.properties to change the value of the json file path (jsondata.path: your "datatoload.json" path)
Please pay attention to the structure if you change the file:{"requests":[{},{}]}
- load or clone the project (git clone https://github.com/nadieva/statistics.git )
- cd into statistics
- mvn clean package
- java -jar target\statistics-0.0.1-SNAPSHOT.jar
- type the endpoints in a web browser to see the results
 
## Info
I used to do so:
Apache Maven 3.6.2
java version "1.8.0_221"
Java(TM) SE Runtime Environment (build 1.8.0_221-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.221-b11, mixed mode)

## Resources
- http://zetcode.com/springboot/datajpaquery/
- https://stackoverflow.com/questions/1688099/converting-json-data-to-java-object
- [JPQL](https://thorben-janssen.com/spring-data-jpa-query-annotation/)
- [Jackson API](https://www.concretepage.com/jackson-api/jackson-jsonformat-example)
- [GSON] (https://www.tutorialspoint.com/gson/gson_object_serialization.htm)
- [H2 database](http://www.h2database.com/html/functions.html)
- [SpringBoot with H2](https://www.baeldung.com/spring-boot-h2-database)
- [Spring Data JPA](https://www.baeldung.com/spring-data-jpa-query)
