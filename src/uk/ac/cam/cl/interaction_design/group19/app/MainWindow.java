package uk.ac.cam.cl.interaction_design.group19.app;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import uk.ac.cam.cl.interaction_design.group19.app.GDDs.GDDsView;
import uk.ac.cam.cl.interaction_design.group19.app.map.MapsView;
import uk.ac.cam.cl.interaction_design.group19.app.settings.SettingsView;

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

        addTabs();

        this.setVisible(true);
    }

    public void initWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Farmer Weather App");
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void addTabs() {
        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Weather", new WeatherView());
        tabs.addTab("Map", new MapsView());
        tabs.addTab("GDDs", new GDDsView());
        tabs.addTab("Settings", new SettingsView());

        tabs.setTabPlacement(JTabbedPane.BOTTOM);
        tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        this.add(tabs, BorderLayout.CENTER);
    }
}
