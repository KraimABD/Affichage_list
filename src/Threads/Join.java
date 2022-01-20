package Threads;

import org.w3c.dom.ls.LSOutput;

public class Join extends Thread{


    public static void main(String[] args) throws InterruptedException {


        Thread exameple = new Thread() {

            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException exception){

                }
            }
        };


        exameple.start();
        exameple.join();
        System.out.println(Thread.currentThread().getName() + " J'ai Fini");


    }
}