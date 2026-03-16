package com.ihit.lab4.mapper;

import com.ihit.lab4.dto.CustomerClassificationDTO;
import com.ihit.lab4.entity.CustomerClassification;
import com.ihit.lab4.entity.CustomerDetail;
import com.ihit.lab4.entity.CustomerName;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-16T19:04:31+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 25.0.2 (Arch Linux)"
)
@Component
public class CustomerClassificationMapperImpl implements CustomerClassificationMapper {

    @Override
    public CustomerClassificationDTO toDTO(CustomerClassification entity) {
        if ( entity == null ) {
            return null;
        }

        CustomerClassificationDTO customerClassificationDTO = new CustomerClassificationDTO();

        customerClassificationDTO.setId( entity.getCustomer_identifier() );
        customerClassificationDTO.setType( entity.getCustomer_classification_type() );
        customerClassificationDTO.setValue( entity.getCustomer_classification_value() );
        customerClassificationDTO.setEffectiveDate( entity.getEffective_date() );
        customerClassificationDTO.setNameId( entityCustomerCustomer_identifier( entity ) );
        customerClassificationDTO.setDetailId( entityCustomer_detailsCustomer_identifier( entity ) );

        return customerClassificationDTO;
    }

    @Override
    public CustomerClassification toEntity(CustomerClassificationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CustomerClassification customerClassification = new CustomerClassification();

        customerClassification.setCustomer_classification_type( dto.getType() );
        customerClassification.setCustomer_classification_value( dto.getValue() );
        customerClassification.setEffective_date( dto.getEffectiveDate() );

        return customerClassification;
    }

    @Override
    public void updateEntityFromDto(CustomerClassificationDTO dto, CustomerClassification entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getType() != null ) {
            entity.setCustomer_classification_type( dto.getType() );
        }
        if ( dto.getValue() != null ) {
            entity.setCustomer_classification_value( dto.getValue() );
        }
        if ( dto.getEffectiveDate() != null ) {
            entity.setEffective_date( dto.getEffectiveDate() );
        }
    }

    private Long entityCustomerCustomer_identifier(CustomerClassification customerClassification) {
        if ( customerClassification == null ) {
            return null;
        }
        CustomerName customer = customerClassification.getCustomer();
        if ( customer == null ) {
            return null;
        }
        Long customer_identifier = customer.getCustomer_identifier();
        if ( customer_identifier == null ) {
            return null;
        }
        return customer_identifier;
    }

    private Long entityCustomer_detailsCustomer_identifier(CustomerClassification customerClassification) {
        if ( customerClassification == null ) {
            return null;
        }
        CustomerDetail customer_details = customerClassification.getCustomer_details();
        if ( customer_details == null ) {
            return null;
        }
        Long customer_identifier = customer_details.getCustomer_identifier();
        if ( customer_identifier == null ) {
            return null;
        }
        return customer_identifier;
    }
}
