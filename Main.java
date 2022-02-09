import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimerTask;

public class Main implements ActionListener {
    private NextGui nextGui;
    private Timer colorSwitchTimer;
    private Color selectedColor;
    private Color previousColor;

    JFrame f = new JFrame("Timer App");
    JLabel label = new JLabel("Selected Color!");
    JLabel label1 = new JLabel("Speed: ");

    JRadioButton onTime = new JRadioButton("On Time: ");
    JRadioButton countDow = new JRadioButton("Countdown (sec): ");
    TextField t1 = new TextField();
    TextField t3 = new TextField();
    Button b1 = new Button("Choose color:");
    JComboBox jcb = new JComboBox();
    Button b2 = new Button("Start Countdown");
    Button b3 = new Button("Stop");


    Main() {


        jcb.addItem(1);
        jcb.addItem(2);
        jcb.addItem(3);
        jcb.addItem(4);

        f.getContentPane().setBackground(Color.orange);
        onTime.setBounds(20, 10, 150, 20);
        onTime.setBackground(Color.WHITE);
        countDow.setBounds(20, 40, 150, 20);
        countDow.setBackground(Color.WHITE);
        t1.setBounds(220, 10, 180, 20);
        t3.setBounds(220, 40, 180, 20);
        b1.setBounds(20, 100, 150, 40);
        b1.setBackground(Color.WHITE);
        label.setBounds(220, 105, 150, 30);
        label.setBackground(null);
        jcb.setBounds(110, 160, 60, 20);
        label1.setBounds(30, 160, 100, 20);
        b2.setBounds(20, 230, 150, 40);
        b2.setBackground(Color.WHITE);
        b3.setBounds(220, 230, 150, 40);
        b3.setBackground(Color.WHITE);


        f.add(onTime);
        f.add(countDow);
        f.add(t1);
        f.add(t3);
        f.add(b1);
        f.add(label);
        f.add(jcb);
        f.add(label1);
        f.add(b2);
        f.add(b3);
        f.setLayout(null);
        f.setVisible(true);
        f.setSize(450, 350);
        f.setLocation(450, 150);

        b1.addActionListener(this);
        b2.addActionListener(e -> {
            if (onTime.isSelected()) {
                String dateString = t1.getText();


                String[] timeArray = dateString.split(":");
                LocalDate localDate = LocalDate.now();
                LocalDateTime localDateTime = localDate.atTime(LocalTime.of(Integer.parseInt(timeArray[0]), Integer.parseInt(timeArray[1])));
                Date onTime = java.sql.Timestamp.valueOf(localDateTime);

                TimerTask task = new TimerTask() {
                    public void run() {
                        openNextGui();
                    }
                };
                java.util.Timer timer = new java.util.Timer();
                timer.schedule(task, onTime);


            } else if (countDow.isSelected()) {
                Integer delay = Integer.parseInt(t3.getText().toString());
                TimerTask task = new TimerTask() {
                    public void run() {
                        openNextGui();
                    }
                };
                java.util.Timer timer = new java.util.Timer();
                timer.schedule(task, delay * 1000);
            }

        });
        b3.addActionListener(e -> {
            nextGui.setVisible(false);
            nextGui.dispose();
        });

        onTime.addActionListener(e -> {
            countDow.setSelected(false);
        });

        countDow.addActionListener(e -> {
            onTime.setSelected(false);
        });


        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int rez = JOptionPane.showConfirmDialog(f,
                        "Da li stvarno zelite napustiti program ?", "Potvrdite izlaz: ",
                        JOptionPane.YES_NO_OPTION);
                if (rez == JOptionPane.YES_OPTION)
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                else if (rez == JOptionPane.NO_OPTION)
                    f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
    }


    public static void main(String[] args) {


        JFrame frame = new JFrame("Timer App");
        Object[] options = {"Settings",
                "Close"};
        int p = JOptionPane.showOptionDialog(frame,
                "Choose option:",
                "Welcome to Timer Application",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (p == JOptionPane.YES_OPTION)
            new Main();

        else if (p == JOptionPane.NO_OPTION)
            System.exit(0);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            selectedColor = JColorChooser.showDialog(null, "Pick a color", label.getForeground());
            if (selectedColor != null)
                label.setForeground(selectedColor);
        }
    }


    private void openNextGui() {
        var ref = new Object() {
            Color previousColor = selectedColor;
        };
        if (nextGui != null) {
            colorSwitchTimer.stop();
            nextGui.setVisible(false);
            nextGui.dispose();
        }
        nextGui = new NextGui();
        nextGui.setBackground(selectedColor);
        int delay = Integer.parseInt(jcb.getSelectedItem().toString());
        colorSwitchTimer = new Timer(delay * 1000, e1 -> {
            if (ref.previousColor == selectedColor){
                nextGui.setBackground(Color.CYAN);
            } else nextGui.setBackground(selectedColor);
                ref.previousColor = ref.previousColor == selectedColor ? Color.CYAN : selectedColor;
            });
        if (jcb.getSelectedItem().equals(1)) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            if (jcb.getSelectedItem().equals(2)) {
                try {
                    selectedColor.wait(1200);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                if (jcb.getSelectedItem().equals(3)) {
                    try {
                        selectedColor.wait(800);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    if (jcb.getSelectedItem().equals(4)) {
                        try {
                            selectedColor.wait(400);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        colorSwitchTimer.start();
                    }
                }
            }
        }
    }
}



