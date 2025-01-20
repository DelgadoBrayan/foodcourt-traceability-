package com.service.traceability.infrastucture.out.mongodb.adapter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import com.service.traceability.infrastucture.out.mongodb.entity.TrackingEntity;
import com.service.traceability.infrastucture.out.mongodb.mapper.TrackingEntityMapper;
import com.service.traceability.infrastucture.out.mongodb.repository.TrackingMongoRepository;

@ExtendWith(MockitoExtension.class)
 class TrackingMongoAdapterTest {

    @Mock
    private TrackingMongoRepository trackingMongoRepository;

    @Mock
    private TrackingEntityMapper trackingEntityMapper;

    @InjectMocks
    private TrackingMongoAdapter trackingMongoAdapter;

    private Long orderId;
    private Long clientId;
    private Tracking tracking;

    @BeforeEach
    void setUp() {
        orderId = 1L;
        clientId = 1L;
        tracking = new Tracking();
    }

    @Test
    void testSaveTracking() {
        when(trackingEntityMapper.toEntity(tracking)).thenReturn(new TrackingEntity());
        trackingMongoAdapter.saveTracking(tracking);
        verify(trackingMongoRepository, times(1)).save(any(TrackingEntity.class));
    }

    @Test
    void testGetTrackingByOrderIdWhenExists() {
        TrackingEntity trackingEntity = new TrackingEntity();
        when(trackingMongoRepository.findByOrderId(orderId)).thenReturn(Optional.of(trackingEntity));
        when(trackingEntityMapper.toDomain(trackingEntity)).thenReturn(tracking);

        Optional<Tracking> result = trackingMongoAdapter.getTrackingByOrderId(orderId);
        assertTrue(result.isPresent());
        assertEquals(tracking, result.get());
    }

    @Test
    void testGetTrackingByOrderIdWhenDoesNotExist() {
        when(trackingMongoRepository.findByOrderId(orderId)).thenReturn(Optional.empty());

        Optional<Tracking> result = trackingMongoAdapter.getTrackingByOrderId(orderId);
        assertFalse(result.isPresent());
    }

    @Test
    void testGetTrackingByClientIdWhenExists() {
        TrackingEntity trackingEntity = new TrackingEntity();
        when(trackingMongoRepository.findByClientId(clientId)).thenReturn(Optional.of(trackingEntity));
        when(trackingEntityMapper.toDomain(trackingEntity)).thenReturn(tracking);

        Optional<Tracking> result = trackingMongoAdapter.getTrackingByClientId(clientId);
        assertTrue(result.isPresent());
        assertEquals(tracking, result.get());
    }

    @Test
    void testGetTrackingByClientIdWhenDoesNotExist() {
        when(trackingMongoRepository.findByClientId(clientId)).thenReturn(Optional.empty());

        Optional<Tracking> result = trackingMongoAdapter.getTrackingByClientId(clientId);
        assertFalse(result.isPresent());
    }

    @Test
    void testUpdateTracking() {
        when(trackingEntityMapper.toEntity(tracking)).thenReturn(new TrackingEntity());
        trackingMongoAdapter.updateTracking(tracking);
        verify(trackingMongoRepository, times(1)).save(any(TrackingEntity.class));
    }
}
