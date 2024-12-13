# Use an official OpenJDK image as the base image
FROM openjdk:17-jdk-slim

# Set environment variables for the application
ENV APP_HOME=/usr/src/app \
    JAVA_OPTS="-Xmx512m"

# Set the working directory inside the container
WORKDIR $APP_HOME

# Copy the application source code to the container
COPY . $APP_HOME

# Install build tools if needed (e.g., Maven or Gradle)
# Uncomment one of these if your application requires it
 RUN apt-get update && apt-get install -y maven
# RUN apt-get update && apt-get install -y gradle

# If using Maven or Gradle, build the application
# Uncomment the appropriate line if necessary
RUN mvn clean install
# RUN ./gradlew build

# Expose the port your application runs on
EXPOSE 8080

# Default command to run the application (adjust the path to the main class JAR)
# Replace 'your-app.jar' with the actual name of your JAR file
CMD ["java", "-jar", "target/your-app.jar"]
