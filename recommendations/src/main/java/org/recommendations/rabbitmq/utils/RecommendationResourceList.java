package org.recommendations.rabbitmq.utils;

import org.recommendations.utils.RecommendationResource;

import java.io.Serializable;
import java.util.List;

public record RecommendationResourceList (
        List<RecommendationResource> recommendationResourceList
) implements Serializable
{}
