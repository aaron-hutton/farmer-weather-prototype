package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import uk.ac.cam.cl.interaction_design.group19.app.MainWindow;
import uk.ac.cam.cl.interaction_design.group19.app.ScrollBarImplementation;
import uk.ac.cam.cl.interaction_design.group19.app.api.HourlyData;
import uk.ac.cam.cl.interaction_design.group19.app.api.WeatherData;

public class WeeklyTable extends JPanel {
    
    
    public static int MINIMUM_ROW_HEIGHT = 70;
    
    public WeeklyTable(List<HourlyData> data) {
        this.setLayout(new BorderLayout());
        
        JTable table = new JTable(new WeatherTableModel(data));
        
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(1).setMaxWidth(40);
        table.getColumnModel().getColumn(2).setMaxWidth(50);
        table.getColumnModel().getColumn(3).setMaxWidth(30);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        table.setDefaultRenderer(WeatherData.class, new WeeklyWeatherRenderer(data.size()));
        
        this.add(setupTableAndBundle(table, data.size()), BorderLayout.CENTER);
    }
    
    public static JComponent setupTableAndBundle(JTable table, int rows) {
        table.setOpaque(false);
        table.setBorder(BorderFactory.createEmptyBorder());
        table.setTableHeader(null);
    
        table.setRowHeight(Math.min((MainWindow.SCREEN_HEIGHT-80)/rows, MINIMUM_ROW_HEIGHT));
        table.setMaximumSize(new Dimension(MainWindow.SCREEN_WIDTH-60, table.getPreferredSize().height));
    
        JScrollPane scroller = new JScrollPane(table);
        scroller.setBackground(MainWindow.BACKGROUND_COLOR);
        scroller.getVerticalScrollBar().setUI(new ScrollBarImplementation());
        scroller.setBorder(BorderFactory.createEmptyBorder());
        scroller.setPreferredSize(new Dimension(MainWindow.SCREEN_WIDTH-20, MainWindow.SCREEN_HEIGHT-100));
        return scroller;
    }
}
