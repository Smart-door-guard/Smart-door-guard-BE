package com.smartdoorguard.smart_door_guard.repository;

import com.smartdoorguard.smart_door_guard.domain.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<Setting, String> {
}
