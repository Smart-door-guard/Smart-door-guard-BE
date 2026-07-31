package com.smartdoorguard.smart_door_guard.repository;

import com.smartdoorguard.smart_door_guard.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, String> {
}
