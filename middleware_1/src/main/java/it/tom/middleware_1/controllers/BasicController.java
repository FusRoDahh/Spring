package it.tom.middleware_1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/time")
public class BasicController {

    @GetMapping
    public Date returnDate() {
        return new Date();
    }
}
