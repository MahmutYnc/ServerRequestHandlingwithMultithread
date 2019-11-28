package com.company;

public class SubThreadCreator extends Thread {
    private MotherThread motherThread;

    public SubThreadCreator(MotherThread motherThread) {
        this.motherThread = motherThread;
    }
    public void run() {
        while (true) {
            for (int i = 0; i < motherThread.subList.size(); i++) {
                SubThread currentThread = motherThread.subList.get(i);
                System.out.println("--------------   :  " + currentThread.currentReq);
                if (currentThread.currentReq > 350) {
                    SubThread newer = new SubThread(motherThread);
                    newer.currentReq = 175;
                    System.out.println("yeni thread eklenmeliydi");
                    currentThread.currentReq = newer.currentReq;
                    motherThread.subList.add(newer);

                }

                for (int j = 0; j < motherThread.subList.size(); j++) {
                    if (motherThread.subList.get(i).isAlive()==false){
                        motherThread.subList.get(i).start();
                    }
                }

                System.out.println(motherThread.subList.size());

                if(currentThread.data.deger == 0 && motherThread.subList.size() > 2) {
                    //burada thread silme işlemi yapılacak
                }
            }
            try {
                Thread.sleep(300);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
