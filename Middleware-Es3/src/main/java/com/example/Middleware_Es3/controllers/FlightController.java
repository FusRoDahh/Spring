package com.example.Middleware_Es3.controllers;

import com.example.Middleware_Es3.entities.Flight;
import com.example.Middleware_Es3.repositories.FlightRepository;
import com.example.Middleware_Es3.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/provision")
    public List<Flight> createFlight(){
        return  flightService.create();

    }
    @GetMapping("/list")
    public List<Flight> getAll(){
        return  flightService.getAll();
    }
}
