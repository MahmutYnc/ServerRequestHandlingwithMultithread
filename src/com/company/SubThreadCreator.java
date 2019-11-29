package com.company;

import java.io.IOException;

public class SubThreadCreator extends Thread {
    private MotherThread motherThread;
    private ProgressBar pb ;
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
                pb.loading("Calculating....");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
class ProgressBar {
    private static boolean loading = true;
    public static synchronized void loading(String msg) throws IOException, InterruptedException {
        System.out.println(msg);
        Thread th = new Thread() {
            int i= 10;
            @Override
            public void run() {
                try {
                    System.out.write("\r|".getBytes());
                    while(loading) {
                        System.out.write("-".getBytes());
                        Thread.sleep(500);
                    }
                    System.out.write("| Done \r\n".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        th.start();
    }
}