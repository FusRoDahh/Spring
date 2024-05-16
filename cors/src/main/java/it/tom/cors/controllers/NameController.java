package it.tom.cors.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://example.com")
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
