package gui;

import com.Sounds;

import javax.swing.*;
import java.awt.*;

public class GManager extends JPanel {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int H_FRAME = (int) screenSize.getHeight();
    private final int W_FRAME = (int) screenSize.getWidth();

    private MenuPanel menuPanel;
    private CardLayout cardLayout;
    private static String MENU_TAG = "menu";
    private static String PLAYGAME_TAG = "playgame";
    private BoomPanel boomPanel;

    public int getH_FRAME() {
        return H_FRAME;
    }

    public int getW_FRAME() {
        return W_FRAME;
    }

    public GManager(){
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        menuPanel = new MenuPanel(this);
        add(menuPanel, MENU_TAG);
        boomPanel = new BoomPanel(this);
        add(boomPanel, PLAYGAME_TAG);
        showMenu();
    }

    private void showMenu() {
        cardLayout.show(this, MENU_TAG);
        menuPanel.requestFocus();
        Sounds.getIstance().stop();
        Sounds.getIstance().getAudio(Sounds.MENU).loop();

    }

    public void exit() {
        boomPanel.setIS_RUNNING(false);
        System.exit(0);
    }
}
