package com.reactiveinbox.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.reactiveinbox.model.EventStatus;

import java.time.Instant;

public record EventView(String eventId,
                        String type,
                        String source,
                        JsonNode payload,
                        EventStatus status,
                        Instant createdAt,
                        Instant processedAt
)  {
}
