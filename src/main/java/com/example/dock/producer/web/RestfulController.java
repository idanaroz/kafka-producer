package com.example.dock.producer.web;

import com.example.dock.producer.producers.ProducerDemoWithCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
class RestfulController {

    @Value("${kafka_bootstrapServer}")
    String bootstrapServer;

    @PostMapping(path = "/produce", consumes = "application/json")
    public String produceMessage(@RequestBody String content) {
        ProducerDemoWithCallback producer = new ProducerDemoWithCallback(bootstrapServer, content);
        producer.run();
        return "producer-service container has been requested to produce message";
    }


    @GetMapping(path = "/")
    public String getBootstrapSeverValue() {
        return bootstrapServer;
    }
}
