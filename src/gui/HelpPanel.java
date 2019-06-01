package gui;

import com.Position;
import com.Sounds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HelpPanel extends JPanel implements MouseListener {
    private GManager gManager;
    private JLabel Back;
    private JLabel Left;
    private JLabel Right;
    private JLabel Tutorial;
    private Integer Slide = 0;

    private boolean IS_RUNNING = false;

    public HelpPanel(GManager gManager) {
        setLayout(null);
        this.gManager = gManager;
        addComponent();
        Left.addMouseListener(this);
        Right.addMouseListener(this);

    }

    private void addComponent() {
        Position buttonPos = new Position(gManager.getW_FRAME()/2,gManager.getH_FRAME()/10);
        Tutorial = setLabel(buttonPos.getX(),buttonPos.getY(),"/asset/menu/control_tutorial.png");
        add(Tutorial);
        buttonPos.setY(gManager.getH_FRAME() - gManager.getH_FRAME()/10 - 100);
        Back = setLabel(buttonPos.getX(),buttonPos.getY(),"/asset/menu/button_empty.png");
        add(Back);
        Back.addMouseListener(this);
        buttonPos.setX(buttonPos.getX()-250);
        Left = setLabel(buttonPos.getX(),buttonPos.getY(),"/asset/menu/arrow_left.png");
        buttonPos.setX(buttonPos.getX()+500);
        add(Left);
        Right = setLabel(buttonPos.getX(),buttonPos.getY(),"/asset/menu/arrow_right.png");
        add(Right);
    }
    private JLabel changeLabel(String url){
        JLabel jLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(url));
        jLabel.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jLabel.setLocation(gManager.getW_FRAME()/2-imageIcon.getIconWidth()/2, 70);
        jLabel.setIcon(imageIcon);
        return jLabel;
    }

    private JLabel setLabel(int x, int y, String url){
        JLabel jLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(url));
        jLabel.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jLabel.setLocation(x-imageIcon.getIconWidth()/2, y);
        jLabel.setIcon(imageIcon);
        return jLabel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource().equals(Back)) {
            gManager.showMenu();
        }
        if (e.getSource().equals(Left)) Slide--;
        if (e.getSource().equals(Right)) Slide++;
        if (Slide < 0) Slide = 0;
        if (Slide > 1) Slide = 1;
        System.out.println(Slide);
        if (Slide == 0 ) {
            remove(Tutorial);
            Tutorial = changeLabel("/asset/menu/control_tutorial.png");
            add(Tutorial);
            updateUI();
        }
        if (Slide == 1) {
            remove(Tutorial);
            Tutorial = changeLabel("/asset/menu/item_tutorial.png");
            add(Tutorial);
            updateUI();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Sounds.getIstance().getAudio(Sounds.BUTTON_CLICKED).setFramePosition(0);
        Sounds.getIstance().getAudio(Sounds.BUTTON_CLICKED).start();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
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
