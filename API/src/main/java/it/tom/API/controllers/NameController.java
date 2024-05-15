package it.tom.API.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NameController {

    @GetMapping()
    public String get(@RequestParam String nome) {
        return nome;
    }

    @PostMapping()
    public StringBuilder post(@RequestBody String nome) {
        StringBuilder sbName = new StringBuilder(nome);
        return sbName.reverse();
    }
}
