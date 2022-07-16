package com.sougata.digitalenigma.model;

import com.sougata.digitalenigma.enums.Letter;
import com.sougata.digitalenigma.enums.RotorIdentifier;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class RotorPack {
    private RotorPack() {}

    public static final Map<RotorIdentifier, Rotor> pack = new EnumMap<>(RotorIdentifier.class);

    static  {
        Rotor rotorA = new Rotor();
        List<Letter> rotorMappingSequenceA = Arrays.asList(
                Letter.N, Letter.O, Letter.Y, Letter.Z, Letter.P, Letter.Q,
                Letter.R, Letter.T, Letter.S, Letter.U, Letter.V, Letter.X, Letter.W
        );
        configureRotor(rotorA, rotorMappingSequenceA);

        Rotor rotorB = new Rotor();
        List<Letter> rotorMappingSequenceB = Arrays.asList(
                Letter.R, Letter.Y, Letter.O, Letter.Z, Letter.P, Letter.Q,
                Letter.N, Letter.U, Letter.S, Letter.T, Letter.V, Letter.W, Letter.X
        );
        configureRotor(rotorB, rotorMappingSequenceB);

        Rotor rotorC = new Rotor();
        List<Letter> rotorMappingSequenceC = Arrays.asList(
                Letter.O, Letter.N, Letter.Y, Letter.P, Letter.Z, Letter.Q,
                Letter.T, Letter.R, Letter.S, Letter.V, Letter.U, Letter.X, Letter.W
        );
        configureRotor(rotorC, rotorMappingSequenceC);

        Rotor rotorD = new Rotor();
        List<Letter> rotorMappingSequenceD = Arrays.asList(
                Letter.Y, Letter.O, Letter.N, Letter.Z, Letter.X, Letter.Q,
                Letter.R, Letter.W, Letter.S, Letter.U, Letter.V, Letter.P, Letter.T
        );
        configureRotor(rotorD, rotorMappingSequenceD);

        Rotor rotorE = new Rotor();
        List<Letter> rotorMappingSequenceE = Arrays.asList(
                Letter.N, Letter.S, Letter.Y, Letter.X, Letter.P, Letter.Q,
                Letter.W, Letter.T, Letter.O, Letter.U, Letter.V, Letter.Z, Letter.R
        );
        configureRotor(rotorE, rotorMappingSequenceE);

        pack.put(RotorIdentifier.A, rotorA);
        pack.put(RotorIdentifier.B, rotorB);
        pack.put(RotorIdentifier.C, rotorC);
        pack.put(RotorIdentifier.D, rotorD);
        pack.put(RotorIdentifier.E, rotorE);
    }
    
    private static void configureRotor(Rotor rotor, List<Letter> rotorMappingSequence) {
        rotor.addMapping(Letter.A, rotorMappingSequence.get(0));
        rotor.addMapping(Letter.B, rotorMappingSequence.get(1));
        rotor.addMapping(Letter.C, rotorMappingSequence.get(2));
        rotor.addMapping(Letter.D, rotorMappingSequence.get(3));
        rotor.addMapping(Letter.E, rotorMappingSequence.get(4));
        rotor.addMapping(Letter.F, rotorMappingSequence.get(5));
        rotor.addMapping(Letter.G, rotorMappingSequence.get(6));
        rotor.addMapping(Letter.H, rotorMappingSequence.get(7));
        rotor.addMapping(Letter.I, rotorMappingSequence.get(8));
        rotor.addMapping(Letter.J, rotorMappingSequence.get(9));
        rotor.addMapping(Letter.K, rotorMappingSequence.get(10));
        rotor.addMapping(Letter.L, rotorMappingSequence.get(11));
        rotor.addMapping(Letter.M, rotorMappingSequence.get(12));
    }
}
