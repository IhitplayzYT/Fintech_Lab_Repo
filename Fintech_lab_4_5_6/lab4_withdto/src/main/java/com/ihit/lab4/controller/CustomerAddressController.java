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
import com.ihit.lab4.service.CustomerAddressService;

@RestController
@RequestMapping("/api/address")
public class CustomerAddressController {

    private final CustomerAddressService service;

    public CustomerAddressController(CustomerAddressService service) {
        this.service = service;
    }

    @PostMapping public CustomerAddressDTO create(@RequestBody CustomerAddressDTO d){return service.create(d);}
    @GetMapping("/{id}") public CustomerAddressDTO get(@PathVariable Long id){return service.getById(id);}
    @PutMapping("/{id}") public CustomerAddressDTO update(@PathVariable Long id,@RequestBody CustomerAddressDTO d){return service.update(id,d);}
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){service.delete(id);}
}
