
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GridLayoutExample {

  
    public static void main(String[] args) {
        JFrame frame = new JFrame("Grid Layout");

        for (int i = 1; i <= 4; i++) {
            frame.add(new JButton("Button - " + i));
        }

        frame.setLayout(new GridLayout(2,2));

        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
