package com.company;

public class SubThread extends Thread {
    private int i;
    public static final int subCapacity = 5000;
    public int currentReq = 0;
    public int mainReq;

    public int req;
    public int res;

    public static final int requestTime = 500;
    public static final int responseTime = 300;

    Request data = new Request();
    MotherThread motherThread;

    public SubThread(MotherThread motherThread) {
        this.motherThread = motherThread;
    }

    @Override
    public void run() {
        while(true){

            data.deger = motherThread.getRequest(101);
            try {
                Thread.currentThread().sleep(requestTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Request part
            if(data.deger <= 100) {
                req = (int)(Math.random()*data.deger);
            }else{
                req = (int)(Math.random()*100);
            }

            if(currentReq + req <= subCapacity) {
                currentReq += req;
                data.deger -= req;
            }else if (currentReq + req > subCapacity) {
                continue;
            }

            System.out.println("SubThread-->  alınan istek : " + req + "     toplam istek sayısı : "
                    + currentReq);



            //Response part
            try {
                Thread.currentThread().sleep(responseTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(currentReq < 50) {
                res = (int)(Math.random()*currentReq);
            }else{
                res = (int)(Math.random()*50);
            }

            if(currentReq - res >= 0) {
                currentReq -= res;
            }else if (currentReq < 0) {
                //Thread.currentThread().stop();
                continue;
            }

            System.out.println("SubThread-->"+ Thread.currentThread()+" cevaplanan istek : " + res
                    + "     eldeki isteksayısı : " + currentReq);

        }



    }
}
