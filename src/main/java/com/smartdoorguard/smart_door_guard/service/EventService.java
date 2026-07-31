package com.smartdoorguard.smart_door_guard.service;

import com.smartdoorguard.smart_door_guard.domain.Event;
import com.smartdoorguard.smart_door_guard.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event record(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> findByDevice(String deviceId) {
        return eventRepository.findByDeviceIdOrderByCreatedAtDesc(deviceId);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }
}
