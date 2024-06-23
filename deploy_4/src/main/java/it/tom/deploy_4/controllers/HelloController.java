package it.tom.deploy_4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private Environment environment;

    @GetMapping
    public String message(){
        return environment.getProperty("myCustomVarTree.welcomeMsg");
    }
}
