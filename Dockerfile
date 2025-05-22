FROM eclipse-temurin:21-jdk-alpine

RUN mkdir /app

WORKDIR /app

COPY build.gradle .
COPY settings.gradle .
COPY src ./src

# install gradle and build the app
RUN apk add --no-cache gradle && gradle build -x test

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY build/libs/authentication-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/app/app.jar"]