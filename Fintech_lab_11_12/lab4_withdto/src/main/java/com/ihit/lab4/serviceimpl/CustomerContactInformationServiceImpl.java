package com.ihit.lab4.serviceimpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ihit.lab4.dao.CustomerContactInformationRepo;
import com.ihit.lab4.dao.CustomerDetailRepo;
import com.ihit.lab4.dto.CustomerContactInformationDTO;
import com.ihit.lab4.entity.CustomerContactInformation;
import com.ihit.lab4.mapper.CustomerContactInformationMapper;
import com.ihit.lab4.service.CustomerContactInformationService;

import jakarta.transaction.Transactional;

@Service
public class CustomerContactInformationServiceImpl implements CustomerContactInformationService {

    private final CustomerContactInformationRepo repo;
    private final CustomerDetailRepo detailRepo;
    private final CustomerContactInformationMapper mapper;

    public CustomerContactInformationServiceImpl(CustomerContactInformationRepo repo,
                                                 CustomerDetailRepo detailRepo,
                                                 CustomerContactInformationMapper mapper
                                                ) {
        this.repo = repo;
        this.detailRepo = detailRepo;
        this.mapper = mapper;
    }

    public Page<CustomerContactInformationDTO> getPage(int page,int page_sz) {
        return repo.findByCrudFlagNot("D",PageRequest.of(page, page_sz)).map(mapper::toDTO);
    }
public Page<CustomerContactInformationDTO> findPage(int page,int page_sz) {

    PageRequest pageable = PageRequest.of(page-1, page_sz);
    Page<CustomerContactInformation> ret = repo.findActive("D", pageable);

    return ret.map(mapper::toDTO);
}


    public CustomerContactInformationDTO create(CustomerContactInformationDTO dto){
        var e = mapper.toEntity(dto);
        e.setCustomer(detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        e.setCrudFlag("C");
        return mapper.toDTO(repo.save(e));
    }

@Override
public CustomerContactInformationDTO getById(Long id){

    var entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    if ("D".equals(entity.getCrudFlag())) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record deleted");
    }

    return mapper.toDTO(entity);
}

@Override
@Transactional
public CustomerContactInformationDTO update(Long id, CustomerContactInformationDTO dto) {

    CustomerContactInformation entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    if ("D".equals(entity.getCrudFlag())){
        throw new RuntimeException("Cannot Update Deleted Record");

    }

    entity.setCustomer_contact_type(dto.getType());
    entity.setCustomer_contact_value(dto.getValue());
    entity.setEffective_date(dto.getEffectiveDate());
    entity.setEnd_date(dto.getEndDate());
    entity.setStart_date(dto.getStartDate());
    entity.setCustomer(
            detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    entity.setCrudFlag("U");
    return mapper.toDTO(repo.save(entity));
}



@Transactional
    public void delete(Long id){ 
        var entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
if ("D".equals(entity.getCrudFlag())) {
    return;
}

        var x = entity.getCustomer();
        if (x != null && !"D".equals(x.getCrudFlag())) x.setCrudFlag("D");
        entity.setCrudFlag("D");
     }
}

