package gui;

import com.Position;
import com.Sounds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class HighScorePanel extends JPanel implements MouseListener {
    private GManager gManager;
    private JLabel Back;
    private JLabel HighScore;
    private String fileName = "src/main/HighScore.txt";
    private File file = new File(fileName);
    private FileReader fileReader;
    String lines[] = new String[10];
    Integer number = 0;
            
    public HighScorePanel(GManager gManager) {
        setLayout(null);
        this.gManager = gManager;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while (((line = bufferedReader.readLine() )!= null) && number < 10) {
                lines[number++] = line;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addComponent();
    }

    private void addComponent() {
        Position tablePos = new Position(gManager.getW_FRAME()/2,30);
        tablePos.setX(tablePos.getX()-300);
        HighScore = displayHighScore(tablePos.getX(),tablePos.getY());
        add(HighScore);
        tablePos.setX(tablePos.getX()+300);
        tablePos.setY(tablePos.getY()+HighScore.getHeight()+50);
        Back = setLabel(tablePos.getX(),tablePos.getY(),"/asset/menu/button_empty.png");
        add(Back);
        Back.addMouseListener(this);
        tablePos.setY(tablePos.getY()+Back.getHeight()+50);

    }

    private JLabel displayHighScore(int x, int y){
        JLabel jLabel = new JLabel("<html><table width=\"900\">");
        for (String highScore: lines) {
            if (highScore == null) {jLabel.setText(jLabel.getText()+"<tr><td>empty</td></tr>");}
            else{
                jLabel.setText(jLabel.getText()+highScore);
            }
        }
//        System.out.println("Return:" + jLabel.getText());
        jLabel.setFont(new Font("Verdana",1,40));
        jLabel.setBounds(x,y,900,600);
        jLabel.setText(jLabel.getText()+"</table></html>");
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
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Sounds.getIstance().getAudio(Sounds.BUTTON_CLICKED).setFramePosition(0);
        Sounds.getIstance().getAudio(Sounds.BUTTON_CLICKED).start();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        Image image = new ImageIcon(getClass().getResource("/asset/menu/bg2.png")).getImage();
        graphics2D.drawImage(image,-24,0,gManager.getW_FRAME()+24,gManager.getH_FRAME(),null);
    }
}
