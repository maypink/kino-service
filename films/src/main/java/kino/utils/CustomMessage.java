package kino.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record CustomMessage(@JsonProperty("text") String text,
                            @JsonProperty("priority") int priority)
        implements Serializable {
}