package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame2 extends JFrame implements ActionListener{


    private ImageIcon imgrock = new ImageIcon("C:\\Users\\kraym\\IdeaProjects\\Affichage_list\\src\\Project\\Images\\rock.jpeg");
    private ImageIcon imgscisors = new ImageIcon("C:\\Users\\kraym\\IdeaProjects\\Affichage_list\\src\\Project\\Images\\scisors.jpeg");
    private ImageIcon imgpaper = new ImageIcon("C:\\Users\\kraym\\IdeaProjects\\Affichage_list\\src\\Project\\Images\\paper.jpeg");


    private JPanel panel;
    private JPanel panelhead;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panelbuttom;
    private JPanel panelh1;
    private JPanel panelh2;
    private JLabel pic2,pic;
    JLabel connection, rounds, score;
    private JButton play, cancel,scisoresbtn,rockbtn,paperbtn;
    private ImageIcon opimage;
    String choice ="";
    int sc,rds;

    public MyFrame2() {
        init();
    }

    private void init() {
        sc = 0;
        rds = -1;
        connection =new JLabel();
        pic2 = new JLabel();
        pic = new JLabel();
        rounds =new JLabel("Rounds : 0");
        score =new JLabel("Score : "+sc);

        scisoresbtn = new JButton("Scisor");
        rockbtn = new JButton("Rock");
        paperbtn = new JButton("Paper");
        play = new JButton("Play");
        cancel = new JButton("Cancel");

        panelhead= new JPanel(new BorderLayout());
        panelbuttom= new JPanel(new FlowLayout());
        panelh1= new JPanel(new FlowLayout(FlowLayout.CENTER,100,10));
        panelh2= new JPanel(new FlowLayout(FlowLayout.CENTER,150,5));

        panel1=new JPanel();
        panel1.setPreferredSize(new Dimension(330,40));
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT,3,10));
        panel1.setBorder(BorderFactory.createEtchedBorder());

        panel2=new JPanel();
        panel2.setPreferredSize(new Dimension(330,40));
        panel2.setLayout(new FlowLayout(FlowLayout.RIGHT,3,10));
        panel2.setBorder(BorderFactory.createEtchedBorder());

        panel= (JPanel) getContentPane();
        panel.setLayout(new BorderLayout());

        panel.add(panelhead,BorderLayout.NORTH);
        panel.add(panelbuttom,BorderLayout.SOUTH);
        panel.add(panel1,BorderLayout.WEST);
        panel.add(panel2,BorderLayout.EAST);

        panelhead.add(panelh1,BorderLayout.NORTH);
        panelhead.add(panelh2,BorderLayout.SOUTH);

        panelh1.add(connection);
        panelh1.add(rounds);
        panelh1.add(score);

        panelh2.add(scisoresbtn);
        panelh2.add(rockbtn);
        panelh2.add(paperbtn);

        panelbuttom.add(play);
        panelbuttom.add(cancel);

        scisoresbtn.setEnabled(false);
        rockbtn.setEnabled(false);
        paperbtn.setEnabled(false);

        connection.setText("Waiting For Connection");

        play.addActionListener(this);
        cancel.addActionListener(this);
        scisoresbtn.addActionListener(this);
        rockbtn.addActionListener(this);
        paperbtn.addActionListener(this);
    }

    public void paint(String img1,String img2){
        System.out.println(img1);
        if (img1.equals("scisors")){
            pic2.setIcon(imgscisors);
        }else if (img1.equals("paper")) {
            pic2.setIcon(imgpaper);
        }else {
            pic2.setIcon(imgrock);
            System.out.println("Bonjour");
        }
        panel2.add(pic2);
        if (img2.equals("scisors")){
            pic.setIcon(imgscisors);
        }else if (img2.equals("paper")) {
            pic.setIcon(imgpaper);
        }else {pic.setIcon(imgrock);}
        panel1.add(pic);
        this.repaint();
        this.repaint();
    }
    public void incrementScore(){
        sc+=10;
        score.setText("Score : "+sc);
        this.repaint();
    }
    public void setrds(int rds2){
        rds = rds2;
        rounds.setText("Rounds : "+rds);
    }
    public String getChoice(){return choice;}
    public void reintial(String result){
        choice="";
        scisoresbtn.setEnabled(true);
        rockbtn.setEnabled(true);
        paperbtn.setEnabled(true);
        showResult(result);
        panel1.removeAll();
        panel2.removeAll();
        rds-=1;
        rounds.setText("Rounds : "+rds);
        this.repaint();
    }

    public void showResult(String result){
        String[] options = new String[]{"Continue", "Exit"};
        if (result.equals("Win")){
            int response = JOptionPane.showOptionDialog(this, "You Win this Round, your score is "+sc+" !", "You Win",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
            if (response==1){
                System.exit(0);
            }
        }else if (result.equals("Lost")){
            int response = JOptionPane.showOptionDialog(this, "You Lost this Round, your score is "+sc+" !", "You Lost",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
            if (response==1){
                System.exit(0);
            }
        }else {
            int response = JOptionPane.showOptionDialog(this, "You Draw this Round, your score is "+sc+" !", "You Draw",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
            if (response==1){
                System.exit(0);
            }
        }if(rds==0){
            JOptionPane.showMessageDialog(null, "Well Played"+sc+"","Finish", JOptionPane.OK_OPTION);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == cancel) {
            System.exit(0);
        }
       if (source==play){
           connection.setText("Connected");
           scisoresbtn.setEnabled(true);
           rockbtn.setEnabled(true);
           paperbtn.setEnabled(true);
           this.repaint();
        }else{
            play.setEnabled(false);
            if (source==scisoresbtn){
                choice="scisors";
            }else if (source==rockbtn){
                choice="rock";
            }else if (source==paperbtn){
                choice="paper";
            }
            if (choice!=""){
                scisoresbtn.setEnabled(false);
                rockbtn.setEnabled(false);
                paperbtn.setEnabled(false);
            }
        }
        this.repaint();

    }
}

