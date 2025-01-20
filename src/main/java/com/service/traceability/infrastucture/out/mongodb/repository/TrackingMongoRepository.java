package com.service.traceability.infrastucture.out.mongodb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.service.traceability.infrastucture.out.mongodb.entity.TrackingEntity;

public interface TrackingMongoRepository extends MongoRepository<TrackingEntity, String> {
    Optional<TrackingEntity> findByOrderId(Long orderId);

    Optional<TrackingEntity> findByClientId(Long clientId);
}
