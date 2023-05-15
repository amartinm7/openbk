FROM openjdk:19

MAINTAINER amartinm7@gmail.com

COPY build/libs/openbk*.jar /usr/local/openbk/openbk.jar

WORKDIR /usr/local/openbk

ENTRYPOINT ["java","-jar","openbk.jar", "--spring.config.name=application", "--spring.profiles.active=dev"]


