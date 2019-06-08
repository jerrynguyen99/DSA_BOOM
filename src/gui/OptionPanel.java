package gui;

import com.Position;
import com.Sounds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OptionPanel extends JPanel implements MouseListener {

    private GManager gManager;
    private JLabel Back;
    private JLabel imageCharacter_1; // Poor me, i'm sleeping doing this
    private JLabel imageCharacter_2;
    private JLabel imageCharacter_3;
    private JLabel imageCharacter_4;
    private JLabel imageBomb_1;
    private JLabel imageBomb_2;
    private JLabel imageBomb_3;
    private JLabel imageBomb_4;
    private JLabel imageMap1;
    private JLabel imageMap2;
    private JLabel imageMap3;
    private JLabel imageMap4;
    private JLabel chooseCharacter;
    private JLabel chooseBomb;
    private JLabel chooseMap;
    private Integer chooseCharacterArrowPosition;
    private Integer chooseBombArrowPostion;
    private Integer chooseMapArrowPosition;


    OptionPanel(GManager gManager) {
        setLayout(null);
        this.gManager = gManager;
        drawComponent();
    }

    private void drawComponent() {
        Position position = new Position(gManager.getW_FRAME()/2-150,
                gManager.getH_FRAME() - gManager.getH_FRAME()/10 - 100);
        Position distance = new Position((gManager.getW_FRAME() - 170*4)/ 5,0);
        Back = setLabel(position.getX(),position.getY(),"/asset/menu/button_empty.png",0);
        add(Back);
        Back.addMouseListener(this);
        position.setX(gManager.getW_FRAME()/2 - 170 - distance.getX()/2);
        position.setY(40);
        imageCharacter_2 = setLabel(position.getX(),position.getY(),"/asset/menu/evie.png",170);
        add(imageCharacter_2);
        position.setX(position.getX()- 170 - distance.getX());
        imageCharacter_1 = setLabel(position.getX(),position.getY(),"/asset/menu/boz.png",170);
        add(imageCharacter_1);
        position.setX(gManager.getW_FRAME()/2 + distance.getX()/2);
        imageCharacter_3 = setLabel(position.getX(),position.getY(),"/asset/menu/ike.png",170);
        add(imageCharacter_3);
        position.setX(position.getX() + 170 + distance.getX());
        imageCharacter_4 = setLabel(position.getX(), position.getY(), "/asset/menu/plunk.png",170);
        add(imageCharacter_4);
        imageCharacter_1.addMouseListener(this);
        imageCharacter_2.addMouseListener(this);
        imageCharacter_3.addMouseListener(this);
        imageCharacter_4.addMouseListener(this);

        position.setY(position.getY()+imageCharacter_1.getHeight()+40);
        distance.setX((gManager.getW_FRAME()- 150*4)/5);
        position.setX(distance.getX());
        imageBomb_1 = setLabel(position.getX(),position.getY(),"/asset/bomb/xmas_bomb.png",150);
        add(imageBomb_1);
        position.setX(position.getX()+150+distance.getX());
        imageBomb_2 = setLabel(position.getX(),position.getY(),"/asset/bomb/halloween_bomb.png",150);
        add(imageBomb_2);
        position.setX(position.getX()+150+distance.getX());
        imageBomb_3 = setLabel(position.getX(),position.getY(),"/asset/bomb/robot_bomb.png",150);
        add(imageBomb_3);
        position.setX(position.getX()+150+distance.getX());
        imageBomb_4 = setLabel(position.getX(),position.getY(),"/asset/bomb/rose_bomb.png",150);
        add(imageBomb_4);
        imageBomb_1.addMouseListener(this);
        imageBomb_2.addMouseListener(this);
        imageBomb_3.addMouseListener(this);
        imageBomb_4.addMouseListener(this);

        position.setY(position.getY()+imageBomb_1.getHeight()+40);
        distance.setX((gManager.getW_FRAME()- 128*4)/5);
        position.setX(distance.getX());
        imageMap1 = setLabel(position.getX(),position.getY(),"/asset/map_asset/DESERT/floor.png",0);
        add(imageMap1);
        position.setX(position.getX()+128+distance.getX());
        imageMap2 = setLabel(position.getX(),position.getY(),"/asset/map_asset/TOWN/floor.png",0);
        add(imageMap2);
        position.setX(position.getX()+128+distance.getX());
        imageMap3 = setLabel(position.getX(),position.getY(),"/asset/map_asset/UNDERWATER/floor.png",0);
        add(imageMap3);
        position.setX(position.getX()+128+distance.getX());
        imageMap4 = setLabel(position.getX(),position.getY(),"/asset/map_asset/XMAS/floor.png",0);
        add(imageMap4);
        imageMap1.addMouseListener(this);
        imageMap2.addMouseListener(this);
        imageMap3.addMouseListener(this);
        imageMap4.addMouseListener(this);

        loadInput();
        chooseCharacter = setLabel(imageCharacter_1.getLocation().x - 70 + chooseCharacterArrowPosition*((gManager.getW_FRAME() - 170*4)/ 5 + 170),
                imageCharacter_1.getLocation().y +imageCharacter_1.getHeight()/2-50,"/asset/menu/choose.png",100);
        add(chooseCharacter);

        chooseBomb = setLabel(imageBomb_1.getLocation().x - 70 + chooseBombArrowPostion*((gManager.getW_FRAME() - 150*4)/ 5 + 150),
                imageBomb_1.getLocation().y +imageBomb_1.getHeight()/2-50,"/asset/menu/choose.png",100);
        add(chooseBomb);

        chooseMap = setLabel(imageMap1.getLocation().x - 90 + chooseMapArrowPosition*((gManager.getW_FRAME() - 128*4)/ 5 + 128),
                imageMap1.getLocation().y +imageMap1.getHeight()/2-50,"/asset/menu/choose.png",100);
        add(chooseMap);
        updateUI();
    }

    private void changeLabelPosition(JLabel jLabel, int x, int y){
        int range = 0;
        int start = 0;
        if (x == 0) {range = 170; start = imageCharacter_1.getLocation().x; }
        if (x == 1) {range = 150; start = imageBomb_1.getLocation().x;}
        if (x == 2) {range = 128; start = imageMap1.getLocation().x;}
        jLabel.setLocation(start - 90 + y*((gManager.getW_FRAME() - range*4)/ 5 + range),
                jLabel.getLocation().y + jLabel.getHeight()/2-50);
    }

    private JLabel setLabel(int x, int y, String url, int scale) {
        JLabel jLabel = new JLabel();
        ImageIcon imageIcon;
        if (scale != 0) {
            imageIcon = new ImageIcon(new ImageIcon(getClass().getResource(url)).getImage().getScaledInstance(scale, scale, Image.SCALE_DEFAULT));
        }
        else
        {
            imageIcon = new ImageIcon(getClass().getResource(url));
        }
        jLabel.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jLabel.setLocation(x, y);
        jLabel.setIcon(imageIcon);
        return jLabel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel event = (JLabel) e.getSource();
        if (imageCharacter_1.equals(event)) {
            chooseCharacterArrowPosition = 0;
        } else if (imageCharacter_2.equals(event)) {
            chooseCharacterArrowPosition = 1;
        } else if (imageCharacter_3.equals(event)) {
            chooseCharacterArrowPosition = 2;
        } else if (imageCharacter_4.equals(event)) {
            chooseCharacterArrowPosition = 3;
        }
        changeLabelPosition(chooseCharacter, 0, chooseCharacterArrowPosition);

        if (imageCharacter_1.equals(event)) {
            chooseCharacterArrowPosition = 0;
        } else if (imageCharacter_2.equals(event)) {
            chooseCharacterArrowPosition = 1;
        } else if (imageCharacter_3.equals(event)) {
            chooseCharacterArrowPosition = 2;
        } else if (imageCharacter_4.equals(event)) {
            chooseCharacterArrowPosition = 3;
        }
        changeLabelPosition(chooseCharacter, 0, chooseCharacterArrowPosition);

        if (imageBomb_1.equals(event)) {
            chooseBombArrowPostion = 0;
        } else if (imageBomb_2.equals(event)) {
            chooseBombArrowPostion = 1;
        } else if (imageBomb_3.equals(event)) {
            chooseBombArrowPostion = 2;
        } else if (imageBomb_4.equals(event)) {
            chooseBombArrowPostion = 3;
        }
        changeLabelPosition(chooseBomb, 1, chooseBombArrowPostion);

        if (imageMap1.equals(event)) {
            chooseMapArrowPosition = 0;
        } else if (imageMap2.equals(event)) {
            chooseMapArrowPosition = 1;
        } else if (imageMap3.equals(event)) {
            chooseMapArrowPosition = 2;
        } else if (imageMap4.equals(event)) {
            chooseMapArrowPosition = 3;
        }
        changeLabelPosition(chooseMap, 1, chooseMapArrowPosition);
    }

    private void loadInput() {
        try (InputStream input = new FileInputStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            chooseCharacterArrowPosition =  Integer.parseInt(properties.getProperty("CHOOSECHARACTER"));
            chooseBombArrowPostion = Integer.parseInt(properties.getProperty("CHOOSEBOMB"));
            chooseMapArrowPosition = Integer.parseInt(properties.getProperty("CHOOSEMAP"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String getCharacterName(){
        switch (chooseCharacterArrowPosition){
            case 1: return "evie";
            case 2: return "ike";
            case 3: return "plunk";
            default: return "boz";
        }
    }

    private String getMapName(){
        switch (chooseMapArrowPosition){
            case 1: return "TOWN";
            case 2: return "UNDERWATER";
            case 3: return "XMAS";
            default: return "DESERT";
        }
    }

    private void storeInput() {
        try (InputStream input = new FileInputStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            properties.setProperty("NAME",getCharacterName());
            properties.setProperty("CHOOSECHARACTER", String.valueOf(chooseCharacterArrowPosition));
            properties.setProperty("CHOOSEBOMB", String.valueOf(chooseBombArrowPostion));
            properties.setProperty("CHOOSEMAP", String.valueOf(chooseMapArrowPosition));
            properties.setProperty("MAP",getMapName());
            properties.store(new FileOutputStream("config.properties"),null);
            properties.store(System.out,"Option Updated!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource().equals(Back)){
            storeInput();
            gManager.showMenu();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Sounds.getIstance().getAudio(Sounds.BUTTON_CLICKED).setFramePosition(0);
        Sounds.getIstance().getAudio(Sounds.BUTTON_CLICKED).start();

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Sounds.getIstance().getAudio(Sounds.BUTTON_MOVEOVER).setFramePosition(0);
        Sounds.getIstance().getAudio(Sounds.BUTTON_MOVEOVER).start();
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Sounds.getIstance().getAudio(Sounds.BUTTON_MOVEOVER).stop();
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        Image image = new ImageIcon(getClass().getResource("/asset/menu/bg2.png")).getImage();
        graphics2D.drawImage(image,-24,0,gManager.getW_FRAME()+24,gManager.getH_FRAME(),null);
    }

}
