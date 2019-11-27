package com.company;


class MotherThread extends Thread {

    public Request data = new Request();

    public static final int mainCapacity = 10000;
    public int currentReq = 0;

    public int req;
    public int res;

    public static final int requestTime = 500;
    public static final int responseTime = 200;

    public MotherThread() {
        SubThread subThread = new SubThread(this);
        subThread.start();
    }

    public int getCurrentReq() {
        return currentReq;
    }

    public void setCurrentReq(int currentReq) {
        this.currentReq = currentReq;
    }

    @Override
    public void run() {
        data.deger = 0;

        while (true) {

            req = (int) (Math.random() * 100);
            res = (int) (Math.random() * 50);

            //Producer part
            if (data.deger + req <= mainCapacity) {
                data.deger = data.deger + req;
            }else if(data.deger + req > mainCapacity) {
                data.deger = mainCapacity;
            }

            System.out.println("main'e gelen istek : " + req + "    maindeki istek sayısı : " + data.deger + "\n");
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

            System.out.println("main cevap : " + res + "         kalan istek : " + data.deger +"\n");

            try {
                Thread.currentThread().sleep(responseTime);
            } catch (InterruptedException e) {
                System.out.println("wait interrupted");
            }


            if(getRequest() <= data.deger) {
                data.deger -= getRequest();
            }

        }


    }

    public int getRequest() {
        int getRequest = (int)(Math.random()*100);
        return getRequest;
    }
}


public class ThreadHandling {
    private static Request data;

    public static void main(String[] args) {
        MotherThread motherThread = new MotherThread();
        motherThread.start();

    }

}

class Request {
    int deger;
}

