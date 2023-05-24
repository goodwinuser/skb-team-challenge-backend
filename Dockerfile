FROM eclipse-temurin:17-alpine as builder
ADD . /src
WORKDIR /src
RUN chmod +x mvnw
RUN --mount=type=cache,target=/root/.m2,rw ./mvnw clean package -am -DskipTests -T 1C

FROM alpine:3.18
LABEL maintainer="Pavel 'P0var' Lukash & Simeon 'DragonDevel' Zagaynov"
RUN apk --no-cache add openjdk17-jdk
COPY --from=builder /src/target/app.jar app.jar

EXPOSE 3000
ENTRYPOINT ["echo","App builded"]