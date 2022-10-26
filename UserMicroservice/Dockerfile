FROM alpine
RUN apk update && \
    apk add openjdk17-jre-headless
COPY ./build/libs/UserMicroservice-0.0.1-SNAPSHOT.jar user-service.jar
ENTRYPOINT ["java", "-jar", "user-service.jar"]
