package it.tom.deploy_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private Environment environment;

    @GetMapping
    public String devName(){
        return environment.getProperty("myCustomVarTree.devName");
    }
}
