package uk.ac.cam.cl.interaction_design.group19.app.weather.summary;

import java.awt.GridLayout;
import java.util.function.Supplier;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import uk.ac.cam.cl.interaction_design.group19.app.util.WeatherData;
import uk.ac.cam.cl.interaction_design.group19.app.util.Icons;
import uk.ac.cam.cl.interaction_design.group19.app.util.WindDir;

/**
 * Panel showing the more info page which includes less important information
 * such as soil and wind data
 */
public class FullInfoPanel extends WeatherPanel {
    private static final int WIND_DIR_ICON_WIDTH = 70;
    
    private final JLabel  soilMoistLabel   = createLabel();
    private final JLabel  soilTempLabel    = createLabel();
    private final JLabel  windDirIconLabel = new JLabel();
    private final JLabel  windDirLabel     = createLabel();
    private final JLabel  windSpeedLabel   = createLabel();
    private final JLabel  cloudCoverLabel  = createLabel();
    private final JButton summary          = new JButton("summary >");
    private       int     soilMoist;
    private       int     soilTemp;
    private       WindDir windDir;
    private       int     windSpeed;
    private       int     cloudCover;
    
    private final Supplier<WeatherData> dataSupplier;
    
    public FullInfoPanel(Supplier<WeatherData> dataSupplier, Runnable showSummary) {
        this.dataSupplier = dataSupplier;
        addOnClick(summary, showSummary);
        populate();
    }
    
    @Override
    protected JPanel createMainPanel() {
        var summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.PAGE_AXIS));
        
        var soilMoistPanel = createPanel(createLabel("Soil moisture"), soilMoistLabel);
        summaryPanel.add(soilMoistPanel);
        
        var soilTempPanel = createPanel(createLabel("Soil temperature"), soilTempLabel);
        summaryPanel.add(soilTempPanel);
        
        var windPanel = createPanel(
                createLabel("Wind"),
                windDirIconLabel,
                windDirLabel,
                windSpeedLabel);
        summaryPanel.add(windPanel);
        
        var cloudCoverPanel = createPanel(createLabel("Cloud cover"), cloudCoverLabel);
        summaryPanel.add(cloudCoverPanel);
        
        return summaryPanel;
    }
    
    @Override
    protected JPanel createButtonsPanel() {
        var bottomButtons = new JPanel();
        bottomButtons.setLayout(new GridLayout(1, 2));
        
        bottomButtons.add(new JPanel());
        
        summary.setHorizontalAlignment(SwingConstants.RIGHT);
        bottomButtons.add(summary);
        
        return bottomButtons;
    }
    
    @Override
    public void update() {
        updateData();
        updateLabels();
    }
    
    private void updateData() {
        WeatherData data = dataSupplier.get();
        if (data == null) {
            return;
        }
        soilMoist = data.soil_moisture;
        soilTemp = data.soil_temperature;
        windDir = data.wind_direction;
        windSpeed = data.wind_speed;
        cloudCover = data.cloud_cover;
    }
    
    private void updateLabels() {
        soilMoistLabel.setText(soilMoist + " %");
        soilTempLabel.setText(soilTemp + " °C");
        windDirIconLabel.setIcon(new ImageIcon(Icons.getSizedWidthIcon(windDir, WIND_DIR_ICON_WIDTH)));
        windDirLabel.setText(windDir + "");
        windSpeedLabel.setText(windSpeed + " km/h");
        cloudCoverLabel.setText(cloudCover + " %");
    }
}
