package com.company;

public class SubThread extends Thread {
    private int i;
    public static final int subCapacity = 5000;
    public int currentReq = 0;

    public int req;
    public int res;

    public static final int requestTime = 300;
    public static final int responseTime = 200;

    private boolean flag = true;

    Request data = new Request();
    MotherThread motherThread;

    public SubThread(MotherThread motherThread) {
        this.motherThread = motherThread;
    }

    @Override
    public synchronized void run() {
        while (flag) {

            data.deger = motherThread.getRequest(motherThread.data.deger);

            motherThread.data.deger -= data.deger;
            if (motherThread.data.deger <= 0 )
                motherThread.data.deger = 0;

            req = data.deger;


            //Request part

            if (currentReq + req <= subCapacity) {
                currentReq += req;
            } else if (currentReq + req > subCapacity) {
                currentReq = subCapacity;
            }

            try {
                Thread.currentThread().sleep(requestTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



            //Response part

            res = (int) (Math.random() * 75);

            if (currentReq - res >= 0) {
                currentReq -= res;
            } else if (currentReq -res < 0) {
                currentReq = 0;
            }
            try {
                Thread.currentThread().sleep(responseTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }

    public void shutterDown() {
        flag = false;
    }


}
