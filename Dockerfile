FROM openjdk:14.0.2-slim-buster

ADD build/libs/uftpapi-*.jar app.jar

RUN adduser --system java

EXPOSE 8080

USER java

ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]

