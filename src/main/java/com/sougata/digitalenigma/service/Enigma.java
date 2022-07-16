package com.sougata.digitalenigma.service;

import com.sougata.digitalenigma.model.Rotor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Enigma {
    Rotor rotorX;
    Rotor rotorY;
    Rotor rotorZ;
}
