package com.ihit.lab4.mapper;

import org.mapstruct.*;
import com.ihit.lab4.entity.CustomerProofOfId;
import com.ihit.lab4.dto.CustomerProofOfIdDTO;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerProofOfIdMapper {

    @Mapping(source = "customer_identifier", target = "id")
    @Mapping(source = "customer_proof_of_id_type", target = "type")
    @Mapping(source = "customer_proof_of_id_value", target = "value")
    @Mapping(source = "effective_date", target = "effectiveDate")
    @Mapping(source = "start_date", target = "startDate")
    @Mapping(source = "end_date", target = "endDate")
    @Mapping(source = "customer.customer_identifier", target = "detailId")
    CustomerProofOfIdDTO toDTO(CustomerProofOfId e);

    @Mapping(source = "id", target = "customer_identifier")
    @Mapping(source = "type", target =  "customer_proof_of_id_type")
    @Mapping(source = "value", target = "customer_proof_of_id_value")
    @Mapping(source = "effectiveDate", target = "effective_date")
    @Mapping(source = "startDate", target = "start_date")
    @Mapping(source = "endDate", target = "end_date")
    @Mapping(target = "customer", ignore = true)
    CustomerProofOfId toEntity(CustomerProofOfIdDTO d);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "type", target = "customer_proof_of_id_type")
    @Mapping(source = "value", target ="customer_proof_of_id_value")
    @Mapping(source = "effectiveDate", target = "effective_date")
    @Mapping(source = "startDate", target = "start_date")
    @Mapping(source = "endDate", target = "end_date")
    void updateEntityFromDto(CustomerProofOfIdDTO dto, @MappingTarget CustomerProofOfId e);
}
