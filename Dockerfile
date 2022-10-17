FROM maven:3.8.6-openjdk-11 AS mvn-compile
WORKDIR /maven
COPY . .
RUN mvn -f ./backend/pom.xml clean install


FROM openjdk:11-jdk
ARG SPRING_PROFILE=default
ENV PROFILE=$SPRING_PROFILE
COPY --from=mvn-compile /maven/backend/target/*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${PROFILE}","-jar","/app.jar"]