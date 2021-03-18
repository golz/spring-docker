FROM openjdk:8-jdk-alpine
COPY . /app
WORKDIR /app
RUN ./mvnw package -DskipTests
CMD java -jar target/spring-docker-0.0.1-SNAPSHOT.jar