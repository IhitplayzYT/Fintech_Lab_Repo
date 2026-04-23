package com.mit.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
	public class BatchController {

		@Autowired
		private JobLauncher jobLauncher;

		@Autowired
		private Job job;

		
		@Scheduled(cron = "0 48 22 * * *",zone = "Asia/Kolkata") // Schedule at 21:25 PM daily
		public void processBatch() throws JobExecutionAlreadyRunningException, JobRestartException,
				JobInstanceAlreadyCompleteException, JobParametersInvalidException {
//			JobParameters jobParameters = new JobParametersBuilder().addLong("Start-At", System.currentTimeMillis())
//					.toJobParameters();
			JobParameters jobParameters = new JobParametersBuilder().toJobParameters();
			jobLauncher.run(job, jobParameters);
		}

}
