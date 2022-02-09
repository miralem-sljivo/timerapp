import javax.swing.*;
import java.awt.*;

public class NextGui extends JFrame {
    private JFrame nextGui;

    public NextGui(){
     configurePanel();
    }
    private void configurePanel() {
        this.setSize(250, 150);
        this.setLocation(450, 150);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }

    public void setBackgroundColor(Color color) {
        nextGui.setBackground(color);
    }

}
