package com.gemini.jobCoin.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    private String timeStamp;
    private String toAddress;
    private String amount;


    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }



    public class Scrap{

        List<List<Integer>> result = new ArrayList();

        public void findCombo(int[] nums){

            comboHelper(nums, new ArrayList<>(), 0);
        }

        public void comboHelper(int[] nums, List<Integer> tempList, int start){
            result.add(new ArrayList<Integer>(tempList));

            for (int i=start; i<nums.length; i++){
                tempList.add(nums[i]);
                comboHelper(nums, tempList, start+1);
                tempList.remove(tempList.size()-1);
            }
        }


    }
}
