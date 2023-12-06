package kino.rabbitmq.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

public record UserResource(

        UUID id,

        String username,

        String name,

        String surname
) implements Serializable
{}
