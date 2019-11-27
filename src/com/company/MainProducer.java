package com.company;

public class MainProducer extends Thread {
    private Request data;

    public int currentReq = 0;
    public int req;
    public static final int requestTime = 500;

    public MainProducer(Request data) {
        this.data = data;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());

    }

    public int reqReturner() {
        while(true) {
            req = (int)(Math.random()*100);

            System.out.println(Thread.currentThread().getName());

            try{
                Thread.currentThread().sleep(requestTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentReq += req;
            System.out.println("üretici : "+data.deger);

            return currentReq;
        }
    }
}
