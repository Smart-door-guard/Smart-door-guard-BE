package com.smartdoorguard.smart_door_guard.service;

import com.smartdoorguard.smart_door_guard.domain.Device;
import com.smartdoorguard.smart_door_guard.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device registerOrUpdate(Device device) {
        return deviceRepository.save(device);
    }

    public Device findById(String id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Device not found: " + id));
    }

    public List<Device> findAll() {
        return deviceRepository.findAll();
    }
}
