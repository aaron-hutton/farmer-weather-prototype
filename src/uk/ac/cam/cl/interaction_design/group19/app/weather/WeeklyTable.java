package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import uk.ac.cam.cl.interaction_design.group19.app.api.HourlyData;
import uk.ac.cam.cl.interaction_design.group19.app.api.WeatherData;

public class WeeklyTable extends JPanel {

    public WeeklyTable(List<HourlyData> data) {

        JTable table = new JTable(new WeatherTableModel(data));
        table.setDefaultRenderer(WeatherData.class, new WeeklyWeatherRenderer());
        table.setOpaque(false);
        table.setBorder(BorderFactory.createEmptyBorder());
        table.setTableHeader(null);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scroller = new JScrollPane(table);
        scroller.setBorder(BorderFactory.createEmptyBorder());
        table.setFillsViewportHeight(true);
        this.add(scroller);
    }
}
