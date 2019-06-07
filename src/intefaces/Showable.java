package intefaces;

import com.Position;

import javax.swing.*;

public interface Showable {
    public JLabel isShowing = new JLabel();

    void changeImg(Position position);
    JLabel loadLabel();

}
