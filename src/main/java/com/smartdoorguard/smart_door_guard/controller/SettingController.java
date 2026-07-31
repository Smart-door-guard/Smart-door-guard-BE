package com.smartdoorguard.smart_door_guard.controller;

import com.smartdoorguard.smart_door_guard.domain.Setting;
import com.smartdoorguard.smart_door_guard.service.SettingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devices/{deviceId}/settings")
public class SettingController {

    private final SettingService settingService;

    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping
    public Setting getOrCreateDefault(@PathVariable String deviceId) {
        return settingService.getOrCreateDefault(deviceId);
    }

    @PutMapping
    public Setting update(@PathVariable String deviceId, @RequestBody Setting settings) {
        return settingService.update(deviceId, settings);
    }
}
