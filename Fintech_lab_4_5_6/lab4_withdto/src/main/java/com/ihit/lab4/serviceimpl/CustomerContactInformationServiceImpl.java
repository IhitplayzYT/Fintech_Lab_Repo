package com.ihit.lab4.serviceimpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import jakarta.transaction.Transactional;

import com.ihit.lab4.dao.CustomerContactInformationRepo;
import com.ihit.lab4.dao.CustomerDetailRepo;
import com.ihit.lab4.dto.CustomerContactInformationDTO;
import com.ihit.lab4.entity.CustomerContactInformation;
import com.ihit.lab4.mapper.CustomerContactInformationMapper;
import com.ihit.lab4.service.CustomerContactInformationService;

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

    public CustomerContactInformationDTO create(CustomerContactInformationDTO dto){
        var e = mapper.toEntity(dto);
        e.setCustomer(detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        e.setCrud_flag("C");
        return mapper.toDTO(repo.save(e));
    }

    public CustomerContactInformationDTO getById(Long id){
        var entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if ("D".equals(entity.getCrud_flag())) {
            throw new RuntimeException("Cannot get deleted record");
        }
        return mapper.toDTO(entity);
    }

@Override
@Transactional
public CustomerContactInformationDTO update(Long id, CustomerContactInformationDTO dto) {

    CustomerContactInformation entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    if ("D".equals(entity.getCrud_flag())){
        throw new RuntimeException("Cannot Update Deleted Record");

    }

    entity.setCustomer_contact_type(dto.getType());
    entity.setCustomer_contact_value(dto.getValue());
    entity.setEffective_date(dto.getEffectiveDate());
    entity.setEnd_date(dto.getEndDate());
    entity.setStart_date(dto.getStartDate());
    entity.setCustomer(
            detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    entity.setCrud_flag("U");
    return mapper.toDTO(repo.save(entity));
}



@Transactional
    public void delete(Long id){ 
        var entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if ("D".equals(entity.getCrud_flag())) {
throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "Record already deleted"
);
        }
        var x = entity.getCustomer();
        if (x != null && !"D".equals(x.getCrud_flag())) x.setCrud_flag("D");
        entity.setCrud_flag("D");
     }
}

