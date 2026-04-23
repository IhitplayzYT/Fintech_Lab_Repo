package com.ihit.lab4.mapper;

import com.ihit.lab4.dto.CustomerNameDTO;
import com.ihit.lab4.entity.CustomerDetail;
import com.ihit.lab4.entity.CustomerName;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-27T09:01:14+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260128-0750, environment: Java 25.0.2 (Arch Linux)"
)
@Component
public class CustomerNameMapperImpl implements CustomerNameMapper {

    @Override
    public CustomerNameDTO toDTO(CustomerName e) {
        if ( e == null ) {
            return null;
        }

        CustomerNameDTO customerNameDTO = new CustomerNameDTO();

        customerNameDTO.setId( e.getCustomer_identifier() );
        customerNameDTO.setType( e.getCustomer_name_type() );
        customerNameDTO.setValue( e.getCustomer_name_value() );
        customerNameDTO.setEffectiveDate( e.getEffective_date() );
        customerNameDTO.setDetailId( eCustomerCustomer_identifier( e ) );

        return customerNameDTO;
    }

    @Override
    public CustomerName toEntity(CustomerNameDTO d) {
        if ( d == null ) {
            return null;
        }

        CustomerName customerName = new CustomerName();

        customerName.setCustomer_identifier( d.getId() );
        customerName.setCustomer_name_type( d.getType() );
        customerName.setCustomer_name_value( d.getValue() );
        customerName.setEffective_date( d.getEffectiveDate() );

        return customerName;
    }

    @Override
    public void updateEntityFromDto(CustomerNameDTO dto, CustomerName e) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getType() != null ) {
            e.setCustomer_name_type( dto.getType() );
        }
        if ( dto.getValue() != null ) {
            e.setCustomer_name_value( dto.getValue() );
        }
        if ( dto.getEffectiveDate() != null ) {
            e.setEffective_date( dto.getEffectiveDate() );
        }
    }

    private Long eCustomerCustomer_identifier(CustomerName customerName) {
        if ( customerName == null ) {
            return null;
        }
        CustomerDetail customer = customerName.getCustomer();
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
