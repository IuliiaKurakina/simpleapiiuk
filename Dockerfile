FROM openjdk:8-jdk-alpine

RUN mkdir -p /apps
COPY ./target/simpleapiiuk-1.0.jar /apps/app.jar
COPY ./entrypoint.sh /apps/entrypoint.sh

RUN chmod +x /apps/entrypoint.sh
CMD ["/apps/entrypoint.sh"]