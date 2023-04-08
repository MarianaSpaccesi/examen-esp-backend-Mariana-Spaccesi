package com.dh.serie.event;

import com.dh.serie.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class SerieCreatedProducerEvent {

    private final RabbitTemplate rabbitTemplate;

    public void publishNewSeriePublish(SerieCreatedProducerEvent.Data data){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,RabbitMQConfig.TOPIC_SERIE_CREATED,data);
    }

    public SerieCreatedProducerEvent(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Data{

        private String id;

        private String name;

        private String genre;

    }
}
