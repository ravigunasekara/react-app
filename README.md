### Build and Test Execution
To test & build the application:
```
./gradlew test
./gradlew clean build
```

You can run application via gradle
```
./gradlew bootRun
```
You can build Docker image by running
```
./gradlew bootBuildImage --imageName=todo-list/todo-app-docker
```
You can run the APP in container by running 
```
docker run -p 8080:8080 -t todo-list/todo-app-docker
```

Implementation, Assumptions and Improvements

1. App keeps added items in memory
2. Can be introduced persistence layer with flyway scripts