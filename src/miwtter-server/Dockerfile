FROM gradle:7-jdk11
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon
EXPOSE 80
ENTRYPOINT ["gradle", "runServer", "-d"]