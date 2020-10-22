FROM maven:3.6.1-jdk-8-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -f pom.xml clean package

FROM openjdk:latest
COPY --from=build /workspace/target/Exchange-Trading-JavaApi.jar Exchange-Trading-JavaApi.jar
USER 1001
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Exchange-Trading-JavaApi.jar"]