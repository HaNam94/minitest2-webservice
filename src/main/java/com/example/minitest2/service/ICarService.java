package com.example.minitest2.service;

import com.example.minitest2.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICarService extends  com.example.minitest2.service.IService<Car>{
    Page<Car> findByNameContainingPageAndSort(String name , Pageable pageable);

    void saveCar(Car car);

    Page<Car> findAllPageAndSort(Pageable pageable);
}
