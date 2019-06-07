package intefaces;

import javax.swing.*;
import java.awt.event.KeyEvent;

public interface Walkable {
    boolean isStop = false;
    boolean isStuck = false;

    void move(KeyEvent event, int speed, boolean isStuck);
    void idle(JLabel label, int state);
}

