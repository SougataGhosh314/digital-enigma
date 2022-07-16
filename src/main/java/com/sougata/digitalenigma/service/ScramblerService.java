package com.sougata.digitalenigma.service;

import com.sougata.digitalenigma.enums.Letter;
import com.sougata.digitalenigma.model.*;
import com.sougata.digitalenigma.model.dto.ScramblerLetterWrapper;
import com.sougata.digitalenigma.util.RotorUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScramblerService {
    public String operate(String input, Settings settings) {
        if (!input.matches("[a-zA-Z]+")) {
            return "invalid input";
        }

        List<Letter> inputLetterList = new ArrayList<>();
        String inputInCaps = input.toUpperCase();
        for (int i=0; i<inputInCaps.length(); i++) {
            String stringifiedCharacter = Character.toString(inputInCaps.charAt(i));
            Letter letter = Letter.valueOf(stringifiedCharacter);
            inputLetterList.add(letter);
        }

        boolean isEnigmaSet = false;

        boolean isRotorXIdentifierValid = settings.getRotorIdentifierX() != null;
        boolean isRotorYIdentifierValid = settings.getRotorIdentifierY() != null;
        boolean isRotorZIdentifierValid = settings.getRotorIdentifierZ() != null;

        if (!isRotorXIdentifierValid || !isRotorYIdentifierValid || !isRotorZIdentifierValid)
            return "invalid settings";

        Rotor rotorX = RotorPack.pack.get(settings.getRotorIdentifierX());
        Rotor rotorY = RotorPack.pack.get(settings.getRotorIdentifierY());
        Rotor rotorZ = RotorPack.pack.get(settings.getRotorIdentifierZ());

        boolean isRotorXSet = rotorX.setStartingPosition(settings.getStartPosX());
        boolean isRotorYSet = rotorY.setStartingPosition(settings.getStartPosY());
        boolean isRotorZSet = rotorZ.setStartingPosition(settings.getStartPosZ());

        boolean isPlugboardSet = Plugboard.configure(settings.getPlugboardConnections());

        if (isRotorXSet && isRotorYSet && isRotorZSet && isPlugboardSet) {
            isEnigmaSet = true;
        }

        if (!isEnigmaSet)
            return "invalid settings";

        Enigma enigma = Enigma.builder()
                .rotorX(rotorX)
                .rotorY(rotorY)
                .rotorZ(rotorZ)
                .build();

        List<Letter> outputLetterList = new ArrayList<>();

        Enigma changingEnigma = enigma;
        for (Letter letter: inputLetterList) {
            ScramblerLetterWrapper wrapperInput = ScramblerLetterWrapper.builder()
                    .letter(letter)
                    .enigma(changingEnigma)
                    .build();
            ScramblerLetterWrapper wrapperOutput = getTransformedWrapper(wrapperInput);
            outputLetterList.add(wrapperOutput.getLetter());
            changingEnigma = wrapperOutput.getEnigma();
        }

        String output = "";
        for (Letter letter: outputLetterList) {
            output = output.concat(letter.name());
        }

        return output;
    }

    private ScramblerLetterWrapper getTransformedWrapper(ScramblerLetterWrapper wrapper) {
        Letter letter = wrapper.getLetter();
        Enigma enigma = wrapper.getEnigma();

        Letter plugboardOutputFirstLeg = Plugboard.transform(letter);
        // first leg
        Letter rotorXOutputFirstLeg = enigma.getRotorX().getMappingFor(plugboardOutputFirstLeg);
        Letter inputToRotorYFirstLeg = RotorUtil.inputToNextRotor(
                rotorXOutputFirstLeg, enigma.getRotorX(), enigma.getRotorY()
        );
        Letter rotorYOutputFirstLeg = enigma.getRotorY().getMappingFor(inputToRotorYFirstLeg);
        Letter inputToRotorZFirstLeg = RotorUtil.inputToNextRotor(
                rotorYOutputFirstLeg, enigma.getRotorY(), enigma.getRotorZ()
        );
        Letter rotorZOutputFirstLeg = enigma.getRotorZ().getMappingFor(inputToRotorZFirstLeg);

        Letter inputToReflector = RotorUtil.inputToReflector(rotorZOutputFirstLeg, enigma.rotorZ);
        Letter outputOfReflector = Reflector.getReflection(inputToReflector);
        // second leg
        Letter inputToRotorZSecondLeg = RotorUtil.inputToRotorZSecondLeg(outputOfReflector, enigma.rotorZ);

        Letter rotorZOutputSecondLeg = enigma.getRotorZ().getMappingFor(inputToRotorZSecondLeg);
        Letter inputToRotorYSecondLeg = RotorUtil.inputToNextRotor(
                rotorZOutputSecondLeg, enigma.getRotorZ(), enigma.getRotorY()
        );
        Letter rotorYOutputSecondLeg = enigma.getRotorY().getMappingFor(inputToRotorYSecondLeg);
        Letter inputToRotorXSecondLeg = RotorUtil.inputToNextRotor(
                rotorYOutputSecondLeg, enigma.getRotorY(), enigma.getRotorX()
        );
        Letter rotorXOutputSecondLeg = enigma.getRotorX().getMappingFor(inputToRotorXSecondLeg);

        Letter plugboardOutputSecondLeg = Plugboard.transform(rotorXOutputSecondLeg);

        moveRotors(enigma);

        return ScramblerLetterWrapper.builder()
                .letter(plugboardOutputSecondLeg)
                .enigma(enigma)
                .build();
    }

    private void moveRotors(Enigma enigma) {
        Rotor rotorX = enigma.getRotorX();
        Rotor rotorY = enigma.getRotorY();
        Rotor rotorZ = enigma.getRotorZ();
        boolean isCycleCompleteX = false;
        boolean isCycleCompleteY = false;

        isCycleCompleteX = rotorX.incrementStartingPosition();
        if (isCycleCompleteX) {
            isCycleCompleteY = rotorY.incrementStartingPosition();
        }
        if (isCycleCompleteY) {
            rotorZ.incrementStartingPosition();
        }
        enigma.setRotorX(rotorX);
        enigma.setRotorY(rotorY);
        enigma.setRotorZ(rotorZ);
    }
}
