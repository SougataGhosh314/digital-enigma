package com.sougata.digitalenigma.model.dto;

import com.sougata.digitalenigma.enums.Letter;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlugboardConnection {
    private Letter input;
    private Letter output;
}
