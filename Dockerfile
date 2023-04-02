FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR Book-Managing
COPY --from=build target/*.jar Book-Managing.jar
ENTRYPOINT ["java" ,"-jar" , "Book-Managing.jar"]
