package com.example.Middleware_Es3.services;

import com.example.Middleware_Es3.entities.Flight;
import com.example.Middleware_Es3.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> create(){
        List<Flight> flights = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i<50; i++){
            Flight newFlight = new Flight(
                    random.ints(97, 122 + 1)
                            .limit(10)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString(),
                    random.ints(97, 122 + 1)
                            .limit(3)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString(),
                    random.ints(97, 122 + 1)
                            .limit(3)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString(),
                    Flight.Status.ONTIME);
            flights.add(newFlight);
        }
        return flightRepository.saveAll(flights);
    }

    public List<Flight> getAll(){
        return flightRepository.findAll();
    }
}
