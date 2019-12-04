package com.gemini.jobCoin;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface JobCoinServiceInt {

    public ResponseEntity<String> sendCoin(String sender, String receiver, String amount) throws IOException;
    public String getBalance(String address);
}
