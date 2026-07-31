package com.smartdoorguard.smart_door_guard.controller;

import com.smartdoorguard.smart_door_guard.domain.Device;
import com.smartdoorguard.smart_door_guard.service.DeviceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public Device registerOrUpdate(@RequestBody Device device) {
        return deviceService.registerOrUpdate(device);
    }

    @GetMapping
    public List<Device> findAll() {
        return deviceService.findAll();
    }

    @GetMapping("/{id}")
    public Device findById(@PathVariable String id) {
        return deviceService.findById(id);
    }
}
