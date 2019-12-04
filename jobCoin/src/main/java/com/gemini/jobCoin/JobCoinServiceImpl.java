package com.gemini.jobCoin;

import com.gemini.jobCoin.domain.Address;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;



@Profile("dev")
@Component
public class JobCoinServiceImpl implements JobCoinServiceInt{

    private HttpHeaders headers = new HttpHeaders();
    private RestTemplate restTemplate = new RestTemplate();
    //headers.setContentType(APPLICATION_FORM_URLENCODED);

    @Override
    public ResponseEntity<String> sendCoin(String sender, String receiver, String amount){


        @Value("${cryptoMixer.url.transaction}")
        String url;

        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
        params.add("fromAddress", sender);
        params.add("toAddress", receiver);
        params.add("amount", amount);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        return response;

    }


    @Override
    public String getBalance(String address){

        @Value("${cryptoMixer.url.address}")
        String url;
        url = url + address;
        Address addressResponse = restTemplate.getForObject(url, Address.class);

        return addressResponse.getBalance();
    }


}
