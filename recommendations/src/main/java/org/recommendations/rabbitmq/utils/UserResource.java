package org.recommendations.rabbitmq.utils;

import java.io.Serializable;
import java.util.UUID;

public record UserResource(

    UUID id,

    String username,

    String name,

    String surname
) implements Serializable
{}
