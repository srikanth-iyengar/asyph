FROM redis:7.0.5-alpine
RUN apk update && \
    apk add openjdk17-jre && \
    apk add supervisor 
COPY ./build/libs/OnlineJudge-0.0.1-SNAPSHOT.jar online-judge.jar
COPY supervisor.conf /etc/supervisor/conf.d/supervisord.conf
COPY supervisor.conf /etc/supervisord.conf
EXPOSE 8081
ENTRYPOINT ["/usr/bin/supervisord"]
