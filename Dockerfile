FROM maven:3.9-amazoncorretto-17-debian AS MAVEN_TOOL_CHAIN
COPY ./ /usr/src/mymaven
WORKDIR /usr/src/mymaven
RUN adduser --disabled-password --gecos "" maven && \
    chown -R maven:maven /usr/src/mymaven
USER maven
RUN mvn clean package

FROM openjdk:17-jdk-slim
ARG execution_env=docker
VOLUME /tmp
COPY --from=MAVEN_TOOL_CHAIN /usr/src/mymaven/target/cm-app-service*.jar /app.jar
COPY src/main/resources/application.yml /BOOT-INF/classes/application.yml.template
RUN sed -e "s/spring.profiles.active: local/spring.profiles.active: ${execution_env}/" /BOOT-INF/classes/application.yml.template > /BOOT-INF/classes/application.yml  && \
    jar vfu /app.jar /BOOT-INF/ && \
    chmod 664 /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]