package uk.ac.cam.cl.interaction_design.group19.app;

import uk.ac.cam.cl.interaction_design.group19.app.GDDs.GDDsView;
import uk.ac.cam.cl.interaction_design.group19.app.map.MapsView;
import uk.ac.cam.cl.interaction_design.group19.app.settings.SettingsView;
import uk.ac.cam.cl.interaction_design.group19.app.weather.TodayPanel;
import uk.ac.cam.cl.interaction_design.group19.app.weather.TomorrowPanel;
import uk.ac.cam.cl.interaction_design.group19.app.weather.WeeklyPanel;

import javax.swing.*;
import java.awt.*;

public class WeatherView extends JPanel {

    public static final int TOP_TAB_WIDTH = 80;

    public WeatherView() {
        super(new GridLayout(1, 1));
        //this.setSize(new Dimension(300, 300));
        JTabbedPane timeTabs = new JTabbedPane();

        JLabel todayLabel = new JLabel("Today");
        todayLabel.setPreferredSize(new Dimension(TOP_TAB_WIDTH, todayLabel.getPreferredSize().height));

        JLabel tomorrowLabel = new JLabel("Tomorrow");
        tomorrowLabel.setPreferredSize(new Dimension(TOP_TAB_WIDTH, tomorrowLabel.getPreferredSize().height));

        JLabel weeklyLabel = new JLabel("Weekly");
        weeklyLabel.setPreferredSize(new Dimension(TOP_TAB_WIDTH, weeklyLabel.getPreferredSize().height));

        timeTabs.addTab("Today", new TodayPanel());
        timeTabs.addTab("Tomorrow", new TomorrowPanel());
        timeTabs.addTab("Weekly", new WeeklyPanel());

        timeTabs.setTabComponentAt(0, todayLabel);
        timeTabs.setTabComponentAt(1, tomorrowLabel);
        timeTabs.setTabComponentAt(2, weeklyLabel);

        this.add(timeTabs);
        timeTabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
}
