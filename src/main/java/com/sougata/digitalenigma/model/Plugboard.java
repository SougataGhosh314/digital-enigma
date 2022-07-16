package com.sougata.digitalenigma.model;

import com.sougata.digitalenigma.enums.Letter;
import com.sougata.digitalenigma.model.dto.PlugboardConnection;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.List;

public final class Plugboard {
    private Plugboard() {}

    static BidiMap<Letter, Letter> plugboardMappings = new DualHashBidiMap<>();

    public static boolean configure(List<PlugboardConnection> plugboardConnections) {
        if (isSettingValid(plugboardConnections)) {
            plugboardMappings.clear();
            for (PlugboardConnection connection: plugboardConnections) {
                plugboardMappings.put(connection.getInput(), connection.getOutput());
            }
            return true;
        }
        return false;
    }

    private static boolean isSettingValid(List<PlugboardConnection> plugboardConnections) {
        if (plugboardConnections.size() > 10)
            return false;
        for (int i=0; i<plugboardConnections.size(); i++) {
            for (int j=0; j<plugboardConnections.size(); j++) {
                if (
                        (i!=j) &&
                        (
                            plugboardConnections.get(i).getInput() == plugboardConnections.get(j).getInput() ||
                            plugboardConnections.get(i).getOutput() == plugboardConnections.get(j).getOutput()
                        )
                ) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Letter transform(Letter input) {
        if (plugboardMappings.containsKey(input))
            return plugboardMappings.get(input);
        if (plugboardMappings.containsValue(input))
            return plugboardMappings.getKey(input);
        return input;
    }
}
