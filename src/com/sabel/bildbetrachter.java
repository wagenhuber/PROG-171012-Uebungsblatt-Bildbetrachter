package com.sabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class bildbetrachter extends JFrame {


    //DATENFELDER
    private JPanel panelWest;
    private JPanel panelCenter;
    private JPanel panelSouth;

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




    //Konstruktor
    public bildbetrachter() {
        super("Bilder");
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setSize(400, 400);
        this.setResizable(false);
        this.initComponents();
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }


        });
    }


    //Methoden

    private void exit() {
        int exitParameter = JOptionPane.showConfirmDialog(bildbetrachter.this, "Möchten  Sie das Programm jetzt beenden?", "Programm beenden?", JOptionPane.YES_NO_OPTION);
        if (exitParameter == JOptionPane.YES_OPTION){//ist eine KONSTANTE und kann daher einfach abegefragt werden!!!
            System.exit(NORMAL);
        }
    }


    private void initComponents() {

        //Anzahl Bilder im Bilderverzeichnis erfassen:
        directory = new File("C:\\Users\\wagenhuberg\\IdeaProjects\\171012-Uebungsblatt-Bildbetrachter-GW\\src\\com\\sabel\\bilder");
        //directory = new File("src\\com\\sabel\\bilder");
        //directory = new File(directory.getAbsolutePath());
        System.out.println(directory.getAbsolutePath());
        String[] fileList = directory.list();
        fileCount = fileList.length; // Die Anzahl gefundener Dateien ist nun in count...


        //INIT PANELs
        panelWest = new JPanel();
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
        panelCenter = new JPanel();
        panelSouth = new JPanel();


        //INIT ButtonGroup, ImageIcons-Array, RadioButtons-Array, jLabel für Bild, Scrollpane für Bildlaufleiste
        buttonGroup = new ButtonGroup();
        radioButtons = new JRadioButton[fileCount];

        imageIcons = new ImageIcon[fileCount];
        for (int index = 0; index < fileCount; index++) {

            imageIcons[index] = new ImageIcon(getClass().getResource("./bilder/Bild" + (index + 1) + ".jpg"));
            //imageIcons[index] = new ImageIcon(getClass().getResource(+"Bild" + (index + 1) + ".jpg"));
        }

        //Jlabel
        jLabel = new JLabel(imageIcons[0]);

        //JScrollpane
        scrollPane = new JScrollPane(jLabel);


        //INIT Buttons im Bereich South und ActionListener angefügt
        buttonForward = new JButton("Nächstes Bild");
        meinActionListenerForwardButton alfb;
        buttonForward.addActionListener(alfb = new meinActionListenerForwardButton());

        buttonBackwared = new JButton("Vorheriges Bild");
        meinActionListenerBackwardButton albb;
        buttonBackwared.addActionListener(albb = new meinActionListenerBackwardButton());


        //INIT RadioButtons + ADD RadioButtions to JFRAME
        for (int i = 0; i < fileCount; i++) {
            radioButtons[i] = new JRadioButton("Bild" + (i + 1));
            meinActionListener meinal;
            radioButtons[i].addActionListener(meinal = new meinActionListener(i));

            panelWest.add(radioButtons[i]);
            buttonGroup.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);


        //ADD Panels to JFrame
        this.add(panelWest, BorderLayout.WEST);
        this.add(panelCenter);
        this.add(panelSouth, BorderLayout.SOUTH);

        //ADD Scrollpane to JFrame
        this.add(scrollPane, BorderLayout.CENTER);

        //ADD Buttons to Panel
        panelSouth.add(buttonBackwared);
        panelSouth.add(buttonForward);



    }//ende InitComponents







    //Prüfung welcher RadioButton ist ausgewählt
    public int getSelectedRadioButton() {
        int index = 0;
        while (!radioButtons[index].isSelected()) {
            index++;
        }
        return index;
    }


    //Innere Klassen:


    //Innere Klasse - für ActionListener RadioButtons
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


    //Innere Klasse - für ActionListener ButtonForward
    public class meinActionListenerForwardButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectRadioButton = getSelectedRadioButton();
            if (getSelectedRadioButton() == (imageIcons.length - 1)) {
                jLabel.setIcon(imageIcons[0]);
                radioButtons[0].setSelected(true);
            } else {
                jLabel.setIcon(imageIcons[(selectRadioButton + 1)]);
                radioButtons[(selectRadioButton + 1)].setSelected(true);
            }
        }
    }


    //Innere Klasse - für ActionListener ButtonBackward
    public class meinActionListenerBackwardButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectRadioButton = getSelectedRadioButton();
            if (getSelectedRadioButton() == (imageIcons.length - imageIcons.length)) {
                jLabel.setIcon(imageIcons[(imageIcons.length - 1)]);
                radioButtons[(radioButtons.length - 1)].setSelected(true);
            } else {
                jLabel.setIcon(imageIcons[(selectRadioButton - 1)]);
                radioButtons[(selectRadioButton - 1)].setSelected(true);
            }
        }
    }


    //MAIN
    public static void main(String[] args) {
        new bildbetrachter();
    }

}
