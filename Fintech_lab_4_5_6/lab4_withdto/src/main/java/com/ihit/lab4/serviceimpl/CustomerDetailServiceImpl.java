package com.ihit.lab4.serviceimpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import jakarta.transaction.Transactional;

import com.ihit.lab4.dao.CustomerDetailRepo;
import com.ihit.lab4.dto.CustomerDetailDTO;
import com.ihit.lab4.entity.CustomerDetail;
import com.ihit.lab4.mapper.CustomerDetailMapper;
import com.ihit.lab4.service.CustomerDetailService;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {

    private final CustomerDetailRepo repo;
    private final CustomerDetailMapper mapper;

    public CustomerDetailServiceImpl(CustomerDetailRepo repo, CustomerDetailMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public CustomerDetailDTO create(CustomerDetailDTO dto) {
        CustomerDetail e = mapper.toEntity(dto);
        CustomerDetail saved = repo.save(e);
        e.setCrud_flag("C");
        return mapper.toDTO(saved);
    }

    public CustomerDetailDTO getById(Long id) {
        var entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if ("D".equals(entity.getCrud_flag())) {
            throw new RuntimeException("Cannot get deleted record");
        }
        return mapper.toDTO(entity);

    }

    @Override
@Transactional
    public CustomerDetailDTO update(Long id, CustomerDetailDTO dto) {

    CustomerDetail entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if ("D".equals(entity.getCrud_flag())){
            throw new RuntimeException("Cannot update deleted record");
        }
    entity.setCustomer_gender(dto.getGender());
    entity.setCustomer_type(dto.getType());
    entity.setCustomer_dob(dto.getDob());
    entity.setCustomer_lang(dto.getLang());
    entity.setCustomer_status(dto.getStatus());
    entity.setCustomer_country(dto.getCountry());
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
        e.setCrud_flag("D");
    }
}
