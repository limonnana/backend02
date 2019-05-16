FROM java:8-jdk-alpine

COPY ./target/backend02-1.0.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch backend02-1.0.jar'

ENTRYPOINT ["java","-jar","backend02-1.0.jar"] 

