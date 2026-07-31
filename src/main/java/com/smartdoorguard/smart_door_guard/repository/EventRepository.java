package com.smartdoorguard.smart_door_guard.repository;

import com.smartdoorguard.smart_door_guard.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByDeviceIdOrderByCreatedAtDesc(String deviceId);
}
