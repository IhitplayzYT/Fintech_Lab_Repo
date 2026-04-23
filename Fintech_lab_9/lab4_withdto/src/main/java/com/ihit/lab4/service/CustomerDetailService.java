package com.ihit.lab4.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.ihit.lab4.dto.CustomerDetailDTO;
public interface CustomerDetailService {
    CustomerDetailDTO create(CustomerDetailDTO dto);
    CustomerDetailDTO getById(Long id);
    CustomerDetailDTO update(Long id, CustomerDetailDTO dto);
    void delete(Long id);
    public Page<CustomerDetailDTO> getPage(int page,int page_sz);
    public Page<CustomerDetailDTO> findPage(int page,int page_sz);
    public List<String> processFile(MultipartFile file);
    public void generateRandom(int count);
    public int executeRawQuery(String sql);
}
