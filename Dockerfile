FROM openjdk:14
ADD target/Exchange-Trading-JavaApi.jar Exchange-Trading-JavaApi.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Exchange-Trading-JavaApi.jar"]