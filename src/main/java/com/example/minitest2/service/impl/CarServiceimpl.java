package com.example.minitest2.service.impl;

import com.example.minitest2.model.Car;
import com.example.minitest2.repository.CarRepository;
import com.example.minitest2.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
@Transactional
public class CarServiceimpl  implements ICarService {
    private final CarRepository carRepository;

    @Autowired
    public CarServiceimpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void saveCar(Car car) {
        if (carRepository.existsByCode(car.getCode())) {
            throw new IllegalArgumentException("Mã số biển đã tồn tại");
        }
        carRepository.save(car);
    }

    @Override
    public void save(Car car) {

    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Iterable<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Page<Car> findAllPageAndSort(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    @Override
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Page<Car> findByNameContainingPageAndSort(String name , Pageable pageable) {
        return carRepository.findByNameContaining(name , pageable);
    }
}
