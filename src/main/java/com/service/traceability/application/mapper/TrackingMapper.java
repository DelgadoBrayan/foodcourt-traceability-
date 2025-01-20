package com.service.traceability.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.service.traceability.application.dto.TrackingDto;
import com.service.traceability.domain.model.Tracking;

@Mapper(componentModel = "spring")
public interface TrackingMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "clientId", source = "clientId")
    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "currentState", source = "currentState")
    @Mapping(target = "stateHistory", source = "stateHistory")
    TrackingDto toDTO(Tracking tracking);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "clientId", source = "clientId")
    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "currentState", source = "currentState")
    @Mapping(target = "stateHistory", source = "stateHistory")
    Tracking toDomain(TrackingDto trackingDTO);
}