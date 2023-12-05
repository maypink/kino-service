package org.recommendations.rabbitmq;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RabbitMqListener {

//    private final Gson gson = new Gson();
//
//    @RabbitListener(queues = "getRecommendationForUserId")
//    public void worker1(String userId) {
//        log.info("Accepted on worker 1 for get recommendation for user id");
//    }
}
