package com.example.minitest2.service.impl;

import com.example.minitest2.model.DTO.ProducerDTO;
import com.example.minitest2.model.Producer;
import com.example.minitest2.repository.ProducerRepository;
import com.example.minitest2.service.IProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
@Transactional
public class ProducerServiceimpl implements IProducerService {
    private ProducerRepository producerRepository;

    @Autowired
    public void ProducerServiceImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public Producer save(Producer producer) {
        producerRepository.save(producer);
        return producer;
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void delete(Long id) {
        producerRepository.deleteById(id);
    }

    @Override
    public Iterable<Producer> findAll() {
        return producerRepository.findAll();
    }

    @Override
    public Optional<Producer> findById(Long id) {
        return producerRepository.findById(id);
    }

    @Override
    public Page<ProducerDTO> findQuantityInProducerByIdProducer(Pageable pageable) {
        return producerRepository.findQuantityInProducerByIdProducer(pageable);
    }
}
