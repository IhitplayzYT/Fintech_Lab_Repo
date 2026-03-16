package com.ihit.lab4.mapper;

import com.ihit.lab4.dto.CustomerProofOfIdDTO;
import com.ihit.lab4.entity.CustomerDetail;
import com.ihit.lab4.entity.CustomerProofOfId;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-27T09:01:13+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260128-0750, environment: Java 25.0.2 (Arch Linux)"
)
@Component
public class CustomerProofOfIdMapperImpl implements CustomerProofOfIdMapper {

    @Override
    public CustomerProofOfIdDTO toDTO(CustomerProofOfId e) {
        if ( e == null ) {
            return null;
        }

        CustomerProofOfIdDTO customerProofOfIdDTO = new CustomerProofOfIdDTO();

        customerProofOfIdDTO.setId( e.getCustomer_identifier() );
        customerProofOfIdDTO.setType( e.getCustomer_proof_of_id_type() );
        customerProofOfIdDTO.setValue( e.getCustomer_proof_of_id_value() );
        customerProofOfIdDTO.setEffectiveDate( e.getEffective_date() );
        customerProofOfIdDTO.setStartDate( e.getStart_date() );
        customerProofOfIdDTO.setEndDate( e.getEnd_date() );
        customerProofOfIdDTO.setDetailId( eCustomerCustomer_identifier( e ) );

        return customerProofOfIdDTO;
    }

    @Override
    public CustomerProofOfId toEntity(CustomerProofOfIdDTO d) {
        if ( d == null ) {
            return null;
        }

        CustomerProofOfId customerProofOfId = new CustomerProofOfId();

        customerProofOfId.setCustomer_identifier( d.getId() );
        customerProofOfId.setCustomer_proof_of_id_type( d.getType() );
        customerProofOfId.setCustomer_proof_of_id_value( d.getValue() );
        customerProofOfId.setEffective_date( d.getEffectiveDate() );
        customerProofOfId.setStart_date( d.getStartDate() );
        customerProofOfId.setEnd_date( d.getEndDate() );

        return customerProofOfId;
    }

    @Override
    public void updateEntityFromDto(CustomerProofOfIdDTO dto, CustomerProofOfId e) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getType() != null ) {
            e.setCustomer_proof_of_id_type( dto.getType() );
        }
        if ( dto.getValue() != null ) {
            e.setCustomer_proof_of_id_value( dto.getValue() );
        }
        if ( dto.getEffectiveDate() != null ) {
            e.setEffective_date( dto.getEffectiveDate() );
        }
        if ( dto.getStartDate() != null ) {
            e.setStart_date( dto.getStartDate() );
        }
        if ( dto.getEndDate() != null ) {
            e.setEnd_date( dto.getEndDate() );
        }
    }

    private Long eCustomerCustomer_identifier(CustomerProofOfId customerProofOfId) {
        if ( customerProofOfId == null ) {
            return null;
        }
        CustomerDetail customer = customerProofOfId.getCustomer();
        if ( customer == null ) {
            return null;
        }
        Long customer_identifier = customer.getCustomer_identifier();
        if ( customer_identifier == null ) {
            return null;
        }
        return customer_identifier;
    }
}
