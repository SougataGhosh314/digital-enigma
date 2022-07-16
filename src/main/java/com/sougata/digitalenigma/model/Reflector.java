package com.sougata.digitalenigma.model;

import com.sougata.digitalenigma.enums.Letter;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.Arrays;
import java.util.List;

public final class Reflector {
    private Reflector() {}
    
    private static final BidiMap<Letter, Letter> reflectorMap = new DualHashBidiMap<>();

    public static Letter getReflection(Letter input) {
        if (reflectorMap.containsKey(input))
            return reflectorMap.get(input);
        if (reflectorMap.containsValue(input))
            return reflectorMap.getKey(input);
        return null;
    }

    static {
        List<Letter> reflectorMapsequence = Arrays.asList(
                Letter.T, Letter.R, Letter.Y, Letter.S, Letter.V, Letter.U,
                Letter.O, Letter.N, Letter.P, Letter.Z, Letter.Q, Letter.W, Letter.X
        );
        configureReflector(reflectorMapsequence);
    }

    private static void configureReflector(List<Letter> reflectorMapequence) {
        reflectorMap.put(Letter.A, reflectorMapequence.get(0));
        reflectorMap.put(Letter.B, reflectorMapequence.get(1));
        reflectorMap.put(Letter.C, reflectorMapequence.get(2));
        reflectorMap.put(Letter.D, reflectorMapequence.get(3));
        reflectorMap.put(Letter.E, reflectorMapequence.get(4));
        reflectorMap.put(Letter.F, reflectorMapequence.get(5));
        reflectorMap.put(Letter.G, reflectorMapequence.get(6));
        reflectorMap.put(Letter.H, reflectorMapequence.get(7));
        reflectorMap.put(Letter.I, reflectorMapequence.get(8));
        reflectorMap.put(Letter.J, reflectorMapequence.get(9));
        reflectorMap.put(Letter.K, reflectorMapequence.get(10));
        reflectorMap.put(Letter.L, reflectorMapequence.get(11));
        reflectorMap.put(Letter.M, reflectorMapequence.get(12));
    }
}
