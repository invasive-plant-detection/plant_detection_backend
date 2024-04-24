# plant_detection_backend 🌱
This is a backend service that detects invasive plants from an image.

## Requirements 📝

For building and running the application you need:

- [JDK 21](https://jdk.java.net/21/)
- [Maven 4](https://maven.apache.org)

## Running the application locally 🏃🏻‍♂️

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `ch.eglisi1.plantdetection.plantdetectionbackend.PlantDetectionBackendApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Functionality ⚙️

This is the backend for the [Plant Detection Frontend](https://github.com/invasive-plant-detection/plant_detection_frontend).
The Backend handles the data and the interaction with the [Model](https://github.com/invasive-plant-detection/plant_detection_model).