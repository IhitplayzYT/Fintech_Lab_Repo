package com.mit.batch;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.context.annotation.Configuration;

import com.mit.entity.CustomerDetails;


@Configuration
public class MyItemReadListener implements ItemReadListener<CustomerDetails> {

	private int itemCount = 0;
	@Override
	public void beforeRead() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterRead(CustomerDetails item) {
		++itemCount;
		
	}

	@Override
	public void onReadError(Exception ex) {
		// TODO Auto-generated method stub
		
	}
	public int getItemCount() {
		return itemCount;
	}

}