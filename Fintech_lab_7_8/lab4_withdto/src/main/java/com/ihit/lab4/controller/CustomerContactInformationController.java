package com.ihit.lab4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import com.ihit.lab4.dto.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.ihit.lab4.service.CustomerContactInformationService;
@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:4200")

public class CustomerContactInformationController {

    private final CustomerContactInformationService service;

    public CustomerContactInformationController(CustomerContactInformationService service) {
        this.service = service;
    }

    @GetMapping("/page") public Page<CustomerContactInformationDTO> getPage(@RequestParam int page,@RequestParam int size) {return service.getPage(page,size);}
    @PostMapping public CustomerContactInformationDTO create(@RequestBody CustomerContactInformationDTO d){return service.create(d);}
    @GetMapping("/{id}") public CustomerContactInformationDTO get(@PathVariable Long id){return service.getById(id);}
    @PutMapping("/{id}") public CustomerContactInformationDTO update(@PathVariable Long id,@RequestBody CustomerContactInformationDTO d){return service.update(id,d);}
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){service.delete(id);}
}

