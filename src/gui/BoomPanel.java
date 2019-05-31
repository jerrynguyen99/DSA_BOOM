package gui;

import com.Position;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoomPanel extends JPanel implements MouseListener {
    private GManager gManager;
    private boolean IS_RUNNING = true;
    private JLabel Exit;
    private JLabel Play;
    private JLabel Mute;


    public BoomPanel(GManager gManager) {
        setLayout(null);
        this.gManager = gManager;
        drawComponent();
    }

    public void drawComponent(){
        Position position = new Position(gManager.getW_FRAME()-250,20);
        Play = setLabel(position.getX(),position.getY(),"/asset/menu/play_button.png");
        add(Play);
        position.setX(position.getX()+80);
        Mute = setLabel(position.getX(),position.getY(),"/asset/menu/sound_off.png");
        add(Mute);
        position.setX(position.getX()+80);
        Exit = setLabel(position.getX(),position.getY(),"/asset/menu/close_button.png");
        add(Exit);
        position.setX(position.getX()+80);
        Exit.addMouseListener(this);

    }


    private JLabel setLabel(int x, int y, String url){
        JLabel jLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(url));
        jLabel.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jLabel.setLocation(x, y);
        jLabel.setIcon(imageIcon);
        return jLabel;
    }

    public boolean isIS_RUNNING() {
        return IS_RUNNING;
    }
    public void setIS_RUNNING(boolean IS_RUNNING) {
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
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
