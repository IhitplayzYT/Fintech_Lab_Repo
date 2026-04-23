package com.ihit.lab4.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.data.domain.Page;
import com.ihit.lab4.dto.CustomerAddressDTO;
import com.ihit.lab4.service.CustomerAddressService;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/address")
public class CustomerAddressController {

    private final CustomerAddressService service;

    public CustomerAddressController(CustomerAddressService service) {
        this.service = service;
    }

    @GetMapping("/page") public Page<CustomerAddressDTO> getPage(@RequestParam int page,@RequestParam int size) {return service.getPage(page,size);}
    @PostMapping public CustomerAddressDTO create(@RequestBody CustomerAddressDTO d){return service.create(d);}
    @GetMapping("/{id}") public CustomerAddressDTO get(@PathVariable Long id){return service.getById(id);}
    @PutMapping("/{id}") public CustomerAddressDTO update(@PathVariable Long id,@RequestBody CustomerAddressDTO d){return service.update(id,d);}
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){service.delete(id);}
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        List<String> errorLogs = service.processFile(file);

        if (errorLogs.isEmpty()) {
            return ResponseEntity.ok("Bulk customers inserted successfully");
        } else {
            return ResponseEntity.status(207).body(errorLogs);
        }
    }
}
