package it.tom.crud.controllers;

import it.tom.crud.entities.Car;
import it.tom.crud.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @PostMapping
    public Car postCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @GetMapping("/list")
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable Long id){
        if(carRepository.existsById(id)) {
            return carRepository.findById(id).get();
        } else {
            Car nullCar = new Car();
            nullCar.setId(id);
            return nullCar;
        }
    }

    @PatchMapping("/{id}")
    public Car changeCarType(@PathVariable Long id, @RequestParam String type){
        if(carRepository.existsById(id)) {
            Car changed = carRepository.findById(id).get();
            changed.setId(id);
            changed.setType(type);
            return carRepository.save(changed);
        } else {
            Car nullCar = new Car();
            nullCar.setId(id);
            return nullCar;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCarById(@PathVariable Long id){
        carRepository.deleteById(id);

    }

    @DeleteMapping("/all")
    public void deleteAllCars(){
        carRepository.deleteAll();
    }


}
