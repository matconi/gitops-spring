# Buid stage
FROM maven:3.8.6-openjdk-11-slim AS build
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package
# Execution stage
FROM openjdk:11-jre-slim
COPY --from=build /usr/src/app/target/module-2.7.14.jar /usr/app/module-2.7.14.jar  
ENTRYPOINT ["java", "-jar", "/usr/app/module-2.7.14.jar"] 