package com.example.minitest2.service;

import com.example.minitest2.model.DTO.ProducerDTO;
import com.example.minitest2.model.Producer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProducerService extends IService{
    Producer save(Producer producer);

    Page<ProducerDTO> findQuantityInProducerByIdProducer(Pageable pageable);
}
