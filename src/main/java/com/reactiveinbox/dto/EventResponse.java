package com.reactiveinbox.dto;

import com.reactiveinbox.model.EventStatus;

public record EventResponse(String eventId, EventStatus status) {
}
