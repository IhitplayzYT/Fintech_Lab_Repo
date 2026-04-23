package com.ihit.lab4.serviceimpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ihit.lab4.dao.CustomerDetailRepo;
import com.ihit.lab4.dao.CustomerIdentificationRepo;
import com.ihit.lab4.dto.CustomerIdentificationDTO;
import com.ihit.lab4.entity.CustomerIdentification;
import com.ihit.lab4.mapper.CustomerIdentificationMapper;
import com.ihit.lab4.service.CustomerIdentificationService;

import jakarta.transaction.Transactional;
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
        entity.setCrudFlag("C");
        return mapper.toDTO(repo.save(entity));
    }

    public Page<CustomerIdentificationDTO> getPage(int page,int page_sz) {
        return repo.findByCrudFlagNot("D",PageRequest.of(page, page_sz)).map(mapper::toDTO);
    }

public Page<CustomerIdentificationDTO> findPage(int page,int page_sz) {

    PageRequest pageable = PageRequest.of(page-1, page_sz);
    Page<CustomerIdentification> ret = repo.findActive("D", pageable);

    return ret.map(mapper::toDTO);
}

@Override
public CustomerIdentificationDTO getById(Long id){

    var entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    if ("D".equals(entity.getCrudFlag())) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record deleted");
    }

    return mapper.toDTO(entity);
}

@Override
@Transactional
public CustomerIdentificationDTO update(Long id, CustomerIdentificationDTO dto) {

    CustomerIdentification entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    if ("D".equals(entity.getCrudFlag())){
        throw new RuntimeException("Cannot update deleted record");
    }
    entity.setCustomer_identification_type(dto.getType());
    entity.setCustomer_identification_item(dto.getItem());
    entity.setCustomer(
            detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    entity.setEffective_Date(dto.getEffectiveDate());
    entity.setCrudFlag("U");
    return mapper.toDTO(repo.save(entity));
}

@Transactional
    public void delete(Long id) {
        var e = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
if ("D".equals(e.getCrudFlag())) {
    return;
}
        var x = e.getCustomer();
        if (x != null && !"D".equals(x.getCrudFlag())) {
            x.setCrudFlag("D");
        }
        e.setCrudFlag("D");
    }
}
