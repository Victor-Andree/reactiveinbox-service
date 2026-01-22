package com.reactiveinbox.dto;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EventRequest {

    @NotBlank
    private String type;

    @NotBlank
    private String source;

    private JsonNode payload;

}
