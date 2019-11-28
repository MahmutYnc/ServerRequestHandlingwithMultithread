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
                if (currentThread.currentReq > 3500) {
                    SubThread newer = new SubThread(motherThread);
                    newer.currentReq = (currentThread.currentReq / 2);
                    System.out.println("yeni thread eklenmeliydi");
                    currentThread.currentReq = newer.currentReq;
                    motherThread.subList.add(newer);

                }

                for (int j = 0; j < motherThread.subList.size(); j++) {
                    if (motherThread.subList.get(i).isAlive() == false) {
                        motherThread.subList.get(i).start();
                    }
                }

                System.out.println(motherThread.subList.size());

                if (currentThread.data.deger == 0 && motherThread.subList.size() > 2) {
                    //burada thread silme işlemi yapılacak
                    currentThread.shutterDown();
                    motherThread.subList.remove(currentThread);
                }
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
