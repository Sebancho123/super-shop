FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/sale-shop-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_sale-shop.jar
ENTRYPOINT ["java", "-jar", "app_sale-shop.jar"]