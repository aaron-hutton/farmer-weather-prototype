package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class WeatherView extends JPanel {
    public static final int TOP_TAB_WIDTH = 80;

    private static final int TODAY_INDEX    = 0;
    private static final int TOMORROW_INDEX = 1;
    private static final int WEEKLY_INDEX   = 2;

    private static final Map<Integer, String> tabNames = Map.of(
            TODAY_INDEX, "Today",
            TOMORROW_INDEX, "Tomorrow",
            WEEKLY_INDEX, "Weekly"
    );

    private static final Map<Integer, JPanel> tabPanels = Map.of(
            TODAY_INDEX, new TodayPanel(),
            TOMORROW_INDEX, new TomorrowPanel(),
            WEEKLY_INDEX, new WeeklyPanel()
    );

    public WeatherView() {
        super(new GridLayout(1, 1));

        JTabbedPane timeTabs = new JTabbedPane();

        var indices = List.of(TODAY_INDEX, TOMORROW_INDEX, WEEKLY_INDEX);
        indices.forEach(i -> {
            var name  = tabNames.get(i);
            var label = new JLabel(name);
            label.setPreferredSize(new Dimension(TOP_TAB_WIDTH, label.getPreferredSize().height));
            timeTabs.addTab(name, tabPanels.get(i));
            timeTabs.setTabComponentAt(i, label);
        });

        timeTabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        timeTabs.setSelectedIndex(WEEKLY_INDEX);

        this.add(timeTabs);
    }
}
