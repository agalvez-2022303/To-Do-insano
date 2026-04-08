package com.albertogalvez.ProgramaConIA.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "La aplicación funciona correctamente!";
    }

    @GetMapping("/hola")
    public String hola() {
        return "Hola Mundo desde Spring Boot!";
    }
}