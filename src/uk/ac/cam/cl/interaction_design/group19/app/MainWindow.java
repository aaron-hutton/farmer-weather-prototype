package uk.ac.cam.cl.interaction_design.group19.app;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class MainWindow extends JFrame {

    public static final int SCREEN_WIDTH = 320;
    public static final int SCREEN_HEIGHT = 480;


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
                    try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                            if ("Nimbus".equals(info.getName())) {
                                UIManager.setLookAndFeel(info.getClassName());
                                break;
                            }
                        }
                    } catch (Exception e) {
                        // Default to Java LookAndFell
                    }
                    new MainWindow();
                }
        );
    }

    public MainWindow() {
        initWindow();

        JPanel panel = new JPanel();

        JButton button = new JButton("Click me");
        button.addActionListener((e) -> System.out.println("Click"));
        panel.add(button);


        this.add(new WeatherView(), BorderLayout.CENTER);
        this.add(panel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void initWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Farmer Weather App");
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

    }
}
