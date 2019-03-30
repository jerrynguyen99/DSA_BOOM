package com;

import gui.GManager;

import javax.swing.*;


public class MainGUI extends JFrame {
    public MainGUI() {

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        add(new GManager());
        setVisible(true);
    }

}
