package org.recommendations.rabbitmq;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.recommendations.rabbitmq.utils.FilmRatingResourceList;
import org.recommendations.rabbitmq.utils.RecommendationResourceList;
import org.recommendations.service.RecommendationService;
import org.recommendations.utils.RecommendationResource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RabbitMqProducerFacade {

    @Autowired
    RabbitTemplate template;

    @Autowired
    RecommendationService recommendationService;

    private final Gson gson = new Gson();

    // FACADE
    public void getRecommendationForUsernameForFacade(String username) throws InterruptedException {
        log.info("Recommendations Service -- RabbitMqProducer.getRecommendationForUsernameForFacade method was evoked.");
        List<RecommendationResource> recommendationResourceList = recommendationService.getRecommendationForUsername(username);
        RecommendationResourceList recommendationResourceList1 = new RecommendationResourceList(recommendationResourceList);
        template.convertAndSend("getRecommendationForUsernameForFacade", gson.toJson(recommendationResourceList1));
    }
}
