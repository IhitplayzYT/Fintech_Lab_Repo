package com.ihit.lab4.controller;

import org.springframework.web.bind.annotation.*;
import com.ihit.lab4.service.CustomerDetailService;
import com.ihit.lab4.dto.CustomerDetailDTO;

@RestController
@RequestMapping("/api/detail")
public class CustomerDetailController {

    private final CustomerDetailService service;

    public CustomerDetailController(CustomerDetailService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerDetailDTO create(@RequestBody CustomerDetailDTO dto){
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public CustomerDetailDTO get(@PathVariable Long id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public CustomerDetailDTO update(@PathVariable Long id,@RequestBody CustomerDetailDTO dto){
        return service.update(id,dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}

