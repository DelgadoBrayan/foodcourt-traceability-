package com.service.traceability.domain.usecase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.service.traceability.domain.api.ITrackingServicePort;
import com.service.traceability.domain.model.Tracking;
import com.service.traceability.domain.spi.ITrackingPersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrackingUseCase implements ITrackingServicePort {
    private final ITrackingPersistencePort trackingPersistencePort;

        @Override
    public void createTracking(Long clientId, Long orderId, String initialState) {
        Tracking tracking = new Tracking(
            null,
            clientId,
            orderId,
            initialState,
            new ArrayList<>()
        );
        tracking.getStateHistory().add(
           new Tracking.StateHistory(LocalDateTime.now(), initialState, "Tu pedido ha sido recibido y está en cola para ser asignado a uno de nuestros chefs.")
        );
        trackingPersistencePort.saveTracking(tracking);
    }

    @Override
    public void addState(Long orderId, String newState, String description) {
        Optional<Tracking> trackingOpt = trackingPersistencePort.getTrackingByOrderId(orderId);

        if (trackingOpt.isPresent()) {
            Tracking tracking = trackingOpt.get();
            tracking.setCurrentState(newState);
           
            tracking.getStateHistory().add(
                new Tracking.StateHistory(LocalDateTime.now(), newState, description)
            );
            trackingPersistencePort.updateTracking(tracking);
        } else {
            throw new IllegalArgumentException("Tracking not found for the provided orderId.");
        }
    }

    @Override
    public Optional<Tracking> getTrackingByOrderId(Long orderId) {
        return trackingPersistencePort.getTrackingByOrderId(orderId);
    }

    @Override
    public Optional<Tracking> getTrackingByClientId(Long clientId) {
        return trackingPersistencePort.getTrackingByClientId(clientId);
    }
}
