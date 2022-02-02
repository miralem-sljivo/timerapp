import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Main extends JFrame {
        static LocalTime counter = LocalTime.now() ;
        JFrame frame = new JFrame("Timer App-Miralem Sljivo");
        static Timer timer;
        JButton start = new JButton("Start");
        JButton colorButton = new JButton("Choose Color");
        JButton stop1 = new JButton("Stop");
        JLabel l2 = new JLabel("Local time :");
        JButton restart = new JButton("Restart");

 Main(){
     start.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                     timer = new Timer(1000, new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {

                                             l2.setText("Counter = " + LocalTime.now());

                                         }

                                     });
                                     timer.start();
                                 }
                             });


             colorButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     Color backgorundColor = JColorChooser.showDialog(null, "Choose color", Color.blue);

                     if (backgorundColor !=null){
                         l2.setForeground(backgorundColor);
                     }
                 }
             });

             stop1.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     timer.stop();
                 }
             });

     restart.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            if (timer.isRunning()) {
                timer.stop();
            }
                l2.setText("Locar= " + counter);

         }
     });

     start.setBounds(20, 100, 130, 40);
     stop1.setBounds(230, 100, 130, 40);
     l2.setBounds(120, 30, 150, 100);
     colorButton.setBounds(230, 150, 130, 40);
     restart.setBounds(20, 150, 130, 40);
     frame.add(start);
     frame.add(restart);
     frame.add(l2);
     frame.add(stop1);
     frame.add(colorButton);
     frame.setLayout(null);
     frame.setVisible(true);
     frame.setSize(450, 350);
     frame.setLocation(450, 150);
     frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

 }

    public static void main(String[] args) {
        JFrame f = new JFrame("Welcome to Timer app ");

        Object[] options = {"Settings", "Close"};
        int o = JOptionPane.showOptionDialog(f, "Choose option: ", "Welcome to Timer app",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (o == JOptionPane.YES_OPTION){
            new Main();
        }
        if (o == JOptionPane.NO_OPTION){
            System.exit(0);
        }
    }
}
