package com.ihit.lab4.mapper;

import com.ihit.lab4.dto.CustomerIdentificationDTO;
import com.ihit.lab4.entity.CustomerDetail;
import com.ihit.lab4.entity.CustomerIdentification;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-27T09:01:14+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260128-0750, environment: Java 25.0.2 (Arch Linux)"
)
@Component
public class CustomerIdentificationMapperImpl implements CustomerIdentificationMapper {

    @Override
    public CustomerIdentificationDTO toDTO(CustomerIdentification e) {
        if ( e == null ) {
            return null;
        }

        CustomerIdentificationDTO customerIdentificationDTO = new CustomerIdentificationDTO();

        customerIdentificationDTO.setId( e.getCustomer_identifier() );
        customerIdentificationDTO.setType( e.getCustomer_identification_type() );
        customerIdentificationDTO.setItem( e.getCustomer_identification_item() );
        customerIdentificationDTO.setEffectiveDate( e.getEffective_Date() );
        customerIdentificationDTO.setDetailId( eCustomerCustomer_identifier( e ) );

        return customerIdentificationDTO;
    }

    @Override
    public CustomerIdentification toEntity(CustomerIdentificationDTO d) {
        if ( d == null ) {
            return null;
        }

        CustomerIdentification customerIdentification = new CustomerIdentification();

        customerIdentification.setCustomer_identifier( d.getId() );
        customerIdentification.setCustomer_identification_type( d.getType() );
        customerIdentification.setCustomer_identification_item( d.getItem() );
        customerIdentification.setEffective_Date( d.getEffectiveDate() );

        return customerIdentification;
    }

    @Override
    public void updateEntityFromDto(CustomerIdentificationDTO dto, CustomerIdentification e) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getType() != null ) {
            e.setCustomer_identification_type( dto.getType() );
        }
        if ( dto.getItem() != null ) {
            e.setCustomer_identification_item( dto.getItem() );
        }
        if ( dto.getEffectiveDate() != null ) {
            e.setEffective_Date( dto.getEffectiveDate() );
        }
    }

    private Long eCustomerCustomer_identifier(CustomerIdentification customerIdentification) {
        if ( customerIdentification == null ) {
            return null;
        }
        CustomerDetail customer = customerIdentification.getCustomer();
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
