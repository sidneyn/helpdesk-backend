FROM maven:3.8.3-openjdk-11-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11-jre-slim
COPY --from=build /target/helpdesk-0.0.1-SNAPSHOT.jar /helpdesk-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "/helpdesk-0.0.1-SNAPSHOT.jar"]