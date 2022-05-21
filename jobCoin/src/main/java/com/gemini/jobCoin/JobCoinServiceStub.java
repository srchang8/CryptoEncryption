package com.gemini.jobCoin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemini.jobCoin.domain.Address;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;

@Profile("test")
@Component
public class JobCoinServiceStub implements JobCoinServiceInt {



    @Override
    public String getBalance(String address){
        String addressResponseFile = "AddressInfoResponseStub.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File jsonFile = new File(classLoader.getResource(addressResponseFile).getFile());
        ObjectMapper mapper = new ObjectMapper();

        Address addressResponse = null;
        try {
            addressResponse = mapper.readValue(jsonFile, Address.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return addressResponse.getBalance();
    }



    @Override
    public ResponseEntity<String> sendCoin(String sender, String receiver, String amount) throws IOException {

        /*
        String noFundsResponseFile = "AddressInfoResponseNoFundsStub.json";
        String ç = "AddressInfoResponseNoFundsStub.json";
        String successfulResponse = "AddressInfoResponseNoFundsStub.json";

        ClassLoader classLoader = getClass().getClassLoader();
        File jsonFile = new File(classLoader.getResource(noFundsResponseFile).getFile());
        File jsonFileNoFunds = new File(classLoader.getResource(errorResponse).getFile());
        File jsonFileSuccess = new File(classLoader.getResource(successfulResponse).getFile());

        ObjectMapper mapper = new ObjectMapper();

        Address addressResponse = null;

        try {
            addressResponse = mapper.readValue(jsonFile, Address.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int amountInt = Integer.valueOf(amount);
        int responseBalance = Integer.valueOf(addressResponse.getBalance());
        ResponseEntity<String> responseEntity;
        if (responseBalance < amountInt){
            responseEntity = mapper.readValue(jsonFileNoFunds, String.class);
        }
        */

        List<Integer> result = new ArrayList();
        return new ResponseEntity<String>(null);

    }


}
