package com.sougata.digitalenigma.model.dto;

import com.sougata.digitalenigma.enums.Letter;
import com.sougata.digitalenigma.service.Enigma;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ScramblerLetterWrapper {
    private Letter letter;
    private Enigma enigma;
}
