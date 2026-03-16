package com.ihit.lab4.mapper;

import org.mapstruct.*;
import com.ihit.lab4.entity.CustomerAddress;
import com.ihit.lab4.dto.CustomerAddressDTO;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerAddressMapper {

    // ENTITY → DTO
    @Mapping(source = "customer_identifier", target = "id")
    @Mapping(source = "customer_address_type", target = "type")
    @Mapping(source = "customer_address_value", target = "value")
    @Mapping(source = "effective_date", target = "effectiveDate")
    @Mapping(source = "customer_classification.customer_identifier", target = "classificationId")
    @Mapping(source = "customer_detail.customer_identifier", target = "detailId")
    CustomerAddressDTO toDTO(CustomerAddress entity);

    // DTO → ENTITY (CREATE)
    @Mapping(source = "id", target = "customer_identifier")
    @Mapping(source = "type", target = "customer_address_type")
    @Mapping(source = "value", target = "customer_address_value")
    @Mapping(source = "effectiveDate", target = "effective_date")

    // Explicitly ignore relational fields
    @Mapping(target = "customer_classification", ignore = true)
    @Mapping(target = "customer_detail", ignore = true)

    CustomerAddress toEntity(CustomerAddressDTO dto);

    // DTO → ENTITY (UPDATE)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "type", target = "customer_address_type")
    @Mapping(source = "value", target = "customer_address_value")
    @Mapping(source = "effectiveDate", target = "effective_date")
    void updateEntityFromDto(CustomerAddressDTO dto,
                             @MappingTarget CustomerAddress entity);
}
