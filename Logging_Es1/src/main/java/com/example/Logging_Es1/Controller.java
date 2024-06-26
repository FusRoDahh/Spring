package com.example.Logging_Es1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class Controller {
    Logger logger = LoggerFactory.getLogger(Controller.class);
    @Autowired
    private EsService service;

    @GetMapping
    public String welcome(){
        logger.info("Welcome INFO");
        return "Welcome";
    }
    @GetMapping("/exp")
    public int exp(){
        return service.exp();
    }
    @GetMapping("get-errors")
    public void error(){
        logger.error("Custom Error");
    }
}
