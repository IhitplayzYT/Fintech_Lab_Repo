package com.ihit.lab4.serviceimpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ihit.lab4.dao.CustomerDetailRepo;
import com.ihit.lab4.dao.CustomerProofOfIdRepo;
import com.ihit.lab4.dto.CustomerProofOfIdDTO;
import com.ihit.lab4.entity.CustomerProofOfId;
import com.ihit.lab4.mapper.CustomerProofOfIdMapper;
import com.ihit.lab4.service.CustomerProofOfIdService;

import jakarta.transaction.Transactional;

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

    public Page<CustomerProofOfIdDTO> getPage(int page,int page_sz) {
        return repo.findByCrudFlagNot("D",PageRequest.of(page, page_sz)).map(mapper::toDTO);
    }

public Page<CustomerProofOfIdDTO> findPage(int page,int page_sz) {

    PageRequest pageable = PageRequest.of(page-1, page_sz);
    Page<CustomerProofOfId> ret = repo.findActive("D", pageable);

    return ret.map(mapper::toDTO);
}

    public CustomerProofOfIdDTO create(CustomerProofOfIdDTO dto){
        var e = mapper.toEntity(dto);
        e.setCustomer(detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        e.setCrudFlag("C");
        return mapper.toDTO(repo.save(e));
    }

    @Override
public CustomerProofOfIdDTO getById(Long id){

    var entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    if ("D".equals(entity.getCrudFlag())) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record deleted");
    }

    return mapper.toDTO(entity);
}

    @Override
    @Transactional
public CustomerProofOfIdDTO update(Long id, CustomerProofOfIdDTO dto) {

    CustomerProofOfId entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    if ("D".equals(entity.getCrudFlag())){
        throw new RuntimeException("Cannot update deleted record");
    }
    entity.setCustomer_proof_of_id_type(dto.getType());
    entity.setCustomer_proof_of_id_value(dto.getValue());

    entity.setCustomer(
            detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    
    entity.setEffective_date(dto.getEffectiveDate());
    entity.setStart_date(dto.getStartDate());
    entity.setEnd_date(dto.getEndDate());
    entity.setCrudFlag("U");
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

