package com.ihit.lab4.mapper;

import com.ihit.lab4.dto.CustomerContactInformationDTO;
import com.ihit.lab4.entity.CustomerContactInformation;
import com.ihit.lab4.entity.CustomerDetail;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-27T09:01:13+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260128-0750, environment: Java 25.0.2 (Arch Linux)"
)
@Component
public class CustomerContactInformationMapperImpl implements CustomerContactInformationMapper {

    @Override
    public CustomerContactInformationDTO toDTO(CustomerContactInformation e) {
        if ( e == null ) {
            return null;
        }

        CustomerContactInformationDTO customerContactInformationDTO = new CustomerContactInformationDTO();

        customerContactInformationDTO.setId( e.getCustomer_identifier() );
        customerContactInformationDTO.setType( e.getCustomer_contact_type() );
        customerContactInformationDTO.setValue( e.getCustomer_contact_value() );
        customerContactInformationDTO.setEffectiveDate( e.getEffective_date() );
        customerContactInformationDTO.setStartDate( e.getStart_date() );
        customerContactInformationDTO.setEndDate( e.getEnd_date() );
        customerContactInformationDTO.setDetailId( eCustomerCustomer_identifier( e ) );

        return customerContactInformationDTO;
    }

    @Override
    public CustomerContactInformation toEntity(CustomerContactInformationDTO d) {
        if ( d == null ) {
            return null;
        }

        CustomerContactInformation customerContactInformation = new CustomerContactInformation();

        customerContactInformation.setCustomer_identifier( d.getId() );
        customerContactInformation.setCustomer_contact_type( d.getType() );
        customerContactInformation.setCustomer_contact_value( d.getValue() );
        customerContactInformation.setEffective_date( d.getEffectiveDate() );
        customerContactInformation.setStart_date( d.getStartDate() );
        customerContactInformation.setEnd_date( d.getEndDate() );

        return customerContactInformation;
    }

    @Override
    public void updateEntityFromDto(CustomerContactInformationDTO dto, CustomerContactInformation e) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getType() != null ) {
            e.setCustomer_contact_type( dto.getType() );
        }
        if ( dto.getValue() != null ) {
            e.setCustomer_contact_value( dto.getValue() );
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

    private Long eCustomerCustomer_identifier(CustomerContactInformation customerContactInformation) {
        if ( customerContactInformation == null ) {
            return null;
        }
        CustomerDetail customer = customerContactInformation.getCustomer();
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
