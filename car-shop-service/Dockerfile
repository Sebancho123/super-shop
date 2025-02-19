FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/car-shop-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_car-shop-service.jar
ENTRYPOINT ["java", "-jar", "app_car-shop-service.jar"]