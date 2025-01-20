package com.service.traceability.infrastucture.out.mongodb.mapper;

import org.mapstruct.Mapper;

import com.service.traceability.domain.model.Tracking;
import com.service.traceability.infrastucture.out.mongodb.entity.TrackingEntity;

@Mapper(componentModel = "spring")
public interface TrackingEntityMapper {


    TrackingEntity toEntity(Tracking tracking);

    Tracking toDomain(TrackingEntity trackingEntity);
}