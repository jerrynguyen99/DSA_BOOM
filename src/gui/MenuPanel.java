package gui;

import com.Position;
import com.Sounds;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuPanel extends JPanel implements MouseListener {

    private GManager gManager;
    private JLabel Logo;
    private JLabel Play;
    private JLabel Option;
    private JLabel Highscore;
    private JLabel Help;
    private JLabel Exit;

    public MenuPanel(GManager gManager){
        this.gManager= gManager;
        setLayout(null);
        addComponent();
    }

    private void addComponent(){
        Position comPos = new Position(gManager.getW_FRAME()/2-200,gManager.getH_FRAME()/2-350);
        Logo = setLabel(comPos.getX(),comPos.getY(),"/img/wall/gamelogo.png");
        add(Logo);
        comPos.setX(comPos.getX()+100);
        comPos.setY(gManager.getH_FRAME()/2);

        Play = setLabel(comPos.getX(),comPos.getY(),"/img/content/button_play.png");
        add(Play);
        Play.addMouseListener(this);
        comPos.setY(comPos.getY()+70);
        Highscore = setLabel(comPos.getX(),comPos.getY(),"/img/content/button_high-score.png");
        add(Highscore);
        Highscore.addMouseListener(this);
        comPos.setY(comPos.getY()+70);
        Option = setLabel(comPos.getX(),comPos.getY(),"/img/content/button_option.png");
        add(Option);
        Option.addMouseListener(this);
        comPos.setY(comPos.getY()+70);
        Help = setLabel(comPos.getX(),comPos.getY(),"/img/content/button_help.png");
        add(Help);
        Help.addMouseListener(this);
        comPos.setY(comPos.getY()+70);
        Exit = setLabel(comPos.getX(),comPos.getY(),"/img/content/button_exit.png");
        add(Exit);
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

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getSource().equals(Help)){
            gManager.showHelp();
        }
        if (e.getSource().equals(Highscore)){
            gManager.showHighScore();
        }
        if (e.getSource().equals(Play)){
            gManager.showPlayGame();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Sounds.getIstance().getAudio(Sounds.BUTTON_CLICKED).setFramePosition(0);
        Sounds.getIstance().getAudio(Sounds.BUTTON_CLICKED).start();
        if(e.getSource().equals(Exit)){
            gManager.exit();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Sounds.getIstance().getAudio(Sounds.BUTTON_MOVEOVER).setFramePosition(0);
        Sounds.getIstance().getAudio(Sounds.BUTTON_MOVEOVER).start();
        if(e.getSource().equals(Play)){
            Play.setIcon(new ImageIcon(getClass().getResource("/img/content/button_play (1).png")));
        }
        else if(e.getSource().equals(Option)){
            Option.setIcon(new ImageIcon(getClass().getResource("/img/content/button_option (1).png")));
        }
        else if(e.getSource().equals(Highscore)){
            Highscore.setIcon(new ImageIcon(getClass().getResource("/img/content/button_high-score (1).png")));
        }
        else if(e.getSource().equals(Exit)){
            Exit.setIcon(new ImageIcon(getClass().getResource("/img/content/button_exit (1).png")));
        }
        else if(e.getSource().equals(Help)){
            Help.setIcon(new ImageIcon(getClass().getResource("/img/content/button_help (1).png")));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Sounds.getIstance().getAudio(Sounds.BUTTON_MOVEOVER).stop();

        if(e.getSource().equals(Play)){
            Play.setIcon(new ImageIcon(getClass().getResource("/img/content/button_play.png")));
        }
        else if(e.getSource().equals(Option)){
            Option.setIcon(new ImageIcon(getClass().getResource("/img/content/button_option.png")));
        }
        else if(e.getSource().equals(Highscore)){
            Highscore.setIcon(new ImageIcon(getClass().getResource("/img/content/button_high-score.png")));
        }
        else if(e.getSource().equals(Exit)){
            Exit.setIcon(new ImageIcon(getClass().getResource("/img/content/button_exit.png")));
        }
        else if(e.getSource().equals(Help)){
            Help.setIcon(new ImageIcon(getClass().getResource("/img/content/button_help.png")));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        Image image = new ImageIcon(getClass().getResource("/img/wall/wallpaper.jpg")).getImage();
        graphics2D.drawImage(image,-24,0,gManager.getW_FRAME()+24,gManager.getH_FRAME(),null);
    }
}
