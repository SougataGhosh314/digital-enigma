package com.sougata.digitalenigma.service;

import com.sougata.digitalenigma.enums.Letter;
import com.sougata.digitalenigma.enums.RotorIdentifier;
import com.sougata.digitalenigma.model.Settings;
import com.sougata.digitalenigma.model.dto.PlugboardConnection;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScramblerServiceTest {

    List<PlugboardConnection> connections = Arrays.asList(
            PlugboardConnection.builder().input(Letter.A).output(Letter.K).build(),
            PlugboardConnection.builder().input(Letter.B).output(Letter.L).build(),
            PlugboardConnection.builder().input(Letter.C).output(Letter.M).build(),
            PlugboardConnection.builder().input(Letter.D).output(Letter.N).build(),
            PlugboardConnection.builder().input(Letter.E).output(Letter.O).build(),
            PlugboardConnection.builder().input(Letter.F).output(Letter.P).build(),
            PlugboardConnection.builder().input(Letter.G).output(Letter.Q).build(),
            PlugboardConnection.builder().input(Letter.H).output(Letter.R).build(),
            PlugboardConnection.builder().input(Letter.I).output(Letter.S).build(),
            PlugboardConnection.builder().input(Letter.J).output(Letter.T).build()
    );

    Settings settings = Settings.builder()
            .rotorIdentifierX(RotorIdentifier.A)
            .rotorIdentifierY(RotorIdentifier.B)
            .rotorIdentifierZ(RotorIdentifier.C)
            .startPosX(1)
            .startPosY(1)
            .startPosZ(1)
            .plugboardConnections(connections)
            .build();

    ScramblerService scramblerService = new ScramblerService();

    @Test
    void testScrambler() {
        String input = getBigString();
        String scrambled = scramblerService.operate(input, settings);
        String unScrambled = scramblerService.operate(scrambled, settings);
        assertEquals(input, unScrambled);
    }

    @Test
    void testScramblerInvalidInput() {
        String scrambled = scramblerService.operate("12345", settings);
        assertEquals("invalid input", scrambled);
    }

    @Test
    void testScramblerInvalidSettings() {
        ReflectionTestUtils.setField(settings, "startPosX", 100);
        String scrambled = scramblerService.operate("HELLO", settings);

        assertEquals("invalid settings", scrambled);

        ReflectionTestUtils.setField(settings, "rotorIdentifierX", null);
        scrambled = scramblerService.operate("HELLO", settings);

        assertEquals("invalid settings", scrambled);
    }

    private String getBigString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<100000; i++) {
            sb.append((char)('A' + new java.util.Random().nextInt(26)));
        }
        return sb.toString();
    }
}