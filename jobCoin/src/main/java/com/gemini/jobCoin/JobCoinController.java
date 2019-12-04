package com.gemini.jobCoin;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobCoinController {

    @RequestMapping("/")
    public String deposit(){
        return "deposit address";
    }
}
