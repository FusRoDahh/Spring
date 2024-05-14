package it.tom.controller.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameController {

    @GetMapping()
    public String get(@RequestParam String nome) {
        return nome;
    }

    @PostMapping()
    public StringBuilder post(@RequestParam StringBuilder nome) {
        return nome.reverse();
    }
}
