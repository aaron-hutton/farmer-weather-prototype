package uk.ac.cam.cl.interaction_design.group19.app;

import uk.ac.cam.cl.interaction_design.group19.app.weather.TodayPanel;
import uk.ac.cam.cl.interaction_design.group19.app.weather.TomorrowPanel;
import uk.ac.cam.cl.interaction_design.group19.app.weather.WeeklyPanel;

import javax.swing.*;
import java.awt.*;

public class WeatherView extends JPanel {
    public WeatherView() {
        super(new GridLayout(1, 1));
        this.setSize(new Dimension(300, 300));
        JTabbedPane timeTabs = new JTabbedPane();

        timeTabs.addTab("Today", new TodayPanel());
        timeTabs.addTab("Tomorrow", new TomorrowPanel());
        timeTabs.addTab("Weekly", new WeeklyPanel());

        this.add(timeTabs);
        timeTabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
}
