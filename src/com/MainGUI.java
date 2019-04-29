package com;

import gui.GManager;

import javax.swing.*;
import java.awt.*;


public class MainGUI extends JFrame {

    public MainGUI() {
        setTitle("BnB PC Offline");
        Image image = Toolkit.getDefaultToolkit().getImage("src/asset/icon_titlebar.png");
        setIconImage(image);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        add(new GManager());
        setVisible(true);
    }

}
