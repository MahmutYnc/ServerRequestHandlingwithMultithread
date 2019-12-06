package com.company;

import javax.swing.*;
import java.io.IOException;

public class SubThreadCreator extends Thread {
    private MotherThread motherThread;
    ProgressBar progressBar;
    public int x = 100, y = 400;
    final static int width = 200, height = 20;


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


                    JProgressBar progress = new JProgressBar();
                    progress.setMaximum(5000);
                    progress.setStringPainted(true);
                    progress.setBounds(x,y,width,height);
                    progressBar.frame.getContentPane().add(progress);

                    motherThread.progressBarArrayList.add(progress);


                    if(y >= 560) {
                        x = 250;
                        y += 20;
                        x += 250;
                    }else {
                        y += 30;
                    }



                }

                for (int j = 0; j < motherThread.subList.size(); j++) {
                    if (motherThread.subList.get(j).isAlive() == false) {
                        motherThread.subList.get(j).start();


                    }
                }


                System.out.println((i+1)+". alt thread -----******-------" + motherThread.subList.get(i).currentReq);

                if (motherThread.subList.get(i).currentReq == 0 && motherThread.subList.size() > 2) {
                    //burada thread silme işlemi yapılacak
                    motherThread.subList.get(i).shutterDown();
                    motherThread.subList.remove(motherThread.subList.get(i));

                    //progress barı silme işlemi
                    progressBar.frame.remove(motherThread.progressBarArrayList.get(i));
                    motherThread.progressBarArrayList.remove(i);
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
