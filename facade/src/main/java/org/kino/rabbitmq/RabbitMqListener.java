package org.kino.rabbitmq;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.kino.utils.rabbitUtils.RecommendationResource;
import org.kino.utils.rabbitUtils.RecommendationResourceList;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

@Slf4j
@Component
public class RabbitMqListener {

    private final Gson gson = new Gson();

    @RabbitListener(queues = "getRecommendationForUsernameForFacade")
    public RecommendationResourceList worker1(String recommendationString) {
        log.info("Accepted on worker 1 for get recommendation for username");
        log.info("ANSWER FROM REC SERVICE {}", recommendationString);
        RecommendationResourceList recommendationResourceList = gson.fromJson(recommendationString, RecommendationResourceList.class);
        // if the recommendation is not ready yet
        return Objects.requireNonNullElseGet(recommendationResourceList, () -> new RecommendationResourceList(Collections.emptyList()));
    }
}