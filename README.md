# ARCHIVAL NOTICE

*This repository is no longer maintained.*

This repository was originally built to provide an easy-to-run self-contained instance of the Shapeshifter library that
could be used to send Shapeshifter messages, even from programming languages that are not directly supported in a language-specific library.

Because it has not been used in practice by any market parties it has not been maintained properly and became deprecated.
If you feel like this feature would support your use of the protocol, please open an issue on one of the active Shapeshifter repositories.

# uftpapi

To run the api you need to build the uftplib library using the instructions in the uftplib readme. This will produce the library on the relative location:

../uftplib/build/libs/eu.uftplib-0.0.1-SNAPSHOT.jar

In the build.gradle file this library is referenced.

Set the configuration in the application.properties.

The postgres database scheme will be created in the configured postgres database.

Use the following command to start the api:

on linux:
```./gradlew bootRun```

on windows:
```gradlew.bat bootRun```

on Docker:
requires pre-built jar, or:

```./gradlew build```

then:

```
docker build -t uftpapi .
docker run -p 8024:8024 uftpapi
```

