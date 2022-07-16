package com.sougata.digitalenigma.model;

import com.sougata.digitalenigma.enums.RotorIdentifier;
import com.sougata.digitalenigma.model.dto.PlugboardConnection;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Settings {
    private RotorIdentifier rotorIdentifierX;
    private RotorIdentifier rotorIdentifierY;
    private RotorIdentifier rotorIdentifierZ;
    private Integer startPosX;
    private Integer startPosY;
    private Integer startPosZ;
    List<PlugboardConnection> plugboardConnections;
}
