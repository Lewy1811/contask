# Contask

## Decription
Contask is an web-application wchich provides access to breaking news articles and givies 
the user tools for searching via categoies (for polish articles) or phrases (articles from 
all over the world).

## Installation and running
Instal SDK in version 1.8 or higher.

The project contains gradle wrapper so it is not necessary to have Gradle installed.

Please following command project's main catalogue:
```gradlew build```  
This will create project in (...)\build\libs\gs-rest-service-0.1.0.jar

In order to run the application call:
```java -jar build/libs/gs-rest-service-0.1.0.jar``` 

The running project is available at:
```
http://localhost:8000/index.html
```

## API documentation
Swagger framework was used to generate documentation of this project. Swagger UI wchich givies 
you access to documentation is available at:
```
http://localhost:8000/swagger-ui.html#/ 
```
when the project is running.

## Tests information
Use following command to run tests:  
```gradle test```

Please note that there shouldn't be any spaces in the name of project directory path in order 
to avoid possible problems with running tests. Some of them use a json file located in 
(...)\src\test\resources.