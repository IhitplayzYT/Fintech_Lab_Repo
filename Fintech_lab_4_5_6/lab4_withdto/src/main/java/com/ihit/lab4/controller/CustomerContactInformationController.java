package com.ihit.lab4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ihit.lab4.dto.*;
import com.ihit.lab4.service.CustomerContactInformationService;
@RestController
@RequestMapping("/api/contact")
public class CustomerContactInformationController {

    private final CustomerContactInformationService service;

    public CustomerContactInformationController(CustomerContactInformationService service) {
        this.service = service;
    }

    @PostMapping public CustomerContactInformationDTO create(@RequestBody CustomerContactInformationDTO d){return service.create(d);}
    @GetMapping("/{id}") public CustomerContactInformationDTO get(@PathVariable Long id){return service.getById(id);}
    @PutMapping("/{id}") public CustomerContactInformationDTO update(@PathVariable Long id,@RequestBody CustomerContactInformationDTO d){return service.update(id,d);}
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){service.delete(id);}
}

