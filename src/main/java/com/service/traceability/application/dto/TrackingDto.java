package com.service.traceability.application.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class TrackingDto {
    private String id;
    private Long clientId;
    private Long orderId;
    private String currentState;
    private List<StateHistoryDTO> stateHistory;

    @Data
    public static class StateHistoryDTO {
        private LocalDateTime timestamp;
        private String state;
        private String description;

    }

}