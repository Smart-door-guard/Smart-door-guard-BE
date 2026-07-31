package com.smartdoorguard.smart_door_guard.service;

import com.smartdoorguard.smart_door_guard.domain.Setting;
import com.smartdoorguard.smart_door_guard.repository.SettingRepository;
import org.springframework.stereotype.Service;

@Service
public class SettingService {

    private final SettingRepository settingRepository;

    public SettingService(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    public Setting getOrCreateDefault(String deviceId) {
        return settingRepository.findById(deviceId)
                .orElseGet(() -> {
                    Setting setting = new Setting();
                    setting.setDeviceId(deviceId);
                    return settingRepository.save(setting);
                });
    }

    public Setting update(String deviceId, Setting settings) {
        settings.setDeviceId(deviceId);
        return settingRepository.save(settings);
    }
}
