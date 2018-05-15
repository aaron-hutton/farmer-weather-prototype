package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import uk.ac.cam.cl.interaction_design.group19.app.weather.TodayPanel;
import uk.ac.cam.cl.interaction_design.group19.app.weather.TomorrowPanel;
import uk.ac.cam.cl.interaction_design.group19.app.weather.WeeklyPanel;


public class WeatherView extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 500;
    private static final int TODAY_INDEX = 0;
    private static final int TOMORROW_INDEX = 1;
    private static final int WEEKLY_INDEX = 2;

    public WeatherView() {
        super(new GridLayout(1, 1));
        this.setSize(new Dimension(WIDTH, HEIGHT));

        JTabbedPane timeTabs = new JTabbedPane();
        timeTabs.addTab("Today", new TodayPanel());
        timeTabs.addTab("Tomorrow", new TomorrowPanel());
        timeTabs.addTab("Weekly", new WeeklyPanel());

        timeTabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        timeTabs.setSelectedIndex(WEEKLY_INDEX);

        this.add(timeTabs);
    }
}
