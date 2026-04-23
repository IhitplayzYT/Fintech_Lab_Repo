package com.ihit.lab4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ihit.lab4.dto.*;
import com.ihit.lab4.service.CustomerProofOfIdService;

@RestController
@RequestMapping("/api/proof")
public class CustomerProofOfIdController {

    private final CustomerProofOfIdService service;

    public CustomerProofOfIdController(CustomerProofOfIdService service) {
        this.service = service;
    }

    @PostMapping public CustomerProofOfIdDTO create(@RequestBody CustomerProofOfIdDTO d){return service.create(d);}
    @GetMapping("/{id}") public CustomerProofOfIdDTO get(@PathVariable Long id){return service.getById(id);}
    @PutMapping("/{id}") public CustomerProofOfIdDTO update(@PathVariable Long id,@RequestBody CustomerProofOfIdDTO d){return service.update(id,d);}
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){service.delete(id);}
}



