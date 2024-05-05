package com.ifgoiano.biblioteca.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class OlaMundoController {
    
    @GetMapping("/")
    
    public String olaMundo(){
        return "Olá Mundo! Isso no SpringBoot do VSCode";
    }
}
