FROM eclipse-temurin:21-alpine

WORKDIR /

COPY target/*.jar restaurante.jar

ENV URL_DATABASE= \
    USER_DATABASE= \
    PASS_DATABASE=

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "restaurante.jar"]