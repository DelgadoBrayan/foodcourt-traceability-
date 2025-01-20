package com.service.traceability.infrastucture.out.mongodb.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "tracking")
@Data
public class TrackingEntity {
    @Id
    private String id;
    private Long clientId;
    private Long orderId;
    private String currentState;
    private List<StateHistoryEntity> stateHistory;

    @Data
    public static class StateHistoryEntity {
        private LocalDateTime timestamp;
        private String state;
        private String description;
       
    }

}