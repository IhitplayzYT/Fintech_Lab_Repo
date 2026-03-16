package com.ihit.lab4.controller;

import org.springframework.web.bind.annotation.*;
import com.ihit.lab4.service.CustomerIdentificationService;
import com.ihit.lab4.dto.CustomerIdentificationDTO;

import org.springframework.data.domain.Page;
@RestController
@RequestMapping("/api/identification")
@CrossOrigin(origins = "http://localhost:4200")

public class CustomerIdentificationController {

    private final CustomerIdentificationService service;

    public CustomerIdentificationController(CustomerIdentificationService service) {
        this.service = service;
    }

    @GetMapping("/page") public Page<CustomerIdentificationDTO> getPage(@RequestParam int page,@RequestParam int size) {return service.getPage(page,size);}
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
