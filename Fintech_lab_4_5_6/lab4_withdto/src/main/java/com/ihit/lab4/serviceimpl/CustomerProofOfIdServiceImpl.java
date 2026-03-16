package com.ihit.lab4.serviceimpl;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import jakarta.transaction.Transactional;

import com.ihit.lab4.dto.*;
import com.ihit.lab4.mapper.*;
import com.ihit.lab4.dao.*;
import com.ihit.lab4.entity.*;
import com.ihit.lab4.service.CustomerProofOfIdService;
import org.springframework.stereotype.*;


@Service
public class CustomerProofOfIdServiceImpl implements CustomerProofOfIdService {

    private final CustomerProofOfIdRepo repo;
    private final CustomerDetailRepo detailRepo;
    private final CustomerProofOfIdMapper mapper;


    public CustomerProofOfIdServiceImpl(CustomerProofOfIdRepo repo,
                                        CustomerDetailRepo detailRepo,
                                        CustomerProofOfIdMapper mapper) {
        this.repo = repo;
        this.detailRepo = detailRepo;
        this.mapper = mapper;
    }

    public CustomerProofOfIdDTO create(CustomerProofOfIdDTO dto){
        var e = mapper.toEntity(dto);
        e.setCustomer(detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        e.setCrud_flag("C");
        return mapper.toDTO(repo.save(e));
    }

    public CustomerProofOfIdDTO getById(Long id){
        var e = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if ("D".equals(e.getCrud_flag())){
            throw new RuntimeException("Cannot get deleted record");
        }
        return mapper.toDTO(repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @Override
    @Transactional
public CustomerProofOfIdDTO update(Long id, CustomerProofOfIdDTO dto) {

    CustomerProofOfId entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    if ("D".equals(entity.getCrud_flag())){
        throw new RuntimeException("Cannot update deleted record");
    }
    entity.setCustomer_proof_of_id_type(dto.getType());
    entity.setCustomer_proof_of_id_value(dto.getValue());

    entity.setCustomer(
            detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    
    entity.setEffective_date(dto.getEffectiveDate());
    entity.setStart_date(dto.getStartDate());
    entity.setEnd_date(dto.getEndDate());
    entity.setCrud_flag("U");
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

