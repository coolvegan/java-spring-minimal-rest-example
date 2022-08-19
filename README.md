# java-spring-minimal-rest-example
This is a small example how i implement rest api's in java with springboot

# This was the task of a trial work. The goal was to deploy a minimal Spring Boot Rest API in Docker. 

In Fish Shell set your env vars like this
```
set -gx DB_NAME todo
set -gx DB_PORT 3306
set -gx DB_HOST hostname
set -gx DB_USER admin
set -gx DB_PASS strongpassword
```
in bash and zsh use EXPORT <varname>=value

### The todo minimal rest example is running on port 8080.

### run unit test with *mvn test* - result should be right now
```
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.836 s
[INFO] Finished at: 2022-08-19T00:33:14+02:00
[INFO] ------------------------------------------------------------------------
```

### dockerize the app with
please note the database is created and getting dropped afterwards, change it in docker-compose.yaml or 
application.properties
```
docker-compose up 
```

### rebuild because of new sources
```
docker-compose up --build --force-recreate
```

### API Path is /api/todo
supported methods are get /api/todo, get /api/todo/{id}, get /api/todo?finished=true|false,  post /api/todo to create, put /api/todo to update and delete to fly to the mars with elon musk.  

#### Try out

``` 
curl -X POST http://localhost:8080/api/todo -H 'Content-Type: application/json' -d '{ "description":"learn rust", "finished":false}'
curl -X PUT http://localhost:8080/api/todo -H 'Content-Type: application/json' -d '{ "id": 1, "description":"learn c++", "finished":false}'
curl -X DELETE http://localhost:8080/api/todo -H 'Content-Type: application/json' -d '{ "id": 1, "description":"learn c++", "finished":false}'
curl -X GET http://localhost:8080/api/todo 
curl -X GET http://localhost:8080/api/todo/1
curl -X GET http://localhost:8080/api/todo?finished=false
curl -X GET http://localhost:8080/api/todo?finished=true
```




##### Personal Notes
I played with this setup. It was not the right thing to couple these things together. It is only useful for
testing in the hibernate create-drop context or if you have your mariadatabase folder anywhere and you want to 
use it mounted as volume in a container, then you have to change the DB_HIBERNATE_OPTION=update or better delete this
parameter from application.properties and set it there
```
services:
  mariadb:
    image: mariadb:10.2
    environment:
      MYSQL_ROOT_PASSWORD: <root_user>
      MYSQL_DATABASE: <db_name>
      MYSQL_USER: <root_user>
      MYSQL_PASSWORD: <root_user>
    ports:
      - '3306:3306'
    restart: on-failure
    volumes:
      - ${PWD}/mariadb:/var/lib/mysql
  spring-boot:
    image: spring-boot
    build:
      context: .
    environment:
      - DB_USER=${DB_USER}
      - DB_PASS=${DB_PASS}
      - DB_HOST=${DB_HOST}
      - DB_PORT=${DB_PORT}
      - DB_NAME=${DB_NAME}
      - DB_HIBERNATE_OPTION=create-drop
    ports:
      - "8080:8080"
      - "5005:5005"
    depends:
      - mariadb   
```
