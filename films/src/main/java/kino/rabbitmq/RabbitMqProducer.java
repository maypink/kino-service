package kino.rabbitmq;

import com.google.gson.Gson;
import kino.config.RabbitConfiguration;
import kino.rabbitmq.resource.UserResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMqProducer {

    @Autowired
    RabbitConfiguration rabbitConfiguration;

    private final Gson gson = new Gson();

    public UserResource getUserByUsername(String username) throws InterruptedException {
        log.info("Films Service -- RabbitMqProducer.getUserByUsername method was evoked.");
        String response = (String) rabbitConfiguration.rabbitTemplate().convertSendAndReceive("getUserByUsername", username);
        // maybe if response is null then raise exception
        return gson.fromJson(response, UserResource.class);
    }
}
