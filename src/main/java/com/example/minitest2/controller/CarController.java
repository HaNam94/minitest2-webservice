package com.example.minitest2.controller;

import com.example.minitest2.model.Car;
import com.example.minitest2.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@RequestMapping("/cars")
@CrossOrigin("*")
public class CarController {
    private final ICarService carService;

    @Autowired
    public CarController(ICarService carService) {
        this.carService = carService;
    }

    @GetMapping("")
    public ResponseEntity<Page<Car>> getAll(@PageableDefault Pageable pageable) {
        Page<Car> car = carService.findAllPageAndSort(pageable);
        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(car,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Page<Car>> createProduct(@RequestBody Car car) {
        carService.save(car);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateProduct(@PathVariable Long id, @RequestBody Car car) {
        Optional<Car> car1 = carService.findById(id);
        if (car1.isPresent()) {
            car.setId(id);
            carService.save(car);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Car> car = carService.findById(id);
        if (car.isPresent()) {
            carService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Car>> search(@RequestParam String name , @PageableDefault(value = 5) Pageable pageable) {
        Page<Car> car = carService.findByNameContainingPageAndSort(name,pageable);
        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(car,HttpStatus.OK);
    }
}