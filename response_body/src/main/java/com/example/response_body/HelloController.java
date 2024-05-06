package com.example.response_body;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2")
public class HelloController {

    @RequestMapping(method = RequestMethod.GET, path = "/ciao/{provincia}")
    public User hello(
            @PathVariable String provincia,
            @RequestParam String nome
    ) {
        return new User(nome, provincia);
    }
}
