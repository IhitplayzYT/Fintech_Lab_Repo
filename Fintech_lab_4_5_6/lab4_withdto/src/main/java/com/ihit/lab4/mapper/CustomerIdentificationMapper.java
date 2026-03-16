package com.ihit.lab4.mapper;

import org.mapstruct.*;
import com.ihit.lab4.entity.CustomerIdentification;
import com.ihit.lab4.dto.CustomerIdentificationDTO;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerIdentificationMapper {

    @Mapping(source = "customer_identifier", target = "id")
    @Mapping(source = "customer_identification_type", target = "type")
    @Mapping(source = "customer_identification_item", target = "item")
    @Mapping(source = "effective_Date", target = "effectiveDate")
    @Mapping(source = "customer.customer_identifier", target = "detailId")
    CustomerIdentificationDTO toDTO(CustomerIdentification e);

    @Mapping(source = "id", target = "customer_identifier")
    @Mapping(source = "type", target = "customer_identification_type")
    @Mapping(source = "item", target = "customer_identification_item")
    @Mapping(source = "effectiveDate", target = "effective_Date")
    @Mapping(target = "customer", ignore = true)
    CustomerIdentification toEntity(CustomerIdentificationDTO d);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "type", target = "customer_identification_type")
    @Mapping(source = "item", target = "customer_identification_item")
    @Mapping(source = "effectiveDate", target = "effective_Date")
    void updateEntityFromDto(CustomerIdentificationDTO dto, @MappingTarget CustomerIdentification e);
}
