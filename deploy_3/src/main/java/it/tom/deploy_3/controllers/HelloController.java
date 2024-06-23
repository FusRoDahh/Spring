package it.tom.deploy_3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private Environment environment;

    @GetMapping
    public String greetings() {
        return "Hello from " + environment.getProperty("myCustomVarTree.devName") + ". They told me to show you this: " +
                environment.getProperty("myCustomVarTree.authCode");
    }
}
