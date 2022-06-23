FROM amazoncorretto:11-alpine-jdk
ARG JAR_FILE=target
COPY target/OnlineJudge-0.0.1-SNAPSHOT.jar online-judge-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/online-judge-0.0.1.jar"]
