package com.ihit.lab4.serviceimpl;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.Reader;

import com.ihit.lab4.dao.CustomerAddressRepo;
import com.ihit.lab4.dao.CustomerClassificationRepo;
import com.ihit.lab4.dao.CustomerDetailRepo;
import com.ihit.lab4.dto.CustomerAddressDTO;
import com.ihit.lab4.entity.CustomerAddress;
import com.ihit.lab4.entity.CustomerClassification;
import com.ihit.lab4.entity.CustomerDetail;
import com.ihit.lab4.mapper.CustomerAddressMapper;
import com.ihit.lab4.service.CustomerAddressService;

import jakarta.transaction.Transactional;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.core.type.TypeReference;
import java.sql.Date;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.csv.*;
@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private final CustomerAddressRepo repo;
    private final CustomerDetailRepo detailRepo;
    private final CustomerClassificationRepo classificationRepo;
    private final CustomerAddressMapper mapper;

    public Page<CustomerAddressDTO> getPage(int page,int page_sz) {
        return repo.findByCrudFlagNot("D",PageRequest.of(page, page_sz)).map(mapper::toDTO);
    }
public Page<CustomerAddressDTO> findPage(int page,int page_sz) {

    PageRequest pageable = PageRequest.of(page-1, page_sz);
    Page<CustomerAddress> ret = repo.findActive("D", pageable);

    return ret.map(mapper::toDTO);
}


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

    if ("D".equals(entity.getCrudFlag())) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record deleted");
    }

    return mapper.toDTO(entity);
}



    public CustomerAddressDTO create(CustomerAddressDTO dto){
        var e = mapper.toEntity(dto);
        e.setCustomer_detail(detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        e.setCustomer_classification(classificationRepo.findById(dto.getClassificationId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        e.setCrudFlag("C");
        return mapper.toDTO(repo.save(e));
    }

    @Override
@Transactional
    public CustomerAddressDTO update(Long id, CustomerAddressDTO dto) {

        CustomerAddress entity = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if ("D".equals(entity.getCrudFlag())){
            throw new RuntimeException("Cannot update deleted record");
        }

        entity.setCustomer_address_type(dto.getType());
        entity.setCustomer_address_value(dto.getValue());
        entity.setEffective_date(dto.getEffectiveDate());

        entity.setCustomer_detail(
                detailRepo.findById(dto.getDetailId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

        entity.setCustomer_classification(
                classificationRepo.findById(dto.getClassificationId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        entity.setCrudFlag("U");
        return mapper.toDTO(repo.save(entity));
    }

    @Override
@Transactional
    public void delete(Long id){

        var entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

if ("D".equals(entity.getCrudFlag())) {
    return;
}

        CustomerClassification x = entity.getCustomer_classification();
        CustomerDetail y = entity.getCustomer_detail();
        if (x != null && !"D".equals(x.getCrudFlag())) x.setCrudFlag("D");
        if (y != null && !"D".equals(y.getCrudFlag())) y.setCrudFlag("D");
        entity.setCrudFlag("D");
    }
@Transactional
public List<String> processFile(MultipartFile file) {

    List<String> errors = new ArrayList<>();
    List<CustomerAddress> batch = new ArrayList<>();
    final int BATCH_SIZE = 500;

    String filename = file.getOriginalFilename().toLowerCase();

    try {
        List<CustomerAddressDTO> dtos;

        if (filename.endsWith(".xlsx")) {
            dtos = parseExcel(file, errors);
        } else if (filename.endsWith(".csv")) {
            dtos = parseCSV(file, errors);
        } else if (filename.endsWith(".json")) {
            dtos = parseJSON(file, errors);
        } else {
            throw new RuntimeException("Unsupported file type");
        }

        for (int i = 0; i < dtos.size(); i++) {
            CustomerAddressDTO dto = dtos.get(i);

            try {
                // Use MapStruct
                CustomerAddress entity = mapper.toEntity(dto);

                // FK resolution (MANDATORY)
                entity.setCustomer_detail(
                    detailRepo.findById(dto.getDetailId())
                        .orElseThrow(() -> new RuntimeException("Invalid detailId"))
                );

                entity.setCustomer_classification(
                    classificationRepo.findById(dto.getClassificationId())
                        .orElseThrow(() -> new RuntimeException("Invalid classificationId"))
                );

                entity.setCrudFlag("C");

                batch.add(entity);

                if (batch.size() >= BATCH_SIZE) {
                    repo.saveAll(batch);
                    batch.clear();
                }

            } catch (Exception e) {
                errors.add("Record " + i + ": " + e.getMessage());
            }
        }

        if (!batch.isEmpty()) {
            repo.saveAll(batch);
        }

    } catch (Exception e) {
        errors.add("File error: " + e.getMessage());
    }

    return errors;
}


private List<CustomerAddressDTO> parseExcel(MultipartFile file, List<String> errors) {
    List<CustomerAddressDTO> list = new ArrayList<>();

    try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {

        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            try {
                CustomerAddressDTO dto = new CustomerAddressDTO();

                dto.setType(formatter.formatCellValue(row.getCell(0)));
                dto.setValue(formatter.formatCellValue(row.getCell(1)));

                String dateStr = formatter.formatCellValue(row.getCell(2));
                dto.setEffectiveDate(Date.valueOf(dateStr)); // yyyy-MM-dd

                dto.setClassificationId(
                    Long.parseLong(formatter.formatCellValue(row.getCell(3)))
                );

                dto.setDetailId(
                    Long.parseLong(formatter.formatCellValue(row.getCell(4)))
                );

                list.add(dto);

            } catch (Exception e) {
                errors.add("Excel row " + i + ": " + e.getMessage());
            }
        }

    } catch (Exception e) {
        errors.add("Excel parse error: " + e.getMessage());
    }

    return list;
}

private List<CustomerAddressDTO> parseJSON(MultipartFile file, List<String> errors) {
    try {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
            file.getInputStream(),
            new TypeReference<List<CustomerAddressDTO>>() {}
        );
    } catch (Exception e) {
        errors.add("JSON parse error: " + e.getMessage());
        return new ArrayList<>();
    }
}

private List<CustomerAddressDTO> parseCSV(MultipartFile file, List<String> errors) {
    List<CustomerAddressDTO> list = new ArrayList<>();

    try (Reader reader = new InputStreamReader(file.getInputStream());
         CSVParser csv = new CSVParser(
             reader,
             CSVFormat.DEFAULT.builder()
                 .setHeader()
                 .setSkipHeaderRecord(true)
                 .setIgnoreEmptyLines(true)
                 .setTrim(true)
                 .build())) {
        int i = 1;
        for (CSVRecord record : csv) {
            try {
                CustomerAddressDTO dto = new CustomerAddressDTO();

                dto.setType(record.get(0));
                dto.setValue(record.get(1));
                dto.setEffectiveDate(Date.valueOf(record.get(2)));
                dto.setClassificationId(Long.parseLong(record.get(3)));
                dto.setDetailId(Long.parseLong(record.get(4)));

                list.add(dto);

            } catch (Exception e) {
                errors.add("CSV row " + i + ": " + e.getMessage());
            }
            i++;
        }

    } catch (Exception e) {
        errors.add("CSV parse error: " + e.getMessage());
    }

    return list;
}

}
