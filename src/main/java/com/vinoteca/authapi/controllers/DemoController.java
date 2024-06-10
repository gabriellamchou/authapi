package com.vinoteca.authapi.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @PostMapping("")
    public String welcome() {
        return "Esto es un endpoint protegido";
    }
    
}
