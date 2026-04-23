package com.ihit.lab4.service;
import org.springframework.data.domain.Page;

public interface Pagination<T> {
    public Page<T> getPage(int page,int page_sz);
    public Page<T> findPage(int page,int page_sz);
}


