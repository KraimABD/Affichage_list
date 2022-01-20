package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class Test extends JFrame implements ListSelectionListener {

    String[] couleurs = {"Red", "blue", "blanc", "rose", "noir"};;
    JList list;
    JScrollPane jScrollPane;
    JPanel jPanel;
    // Button btn;

    public Test(){

        list = new JList(couleurs);
        jScrollPane = new JScrollPane(list);



        // btn = new Button("Submit");
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.getContentPane().setLayout(new BorderLayout());

        list.addListSelectionListener(this);
        this.add(jScrollPane);
        list.setSelectedIndex(1);

        this.getContentPane().add(jScrollPane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Hi");
        this.setSize(800, 700);
        this.setVisible(true);
        this.setResizable(true);

    }

    public static void main(String[] args) {
        Test test = new Test();

    }

    

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()){
            System.out.println("==>"+e.getSource());
        }
        else {
            System.out.println("***** Affichage des élément sélectionnés:");
            List<String> valeurs = list.getSelectedValuesList();

            for (int i=0; i< valeurs.size(); i++){
                System.out.println(valeurs.get(i));
            }

        }

    }
}
