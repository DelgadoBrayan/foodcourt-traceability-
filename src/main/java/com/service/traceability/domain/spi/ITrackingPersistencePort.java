package com.service.traceability.domain.spi;

import java.util.Optional;

import com.service.traceability.domain.model.Tracking;

public interface ITrackingPersistencePort {
    void saveTracking(Tracking tracking);

    Optional<Tracking> getTrackingByOrderId(Long orderId);

    Optional<Tracking> getTrackingByClientId(Long clientId);

    void updateTracking(Tracking tracking);
}
