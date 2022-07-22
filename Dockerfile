FROM gradle:jdk17-alpine AS BUILDER

MAINTAINER  Alan Oliveira

CMD mkdir /builder

WORKDIR /builder

COPY ./ /builder/

RUN ./gradlew build --exclude-task test

FROM openjdk:17-alpine
WORKDIR /root/
COPY --from=BUILDER /builder/build/libs ./
EXPOSE 8080

ENTRYPOINT ["java","-jar","./api-0.0.1-SNAPSHOT.jar"]