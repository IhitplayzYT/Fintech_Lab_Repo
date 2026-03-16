package com.ihit.lab4.mapper;

import org.mapstruct.*;
import com.ihit.lab4.entity.CustomerName;
import com.ihit.lab4.dto.CustomerNameDTO;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerNameMapper {

    @Mapping(source = "customer_identifier", target = "id")
    @Mapping(source = "customer_name_type", target = "type")
    @Mapping(source = "customer_name_value", target = "value")
    @Mapping(source = "effective_date", target = "effectiveDate")
    @Mapping(source = "customer.customer_identifier", target = "detailId")
    CustomerNameDTO toDTO(CustomerName e);

    @Mapping(source = "id", target = "customer_identifier")
    @Mapping(source = "type", target = "customer_name_type")
    @Mapping(source = "value", target = "customer_name_value")
    @Mapping(source = "effectiveDate", target = "effective_date")
    @Mapping(target = "customer", ignore = true)
    CustomerName toEntity(CustomerNameDTO d);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "type", target = "customer_name_type")
    @Mapping(source = "value", target = "customer_name_value")
    @Mapping(source = "effectiveDate", target = "effective_date")
    void updateEntityFromDto(CustomerNameDTO dto, @MappingTarget CustomerName e);
}
