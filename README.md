# prime-number-service

## Requirements

For building and running the application you need:

- [JDK 11+](https://jdk.java.net/11/)
- [JDK 20](https://jdk.java.net/20/) - Optional
- [Maven 3](https://maven.apache.org)

## Compile

Below maven goal can be used to compile and run Unit and Integration tests

```shell
mvn clean install
```

Below maven goal can be used to only run Unit and Integration tests

```shell
mvn test
```

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method
in the `com.natwest.services.primenumber.PrimeNumberApplication` class from your IDE.

Alternatively you can use
the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html)
like so:

```shell
mvn spring-boot:run
```

## End point to test

Below is localhost endpoint to get prime number using GET method. This endpoint serves JSON and XML data. To get data in
XML, request header should be set as "accept=application/xml" and for json "accept=application/json"

```shell
http://localhost:8080/prime-number-service/v1/primes/10
```

Actuator is available at below URL for cache

```shell
http://localhost:8081/actuator/caches
```


