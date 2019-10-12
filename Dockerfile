FROM openjdk:14-alpine

COPY target/producer-*.jar /producer.jar

ENV kafka_bootstrapServer 192.168.1.21:9092

CMD ["java", "-jar", "/producer.jar", "--kafka_bootstrapServer","${kafka_bootstrapServer}"]


