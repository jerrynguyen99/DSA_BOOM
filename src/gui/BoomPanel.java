package gui;

import javax.swing.*;

public class BoomPanel extends JPanel {
    private GManager gManager;
    private boolean IS_RUNNING = true;

    public BoomPanel(GManager gManager) {
        setLayout(null);
        this.gManager = gManager;
    }

    public boolean isIS_RUNNING() {
        return IS_RUNNING;
    }
    public void setIS_RUNNING(boolean IS_RUNNING) {
        this.IS_RUNNING = IS_RUNNING;
    }
}
