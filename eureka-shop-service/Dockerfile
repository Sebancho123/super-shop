FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/eureka-shop-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_eureka-shop-service.jar
ENTRYPOINT ["java", "-jar", "app_eureka-shop-service.jar"]