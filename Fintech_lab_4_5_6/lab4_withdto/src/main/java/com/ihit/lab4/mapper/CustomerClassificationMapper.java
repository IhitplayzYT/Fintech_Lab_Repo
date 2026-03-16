package com.ihit.lab4.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.ihit.lab4.dto.CustomerClassificationDTO;
import com.ihit.lab4.entity.CustomerClassification;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerClassificationMapper {

    @Mapping(source = "customer_identifier", target = "id")
    @Mapping(source = "customer_classification_type", target = "type")
    @Mapping(source = "customer_classification_value", target = "value")
    @Mapping(source = "effective_date", target = "effectiveDate")
    @Mapping(source = "customer.customer_identifier", target = "nameId")
    @Mapping(source = "customer_details.customer_identifier", target = "detailId")

    CustomerClassificationDTO toDTO(CustomerClassification entity);


    @Mapping(target = "customer_identifier", ignore = true)
    @Mapping(source = "type", target = "customer_classification_type")
    @Mapping(source = "value", target = "customer_classification_value")
    @Mapping(source = "effectiveDate", target = "effective_date")
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "customer_details", ignore = true)

    CustomerClassification toEntity(CustomerClassificationDTO dto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "type", target = "customer_classification_type")
    @Mapping(source = "value", target = "customer_classification_value")
    @Mapping(source = "effectiveDate", target = "effective_date")
    void updateEntityFromDto(CustomerClassificationDTO dto,
                             @MappingTarget CustomerClassification entity);
}


