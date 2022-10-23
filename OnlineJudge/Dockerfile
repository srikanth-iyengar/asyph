FROM amazoncorretto:17-alpine-jdk
ARG JAR_FILE=target
COPY target/OnlineJudge-0.0.1-SNAPSHOT.jar online-judge.jar
ENTRYPOINT ["java", "-jar", "/online-judge.jar"]
