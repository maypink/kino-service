package org.kino.utils.rabbitUtils;

import java.io.Serializable;
import java.util.List;

public record RecommendationResourceList (
        List<RecommendationResourceStr> recommendationResourceList
) implements Serializable
{}
