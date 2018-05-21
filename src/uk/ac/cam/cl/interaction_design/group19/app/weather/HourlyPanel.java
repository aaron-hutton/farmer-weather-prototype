package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.GridLayout;
import java.util.List;
import java.util.function.Supplier;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import uk.ac.cam.cl.interaction_design.group19.app.api.WeatherData;
import uk.ac.cam.cl.interaction_design.group19.app.weather.summary.WeatherPanel;

public class HourlyPanel extends WeatherPanel {
    private final JButton                           summary = new JButton("< summary");
    private final Supplier<List<List<WeatherData>>> dataSupplier;
    
    public HourlyPanel(Supplier<List<List<WeatherData>>> dataSupplier, Runnable showSummary) {
        this.dataSupplier = dataSupplier;
        addOnClick(summary, showSummary);
        populate();
    }
    
    @Override
    protected JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        List<List<WeatherData>> data = dataSupplier.get();
        
        if (data == null || data.size() == 0) {
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
        System.err.println("blah");
    }
}
