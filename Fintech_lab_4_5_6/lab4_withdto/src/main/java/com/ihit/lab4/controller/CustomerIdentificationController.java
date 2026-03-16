package com.ihit.lab4.controller;

import org.springframework.web.bind.annotation.*;
import com.ihit.lab4.service.CustomerIdentificationService;
import com.ihit.lab4.dto.CustomerIdentificationDTO;

@RestController
@RequestMapping("/api/identification")
public class CustomerIdentificationController {

    private final CustomerIdentificationService service;

    public CustomerIdentificationController(CustomerIdentificationService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerIdentificationDTO create(@RequestBody CustomerIdentificationDTO dto){
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public CustomerIdentificationDTO get(@PathVariable Long id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public CustomerIdentificationDTO update(@PathVariable Long id,@RequestBody CustomerIdentificationDTO dto){
        return service.update(id,dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
