# Etapa de construcción
FROM maven:3.8.4-openjdk-11 AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package

# Etapa de ejecución
FROM openjdk:11-jre-slim

WORKDIR /app
COPY --from=builder /app/target/German_Embassy-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]