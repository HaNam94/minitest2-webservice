package com.example.minitest2.service.impl;

import com.example.minitest2.model.DTO.TypeDTO;
import com.example.minitest2.model.Type;
import com.example.minitest2.repository.TypeRepository;
import com.example.minitest2.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class TypeServiceimpl  implements ITypeService {
    private final TypeRepository typeRepository;

    @Autowired
    public TypeServiceimpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public Type save(Type type) {
        typeRepository.save(type);
        return null;
    }

    @Override
    public void delete(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public Iterable<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Optional<Type> findById(Long id) {
        return typeRepository.findById(id);
    }

    @Override
    public Page<TypeDTO> findQuantityInTypeByIdType(Pageable pageable) {
        return typeRepository.findQuantityInProducerByIdProducer(pageable);
    }
}
