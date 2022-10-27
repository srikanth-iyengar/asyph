FROM alpine
RUN apk update && \
    apk add openjdk17-jre-headless
RUN mkdir /opt/app
COPY ./build/libs/UserMicroservice-0.0.1-SNAPSHOT.jar /opt/app/user-service.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "-XX:MaxRAM=165m" ,"/opt/app/user-service.jar"]
