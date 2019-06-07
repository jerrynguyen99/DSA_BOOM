package gui;

import com.Karacter;
import com.Map;
import com.Position;
import com.Sounds;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoomPanel extends JPanel implements MouseListener, KeyListener {
    private GManager gManager;
    private boolean IS_RUNNING = true;
    private JLabel Logo;
    private JLabel Exit;
    private JLabel Timer;
    private JLabel Play;
    private JLabel Mute;
    private Cursor cursor;
    private Map map;
    private Karacter character_1;
    private Boolean isMute = false;
    private Position TopLeft;
    private Position TopRight;
    private Position BottomLeft;
    private Position BottomRight;

    BoomPanel(GManager gManager) {
        setLayout(null);
        this.gManager = gManager;
        drawComponent();
    }

    private void drawComponent(){
        Position position = new Position(0,0);
        Logo = setLabel(position.getX(),position.getY(),"/asset/menu/title_scale.png");
        add(Logo);

        position.setX(gManager.getW_FRAME()-250);
        position.setY(20);
        Play = setLabel(position.getX(),position.getY(),"/asset/menu/play_button.png");
        add(Play);
        position.setX(position.getX()+80);
        Mute = setLabel(position.getX(),position.getY(),"/asset/menu/sound_off.png");
        add(Mute);
        Mute.addMouseListener(this);
        position.setX(position.getX()+80);
        Exit = setLabel(position.getX(),position.getY(),"/asset/menu/close_button.png");
        add(Exit);
        position.setX(position.getX()+80);
        Exit.addMouseListener(this);
        position.setX(gManager.getW_FRAME()/2-100);
        Timer = setLabel(position.getX(),position.getY(),"/asset/menu/time_scale.png");
        add(Timer);
        map = new Map();
        position.setX(gManager.getW_FRAME()/2-map.getWidth()/3);
        position.setY(gManager.getH_FRAME()/2-map.getHeight()/3);
        JLabel thisMap = map.showMap(position);
        add(thisMap);
        TopLeft = new Position(280,150);
        TopRight = new Position(1205,150);
        BottomLeft = new Position(280,720);
        BottomRight = new Position(1205,720);
        character_1 = new Karacter();
        System.out.println(character_1.toString());
        character_1.toShow(TopLeft);
        add(character_1.loadLabel(),1);
        this.addKeyListener(this);
    }

    private JLabel setLabel(int x, int y, String url){
        JLabel jLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(url));
        jLabel.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jLabel.setLocation(x, y);
        jLabel.setIcon(imageIcon);
        return jLabel;
    }

    private void isOutOfMap() {
        if (character_1.loadLabel().getLocation().x < TopLeft.getX()) {
            character_1.loadLabel().setLocation(TopLeft.getX(),character_1.loadLabel().getLocation().y);
        }
        if (character_1.loadLabel().getLocation().x > TopRight.getX()+character_1.loadLabel().getWidth()) {
            character_1.loadLabel().setLocation(TopRight.getX()+character_1.loadLabel().getWidth(),
                    character_1.loadLabel().getLocation().y);
        }
        if (character_1.loadLabel().getLocation().y < TopLeft.getY()) {
            character_1.loadLabel().setLocation(character_1.loadLabel().getLocation().x,TopLeft.getY());
        }
        if (character_1.loadLabel().getLocation().y > BottomLeft.getY()) {
            character_1.loadLabel().setLocation(character_1.loadLabel().getLocation().x, BottomLeft.getY());
        }


    }

    public boolean isIS_RUNNING() {
        return IS_RUNNING;
    }
    void setIS_RUNNING(boolean IS_RUNNING) {
        this.IS_RUNNING = IS_RUNNING;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource().equals(Exit)){
            gManager.showMenu();
        }
        if (e.getSource().equals(Mute)){
            isMute = !isMute;
            if (isMute) {
                Sounds.getIstance().stop();
                Mute.setIcon(new ImageIcon(getClass().getResource("/asset/menu/sound_on.png")));
                updateUI();
            } else {
                Sounds.getIstance().getAudio(Sounds.TAG_SOUND).loop(Clip.LOOP_CONTINUOUSLY);
                Mute.setIcon(new ImageIcon(getClass().getResource("/asset/menu/sound_off.png")));
                updateUI();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        setCursor(cursor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
        setCursor(cursor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        Image image = new ImageIcon(getClass().getResource("/img/content/background_Play.png")).getImage();
        graphics2D.drawImage(image,-24,0,gManager.getW_FRAME()+24,gManager.getH_FRAME(),null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch ( keyCode ) {
            case KeyEvent.VK_UP:
                System.out.println("UP");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("DOWN");
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("LEFT");
                break;
            case KeyEvent.VK_RIGHT :
                System.out.println("RIGHT");
                break;
        }
        character_1.move(e, character_1.getSpeed(), Boolean.FALSE);
        isOutOfMap();
        updateUI();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
