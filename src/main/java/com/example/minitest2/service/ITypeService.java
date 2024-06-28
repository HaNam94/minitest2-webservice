package com.example.minitest2.service;

import com.example.minitest2.model.DTO.TypeDTO;
import com.example.minitest2.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ITypeService {
    Type save(Type type);

    void delete(Long id);

    Iterable<Type> findAll();

    Optional<Type> findById(Long id);

    Page<TypeDTO> findQuantityInTypeByIdType(Pageable pageable);
}
