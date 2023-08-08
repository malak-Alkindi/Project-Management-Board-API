FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY . .

RUN apk add --no-cache maven

RUN mvn clean install -DskipTests

EXPOSE 8080


CMD ["java", "-jar", "target/BoardAPI-0.0.1.jar"]