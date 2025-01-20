package com.service.traceability.domain.api;

import java.util.Optional;

import com.service.traceability.domain.model.Tracking;

public interface ITrackingServicePort {
    void createTracking(Long clientId, Long orderId, String initialState);

    void addState(Long orderId, String newState, String description);

    Optional<Tracking> getTrackingByOrderId(Long orderId);

    Optional<Tracking> getTrackingByClientId(Long clientId);
}
