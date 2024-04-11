FROM maven:3.8.4-openjdk-17-slim as maven_builder
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

FROM openjdk:17-slim
COPY --from=maven_builder /app/target/*.jar /app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app.jar"]