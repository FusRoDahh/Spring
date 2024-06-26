package com.example.Middleware_Es4.controllers;

import com.example.Middleware_Es4.entities.Flight;
import com.example.Middleware_Es4.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/provision{n}")
    public List<Flight> createFlightWithN(@PathVariable int n){
    return flightService.createWithN(n);
    }
    @GetMapping("/pagedlist")
    public Page<Flight> getAll(@RequestParam int page, @RequestParam int size){
        return  flightService.getAllPaged(page, size);
    }
    @GetMapping("ontime")
    public List<Flight> getAllONTIME(){
        return flightService.getAllONTIME();
    }

    @GetMapping("/status")
    public List<Flight> getFlightsByStatus(@RequestParam String p1, @RequestParam String p2) {
        return flightService.getFlightsByStatus(p1, p2);
    }

}
