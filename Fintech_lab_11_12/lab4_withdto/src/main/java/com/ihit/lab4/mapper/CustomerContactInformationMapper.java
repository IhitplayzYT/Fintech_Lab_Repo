package com.ihit.lab4.mapper;

import org.mapstruct.*;
import com.ihit.lab4.entity.CustomerContactInformation;
import com.ihit.lab4.dto.CustomerContactInformationDTO;



@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerContactInformationMapper {

    @Mapping(source = "customer_identifier", target = "id")
    @Mapping(source = "customer_contact_type", target = "type")
    @Mapping(source = "customer_contact_value", target = "value")
    @Mapping(source = "effective_date", target = "effectiveDate")
    @Mapping(source = "start_date", target = "startDate")
    @Mapping(source = "end_date", target = "endDate")
    @Mapping(source = "customer.customer_identifier", target = "detailId")
    CustomerContactInformationDTO toDTO(CustomerContactInformation e);

    @Mapping(source = "id", target = "customer_identifier")
    @Mapping(source = "type", target = "customer_contact_type")
    @Mapping(source = "value", target = "customer_contact_value")
    @Mapping(source = "effectiveDate", target = "effective_date")
    @Mapping(source = "startDate", target = "start_date")
    @Mapping(source = "endDate", target = "end_date")
    @Mapping(target = "customer", ignore = true)
    CustomerContactInformation toEntity(CustomerContactInformationDTO d);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "type", target = "customer_contact_type")
    @Mapping(source = "value", target = "customer_contact_value")
    @Mapping(source = "effectiveDate", target = "effective_date")
    @Mapping(source = "startDate", target = "start_date")
    @Mapping(source = "endDate", target = "end_date")
    void updateEntityFromDto(CustomerContactInformationDTO dto, @MappingTarget CustomerContactInformation e);
}

