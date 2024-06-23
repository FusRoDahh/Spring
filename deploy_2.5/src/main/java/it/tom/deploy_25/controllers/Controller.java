package it.tom.deploy_25.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping
    public String sum(@RequestParam int num1, @RequestParam int num2) {
        int sum = num1 + num2;
        return "La somma dei due numeri è: " + sum;
    }

}
