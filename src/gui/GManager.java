package gui;

import com.Sounds;

import javax.sound.sampled.Clip;
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
    private static String HELP_TAG = "help";
    private static String HIGHSCORE_TAG = "highscore";
    private static String OPTION_TAG = "option";
    private BoomPanel boomPanel;
    private HelpPanel helpPanel;
    private HighScorePanel highScorePanel;
    private OptionPanel optionPanel;

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
        helpPanel = new HelpPanel(this);
        add(helpPanel, HELP_TAG);
        highScorePanel = new HighScorePanel(this);
        add(highScorePanel, HIGHSCORE_TAG);
        optionPanel = new OptionPanel(this);
        add(optionPanel, OPTION_TAG);
        showMenu();
    }

    public void showHelp() {
        cardLayout.show(this, HELP_TAG);
        helpPanel.requestFocus();
        Sounds.getIstance().stop();
        Sounds.getIstance().getAudio(Sounds.TAG_SOUND).loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void showMenu() {
        cardLayout.show(this, MENU_TAG);
        menuPanel.requestFocus();
        Sounds.getIstance().stop();
        Sounds.getIstance().getAudio(Sounds.MENU).loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void exit() {
        boomPanel.setIS_RUNNING(false);
        System.exit(0);
    }

    public void showHighScore() {
        cardLayout.show(this, HIGHSCORE_TAG);
        highScorePanel.requestFocus();
        Sounds.getIstance().stop();
        Sounds.getIstance().getAudio(Sounds.TAG_SOUND).loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void showPlayGame() {
        cardLayout.show(this, PLAYGAME_TAG);
        boomPanel.requestFocus();
        Sounds.getIstance().stop();
        Sounds.getIstance().getAudio(Sounds.TAG_SOUND).loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void showOption() {
        cardLayout.show(this, OPTION_TAG);
        boomPanel.requestFocus();
        Sounds.getIstance().stop();
        Sounds.getIstance().getAudio(Sounds.TAG_SOUND).loop(Clip.LOOP_CONTINUOUSLY);
    }

}
