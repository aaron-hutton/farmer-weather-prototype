package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import uk.ac.cam.cl.interaction_design.group19.app.api.DayData;
import uk.ac.cam.cl.interaction_design.group19.app.api.HourlyData;
import uk.ac.cam.cl.interaction_design.group19.app.api.MetOfficeAPI;
import uk.ac.cam.cl.interaction_design.group19.app.util.Updatable;
import uk.ac.cam.cl.interaction_design.group19.app.weather.summary.TodayPanel;
import uk.ac.cam.cl.interaction_design.group19.app.weather.summary.TomorrowPanel;


public class WeatherView extends JPanel implements Updatable {
    public static final int TOP_TAB_WIDTH = 80;
    
    private static final int TODAY_INDEX    = 0;
    private static final int TOMORROW_INDEX = 1;
    private static final int WEEKLY_INDEX   = 2;
    
    private static final Map<Integer, String> tabNames = Map.of(
            TODAY_INDEX, "Today",
            TOMORROW_INDEX, "Tomorrow",
            WEEKLY_INDEX, "Weekly"
    );
    
    private final Map<Integer, JPanel> tabPanels;
    
    private final TodayPanel    todayPanel;
    private final TomorrowPanel tomorrowPanel;
    private final WeeklyPanel   weeklyPanel;
    
    public WeatherView(Function<LocalDateTime, DayData> getDayData,
                       Function<LocalDateTime, List<List<HourlyData>>> getHourlyData,
                       Function<LocalDateTime, List<List<HourlyData>>> getWeeklyData) {
        super(new GridLayout(1, 1));
        
        todayPanel = new TodayPanel(getDayData, getHourlyData);
        tomorrowPanel = new TomorrowPanel(getDayData, getHourlyData);
        //TODO: does not conform to the rest, fix
        weeklyPanel = new WeeklyPanel(() -> getWeeklyData.apply(LocalDateTime.now()));
        
        tabPanels = Map.of(TODAY_INDEX, todayPanel, TOMORROW_INDEX, tomorrowPanel, WEEKLY_INDEX, weeklyPanel);
        
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
    
    @Override
    public void update() {
        todayPanel.update();
        tomorrowPanel.update();
        weeklyPanel.update();
    }
}
