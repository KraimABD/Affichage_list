package Threads;

public class MonThread extends Thread{

    public MonThread(String name){
        super(name);
    }

    @Override
    public void run() {

        if (!isInterrupted()){
            for (int i=0; i<5 ; i++){
                System.out.print(this.getName() + "");
            }
        }
        else {
            System.out.println(this.getName() + " is interrupted");
        }

    }


    public static void main(String[] args) {
        MonThread A = new MonThread("A");
        MonThread B = new MonThread("B");
        MonThread C = new MonThread("C");

        System.out.println("is A alive -1:"+ A.isAlive());
        A.start();
        System.out.println("is A alive -2:"+ A.isAlive());


        B.start();

        A.interrupt();

        C.start();

        System.out.println(Thread.currentThread().getName() + " J'ai fini");


    }


}
