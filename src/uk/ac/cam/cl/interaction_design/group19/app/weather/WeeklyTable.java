package uk.ac.cam.cl.interaction_design.group19.app.weather;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import uk.ac.cam.cl.interaction_design.group19.app.WeatherData;

public class WeeklyTable extends JPanel {

    public WeeklyTable(WeatherData[] data) {

        JTable table = new JTable(new WeatherTableModel(data));
        table.setDefaultRenderer(WeatherData.class, new WeeklyWeatherRenderer());

        JScrollPane scroller = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        this.add(scroller);
    }
}
