FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY src ./src

COPY build.gradle .
COPY gradlew .
COPY gradle ./gradle

RUN ./gradlew build -x test

COPY build/libs/reporting-0.0.1-SNAPSHOT.jar ./build/libs/reporting-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "build/libs/reporting-0.0.1-SNAPSHOT.jar"]
