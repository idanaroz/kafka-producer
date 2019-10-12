package com.example.dock.producer.producers;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ProducerDemoWithCallback {

    private String bootstrapServers;
    private Date date;
    private String content;
    private String topic;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    public ProducerDemoWithCallback(String bootstrapServers, String content) {
        this.date = new Date();
        this.bootstrapServers = bootstrapServers;
        this.content = content;
        topic = "second_topic";
    }

    public void run() {
        final Logger logger = LoggerFactory.getLogger(ProducerDemoWithCallback.class);

        //create producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        JSONObject message = new JSONObject();
        try {
            message.put("content", content);
            message.put("timestamp", dateFormat.format(this.date));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //create producer record
        final ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, message.toString());

        //send data
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                //execute everytime a record is successfully sent or exception is thrown
                if (e == null) {
                    //the record was succsessfully sent
                    logger.info("Received new metadata. \n" +
                            "topic:" + recordMetadata.topic() + "\n" +
                            "Offset:" + recordMetadata.offset() + "\n" +
                            "TimeStamp: " + recordMetadata.timestamp());
                } else {
                    logger.error("Error while producing", e);
                }
            }
        });

        //flush data
        producer.flush();
        //flush and close producer
        producer.close();
    }
}
