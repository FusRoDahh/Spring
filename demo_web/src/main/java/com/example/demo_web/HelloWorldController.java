package com.example.demo_web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;


@RestController
@RequestMapping("/v1")
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.GET, path = "/helloWorld")
    public String helloWorld() {
        return "Hello World!";
    }
}
