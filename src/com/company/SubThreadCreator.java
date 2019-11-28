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
                if (currentThread.currentReq > 3500) {
                    SubThread newer = new SubThread(motherThread);
                    newer.currentReq = (currentThread.currentReq* 1/2);
                    System.out.println("yeni thread eklenmeliydi");
                    currentThread.currentReq = newer.currentReq;
                    motherThread.subList.add(newer);

                }

                for (int j = 0; j < motherThread.subList.size(); j++) {
                    if (motherThread.subList.get(j).isAlive() == false) {
                        motherThread.subList.get(j).start();
                    }
                }


                System.out.println((i+1)+"-----******-------" + motherThread.subList.get(i).currentReq);

                if (motherThread.subList.get(i).currentReq == 0 && motherThread.subList.size() > 2) {
                    //burada thread silme işlemi yapılacak
                    motherThread.subList.get(i).shutterDown();
                    motherThread.subList.remove(motherThread.subList.get(i));
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
