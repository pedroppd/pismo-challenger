FROM openjdk:21-jdk

WORKDIR /app

COPY target/*.jar /app/customer-transactions.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/customer-transactions.jar"]
