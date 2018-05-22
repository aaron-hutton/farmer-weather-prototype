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
import uk.ac.cam.cl.interaction_design.group19.app.util.WeatherData;

public class WeeklyTable extends JPanel {
    
    
    public static int ROW_HEIGHT = 55;
    
    public WeeklyTable(List<WeatherData> data) {
        this.setLayout(new BorderLayout());
        
        JTable table = new JTable(new WeatherTableModel(data));
        
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(1).setMaxWidth(40);
        table.getColumnModel().getColumn(2).setMaxWidth(50);
        table.getColumnModel().getColumn(3).setMaxWidth(30);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        table.setDefaultRenderer(WeatherData.class, new WeeklyWeatherRenderer(data.size()));
        
        this.add(setupTableAndBundle(table, MainWindow.SCREEN_HEIGHT-120), BorderLayout.CENTER);
    }
    
    public static JComponent setupTableAndBundle(JTable table, int height) {
        table.setOpaque(false);
        table.setBackground(MainWindow.BACKGROUND_COLOR);
        table.setBorder(BorderFactory.createEmptyBorder());
        table.setTableHeader(null);
    
        table.setRowHeight(ROW_HEIGHT);
        table.setMaximumSize(new Dimension(MainWindow.SCREEN_WIDTH-60, table.getPreferredSize().height));
    
        JScrollPane scroller = new JScrollPane(table);
        scroller.getViewport().setBackground(MainWindow.BACKGROUND_COLOR);
        scroller.getVerticalScrollBar().setUI(new ScrollBarImplementation());
        scroller.setBorder(BorderFactory.createEmptyBorder());
        scroller.setPreferredSize(new Dimension(MainWindow.SCREEN_WIDTH-20, height));
        return scroller;
    }
}
