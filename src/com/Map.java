package com;

import enums.MapName;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Map {
    MapName mapName;
    private String brick;
    private String floor;
    private String gift_box;
    private String stone;

    private MapName getMapName() {
        MapName gMapName;
        try (InputStream input = new FileInputStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            String string = properties.getProperty("MAP");
            switch (string) {
                case "XMAS":
                    gMapName = MapName.XMAS;
                    break;
                case "LANDMODE":
                    gMapName = MapName.LANDMODE;
                    break;
                case "TOWN":
                    gMapName = MapName.TOWN;
                    break;
                case "UNDERWATER":
                    gMapName = MapName.UNDERWATER;
                    break;
                default:
                    gMapName = MapName.DESERT;
            }
            return gMapName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return MapName.DESERT;
    }

    private JLabel loadImage(int x, int y, String url){
        JLabel jLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(url));
        jLabel.setSize(imageIcon.getIconWidth(),imageIcon.getIconHeight());
        jLabel.setLocation(x-imageIcon.getIconWidth()/2, y);
        jLabel.setIcon(imageIcon);
        return jLabel;

    }

    private void loadMap() {
        mapName = getMapName();
        brick = "/asset/map_asset/"+mapName.toString()+"/brick.png";
        floor = "/asset/map_asset/"+mapName.toString()+"/floor.png";
        gift_box = "/asset/map_asset/"+mapName.toString()+"/gift_box.png";
        stone = "/asset/map_asset/"+mapName.toString()+"/stone.png";
    }

    private int getImageIconWidth(String url){
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(url));
        return imageIcon.getIconWidth();
    }

    private int getImageIconHeight(String url){
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(url));
        return imageIcon.getIconHeight();
    }

    public int getHeight() {
        return showMap(new Position(0,0)).getHeight();
    }
    public int getWidth() {
        return showMap(new Position(0,0)).getWidth();
    }

    public JLabel showMap(Position position){
        loadMap();
        JLabel jLabel = new JLabel();
        Position playGround = new Position(0,0);
        playGround.setX(position.getX());
        playGround.setY(position.getY());

        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 8; j++) {
                    jLabel.add(loadImage(playGround.getX(), playGround.getY(), floor));
                    playGround.setX(playGround.getX()+128);
                }
            playGround.setY(playGround.getY()+128);
            playGround.setX(playGround.getX()-128*8);
        }
        Position gridPosition = new Position(0,0);
        gridPosition.setX(position.getX()-getImageIconWidth(brick)*3/2);
        gridPosition.setY(position.getY()-getImageIconHeight(brick));
        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 9; j++) {
                if (i == 1 || i == 7) { // Why does jLabel "add" image??
                    jLabel.add(loadImage(gridPosition.getX(), gridPosition.getY(), brick));
                    gridPosition.setX(gridPosition.getX() + 64);
                    jLabel.add(loadImage(gridPosition.getX(), gridPosition.getY(), brick));
                    gridPosition.setX(gridPosition.getX() + 64);
                } else if(j == 1) {
                    jLabel.add(loadImage(gridPosition.getX(), gridPosition.getY(), brick));
                    gridPosition.setY(gridPosition.getY() + 64);
                    jLabel.add(loadImage(gridPosition.getX(), gridPosition.getY(), brick));
                    gridPosition.setY(gridPosition.getY() - 64);
                    gridPosition.setX(gridPosition.getX() + 64 + getImageIconWidth(floor)*8);
                } else if(j == 9) {
                    jLabel.add(loadImage(gridPosition.getX(), gridPosition.getY(), brick));
                    gridPosition.setY(gridPosition.getY()+64);
                    jLabel.add(loadImage(gridPosition.getX(), gridPosition.getY(), brick));
                    gridPosition.setY(gridPosition.getY() - 64);
                    gridPosition.setX(gridPosition.getX()+64);
                }
            }
            if (i == 1 || i == 7)
                gridPosition.setY(gridPosition.getY()+64);
            else
                gridPosition.setY(gridPosition.getY()+128);
            gridPosition.setX(gridPosition.getX()-getImageIconWidth(floor)*9);
        }
        jLabel.setBounds(0,0, 1366, 896); // How about screen resolution?
        System.out.print("DONE!");
        return jLabel;
    }

}

