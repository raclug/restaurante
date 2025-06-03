FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-alpine

WORKDIR /

COPY --from=build /app/target/*.jar restaurante.jar

ENV URL_DATABASE= \
    USER_DATABASE= \
    PASS_DATABASE=

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "restaurante.jar"]