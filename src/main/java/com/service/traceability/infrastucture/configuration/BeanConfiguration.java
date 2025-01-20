package com.service.traceability.infrastucture.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.service.traceability.domain.api.ITrackingServicePort;
import com.service.traceability.domain.spi.ITrackingPersistencePort;
import com.service.traceability.domain.usecase.TrackingUseCase;
import com.service.traceability.infrastucture.out.mongodb.adapter.TrackingMongoAdapter;
import com.service.traceability.infrastucture.out.mongodb.mapper.TrackingEntityMapper;
import com.service.traceability.infrastucture.out.mongodb.repository.TrackingMongoRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final TrackingMongoRepository trackingMongoRepository;
    private final TrackingEntityMapper trackingEntityMapper;

    @Bean
    ITrackingPersistencePort trackingPersistencePort(){
        return new TrackingMongoAdapter(trackingMongoRepository, trackingEntityMapper);
    }

    @Bean
    ITrackingServicePort trackingServicePort(){
        return new TrackingUseCase(trackingPersistencePort());
    }
}
