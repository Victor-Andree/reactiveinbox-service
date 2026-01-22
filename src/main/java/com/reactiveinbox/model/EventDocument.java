package com.reactiveinbox.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "events")
public class EventDocument {

    @Id
    private String id;

    private String eventId;

    private String type;

    private String source;

    private JsonNode payload;

    private EventStatus status;

    private Instant createdAt;

    private Instant processedAt;

}
