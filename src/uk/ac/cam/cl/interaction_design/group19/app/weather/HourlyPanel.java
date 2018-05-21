package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import uk.ac.cam.cl.interaction_design.group19.app.api.HourlyData;
import uk.ac.cam.cl.interaction_design.group19.app.api.MetOfficeAPI;
import uk.ac.cam.cl.interaction_design.group19.app.weather.summary.WeatherPanel;

public class HourlyPanel extends WeatherPanel {
    private final JButton summary = new JButton("< summary");
    
    public HourlyPanel(Supplier<LocalDateTime> dateSupplier, Runnable showSummary) {
        super(dateSupplier);
        addOnClick(summary, showSummary);
        populate();
        
    }
    
    @Override
    protected JPanel createMainPanel() {
        JPanel                 mainPanel = new JPanel();
        // TODO: Fix location
        List<List<HourlyData>> data      = MetOfficeAPI.fiveDayForecast(3066);
        
        if(data == null || data.size() == 0) {
            JLabel failLabel = new JLabel("There is no data to display.");
            mainPanel.add(failLabel);
        } else {
            HourlyTable table = new HourlyTable(data.get(0));
            mainPanel.add(table);
        }
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
    
    }
}
