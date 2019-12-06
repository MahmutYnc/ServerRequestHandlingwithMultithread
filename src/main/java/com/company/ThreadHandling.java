package com.company;


import javax.swing.*;
import java.util.ArrayList;


class MotherThread extends Thread {

    public Request data = new Request();
    public ProgressBar progressBar = new ProgressBar(this);

    public static final int mainCapacity = 10000;
    public int currentReq = 0;

    public int req;
    public int res;



    public static final int requestTime = 300;
    public static final int responseTime = 200;
    public ArrayList<SubThread> subList = new ArrayList<>();
    public ArrayList<JProgressBar> progressBarArrayList = new ArrayList<>();

    SubThreadCreator stc = new SubThreadCreator(this);

    public MotherThread() {
        data.deger = 0;
        SubThread subThread = new SubThread(this);
        subThread.start();
        SubThread subThread1 = new SubThread(this);
        subThread1.start();


        subThread.currentReq = 0;
        subThread1.currentReq = 0;


        subList.add(subThread);
        subList.add(subThread1);

        JProgressBar bar = new JProgressBar();
        bar.setMaximum(5000);
        bar.setStringPainted(true);
        bar.setBounds(47, 255, 200, 55);
        progressBar.frame.getContentPane().add(bar);
        progressBarArrayList.add(bar);

        JProgressBar bar2 = new JProgressBar();
        bar2.setMaximum(5000);
        bar2.setStringPainted(true);
        bar2.setBounds(47, 255, 200, 55);
        progressBar.frame.getContentPane().add(bar2);
        progressBarArrayList.add(bar2);


        stc.start();

    }


    @Override
    public synchronized void run() {
        data.deger = 0;

        while (true) {

            req = (int) (Math.random() * 500);
            res = (int) (Math.random() * 50);

            //Producer part

                if (data.deger + req <= mainCapacity) {
                    data.deger = data.deger + req;
                }else if(data.deger + req > mainCapacity) {
                    data.deger = mainCapacity;
                }


    //        System.out.println(" maindeki istek sayısı : " + data.deger + "\n");
            try {
                Thread.currentThread().sleep(requestTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //eger data.deger kücüktür 10000 ise current req e eklemeye devam et eğer kücük değilse ekleme yapma



            //consumer part

                if(data.deger - res <= 0) {
                    continue;
                }else {
                    data.deger -= res;
                }




                if(getRequest(data.deger) <= data.deger) {
                    data.deger -= getRequest(data.deger);
                } else if(getRequest(data.deger) > data .deger &&data.deger!=0){
                    getRequest(data.deger);
                    data.deger = 0;
                } else if (data.deger == 0){
                    getRequest(0);
                }

            try {
                Thread.currentThread().sleep(responseTime);
            } catch (InterruptedException e) {
                System.out.println("wait interrupted");
            }



        }


    }

    public int getRequest(int conditionalReq) {

        int getRequest = (int)(Math.random()*100);

        if(getRequest > conditionalReq) {
            return conditionalReq;
        }else {
            return getRequest;
        }

    }

}


public class ThreadHandling {


}

class Request {
    int deger;
}

