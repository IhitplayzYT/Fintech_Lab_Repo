package com.ihit.lab4.mapper;

import org.mapstruct.*;
import com.ihit.lab4.entity.CustomerDetail;
import com.ihit.lab4.dto.CustomerDetailDTO;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerDetailMapper {

    @Mapping(source = "customer_identifier", target = "id")
    @Mapping(source = "customer_gender", target = "gender")
    @Mapping(source = "customer_type", target = "type")
    @Mapping(source = "customer_dob", target = "dob")
    @Mapping(source = "customer_lang", target = "lang")
    @Mapping(source = "customer_status", target = "status")
    @Mapping(source = "customer_country", target = "country")
    CustomerDetailDTO toDTO(CustomerDetail e);

    @Mapping(source = "id", target = "customer_identifier")
    @Mapping(source = "gender", target = "customer_gender")
    @Mapping(source = "type", target = "customer_type")
    @Mapping(source = "dob", target = "customer_dob")
    @Mapping(source = "lang", target = "customer_lang")
    @Mapping(source = "status", target = "customer_status")
    @Mapping(source = "country", target = "customer_country")
    CustomerDetail toEntity(CustomerDetailDTO d);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "gender", target = "customer_gender")
    @Mapping(source = "type", target = "customer_type")
    @Mapping(source = "dob", target = "customer_dob")
    @Mapping(source = "lang", target = "customer_lang")
    @Mapping(source = "status", target = "customer_status")
    @Mapping(source = "country", target = "customer_country")
    void updateEntityFromDto(CustomerDetailDTO dto, @MappingTarget CustomerDetail e);
}
