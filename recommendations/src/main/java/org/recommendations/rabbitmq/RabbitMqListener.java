package org.recommendations.rabbitmq;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.recommendations.rabbitmq.utils.RecommendationResourceList;
import org.recommendations.rabbitmq.utils.RecommendationResourceStr;
import org.recommendations.service.RecommendationService;
import org.recommendations.utils.RecommendationResource;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RabbitMqListener {

    private final Gson gson = new Gson();

    @Autowired
    RecommendationService recommendationService;

    @RabbitListener(queues = "getRecommendationForUsername")
    public String worker1(String username) throws InterruptedException {
        log.info("Accepted on worker 1 for get recommendation for username");
        List<RecommendationResource> recommendationResourceList = recommendationService.getRecommendationForUsername(username);
        List<RecommendationResourceStr> recommendationResourceStrs = recommendationResourceList
                .stream()
                .map(r -> new RecommendationResourceStr(
                        r.id(),
                        r.userId(),
                        r.filmId(),
                        r.score(),
                        r.dateTime().toString()))
                .toList();
        RecommendationResourceList recommendationResourceList1 = new RecommendationResourceList(recommendationResourceStrs);
        return gson.toJson(recommendationResourceList1);
    }
}
