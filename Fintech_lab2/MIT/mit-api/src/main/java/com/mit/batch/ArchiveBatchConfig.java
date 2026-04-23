package com.mit.batch;

import java.util.Collections;
import java.util.Date;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mit.dao.CustomerDetailsBackUpRepository;
import com.mit.entity.CustomerDetails;
import com.mit.entity.CustomerDetailsBackup;


@Configuration
@EnableBatchProcessing
public class ArchiveBatchConfig {
	@Autowired
	CustomerDetailsBackUpRepository customerDetailsBackupRepository;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private JobBuilderFactory jobBuilderFactory;


	@Autowired
	MyItemReadListener myItemReadListener;

	final long oneDayInMillis = 24 * 60 * 60 * 1000;
	int oldNoOfDayForArchive = 1;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Bean
	public JpaPagingItemReader<CustomerDetails> jpaPagingItemReader() {
		Date cutoffTimeDate = new Date(new Date().getTime() - (oldNoOfDayForArchive * oneDayInMillis));
		JpaPagingItemReader<CustomerDetails> reader = new JpaPagingItemReaderBuilder<CustomerDetails>()
				.name("myEntityReader").entityManagerFactory(entityManagerFactory)
				.queryString("SELECT e FROM CustomerDetails e WHERE e.hostTimestamp > :someValue")
				.parameterValues(Collections.singletonMap("someValue", cutoffTimeDate)).pageSize(100).build();
		return reader;
	}

	@Bean
	public ArchiveBatchProcessor createProcessor() {
		// TODO Auto-generated method stub
		return new ArchiveBatchProcessor();

	}

	@Bean
	public RepositoryItemWriter<CustomerDetailsBackup> itemWriter() {
		RepositoryItemWriter<CustomerDetailsBackup> repositoryItemWriter = new RepositoryItemWriter<CustomerDetailsBackup>();
		repositoryItemWriter.setRepository(customerDetailsBackupRepository);
		repositoryItemWriter.setMethodName("save");
		return repositoryItemWriter;
	}

	@Bean
	public Step step() {

		Step step = stepBuilderFactory.get("step-1").<CustomerDetails, CustomerDetailsBackup>chunk(10)
				.reader(jpaPagingItemReader()).processor(createProcessor()).writer(itemWriter())
				.listener(myItemReadListener).build();
		if (myItemReadListener.getItemCount() > 0) {
			//do extra operation like send Email
		}
		return step;

	}

	
	@Bean
	public Job job() {
		return jobBuilderFactory.get("CustomerBackup-job").flow(step()).end().build();
	}

}