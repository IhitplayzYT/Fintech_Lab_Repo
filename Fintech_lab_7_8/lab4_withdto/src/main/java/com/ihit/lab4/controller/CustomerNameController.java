package com.ihit.lab4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import com.ihit.lab4.service.CustomerNameService;
import com.ihit.lab4.dto.*;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/name")
public class CustomerNameController {

    private final CustomerNameService service;
    

    @GetMapping("/page") public Page<CustomerNameDTO> getPage(@RequestParam int page,@RequestParam int size) {return service.getPage(page,size);}
    public CustomerNameController(CustomerNameService service) {
        this.service = service;
    }
    @PostMapping public CustomerNameDTO create(@RequestBody CustomerNameDTO d){return service.create(d);}
    @GetMapping("/{id}") public CustomerNameDTO get(@PathVariable Long id){return service.getById(id);}
    @PutMapping("/{id}") public CustomerNameDTO update(@PathVariable Long id,@RequestBody CustomerNameDTO d){return service.update(id,d);}
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){service.delete(id);}
}
