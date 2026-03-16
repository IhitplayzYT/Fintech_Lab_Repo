package com.mit.batch;

import org.springframework.batch.item.ItemProcessor;

import com.mit.entity.CustomerDetails;
import com.mit.entity.CustomerDetailsBackup;

public class ArchiveBatchProcessor implements ItemProcessor<CustomerDetails, CustomerDetailsBackup> {

	

	@Override
	public CustomerDetailsBackup process(CustomerDetails item) throws Exception {
		
		CustomerDetailsBackup customerDetailsBackup = new CustomerDetailsBackup();
		customerDetailsBackup.setAcceptanceTimestamp(item.getAcceptanceTimestamp());
		customerDetailsBackup.setAcceptanceTimestampUTCoffset(item.getAcceptanceTimestampUTCoffset());
		customerDetailsBackup.setCRUDValue(item.getCRUDValue());
		customerDetailsBackup.setCustomerCountryOrigin(item.getCustomerCountryOrigin());
		customerDetailsBackup.setCustomerDOB(item.getCustomerDOB());
		customerDetailsBackup.setCustomerGender(item.getCustomerGender());
		customerDetailsBackup.setCustomerIdentifier(item.getCustomerIdentifier());
		customerDetailsBackup.setCustomerPreferredLanguage(item.getCustomerPreferredLanguage());
		customerDetailsBackup.setCustomerStatus(item.getCustomerStatus());
		customerDetailsBackup.setCustomerType(item.getCustomerType());
		customerDetailsBackup.setEffectiveDate(item.getEffectiveDate());
		customerDetailsBackup.setHostTimestamp(item.getHostTimestamp());
		customerDetailsBackup.setLocalTimestamp(item.getLocalTimestamp());
		customerDetailsBackup.setProgramID(item.getProgramID());
		customerDetailsBackup.setUserID(item.getUserID());
		customerDetailsBackup.setUuid(item.getUuid());
		customerDetailsBackup.setWorkstationID(item.getWorkstationID());
		customerDetailsBackup.setAcceptanceTimestampUTCoffset(item.getAcceptanceTimestampUTCoffset());
		return customerDetailsBackup;
	}
	
}