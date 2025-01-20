package com.service.traceability.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.service.traceability.domain.model.Tracking;
import com.service.traceability.domain.spi.ITrackingPersistencePort;

@ExtendWith(MockitoExtension.class)
 class TrackingUseCaseTest {

    @Mock
    private ITrackingPersistencePort trackingPersistencePort;

    @InjectMocks
    private TrackingUseCase trackingUseCase;

    private Long clientId;
    private Long orderId;
    private String initialState;
    private String newState;
    private String description;

    @BeforeEach
    void setUp() {
        clientId = 1L;
        orderId = 1L;
        initialState = "PENDING";
        newState = "IN_PROCESESS";
        description = "Tu pedido fue asignado a un cheft y esta en preparacion";
    }

    @Test
    void testCreateTracking() {
        trackingUseCase.createTracking(clientId, orderId, initialState);
        verify(trackingPersistencePort, times(1)).saveTracking(any(Tracking.class));
    }


    @Test
    void testAddStateWhenTrackingDoesNotExist() {
        when(trackingPersistencePort.getTrackingByOrderId(orderId)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> trackingUseCase.addState(orderId, newState, description));
    }

    @Test
    void testGetTrackingByOrderIdWhenExists() {
        when(trackingPersistencePort.getTrackingByOrderId(orderId)).thenReturn(Optional.of(new Tracking()));
        Optional<Tracking> result = trackingUseCase.getTrackingByOrderId(orderId);
        assertTrue(result.isPresent());
    }

    @Test
    void testGetTrackingByOrderIdWhenDoesNotExist() {
        when(trackingPersistencePort.getTrackingByOrderId(orderId)).thenReturn(Optional.empty());
        Optional<Tracking> result = trackingUseCase.getTrackingByOrderId(orderId);
        assertFalse(result.isPresent());
    }

    @Test
    void testGetTrackingByClientIdWhenExists() {
        when(trackingPersistencePort.getTrackingByClientId(clientId)).thenReturn(Optional.of(new Tracking()));
        Optional<Tracking> result = trackingUseCase.getTrackingByClientId(clientId);
        assertTrue(result.isPresent());
    }

    @Test
    void testGetTrackingByClientIdWhenDoesNotExist() {
        when(trackingPersistencePort.getTrackingByClientId(clientId)).thenReturn(Optional.empty());
        Optional<Tracking> result = trackingUseCase.getTrackingByClientId(clientId);
        assertFalse(result.isPresent());
    }
}
