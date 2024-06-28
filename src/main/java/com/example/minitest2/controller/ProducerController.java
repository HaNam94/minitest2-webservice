package com.example.minitest2.controller;

import com.example.minitest2.model.DTO.ProducerDTO;
import com.example.minitest2.model.Producer;
import com.example.minitest2.service.IProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/producers")
@CrossOrigin("*")
public class ProducerController {
    private final IProducerService producerService;

    @Autowired
    public ProducerController(IProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Producer>> getAllProducers() {
        Iterable<Producer> producers = producerService.findAll();
        return new ResponseEntity<>(producers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producer> getProducerById(@PathVariable Long id) {
        Optional<Producer> producer = producerService.findById(id);
        return producer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducer(@PathVariable Long id) {
        Optional<Producer> producer = producerService.findById(id);
        if (producer.isPresent()) {
            producerService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<Producer> createProducer(@Validated @RequestBody Producer producer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Producer savedProducer = producerService.save(producer);
        return new ResponseEntity<>(savedProducer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producer> updateProducer(@PathVariable Long id, @Validated @RequestBody Producer producer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Producer> existingProducer = producerService.findById(id);
        if (existingProducer.isPresent()) {
            producer.setId(id);
            Producer updatedProducer = producerService.save(producer);
            return new ResponseEntity<>(updatedProducer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/statistic")
    public ResponseEntity<Page<ProducerDTO>> getStatistic(@PageableDefault(value = 5) Pageable pageable) {
        Page<ProducerDTO> statistics = producerService.findQuantityInProducerByIdProducer(pageable);
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }
}
