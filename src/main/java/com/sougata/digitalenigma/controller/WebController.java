package com.sougata.digitalenigma.controller;

import com.sougata.digitalenigma.model.Settings;
import com.sougata.digitalenigma.service.ScramblerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    ScramblerService scramblerService;

    @PostMapping(path = "/transform")
    public String getTransformedMessage(@RequestParam String input, @RequestBody Settings settings) {
        return scramblerService.operate(input, settings);
    }
}
