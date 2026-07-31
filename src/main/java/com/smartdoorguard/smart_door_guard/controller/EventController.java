package com.smartdoorguard.smart_door_guard.controller;

import com.smartdoorguard.smart_door_guard.domain.Event;
import com.smartdoorguard.smart_door_guard.service.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Event record(@RequestBody Event event) {
        return eventService.record(event);
    }

    @GetMapping
    public List<Event> findAll(@RequestParam(required = false) String deviceId) {
        if (deviceId != null) {
            return eventService.findByDevice(deviceId);
        }
        return eventService.findAll();
    }
}
