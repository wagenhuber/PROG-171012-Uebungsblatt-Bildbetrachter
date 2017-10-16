package com.sabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class bildbetrachter extends JFrame {

    //ACHTUNG ICON SOLL EIN ARRAY WERDEN!!!!!!!!!!!!!!!!!!!!!!!!


    //DATENFELDER
    private JPanel panelWest;
    private JPanel panelCenter;
    private JPanel panelSouth;
    //private JLabel[] jLabels;
    private JLabel jLabel;
    private JRadioButton[] radioButtons;
    private ImageIcon[] imageIcons;

    private ButtonGroup buttonGroup;
    private JButton buttonForward;
    private JButton buttonBackwared;
    private JScrollPane scrollPane;
    private File directory;
    private int fileCount;


    private ImageIcon imageIcon;


    public bildbetrachter() {
        super("Bilder");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.initComponents();

        this.setVisible(true);

    }

    private void initComponents() {

        //INIT PANELs
        panelWest = new JPanel();
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
        panelCenter = new JPanel();
        panelSouth = new JPanel();


        //Bildverzeichnis inventisieren
        directory = new File("C:\\Users\\guenther\\IdeaProjects\\SE3-171012-Uebungsblatt-Bildbetrachter\\src\\com\\sabel\\bilder");
        String[] fileList = directory.list();
        fileCount = fileList.length; // Die Anzahl gefundener Dateien ist nun in count...
        System.out.println(fileCount);


        //INIT ButtonGroup, RadioButtons Array, jLabels Array
        buttonGroup = new ButtonGroup();
        radioButtons = new JRadioButton[fileCount];


        /*jLabels = new JLabel[fileCount];*/
       /* for (int i = 0; i < fileCount; i++) {

            jLabels[i] = new JLabel(new ImageIcon(getClass().getResource("./bilder/Bild" + (i + 1) + ".jpg")));

        }*/


        imageIcons = new ImageIcon[fileCount];

        for (int index = 0; index < fileCount; index++) {

            imageIcons[index] = new ImageIcon(getClass().getResource("./bilder/Bild" + (index + 1) + ".jpg"));
        }

        jLabel = new JLabel(imageIcons[0]);

        /*imageIcon = new ImageIcon(getClass().getResource("./bilder/Bild1.jpg"));
        jLabel = new JLabel(imageIcon);*/


        scrollPane = new JScrollPane(jLabel);


        buttonForward = new JButton("Nächstes Bild");
        meinActionListenerForwardButton alfb;
        buttonForward.addActionListener(alfb = new meinActionListenerForwardButton());

        buttonBackwared = new JButton("Vorheriges Bild");



        //ADD Panels to JFrame
        this.add(panelWest, BorderLayout.WEST);
        this.add(panelCenter);
        this.add(panelSouth, BorderLayout.SOUTH);


        //INIT RadioButtons
        for (int i = 0; i < fileCount; i++) {
            radioButtons[i] = new JRadioButton("Bild" + (i + 1));
            meinActionListener meinal;
            radioButtons[i].addActionListener(meinal = new meinActionListener(i));

            panelWest.add(radioButtons[i]);
            buttonGroup.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);


        //Buttons zu Panel hinzufügen
        panelSouth.add(buttonBackwared);
        panelSouth.add(buttonForward);


        //ADD Scrollpane to JFrame
        this.add(scrollPane, BorderLayout.CENTER);


    }//ende InitComponents



    //Prüfung welcher RadioButton ist selected

    public int getSelectedRadioButton(){


        int index = 0;


        while (!radioButtons[index].isSelected()){
            index++;
        }

        return index;

    }




    public class meinActionListener implements ActionListener {

        int i;

        public meinActionListener(int i) {
            this.i = i;
        }


        @Override
        public void actionPerformed(ActionEvent e) {



            jLabel.setIcon(imageIcons[i]);


        }
    }



    public class meinActionListenerForwardButton implements ActionListener{



        @Override
        public void actionPerformed(ActionEvent e) {
            int selectRadioButton = getSelectedRadioButton();
            if(getSelectedRadioButton() == (imageIcons.length - 1)){
                jLabel.setIcon(imageIcons[0]);
            }else{
                jLabel.setIcon(imageIcons[selectRadioButton]);
            }
        }
    }





    //MAIN
    public static void main(String[] args) {
        new bildbetrachter();
    }

}
