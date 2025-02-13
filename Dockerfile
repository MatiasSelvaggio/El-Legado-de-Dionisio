FROM gradle:8.5.0-jdk21 AS builds
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test --no-daemon

FROM openjdk:21-slim

# Instalar libfreetype6 y fontconfig
RUN apt-get update && apt-get install -y libfreetype6 fontconfig && rm -rf /var/lib/apt/lists/*

EXPOSE 8080
RUN mkdir -p /app/el-legado-de-dionisio
WORKDIR /app/el-legado-de-dionisio
COPY --from=builds /home/gradle/src/build/libs/api-0.0.1-SNAPSHOT.jar api.jar

ENTRYPOINT [ "java",\
             "-XX:+UnlockExperimentalVMOptions",\
             "-Xms256m",\
             "-Xmx2048m",\
             "-XX:+UseContainerSupport",\
             "-Djava.security.egd=file:/dev/./urandom",\
             "-jar",\
             "/app/el-legado-de-dionisio/api.jar" ]
