package com.gemini.jobCoin;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

//@Profile("local")
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobCoinApplicationTests {


	Mixer mixer = new Mixer();

	@Autowired
	private JobCoinServiceInt jobCoinServiceInt;

	@Test
	public void contextLoads() {

	}

	//Stub Testing used when Jobcoin api is down
	@Test
	public void getBalanceTestSuccess(){
		String address = "abc";
		Assert.assertTrue(jobCoinServiceInt.getBalance(address).equals("50"));
	}


	@Test
	public void createDepositAddressSuccess(){
		List<String> depositAddressList = new ArrayList<String>();

		depositAddressList.add("abcd");
		depositAddressList.add("abcdd");
		depositAddressList.add("abcddd");

		String depositAdd = mixer.createDepositAddress(depositAddressList);

		Assert.assertTrue(mixer.addressStore.get(depositAdd).contains("abcd"));
		Assert.assertTrue(mixer.addressStore.get(depositAdd).contains("abcdd"));
		Assert.assertTrue(mixer.addressStore.get(depositAdd).contains("abcddd"));

	}



}
