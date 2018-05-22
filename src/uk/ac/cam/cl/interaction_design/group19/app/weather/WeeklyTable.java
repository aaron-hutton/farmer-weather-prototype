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

/**
 *  A panel which displays an instance of a JTable which outputs the weekly weather view.
 */
public class WeeklyTable extends JPanel {
    
    /**
     * Height in pixels of the rows of the table
     */
    public static int ROW_HEIGHT = 55;
    
    /**
     * The table and its model and renderer
     */
    private WeatherTableModel model;
    private JTable table;
    private WeatherCustomRenderer renderer;
    
    public WeeklyTable(List<WeatherData> data) {
        this.setLayout(new BorderLayout());
    
        model = new WeatherTableModel(data);
        table = new JTable(model);
        
        // Set the widths of the columns
        table.getColumnModel().getColumn(0).setMaxWidth(MainWindow.SCREEN_WIDTH/4);
        table.getColumnModel().getColumn(1).setMaxWidth(MainWindow.SCREEN_WIDTH/8);
        table.getColumnModel().getColumn(2).setMaxWidth(MainWindow.SCREEN_WIDTH/6);
        table.getColumnModel().getColumn(3).setMaxWidth(MainWindow.SCREEN_WIDTH/8);
        // Leave final column without specified width
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    
        renderer = new WeeklyWeatherRenderer(data.size());
        table.setDefaultRenderer(WeatherData.class, renderer);
        
        this.add(setupTableAndBundle(table, MainWindow.SCREEN_HEIGHT-120), BorderLayout.CENTER);
    }
    
    /**
     * Update the table info when the information changes
     *
     * @param data - the new weather information for this location at this time
     */
    public void updateTable(List<WeatherData> data) {
        model.updateData(data);
        renderer.updateSize(data.size());
        table.repaint();
    }
    
    /**
     * Style the table then bundle in a JScrollPane
     *
     * @param table - The table to be styled
     * @param height - The height of the viewport of the JScrollPane
     * @return the JScrollPane component
     */
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
