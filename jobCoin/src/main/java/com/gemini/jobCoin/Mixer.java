package com.gemini.jobCoin;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Mixer {

    /*

    1. Generate Deposit Address
        - record Deposit address and list of address to DB
    2. Poll Deposit Addresses
        - Add Address to TransactionMonitor
        - Transfer from deposit address to House account address
    3. Dole funds
        - wait some time
        - transfer from house account to deposit address list

     */


    private JobCoinServiceImpl jobCoinServiceImpl = new JobCoinServiceImpl();
    Map<String, List<String>> addressStore = new HashMap<String, List<String>>();
    private TransactionMonitor transactionMonitor = new TransactionMonitor();

    private static final String houseAccount = "HouseAccount";

    Mixer(){
        doleAddresses();
    }


    public String createDepositAddress(List<String> withdrawAddressList){

        String depositAddress = generateAddress();
        addressStore.put(depositAddress, withdrawAddressList);
        transactionMonitor.addToWatchList(depositAddress);
        return depositAddress;

    }

    static public String generateAddress() {
        String alphabet= "abcdefghijklmnopqrstuvwxyz";
        String s = "";
        Random random = new Random();
        int randomLen = 1+random.nextInt(9);
        for (int i = 0; i < randomLen; i++) {
            char c = alphabet.charAt(random.nextInt(26));
            s+=c;
        }
        return s;
    }

    public void depositToHouseAccount(String address){
        jobCoinServiceImpl.sendCoin(address, houseAccount, jobCoinServiceImpl.getBalance(address));
    }

    public void doleDeposit(String depositAddress, int totalBalance){

        int numOfAddress = addressStore.get(depositAddress).size();
        String distributedBalance = String.valueOf(totalBalance / numOfAddress);

        for (String address : addressStore.get(depositAddress)){
            jobCoinServiceImpl.sendCoin(houseAccount, address, distributedBalance);
        }
        System.out.println("Withdraw made from House Account to distribute");

    }


    public void mixerDeposit(){
        System.out.println("Checking for transactions... Queue size: " + transactionMonitor.addressesToUpdateQueue.size());
        while (!transactionMonitor.addressesToUpdateQueue.isEmpty()){
            try {
                //System.out.println("Deposit attemp made to House Account with address: " + transactionMonitor.addressesToUpdateQueue.peek());
                String depositAddress = transactionMonitor.addressesToUpdateQueue.take();
                int totalBalance = Integer.valueOf(jobCoinServiceImpl.getBalance(depositAddress));
                if (totalBalance != 0){
                    depositToHouseAccount(depositAddress);
                    System.out.println("Transfer made to House Account from " + depositAddress);
                    TimeUnit.SECONDS.sleep(10);
                    doleDeposit(depositAddress,totalBalance);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public void doleAddresses(){

        Runnable dollAddressRunnable = new Runnable() {
            public void run(){
                mixerDeposit();
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(dollAddressRunnable, 0, 10, TimeUnit.SECONDS);



    }

    public int searchKsum(int[] nums, int k){
        HashMap<Integer, Integer> map = new HashMap();
        map.put(0,1);

        int count = 0;
        int currSum = 0;

        for (int i=0; i<nums.length; i++){
            currSum += nums[i];
            int missingSum = currSum - k;

            if (map.containsKey(missingSum)){
                count += map.get(missingSum);
            }
            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        }

        return count;
    }


}










