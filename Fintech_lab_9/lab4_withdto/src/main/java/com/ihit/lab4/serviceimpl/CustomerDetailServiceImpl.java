package com.ihit.lab4.serviceimpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.ihit.lab4.dao.CustomerDetailRepo;
import com.ihit.lab4.dto.CustomerDetailDTO;
import com.ihit.lab4.entity.CustomerDetail;
import com.ihit.lab4.mapper.CustomerDetailMapper;
import com.ihit.lab4.service.CustomerDetailService;
import java.io.Reader;
import java.io.InputStreamReader;
import java.util.*;
import org.springframework.web.multipart.MultipartFile;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.core.type.TypeReference;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.commons.csv.*;

import java.sql.Date;
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

@Transactional
public void generateRandom(int count) {

    String[] genders = {"M", "F", "O", "N"};
    String[] types = {"REGULAR", "PREMIUM", "VIP","BLACKLISTED","STAFF","MANAGEMENT"};
    String[] langs = {"EN", "FR", "DE", "ES", "HI","TU","TE","KA","ES","DE"};
    String[] statuses = {"ACTIVE", "INACTIVE", "BLOCKED","DEACTIVATED"};
    String[] countries = {"IN", "US", "UK", "DE", "FR", "JP","GE","RU","AF","AU"};

    java.util.Random rand = new java.util.Random();

    for (int i = 0; i < count; i++) {
        CustomerDetailDTO dto = new CustomerDetailDTO();

        dto.setGender(genders[rand.nextInt(genders.length)]);
        dto.setType(types[rand.nextInt(types.length)]);
        dto.setLang(langs[rand.nextInt(langs.length)]);
        dto.setStatus(statuses[rand.nextInt(statuses.length)]);
        dto.setCountry(countries[rand.nextInt(countries.length)]);

        long minDay = java.sql.Date.valueOf("1970-01-01").getTime();
        long maxDay = java.sql.Date.valueOf("2005-12-31").getTime();
        long randomDay = minDay + (long) (rand.nextDouble() * (maxDay - minDay));
        dto.setDob(new java.sql.Date(randomDay));

        CustomerDetail entity = mapper.toEntity(dto);
        entity.setCrudFlag("C");

        repo.save(entity);
    }
}


@Transactional
public List<String> processFile(MultipartFile file) {

    List<String> errors = new ArrayList<>();
    List<CustomerDetail> batch = new ArrayList<>();
    final int BATCH_SIZE = 500;

    String filename = file.getOriginalFilename();
    if (filename == null) {
        errors.add("Invalid file");
        return errors;
    }
    filename = filename.toLowerCase();

    try {
        List<CustomerDetailDTO> dtos;

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
            try {
                CustomerDetail entity = mapper.toEntity(dtos.get(i));
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

private List<CustomerDetailDTO> parseCSV(MultipartFile file, List<String> errors) {
    List<CustomerDetailDTO> list = new ArrayList<>();

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
                CustomerDetailDTO dto = new CustomerDetailDTO();

                dto.setGender(record.get(0));
                dto.setType(record.get(1));
                dto.setDob(Date.valueOf(record.get(2)));
                dto.setLang(record.get(3));
                dto.setStatus(record.get(4));
                dto.setCountry(record.get(5));

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

private List<CustomerDetailDTO> parseExcel(MultipartFile file, List<String> errors) {
    List<CustomerDetailDTO> list = new ArrayList<>();

    try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {

        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            try {
                CustomerDetailDTO dto = new CustomerDetailDTO();

                dto.setGender(formatter.formatCellValue(row.getCell(0)));
                dto.setType(formatter.formatCellValue(row.getCell(1)));
                dto.setDob(Date.valueOf(formatter.formatCellValue(row.getCell(2))));
                dto.setLang(formatter.formatCellValue(row.getCell(3)));
                dto.setStatus(formatter.formatCellValue(row.getCell(4)));
                dto.setCountry(formatter.formatCellValue(row.getCell(5)));

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

private List<CustomerDetailDTO> parseJSON(MultipartFile file, List<String> errors) {
    try {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
            file.getInputStream(),
            new TypeReference<List<CustomerDetailDTO>>() {}
        );
    } catch (Exception e) {
        errors.add("JSON parse error: " + e.getMessage());
        return new ArrayList<>();
    }
}
@PersistenceContext
private EntityManager entityManager;

@Transactional
public int executeRawQuery(String sql) {
    return entityManager.createNativeQuery(sql).executeUpdate();
}

}
