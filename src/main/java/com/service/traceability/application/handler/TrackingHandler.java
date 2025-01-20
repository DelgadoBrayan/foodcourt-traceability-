package com.service.traceability.application.handler;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.service.traceability.application.dto.TrackingDto;
import com.service.traceability.application.mapper.TrackingMapper;
import com.service.traceability.domain.api.ITrackingServicePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TrackingHandler {
    private final ITrackingServicePort trackingServicePort;
    private final TrackingMapper trackingMapper;

        public void createTracking(Long clientId, Long orderId, String initialState) {
            trackingServicePort.createTracking(clientId, orderId, initialState);
    }

    public void addState(Long orderId, String newState, String description) {
        trackingServicePort.addState(orderId, newState, description);
    }

    public Optional<TrackingDto> getTrackingByOrderId(Long orderId) {
        return trackingServicePort.getTrackingByOrderId(orderId)
                              .map(trackingMapper::toDTO);
    }

    public Optional<TrackingDto> getTrackingByClientId(Long clientId) {
        return trackingServicePort.getTrackingByClientId(clientId)
                              .map(trackingMapper::toDTO);
    }
}
