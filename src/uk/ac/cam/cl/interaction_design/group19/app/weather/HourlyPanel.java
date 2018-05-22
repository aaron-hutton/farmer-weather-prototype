package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import uk.ac.cam.cl.interaction_design.group19.app.util.WeatherData;
import uk.ac.cam.cl.interaction_design.group19.app.weather.summary.WeatherPanel;

/**
 * The panel containing the hourly table and the button to go back to summary
 */
public class HourlyPanel extends WeatherPanel {
    
    private static final int NUM_HOURS_TO_DISPLAY = 24;
    
    private final JButton                           summary = new JButton("< summary");
    private final Supplier<List<List<WeatherData>>> dataSupplier;
    
    private JPanel mainPanel;
    private HourlyTable table;
    
    public HourlyPanel(Supplier<List<List<WeatherData>>> dataSupplier, Runnable showSummary) {
        this.dataSupplier = dataSupplier;
        addOnClick(summary, showSummary);
        populate();
    }
    
    @Override
    protected JPanel createMainPanel() {
        mainPanel = new JPanel();
        
        update();
        
        return mainPanel;
    }
    
    @Override
    protected JPanel createButtonsPanel() {
        var bottomButtons = new JPanel();
        bottomButtons.setLayout(new GridLayout(1, 2));
        
        summary.setHorizontalAlignment(SwingConstants.LEFT);
        bottomButtons.add(summary);
        
        bottomButtons.add(new JPanel());
        
        return bottomButtons;
    }
    
    @Override
    public void update() {
        mainPanel.removeAll();
        List<List<WeatherData>> data = dataSupplier.get();
    
        if (data == null || data.size() == 0) {
            JLabel failLabel = new JLabel("There is no data to display.");
            mainPanel.add(failLabel);
        } else {
            if(table == null) {
                table = new HourlyTable(getNextNHours(data));
            } else {
                table.updateTable(getNextNHours(data));
            }
            mainPanel.add(table);
        }
    }
    
    /**
     * A function to produce a list of the next N hours of weather data
     * where N is NUM_HOURS_TO_DISPLAY defined above. This gets as much
     * of the current and next day as necessary
     * @param data
     * @return
     */
    private static List<WeatherData> getNextNHours(List<List<WeatherData>> data) {
        int counter = 0;
        List<WeatherData> specificData = new ArrayList<>(data.get(0));
        counter += specificData.size();
        if(counter < NUM_HOURS_TO_DISPLAY) {
            specificData.addAll(data.get(1).subList(0, NUM_HOURS_TO_DISPLAY-counter));
        }
        return specificData;
    }
}
