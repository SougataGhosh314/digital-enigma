package com.sougata.digitalenigma.util;

import com.sougata.digitalenigma.enums.Letter;
import com.sougata.digitalenigma.model.Rotor;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

public final class RotorUtil {
    private RotorUtil() {}

    static BidiMap<Integer, Letter> integerLetterMap = new DualHashBidiMap<>();

    static {
        integerLetterMap.put(1, Letter.A);
        integerLetterMap.put(2, Letter.B);
        integerLetterMap.put(3, Letter.C);
        integerLetterMap.put(4, Letter.D);
        integerLetterMap.put(5, Letter.E);
        integerLetterMap.put(6, Letter.F);
        integerLetterMap.put(7, Letter.G);
        integerLetterMap.put(8, Letter.H);
        integerLetterMap.put(9, Letter.I);
        integerLetterMap.put(10, Letter.J);
        integerLetterMap.put(11, Letter.K);
        integerLetterMap.put(12, Letter.L);
        integerLetterMap.put(13, Letter.M);
        integerLetterMap.put(14, Letter.N);
        integerLetterMap.put(15, Letter.O);
        integerLetterMap.put(16, Letter.P);
        integerLetterMap.put(17, Letter.Q);
        integerLetterMap.put(18, Letter.R);
        integerLetterMap.put(19, Letter.S);
        integerLetterMap.put(20, Letter.T);
        integerLetterMap.put(21, Letter.U);
        integerLetterMap.put(22, Letter.V);
        integerLetterMap.put(23, Letter.W);
        integerLetterMap.put(24, Letter.X);
        integerLetterMap.put(25, Letter.Y);
        integerLetterMap.put(26, Letter.Z);
    }

    private static Integer numberFor(Letter letter) {
        return integerLetterMap.getKey(letter);
    }

    private static Letter letterFor(Integer integer) {
        return integerLetterMap.get(integer);
    }

    public static Letter inputToNextRotor(Letter outputOfPreviousRotor, Rotor previousRotor, Rotor nextRotor) {
        Integer index = nextRotor.getStartingPosition() + numberFor(outputOfPreviousRotor) - previousRotor.getStartingPosition();
        if (index > 26)
            index = index - 26;
        if (index < 1) {
            index = index + 26;
        }
        return letterFor(index);
    }

    public static Letter inputToReflector(Letter outputOfRotorZ, Rotor rotorZ) {
        Integer index = numberFor(outputOfRotorZ) - rotorZ.getStartingPosition() + 1;
        if (index > 26)
            index = index - 26;
        if (index < 1) {
            index = index + 26;
        }
        return letterFor(index);
    }

    public static Letter inputToRotorZSecondLeg(Letter outputOfReflector, Rotor rotorZ) {
        Integer index = numberFor(outputOfReflector) + rotorZ.getStartingPosition() - 1;
        if (index > 26)
            index = index - 26;
        if (index < 1) {
            index = index + 26;
        }
        return letterFor(index);
    }
}
