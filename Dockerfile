FROM maven:3.9.0-eclipse-temurin-17 as builder

COPY pom.xml pom.xml
COPY src src

RUN mvn package -DskipTests

FROM eclipse-temurin:17-jre-focal as runtime

COPY --from=builder ./target/s_a_test-1.0-SNAPSHOT.jar ./api.jar

CMD ["java", "-jar", "api.jar"]