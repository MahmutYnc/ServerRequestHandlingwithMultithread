package com.company;


class MotherThread extends Thread{

    private Request data = new Request();

    public static final int mainCapacity = 10000;
    public int currentReq = 0;

    public int req;
    public int res;

    public static final int requestTime = 500;
    public static final int responseTime = 200;

    public MotherThread(Request data) {
        this.data = data;
    }

    @Override
    public void run() {

        while(true) {

            MainProducer mp = new MainProducer(data);


            req = (int)(Math.random()*100);
            res =(int)(Math.random()*50);



            System.out.println(Thread.currentThread().getName());
                /*try{
                    Thread.currentThread().sleep(requestTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                data.deger += mp.reqReturner();
                System.out.println("üretici : "+ req + "     üretilen : "+ data.deger);

                //eger data.deger kücüktür 10000 ise current req e eklemeye devam et eğer kücük değilse ekleme yapma



                try{
                    Thread.currentThread().sleep(responseTime);
                } catch (InterruptedException e) {
                    System.out.println("wait interrupted");
                }
                data.deger -= res;
                System.out.println("tüketici : "+res + "      tüketilen : "+ data.deger);


            }

    }
}



public class ThreadHandling {
    private static Request data;

    public static void main(String[] args) {
        data = new Request();
        data.deger = 0;

        MotherThread motherThread = new MotherThread(data);
        motherThread.start();


    }
}
class Request{
    int deger;
}