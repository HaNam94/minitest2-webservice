package com.example.minitest2.repository;

import com.example.minitest2.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {
    boolean existsByCode(String code);
    Page<Car> findAll(Pageable pageable);
    Page<Car> findByNameContaining(String name, Pageable pageable);
}
