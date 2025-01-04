FROM openjdk:23-jdk-slim
# Set the working directory
WORKDIR /coupon-api
# Copy the application JAR file
COPY build/libs/*.jar coupon-api-0.0.1-SNAPSHOT.jar
# Expose the port the application runs on
EXPOSE 8080
# Command to run the application
ENTRYPOINT ["java", "-jar", "coupon-api-0.0.1-SNAPSHOT.jar"]