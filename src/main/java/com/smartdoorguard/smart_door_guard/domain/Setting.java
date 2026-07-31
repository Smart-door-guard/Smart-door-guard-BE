package com.smartdoorguard.smart_door_guard.domain;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "settings")
@Getter
@Setter
@NoArgsConstructor
public class Setting {

    @Id
    private String deviceId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "setting_sms_numbers", joinColumns = @JoinColumn(name = "device_id"))
    @Column(name = "phone_number")
    private List<String> smsNumbers = new ArrayList<>();

    private int sensitivity = 1;

    private boolean nightMode = false;

    private boolean notiToggle = true;
}
