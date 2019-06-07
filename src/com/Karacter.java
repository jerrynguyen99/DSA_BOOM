package com;

import intefaces.Showable;
import intefaces.Walkable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Karacter implements Showable, Walkable {
    private String name;
    private Integer boom;
    private Integer blast;
    private Integer speed;
    private Integer maxBoom;
    private Integer maxBlast;
    private Integer maxSpeed;
    private String url;
    private int scale = 0;
    private int positionX;
    private int positionY;
    private int par;

    public int getPositionX() {
        if (positionX > 4 ) positionX = 0;
        if (positionX < 0 ) positionX = 4;
        return positionX;
    }

    public void setPositionX() {
        if (par == 4) positionX--;
        if (par == 0) positionX++;
        if (positionX == 0) par = 0;
        if (positionX == 4) par = 4;
    }

    public int getPositionY() {
        if (positionY > 4 ) positionY = 0;
        if (positionY < 0 ) positionY = 4;
        return positionY;
    }

    public Karacter() {
            try (InputStream input = new FileInputStream("config.properties")) {
                Properties properties = new Properties();
                properties.load(input);
                this.name = properties.getProperty("NAME");
                this.boom =  Integer.parseInt(properties.getProperty("COUNT"));
                this.blast = Integer.parseInt(properties.getProperty("BLAST"));
                this.speed = Integer.parseInt(properties.getProperty("SPEED"));
                this.maxBoom = Integer.parseInt(properties.getProperty("MAXCOUNT"));
                this.maxBlast = Integer.parseInt(properties.getProperty("MAXBLAST"));
                this.maxSpeed = Integer.parseInt(properties.getProperty("MAXSPEED"));

            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public void toShow(Position position){
        url = "D:/DSA_BOOM/src/asset/character/" + name + ".png";
        changeImg(position);
    }


    @Override
    public String toString() {
//        return "Character DONE!";
        return "[ " + this.name + ": " + this.boom + " | " + this.blast+ " | "+ this.speed + " | MAX: " + this.maxBoom + " | " + this.maxBlast + " | "+ this.maxSpeed + " ] ";
    }

    @Override
    public void changeImg(Position position) {
        BufferedImage image;
        try {
            image =  ImageIO.read(new File(url));
            BufferedImage thisImage = image.getSubimage(getPositionX()*(image.getWidth()/5), getPositionY()*(image.getHeight()/4),
                    image.getWidth()/5,image.getHeight()/4);
            ImageIcon icon = new ImageIcon(thisImage);
            isShowing.setIcon(icon);
            isShowing.setBounds(position.getX(),position.getY(),icon.getIconWidth(),icon.getIconHeight());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JLabel loadLabel() {
        return isShowing;
    }

    @Override
    public void move(KeyEvent event, int speed, boolean isStuck) {
        int keyCode = event.getKeyCode();
        switch ( keyCode ) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                isShowing.setLocation(isShowing.getLocation().x, isShowing.getLocation().y-speed*2);
                this.positionY = 3;
                setPositionX();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                isShowing.setLocation(isShowing.getLocation().x, isShowing.getLocation().y+speed*2);
                this.positionY = 0;
                setPositionX();
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                isShowing.setLocation(isShowing.getLocation().x-speed*2,isShowing.getLocation().y);
                this.positionY = 1;
                setPositionX();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                isShowing.setLocation(isShowing.getLocation().x+speed*2,isShowing.getLocation().y);
                this.positionY = 2;
                setPositionX();
                break;
        }
        changeImg(new Position(isShowing.getLocation()));
    }

    @Override
    public void idle(JLabel label, int state) {

    }

    public Integer getSpeed() {
        return speed;
    }
}
