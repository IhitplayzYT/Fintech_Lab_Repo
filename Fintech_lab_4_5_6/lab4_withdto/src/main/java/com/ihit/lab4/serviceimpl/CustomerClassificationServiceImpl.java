package com.ihit.lab4.serviceimpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import jakarta.transaction.Transactional;

import com.ihit.lab4.dao.CustomerClassificationRepo;
import com.ihit.lab4.dao.CustomerDetailRepo;
import com.ihit.lab4.dao.CustomerNameRepo;
import com.ihit.lab4.dto.CustomerClassificationDTO;
import com.ihit.lab4.entity.CustomerClassification;
import com.ihit.lab4.entity.CustomerDetail;
import com.ihit.lab4.entity.CustomerName;
import com.ihit.lab4.mapper.CustomerClassificationMapper;
import com.ihit.lab4.service.CustomerClassificationService;

@Service
public class CustomerClassificationServiceImpl implements CustomerClassificationService {

    private final CustomerClassificationRepo repo;
    private final CustomerDetailRepo detailRepo;
    private final CustomerNameRepo nameRepo;
    private final CustomerClassificationMapper mapper;

    public CustomerClassificationServiceImpl(CustomerClassificationRepo repo,
                                             CustomerDetailRepo detailRepo,
                                             CustomerNameRepo nameRepo,
                                             CustomerClassificationMapper mapper) {
        this.repo = repo;
        this.detailRepo = detailRepo;
        this.nameRepo = nameRepo;
        this.mapper = mapper;
    }

    public CustomerClassificationDTO create(CustomerClassificationDTO dto){
        var e = mapper.toEntity(dto);
        e.setCustomer_details(detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        e.setCustomer(nameRepo.findById(dto.getNameId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        e.setCrud_flag("C");
        return mapper.toDTO(repo.save(e));
    }

    public CustomerClassificationDTO getById(Long id){

        var entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if ("D".equals(entity.getCrud_flag())) {
            throw new RuntimeException("Cannot get deleted record");
        }
        return mapper.toDTO(entity);

    }

    @Override
    @Transactional
    public CustomerClassificationDTO update(Long id, CustomerClassificationDTO dto) {

        CustomerClassification entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if ("D".equals(entity.getCrud_flag())) {
            throw new RuntimeException("Cannot Update Deleted Record");

        }

        entity.setCustomer_classification_type(dto.getType());
        entity.setCustomer_classification_value(dto.getValue());
        entity.setCustomer_details(
                detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        entity.setEffective_date(dto.getEffectiveDate());
        entity.setCustomer(nameRepo.findById(dto.getNameId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
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


    CustomerDetail x = entity.getCustomer_details();
    CustomerName y = entity.getCustomer();
    if (x != null && !"D".equals(x.getCrud_flag())) x.setCrud_flag("D");
    if (y != null && !"D".equals(y.getCrud_flag())) y.setCrud_flag("D");
    
        entity.setCrud_flag("D");
    }
}
