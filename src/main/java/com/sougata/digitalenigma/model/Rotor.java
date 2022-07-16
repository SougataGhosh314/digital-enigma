package com.sougata.digitalenigma.model;

import com.sougata.digitalenigma.enums.Letter;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

public class Rotor {
    Integer startingPosition;

    public boolean setStartingPosition(Integer startingPosition) {
        if (startingPosition > 0 && startingPosition < 27) {
            this.startingPosition = startingPosition;
            return true;
        }
        return false;
    }

    public Integer getStartingPosition() {
        return this.startingPosition;
    }

    public boolean incrementStartingPosition() {
        // increments startPos and returns true if cycle is complete
        if (startingPosition == 26) {
            this.startingPosition = 1;
            return true;
        }
        else {
            this.startingPosition++;
            return false;
        }
    }

    BidiMap<Letter, Letter> mappings = new DualHashBidiMap<>();

    public void addMapping(Letter input, Letter output) {
        int size = mappings.size();
        if (size < 26) {
            mappings.put(input, output);
        }
    }

    public Letter getMappingFor(Letter input) {
        if (mappings.containsKey(input))
            return mappings.get(input);
        if (mappings.containsValue(input))
            return mappings.getKey(input);
        return input;
    }
}
