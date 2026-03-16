package com.ihit.lab4.serviceimpl;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.ihit.lab4.dao.*;
import com.ihit.lab4.mapper.CustomerNameMapper;
import com.ihit.lab4.entity.CustomerName;
import com.ihit.lab4.service.CustomerNameService;
import com.ihit.lab4.dto.CustomerNameDTO;

@Service
public class CustomerNameServiceImpl implements CustomerNameService {

    private final CustomerNameRepo repo;
    private final CustomerDetailRepo detailRepo;
    private final CustomerNameMapper mapper;

    public CustomerNameServiceImpl(CustomerNameRepo repo, CustomerDetailRepo detailRepo, CustomerNameMapper mapper) {
        this.repo = repo;
        this.detailRepo = detailRepo;
        this.mapper = mapper;
    }

    public CustomerNameDTO create(CustomerNameDTO dto){
        CustomerName entity = mapper.toEntity(dto);
        entity.setCustomer(detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        entity.setCrud_flag("C");
        return mapper.toDTO(repo.save(entity));
    }

    public CustomerNameDTO getById(Long id){
        var entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if ("D".equals(entity.getCrud_flag())) {
            throw new RuntimeException("Cannot get deleted record");
        }
        return mapper.toDTO(entity);


    }

    @Override
    @Transactional
public CustomerNameDTO update(Long id, CustomerNameDTO dto) {

    CustomerName entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    if ("D".equals(entity.getCrud_flag())){
        throw new RuntimeException("Cannot update deleted record");
    }
    entity.setCrud_flag("U");
    entity.setCustomer_name_type(dto.getType());
    entity.setCustomer_name_value(dto.getValue());

    entity.setCustomer(
            detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    entity.setEffective_date(dto.getEffectiveDate());
    return mapper.toDTO(repo.save(entity));
}

@Transactional
    public void delete(Long id){
        var e = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if ("D".equals(e.getCrud_flag())) {
throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "Record already deleted"
);
        }
        var x = e.getCustomer();
        if (x != null && !"D".equals(e.getCrud_flag())) {
            x.setCrud_flag("D");
        }
        e.setCrud_flag("D");

    }
}
