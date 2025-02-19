FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/products-shop-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_product-shop-service.jar
ENTRYPOINT ["java", "-jar", "app_product-shop-service.jar"]