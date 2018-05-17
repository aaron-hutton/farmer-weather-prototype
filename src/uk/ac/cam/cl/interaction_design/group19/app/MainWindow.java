package uk.ac.cam.cl.interaction_design.group19.app;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import uk.ac.cam.cl.interaction_design.group19.app.GDDs.GDDsView;
import uk.ac.cam.cl.interaction_design.group19.app.map.MapsView;
import uk.ac.cam.cl.interaction_design.group19.app.settings.SettingsView;
import uk.ac.cam.cl.interaction_design.group19.app.weather.WeatherView;

public class MainWindow extends JFrame {

    public static final int SCREEN_WIDTH = 320;
    public static final int SCREEN_HEIGHT = 480;

    public static final int BOTTOM_TAB_WIDTH = 55;


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
                    try {
                        new MainWindow();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    public MainWindow() throws IOException {
        initWindow();

        addTabs();

        this.setVisible(true);
    }

    public void initWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Farmer Weather App");
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void addTabs() throws IOException {
        JTabbedPane tabs = new JTabbedPane();

        JLabel weatherLabel = new JLabel("Weather");
        weatherLabel.setPreferredSize(new Dimension(BOTTOM_TAB_WIDTH, weatherLabel.getPreferredSize().height));

        JLabel mapsLabel = new JLabel("Maps");
        mapsLabel.setPreferredSize(new Dimension(BOTTOM_TAB_WIDTH, mapsLabel.getPreferredSize().height));

        JLabel GDDsLabel = new JLabel("GDDs");
        GDDsLabel.setPreferredSize(new Dimension(BOTTOM_TAB_WIDTH, GDDsLabel.getPreferredSize().height));

        JLabel settingsLabel = new JLabel("Settings");
        settingsLabel.setPreferredSize(new Dimension(BOTTOM_TAB_WIDTH, settingsLabel.getPreferredSize().height));

        tabs.addTab("Weather", new WeatherView());
        tabs.addTab("Maps", new MapsView());
        tabs.addTab("GDDs", new GDDsView());
        tabs.addTab("Settings", new SettingsView());

        tabs.setTabComponentAt(0, weatherLabel);
        tabs.setTabComponentAt(1, mapsLabel);
        tabs.setTabComponentAt(2, GDDsLabel);
        tabs.setTabComponentAt(3, settingsLabel);

        tabs.setTabPlacement(JTabbedPane.BOTTOM);
        tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        this.add(tabs, BorderLayout.CENTER);
    }
}
