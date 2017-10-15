package com.sabel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class bildbetrachter extends JFrame {

    //ACHTUNG ICON SOLL EIN ARRAY WERDEN!!!!!!!!!!!!!!!!!!!!!!!!


    //DATENFELDER
    private JPanel panelWest;
    private JPanel panelCenter;
    private JPanel panelSouth;
    private JLabel label;
    private Icon icon;
    private JRadioButton[] radioButtons;
    private ButtonGroup buttonGroup;
    private JButton buttonForward;
    private JButton buttonBackwared;
    private JScrollPane scrollPane;
    private File directory;



    public bildbetrachter() {
        super("Bilder");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.initComponents();
        //this.initEvents();
        this.setVisible(true);

    }

    private void initComponents() {

        //INIT PANELs
        panelWest = new JPanel();
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
        panelCenter = new JPanel();
        panelSouth = new JPanel();


        //INIT ButtonGroup and Array and Icon and Scrollpane
        buttonGroup = new ButtonGroup();
        radioButtons = new JRadioButton[4];
        //icon = new ImageIcon("bild1");
        label = new JLabel(new ImageIcon(getClass().getResource("Bild1.jpg")));
        //label.setIcon(icon);
        scrollPane = new JScrollPane(label);
        buttonForward = new JButton("Nächstes Bild");
        buttonBackwared = new JButton("Vorheriges Bild");


        //ADD Panels to JFrame
        this.add(panelWest, BorderLayout.WEST);
        this.add(panelCenter);
        this.add(panelSouth, BorderLayout.SOUTH);


        //Bildverzeichnis inventisieren
        directory = new File("./");
        String[] fileList = directory.list(); // nur gewissen Dateien
        int count = fileList.length; // Die Anzahl gefundener Dateien ist nun in count
        System.out.println(count);


        //INIT RadioButtons




        for (int i = 0; i < 4; i++) {
            radioButtons[i] = new JRadioButton("Bild" + (i + 1));
            panelWest.add(radioButtons[i]);
            buttonGroup.add(radioButtons[i]);
        }

        //Buttons zu Panel hinzufügen
        panelSouth.add(buttonBackwared);
        panelSouth.add(buttonForward);


        //ADD Scrollpane to JFrame
        this.add(scrollPane, BorderLayout.CENTER);







    }//ende InitComponents


    //MAIN
    public static void main(String[] args) {
        new bildbetrachter();
    }

}
