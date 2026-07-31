package com.smartdoorguard.smart_door_guard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "devices")
@Getter
@Setter
@NoArgsConstructor
public class Device {

    @Id
    private String id;

    private Long ownerId;

    private String connStatus;

    private String state;

    private String doorStatus;

    private String lockStatus;

    private Instant updatedAt;
}
