package com.ihit.lab4.serviceimpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ihit.lab4.dao.CustomerDetailRepo;
import com.ihit.lab4.dao.CustomerNameRepo;
import com.ihit.lab4.dto.CustomerNameDTO;
import com.ihit.lab4.entity.CustomerName;
import com.ihit.lab4.mapper.CustomerNameMapper;
import com.ihit.lab4.service.CustomerNameService;

import jakarta.transaction.Transactional;
@Service
public class CustomerNameServiceImpl implements CustomerNameService {

    private final CustomerNameRepo repo;
    private final CustomerDetailRepo detailRepo;
    private final CustomerNameMapper mapper;

    public Page<CustomerNameDTO> getPage(int page,int page_sz) {
        return repo.findByCrudFlagNot("D",PageRequest.of(page, page_sz)).map(mapper::toDTO);
    }

public Page<CustomerNameDTO> findPage(int page,int page_sz) {

    PageRequest pageable = PageRequest.of(page-1, page_sz);
    Page<CustomerName> ret = repo.findActive("D", pageable);

    return ret.map(mapper::toDTO);
}
    
    public CustomerNameServiceImpl(CustomerNameRepo repo, CustomerDetailRepo detailRepo, CustomerNameMapper mapper) {
        this.repo = repo;
        this.detailRepo = detailRepo;
        this.mapper = mapper;
    }

    public CustomerNameDTO create(CustomerNameDTO dto){
        CustomerName entity = mapper.toEntity(dto);
        entity.setCustomer(detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        entity.setCrudFlag("C");
        return mapper.toDTO(repo.save(entity));
    }

@Override
public CustomerNameDTO getById(Long id){

    var entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    if ("D".equals(entity.getCrudFlag())) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record deleted");
    }

    return mapper.toDTO(entity);
}

    @Override
    @Transactional
public CustomerNameDTO update(Long id, CustomerNameDTO dto) {

    CustomerName entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    if ("D".equals(entity.getCrudFlag())){
        throw new RuntimeException("Cannot update deleted record");
    }
    entity.setCrudFlag("U");
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
