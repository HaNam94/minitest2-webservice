package com.example.minitest2.controller;

import com.example.minitest2.model.DTO.TypeDTO;
import com.example.minitest2.model.Type;
import com.example.minitest2.service.ICarService;
import com.example.minitest2.service.ITypeService;
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
@RequestMapping("/types")
@CrossOrigin("*")
public class TypeController {
    private final ITypeService typeService;

    @Autowired
    public TypeController(ITypeService typeService, ICarService carService) {
        this.typeService = typeService;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Type>> getAllTypes() {
        Iterable<Type> types = typeService.findAll();
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> getTypeById(@PathVariable Long id) {
        Optional<Type> type = typeService.findById(id);
        return type.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable Long id) {
        Optional<Type> type = typeService.findById(id);
        if (type.isPresent()) {
            typeService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<Type> createType(@Validated @RequestBody Type type, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Type savedType = typeService.save(type);
        return new ResponseEntity<>(savedType, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Type> updateType(@PathVariable Long id, @Validated @RequestBody Type type, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Type> existingType = typeService.findById(id);
        if (existingType.isPresent()) {
            type.setId(id);
            Type updatedType = typeService.save(type);
            return new ResponseEntity<>(updatedType, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/statistic")
    public ResponseEntity<Page<TypeDTO>> getStatistic(@PageableDefault(value = 5) Pageable pageable) {
        Page<TypeDTO> statistics = typeService.findQuantityInTypeByIdType(pageable);
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }
}
