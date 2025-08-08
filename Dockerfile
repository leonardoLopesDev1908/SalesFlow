FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /build
COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21
WORKDIR /salesflow
COPY --from=build /build/target/*.jar ./salesflow.jar

ENTRYPOINT ["java", "-jar", "salesflow.jar"]