package com.ihit.lab4.serviceimpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ihit.lab4.dao.CustomerDetailRepo;
import com.ihit.lab4.dto.CustomerDetailDTO;
import com.ihit.lab4.entity.CustomerDetail;
import com.ihit.lab4.mapper.CustomerDetailMapper;
import com.ihit.lab4.service.CustomerDetailService;

import jakarta.transaction.Transactional;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {

    private final CustomerDetailRepo repo;
    private final CustomerDetailMapper mapper;

    public CustomerDetailServiceImpl(CustomerDetailRepo repo, CustomerDetailMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Page<CustomerDetailDTO> getPage(int page,int page_sz) {
        return repo.findByCrudFlagNot("D",PageRequest.of(page, page_sz)).map(mapper::toDTO);
    }

public Page<CustomerDetailDTO> findPage(int page,int page_sz) {

    PageRequest pageable = PageRequest.of(page-1, page_sz);
    Page<CustomerDetail> ret = repo.findActive("D", pageable);

    return ret.map(mapper::toDTO);
}

    public CustomerDetailDTO create(CustomerDetailDTO dto) {
        CustomerDetail e = mapper.toEntity(dto);
        CustomerDetail saved = repo.save(e);
        e.setCrudFlag("C");
        return mapper.toDTO(saved);
    }

    @Override
public CustomerDetailDTO getById(Long id){

    var entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    if ("D".equals(entity.getCrudFlag())) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record deleted");
    }

    return mapper.toDTO(entity);
}

    @Override
@Transactional
    public CustomerDetailDTO update(Long id, CustomerDetailDTO dto) {

    CustomerDetail entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if ("D".equals(entity.getCrudFlag())){
            throw new RuntimeException("Cannot update deleted record");
        }
    entity.setCustomer_gender(dto.getGender());
    entity.setCustomer_type(dto.getType());
    entity.setCustomer_dob(dto.getDob());
    entity.setCustomer_lang(dto.getLang());
    entity.setCustomer_status(dto.getStatus());
    entity.setCustomer_country(dto.getCountry());
    entity.setCrudFlag("U");
    return mapper.toDTO(repo.save(entity));
}


@Transactional
    public void delete(Long id) {
        var e = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
if ("D".equals(e.getCrudFlag())) {
    return;
}
        e.setCrudFlag("D");
    }
}
