# moviedb-exercise
####Tech exercise: RESTful Web application
####Senior Software Developer, Companies House, May 2019
###Simon Boyd

#Welcome

Welcome to this brief description of my submission for the technical exercise.

#Design

The design is based on a single microservice component containing
- a Web Service application showing views of the data
- a RESTful API Service providing the same views in JSON format

These services could be broken out into separate microservices for a more flexible architecture,
 but there was insufficient time to complete that satisfactorily.   

I used the following technologies in the solution:
- Java OpenJDK 12 (source level 1.8)
- Spring Boot
- Spring Data (Hibernate)
- MongoDB (version 3.4+)
- Spring Web
- Thymeleaf 

#Requirements

The service requires an existing MongoDB server version 3.4 or above to be available where the data
 will be stored. Once started and connected, the service will create a database **`test`** containing
 a collection **`movies`** with the sample data.

#Building

#####NOTE: All commands shown are run from the project root directory.

####Compile and Test
>`mvn clean test`

####Build JAR moviedb-0.0.1-SNAPSHOT.jar
>`mvn verify`

####Unit Test Coverage Report
After running the Maven compile and test step above, the test coverage report is located in

> `target/site/jacoco/index.html`

and is viewable in a web browser.

![coverage report](/screenshots/screenshot0006.png) 


#Running 

####With Maven

>`mvn spring-boot:run`

####Standalone

- Default Web port (8080)
- Default MongoDB host (localhost)
- Default MongoDB port (27017)

>`java -jar target/moviedb-0.0.1-SNAPSHOT.jar`

- Custom Web port (4000)
- Custom MongoDB host (myhost)
- Custom MongoDB port (27000)

><code>java &#8209;Dserver.port=4000 &#8209;Dspring.data.mongodb.host=myhost 
 &#8209;Dspring.data.mongodb.port=27000 &#8209;jar
 target/moviedb&#8209;0.0.1&#8209;SNAPSHOT.jar</code>
 
 #Web Application
 
 With the service running, wiew the Web application by visiting  
 
 > http://localhost:8080  

(the default port, see above how to change the service port).

####Index Page

![index page](/screenshots/screenshot0001.png)

####Movies List Page

![movies list page](/screenshots/screenshot0002.png)

####Comments Page

![comments page](/screenshots/screenshot0003.png) 

####Top User Page

![top user](/screenshots/screenshot0004.png) 

####Top Movie Page

![top movie](/screenshots/screenshot0005.png) 

 #RESTful API
 
 Service endpoints are reachable by REST clients, for example POSTMAN, and web browsers. Data is returned in JSON format.
 
 URLs should be prefixed with http://localhost:8080 or equivalent.
 
 ###Service health
 
 > ###`/actuator/health`
 
 ![health](/screenshots/screenshot0007.png)
 
 ###Movies List
 
 > ###`/api/movies`
 
 ![movies list](/screenshots/screenshot0008.png)
 
 ####Top User
 
 > ###`/api/top-user`
 
 ![top user](/screenshots/screenshot0009.png)
 
  ####Top Movie
  
  > ###`/api/top-movie`
  
  ![top movie](/screenshots/screenshot0010.png)
   