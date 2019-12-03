package com.company;


import java.util.ArrayList;

class MotherThread extends Thread {

    public Request data = new Request();

    public static final int mainCapacity = 10000;
    public int currentReq = 0;

    public int req;
    public int res;

    public static final int requestTime = 300;
    public static final int responseTime = 200;
    public ArrayList<SubThread> subList = new ArrayList<SubThread>();

    SubThreadCreator stc = new SubThreadCreator(this);

    public MotherThread() {
        SubThread subThread = new SubThread(this);
        subThread.start();
        SubThread subThread1 = new SubThread(this);
        subThread1.start();
        subThread.currentReq = 3000;
        subThread1.currentReq = 2500;
        subList.add(subThread);
        subList.add(subThread1);

        stc.start();
    }


    @Override
    public void run() {
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

