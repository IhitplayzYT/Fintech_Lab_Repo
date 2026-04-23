package com.ihit.lab4.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;import com.ihit.lab4.dto.RawQueryRequest;
import java.util.Map;
import java.util.HashMap;
import com.ihit.lab4.dto.CustomerDetailDTO;
import com.ihit.lab4.service.CustomerDetailService;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/detail")
public class CustomerDetailController {

    private final CustomerDetailService service;

    public CustomerDetailController(CustomerDetailService service) {
        this.service = service;
    }

    @GetMapping("/page") public Page<CustomerDetailDTO> getPage(@RequestParam int page,@RequestParam int size) {return service.getPage(page,size);}
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
    @PostMapping("/upload")
      public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
          List<String> errorLogs = service.processFile(file);

          if (errorLogs.isEmpty()) {
          return ResponseEntity.ok("Bulk customers inserted successfully");
          } else {
          return ResponseEntity.status(207).body(errorLogs);
      }
    }
    @PostMapping("/generate/{count}")
    public ResponseEntity<?> generate(@PathVariable int count) {
        service.generateRandom(count);
        return ResponseEntity.ok("Generated " + count + " records");
    }
    @PostMapping("/raw")
    public Map<String, Object> execRaw(@RequestBody RawQueryRequest req) {

        int affected = service.executeRawQuery(req.getQuery());

        Map<String, Object> res = new HashMap<>();
        res.put("rowsAffected", affected);

        return res;
    }

}

