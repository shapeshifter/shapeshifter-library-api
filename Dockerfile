FROM openjdk:14.0.2-slim-buster

ADD build/libs/shapeshifter-library-api-*.jar app.jar

RUN adduser --system java

EXPOSE 8080

USER java

ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]

