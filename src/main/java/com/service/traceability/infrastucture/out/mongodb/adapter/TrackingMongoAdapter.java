package com.service.traceability.infrastucture.out.mongodb.adapter;

import java.util.Optional;

import com.service.traceability.domain.model.Tracking;
import com.service.traceability.domain.spi.ITrackingPersistencePort;
import com.service.traceability.infrastucture.out.mongodb.mapper.TrackingEntityMapper;
import com.service.traceability.infrastucture.out.mongodb.repository.TrackingMongoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrackingMongoAdapter implements ITrackingPersistencePort{
    private final TrackingMongoRepository trackingMongoRepository;
    private final TrackingEntityMapper trackingEntityMapper;


    @Override
    public void saveTracking(Tracking tracking) {
        trackingMongoRepository.save(trackingEntityMapper.toEntity(tracking));
    }

    @Override
    public Optional<Tracking> getTrackingByOrderId(Long orderId) {
        return trackingMongoRepository.findByOrderId(orderId)
                              .map(trackingEntityMapper::toDomain);
    }

    @Override
    public Optional<Tracking> getTrackingByClientId(Long clientId) {
        return trackingMongoRepository.findByClientId(clientId)
                              .map(trackingEntityMapper::toDomain);
    }

    @Override
    public void updateTracking(Tracking tracking) {
        trackingMongoRepository.save(trackingEntityMapper.toEntity(tracking));
    }

}
