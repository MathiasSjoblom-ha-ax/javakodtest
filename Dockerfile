FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY . /app
RUN ./mvnw package
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/target/javakodtest.jar"]