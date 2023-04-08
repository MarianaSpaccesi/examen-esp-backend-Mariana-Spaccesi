package com.dh.movie.event;

import com.dh.movie.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MovieCreatedProducerEvent {

    private final RabbitTemplate rabbitTemplate;

    public MovieCreatedProducerEvent(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMovieCreated (MovieCreatedProducerEvent.Data data){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME , RabbitMQConfig.TOPIC_MOVIE_CREATED, data);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Data{
        private String id;

        private String name;

        private String genre;

        private String urlStream;
    }
}
