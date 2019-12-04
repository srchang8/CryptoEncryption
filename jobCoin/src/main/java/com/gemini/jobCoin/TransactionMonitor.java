package com.gemini.jobCoin;

import java.util.*;
import java.util.concurrent.*;

public class TransactionMonitor {

    /*
        1. Poll transaction to see if there was transaction made
        2. ideally we can check DB address balance and jobcoin service balance to see if there was change
     */

    JobCoinServiceImpl jobCoinServiceImpl = new JobCoinServiceImpl();
    HashMap<String, String > addressWatchList = new HashMap<String, String>();


    BlockingQueue<String> addressesToUpdateQueue = new ArrayBlockingQueue<String>(1000);


    TransactionMonitor(){
        pollAddresses();

    }

    public void trackAddresses(){
        System.out.println("polling.. queue size: " + addressesToUpdateQueue.size() );
        for (String address : addressWatchList.keySet()){
            if (!jobCoinServiceImpl.getBalance(address).equals(addressWatchList.get(address))){
                System.out.println("Transaction detected on Address: " + address + " with Balance: " + jobCoinServiceImpl.getBalance(address));
                addressWatchList.put(address, jobCoinServiceImpl.getBalance(address));
                addressesToUpdateQueue.add(address);
            }
        }
    }

    public void addToWatchList(String address){
        addressWatchList.put(address, "0");
    }

    public void pollAddresses(){

        Runnable pollAddressRunnable = new Runnable() {
            public void run(){
                trackAddresses();
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(pollAddressRunnable, 0, 10, TimeUnit.SECONDS);

    }


}
