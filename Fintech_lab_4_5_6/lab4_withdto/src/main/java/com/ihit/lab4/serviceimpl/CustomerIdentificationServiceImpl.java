package com.ihit.lab4.serviceimpl;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.ihit.lab4.dao.CustomerIdentificationRepo;
import com.ihit.lab4.dao.CustomerDetailRepo;
import com.ihit.lab4.mapper.CustomerIdentificationMapper;
import com.ihit.lab4.entity.CustomerIdentification;
import com.ihit.lab4.dto.CustomerIdentificationDTO;
import com.ihit.lab4.service.CustomerIdentificationService;

@Service
public class CustomerIdentificationServiceImpl implements CustomerIdentificationService {

    private final CustomerIdentificationRepo repo;
    private final CustomerDetailRepo detailRepo;
    private final CustomerIdentificationMapper mapper;

    public CustomerIdentificationServiceImpl(CustomerIdentificationRepo repo,
                                             CustomerDetailRepo detailRepo,
                                             CustomerIdentificationMapper mapper) {
        this.repo = repo;
        this.detailRepo = detailRepo;
        this.mapper = mapper;
    }
    public CustomerIdentificationDTO create(CustomerIdentificationDTO dto) {
        CustomerIdentification entity = mapper.toEntity(dto);
        entity.setCustomer(detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        entity.setCrud_flag("C");
        return mapper.toDTO(repo.save(entity));
    }

    public CustomerIdentificationDTO getById(Long id) {
        var entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if ("D".equals(entity.getCrud_flag())) {
            throw new RuntimeException("Cannot get deleted record");
        }
        return mapper.toDTO(entity);


    }

@Override
@Transactional
public CustomerIdentificationDTO update(Long id, CustomerIdentificationDTO dto) {

    CustomerIdentification entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    if ("D".equals(entity.getCrud_flag())){
        throw new RuntimeException("Cannot update deleted record");
    }
    entity.setCustomer_identification_type(dto.getType());
    entity.setCustomer_identification_item(dto.getItem());
    entity.setCustomer(
            detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    entity.setEffective_Date(dto.getEffectiveDate());
    entity.setCrud_flag("U");
    return mapper.toDTO(repo.save(entity));
}

@Transactional
    public void delete(Long id) {
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
