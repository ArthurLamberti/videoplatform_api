FROM bellsoft/liberica-openjdk-alpine-musl:17

COPY build/libs/*.jar /opt/app/application.jar

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

CMD java -jar /opt/app/application.jar