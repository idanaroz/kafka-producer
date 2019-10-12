FROM openjdk:14-alpine

CMD "apk update"
CMD "apk upgrade"
CMD "apk add bash"
CMD "apk add curl"

COPY target/producer-*.jar /producer.jar

ENV kafka_bootstrapServer 192.168.1.21:9092

CMD ["java", "-jar", "/producer.jar"]


