package com.service.traceability.infrastucture.input.rest;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.traceability.application.dto.TrackingDto;
import com.service.traceability.application.handler.TrackingHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/tracking")
public class TrackingController {

    private final TrackingHandler trackingHandler;

    public TrackingController(TrackingHandler trackingHandler) {
        this.trackingHandler = trackingHandler;
    }

    @Operation(
        summary = "Create tracking entry for a new order",
        description = "Creates an initial tracking entry for a new order with an initial state of 'PENDING'."
    )
    @ApiResponse(responseCode = "200", description = "Tracking created successfully")
    @PostMapping
    public ResponseEntity<Void> createTracking(@RequestParam Long clientId, 
                                                @RequestParam Long orderId, 
                                                @RequestParam String initialState) {
        trackingHandler.createTracking(clientId, orderId, initialState);
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Update the state of an existing order",
        description = "Updates the tracking status of an order with a new state and description."
    )
    @ApiResponse(responseCode = "200", description = "Tracking state updated successfully")
    @PatchMapping("/{orderId}")
    public ResponseEntity<Void> updateTrackingState(@PathVariable Long orderId,
                                                     @RequestParam String newState,
                                                     @RequestParam String description) {
        trackingHandler.addState(orderId, newState, description);
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Get tracking details by order ID",
        description = "Fetches the tracking details for a specific order."
    )
    @ApiResponse(responseCode = "200", description = "Tracking retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Tracking not found")
    @GetMapping("/order/{orderId}")
    public ResponseEntity<TrackingDto> getTrackingByOrderId(@PathVariable Long orderId) {
        Optional<TrackingDto> tracking = trackingHandler.getTrackingByOrderId(orderId);
        return tracking.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Get tracking details by client ID",
        description = "Fetches all tracking details for a specific client."
    )
    @ApiResponse(responseCode = "200", description = "Tracking retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Tracking not found")
    @GetMapping("/client/{clientId}")
    public ResponseEntity<TrackingDto> getTrackingByClientId(@PathVariable Long clientId) {
        Optional<TrackingDto> tracking = trackingHandler.getTrackingByClientId(clientId);
        return tracking.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
}