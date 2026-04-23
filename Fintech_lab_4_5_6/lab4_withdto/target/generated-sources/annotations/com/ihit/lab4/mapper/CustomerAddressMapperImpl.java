package com.ihit.lab4.mapper;

import com.ihit.lab4.dto.CustomerAddressDTO;
import com.ihit.lab4.entity.CustomerAddress;
import com.ihit.lab4.entity.CustomerClassification;
import com.ihit.lab4.entity.CustomerDetail;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-27T09:01:14+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260128-0750, environment: Java 25.0.2 (Arch Linux)"
)
@Component
public class CustomerAddressMapperImpl implements CustomerAddressMapper {

    @Override
    public CustomerAddressDTO toDTO(CustomerAddress entity) {
        if ( entity == null ) {
            return null;
        }

        CustomerAddressDTO customerAddressDTO = new CustomerAddressDTO();

        customerAddressDTO.setId( entity.getCustomer_identifier() );
        customerAddressDTO.setType( entity.getCustomer_address_type() );
        customerAddressDTO.setValue( entity.getCustomer_address_value() );
        customerAddressDTO.setEffectiveDate( entity.getEffective_date() );
        customerAddressDTO.setClassificationId( entityCustomer_classificationCustomer_identifier( entity ) );
        customerAddressDTO.setDetailId( entityCustomer_detailCustomer_identifier( entity ) );

        return customerAddressDTO;
    }

    @Override
    public CustomerAddress toEntity(CustomerAddressDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CustomerAddress customerAddress = new CustomerAddress();

        customerAddress.setCustomer_identifier( dto.getId() );
        customerAddress.setCustomer_address_type( dto.getType() );
        customerAddress.setCustomer_address_value( dto.getValue() );
        customerAddress.setEffective_date( dto.getEffectiveDate() );

        return customerAddress;
    }

    @Override
    public void updateEntityFromDto(CustomerAddressDTO dto, CustomerAddress entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getType() != null ) {
            entity.setCustomer_address_type( dto.getType() );
        }
        if ( dto.getValue() != null ) {
            entity.setCustomer_address_value( dto.getValue() );
        }
        if ( dto.getEffectiveDate() != null ) {
            entity.setEffective_date( dto.getEffectiveDate() );
        }
    }

    private Long entityCustomer_classificationCustomer_identifier(CustomerAddress customerAddress) {
        if ( customerAddress == null ) {
            return null;
        }
        CustomerClassification customer_classification = customerAddress.getCustomer_classification();
        if ( customer_classification == null ) {
            return null;
        }
        Long customer_identifier = customer_classification.getCustomer_identifier();
        if ( customer_identifier == null ) {
            return null;
        }
        return customer_identifier;
    }

    private Long entityCustomer_detailCustomer_identifier(CustomerAddress customerAddress) {
        if ( customerAddress == null ) {
            return null;
        }
        CustomerDetail customer_detail = customerAddress.getCustomer_detail();
        if ( customer_detail == null ) {
            return null;
        }
        Long customer_identifier = customer_detail.getCustomer_identifier();
        if ( customer_identifier == null ) {
            return null;
        }
        return customer_identifier;
    }
}
