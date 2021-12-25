package com.gemini.jobCoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class JobCoinApplication {

	private static final Logger log = LoggerFactory.getLogger(JobCoinApplication.class);


	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(JobCoinApplication.class, args);


		/*
		1. generate list addressmvn
		2. call mixer to get deposit address
		deposit
		3. send bitcoin to deposit address
		 */


		com.gemini.jobCoin.Mixer mixer = new com.gemini.jobCoin.Mixer();
		com.gemini.jobCoin.JobCoinServiceImpl jobCoinServiceImpl = new com.gemini.jobCoin.JobCoinServiceImpl();

		List<String> depositAddressList = new ArrayList<String>();

		depositAddressList.add("abcd");
		depositAddressList.add("abcdd");
		depositAddressList.add("abcddd");

		String depositAdd = mixer.createDepositAddress(depositAddressList);


		jobCoinServiceImpl.sendCoin("Alice", depositAdd, "3");


	}

















}
