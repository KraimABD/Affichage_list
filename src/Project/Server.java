package Project;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



/*scisors=500
paper=501
rock=502*/






public class Server  {


    ServerSocket serverSocket;
    Socket socket;
    PrintWriter printWriter;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    Scanner scanner;
    String message;
    String message2;
    String results;
    Thread receive,send;
    MyFrame fen;

    public Server(MyFrame fen) throws IOException, InterruptedException {
        this.fen=fen;
        serverSocket = new ServerSocket(9999);
        socket = serverSocket.accept();
        System.out.println("Connected" + serverSocket.getLocalPort());
        printWriter = new PrintWriter(socket.getOutputStream());
        inputStreamReader = new InputStreamReader(socket.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        scanner = new Scanner(System.in);
        //communicate();
        while (this.fen.rds==-1 && this.fen.choice.isEmpty()){
            System.out.println(this.fen.rds);
            System.out.println(this.fen.choice);
            Thread.sleep(1000);
        }
        printWriter.println(fen.rds);
        printWriter.flush();
        System.out.println("sent");
        Logic();

    }

    public void communicate2() throws IOException, InterruptedException {
        while(fen.rds==-1){
            Thread.sleep(1000);
        }

    message2 = bufferedReader.readLine();
    while (message2.isEmpty() || fen.choice.isEmpty()){
        Thread.sleep(1000);
    }
    message = fen.choice;
    printWriter.println(message);
    printWriter.flush();
    }
    public void Logic() throws InterruptedException, IOException {
        String fenResult = null;
        while (fen.rds>0){
            communicate2();
            System.out.println("Round "+fen.rds);
    //                if (((message=="rock") || (message=="scisors") || (message=="paper")) && ((message2=="rock") || (message2=="scisors") || (message2=="paper"))){
            //System.out.println("In");
            if ((message.equals(message2)) ) {
                fenResult="Draw";
                System.out.println("Result Draw");
            } else if ((message.equals("scisors") && message2.equals("paper")) || (message.equals("paper") && message2.equals("rock")) || (message.equals("rock") && message2.equals("scisors"))) {
                fenResult="Win";
                System.out.println("You Win, Server");
                fen.incrementScore();
            } else if ((message2.equals("scisors") && message.equals("paper")) || (message2.equals("paper") && message.equals("rock")) || (message2.equals("rock") && message.equals("scisors"))) {
                fenResult="Lost";
                System.out.println("You lost, Server");
            }
            fen.rds = fen.rds -1;
            fen.paint(message,message2);
            fen.repaint();
            fen.rounds.setText("Rounds : "+fen.rds);
            Thread.sleep(1500);
            fen.reintial(fenResult);

        }
    }

        public static void main(String[] args) throws IOException, InterruptedException {
            MyFrame fen = new MyFrame();
            fen.setTitle("Server Interface");
            fen.setVisible(true);
            fen.setSize(700,500);
            Server server = new Server(fen);
    }
}
