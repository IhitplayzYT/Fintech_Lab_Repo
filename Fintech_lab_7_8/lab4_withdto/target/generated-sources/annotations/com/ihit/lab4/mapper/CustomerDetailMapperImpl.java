package com.ihit.lab4.mapper;

import com.ihit.lab4.dto.CustomerDetailDTO;
import com.ihit.lab4.entity.CustomerDetail;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-16T19:04:31+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 25.0.2 (Arch Linux)"
)
@Component
public class CustomerDetailMapperImpl implements CustomerDetailMapper {

    @Override
    public CustomerDetailDTO toDTO(CustomerDetail e) {
        if ( e == null ) {
            return null;
        }

        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO();

        customerDetailDTO.setId( e.getCustomer_identifier() );
        customerDetailDTO.setGender( e.getCustomer_gender() );
        customerDetailDTO.setType( e.getCustomer_type() );
        customerDetailDTO.setDob( e.getCustomer_dob() );
        customerDetailDTO.setLang( e.getCustomer_lang() );
        customerDetailDTO.setStatus( e.getCustomer_status() );
        customerDetailDTO.setCountry( e.getCustomer_country() );

        return customerDetailDTO;
    }

    @Override
    public CustomerDetail toEntity(CustomerDetailDTO d) {
        if ( d == null ) {
            return null;
        }

        CustomerDetail customerDetail = new CustomerDetail();

        customerDetail.setCustomer_identifier( d.getId() );
        customerDetail.setCustomer_gender( d.getGender() );
        customerDetail.setCustomer_type( d.getType() );
        customerDetail.setCustomer_dob( d.getDob() );
        customerDetail.setCustomer_lang( d.getLang() );
        customerDetail.setCustomer_status( d.getStatus() );
        customerDetail.setCustomer_country( d.getCountry() );

        return customerDetail;
    }

    @Override
    public void updateEntityFromDto(CustomerDetailDTO dto, CustomerDetail e) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getGender() != null ) {
            e.setCustomer_gender( dto.getGender() );
        }
        if ( dto.getType() != null ) {
            e.setCustomer_type( dto.getType() );
        }
        if ( dto.getDob() != null ) {
            e.setCustomer_dob( dto.getDob() );
        }
        if ( dto.getLang() != null ) {
            e.setCustomer_lang( dto.getLang() );
        }
        if ( dto.getStatus() != null ) {
            e.setCustomer_status( dto.getStatus() );
        }
        if ( dto.getCountry() != null ) {
            e.setCustomer_country( dto.getCountry() );
        }
    }
}
