# java-spring-minimal-rest-example
This is a small example how i implement rest api's in java with springboot

# This was the task of a trial work. The goal was to deploy a minimal Spring Boot Rest API in Docker. 

In Fish Shell set your env vars like this
```
set -gx DB_NAME todo
set -gx DB_PORT 3306
set -gx DB_HOST localhost
set -gx DB_USER admin
set -gx DB_PASS admin
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
```
docker-compose up 
```

### rebuild because of new sources
```
docker-compose up --build --force-recreate
```

### API Path is /api/todo
supported methods are get, get/{id}, post to create, put to update and delete to fly to the mars with elon musk.  

#### Try out

``` 
curl -X POST http://localhost:8080/api/todo -H 'Content-Type: application/json' -d '{ "description":"learn rust", "finished":false}'
curl -X PUT http://localhost:8080/api/todo -H 'Content-Type: application/json' -d '{ "id": 1, "description":"learn c++", "finished":false}'
curl -X DELETE http://localhost:8080/api/todo -H 'Content-Type: application/json' -d '{ "id": 1, "description":"learn c++", "finished":false}'
curl -X GET http://localhost:8080/api/todo 
curl -X GET http://localhost:8080/api/todo/1
```
