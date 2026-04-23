package com.ihit.lab4.serviceimpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import jakarta.transaction.Transactional;

import com.ihit.lab4.dao.CustomerAddressRepo;
import com.ihit.lab4.dao.CustomerClassificationRepo;
import com.ihit.lab4.dao.CustomerDetailRepo;
import com.ihit.lab4.dto.CustomerAddressDTO;
import com.ihit.lab4.entity.CustomerAddress;
import com.ihit.lab4.entity.CustomerClassification;
import com.ihit.lab4.entity.CustomerDetail;
import com.ihit.lab4.mapper.CustomerAddressMapper;
import com.ihit.lab4.service.CustomerAddressService;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private final CustomerAddressRepo repo;
    private final CustomerDetailRepo detailRepo;
    private final CustomerClassificationRepo classificationRepo;
    private final CustomerAddressMapper mapper;

    public CustomerAddressServiceImpl(CustomerAddressRepo repo,
                                      CustomerDetailRepo detailRepo,
                                      CustomerClassificationRepo classificationRepo,
                                      CustomerAddressMapper mapper) {
        this.repo = repo;
        this.detailRepo = detailRepo;
        this.classificationRepo = classificationRepo;
        this.mapper = mapper;
    }

    @Override
    public CustomerAddressDTO getById(Long id){
        var entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if ("D".equals(entity.getCrud_flag())) {
            throw new RuntimeException("Cannot get deleted record");
        }
        return mapper.toDTO(entity);
    }



    public CustomerAddressDTO create(CustomerAddressDTO dto){
        var e = mapper.toEntity(dto);
        e.setCustomer_detail(detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        e.setCustomer_classification(classificationRepo.findById(dto.getClassificationId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        e.setCrud_flag("C");
        return mapper.toDTO(repo.save(e));
    }

    @Override
@Transactional
    public CustomerAddressDTO update(Long id, CustomerAddressDTO dto) {

        CustomerAddress entity = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if ("D".equals(entity.getCrud_flag())){
            throw new RuntimeException("Cannot update deleted record");
        }

        entity.setCustomer_address_type(dto.getType());
        entity.setCustomer_address_value(dto.getValue());
        entity.setEffective_date(dto.getEffectiveDate());

        entity.setCustomer_detail(
                detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

        entity.setCustomer_classification(
                classificationRepo.findById(dto.getClassificationId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        entity.setCrud_flag("U");
        return mapper.toDTO(repo.save(entity));
    }

    @Override
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

        CustomerClassification x = entity.getCustomer_classification();
        CustomerDetail y = entity.getCustomer_detail();
        if (x != null && !"D".equals(x.getCrud_flag())) x.setCrud_flag("D");
        if (y != null && !"D".equals(y.getCrud_flag())) y.setCrud_flag("D");
        entity.setCrud_flag("D");
    }

}
