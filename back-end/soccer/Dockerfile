FROM openjdk:8-jdk-alpine
MAINTAINER duqian
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
RUN echo "Asia/Shanghai" > /etc/timezone;
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]