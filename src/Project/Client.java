package Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;




public class Client {



    Socket socket;
    PrintWriter printWriter;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    Scanner scanner;
    String message;
    String message2;
    String results;
    MyFrame2 fen;
    public Client(MyFrame2 fen) throws IOException {
        this.fen=fen;
        socket = new Socket("localhost",9999);
        System.out.println("Connected" + socket.getLocalPort());
        printWriter = new PrintWriter(socket.getOutputStream());
        inputStreamReader = new InputStreamReader(socket.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        scanner = new Scanner(System.in);
        int rds = Integer.parseInt(bufferedReader.readLine());
        System.out.println(rds);
        fen.setrds(rds);
        communicate();
    }

    public void communicate(){
        while (true){
            while (Objects.equals(fen.getChoice(), "")){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("Choice="+fen.getChoice());
            message = fen.getChoice();
            printWriter.println(message);
            printWriter.flush();
            System.out.println("Choice "+message);
            try {
                message2 = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("message = "+message+"  message 2 = "+message2);
            if (!Objects.equals(message, "") || !Objects.equals(message2, "")){
                fen.paint(message,message2);
                fen.repaint();
                String fenResult="";
                if ((message.equals(message2))) {
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
                fen.repaint();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fen.reintial(fenResult);
                message="";
                message2="";
            }
        }
    }

    public static void main(String[] args) throws IOException {
        MyFrame2 fen = new MyFrame2();
        fen.setTitle("Client Interface");
        fen.setVisible(true);
        fen.setSize(700,500);
        Client client = new Client(fen);
    }
}
