package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.util.function.Supplier;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FullInfoPanel extends WeatherPanel
{
    private final JLabel soilMoistLabel = createLabel();
    private final JLabel soilTempLabel = createLabel();
    private final JLabel windDirIconLabel = new JLabel();
    private final JLabel windDirLabel = createLabel();
    private final JLabel windSpeedLabel = createLabel();
    private final JLabel cloudCoverLabel = createLabel();
    private final JButton summary = new JButton("summary >");
    private int soilMoist;
    private int soilTemp;
    private WindDir windDir;
    private int windSpeed;
    private int cloudCover;

    /**
     * Soil moisture text | soil moisture value
     * Soil temperature text | soil temperature value
     * Wind text | wind direction icon | wind direction label | wind speed value
     * Wind text | wind direction icon | wind direction label | wind speed value
     * Cloud cover text | Cloud cover value
     * | | summary >>
     */
    public FullInfoPanel(Supplier<LocalDateTime> dateSupplier, Runnable showSummary)
    {
        super(dateSupplier);
        soilMoist = 12;
        soilTemp = 11;
        windDir = WindDir.NE;
        windSpeed = 12;
        cloudCover = 10;
        addOnClick(summary, showSummary);
        populate();
    }

    @Override
    public void update()
    {
        soilMoistLabel.setText(soilMoist + " %");
        soilTempLabel.setText(soilTemp + " °C");
        //TODO: implement wind dir icons
//        windDirIconLabel.setIcon(get wind dir icon)
        windDirLabel.setText(windDir+"");
        windSpeedLabel.setText(windSpeed + " km/h");
        cloudCoverLabel.setText(cloudCover + " %");
    }

    @Override
    protected JPanel createButtonsPanel()
    {
        var bottomButtons = new JPanel();
        bottomButtons.setLayout(new GridLayout(1, 2));

        bottomButtons.add(new JPanel());

        summary.setHorizontalAlignment(SwingConstants.RIGHT);
        bottomButtons.add(summary);

        return bottomButtons;
    }

    @Override
    protected JPanel createMainPanel()
    {
        var summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.PAGE_AXIS));

        var soilMoistPanel = createPanel(createLabel("Soil moisture"), createLabel(soilMoist + " %"));
        summaryPanel.add(soilMoistPanel);

        var soilTempPanel = createPanel(createLabel("Soil temperature"), createLabel(soilTemp + " °C"));
        summaryPanel.add(soilTempPanel);

        var windPanel = createPanel(
                createLabel("Wind"),
                windDirIconLabel,
                windDirLabel,
                windSpeedLabel);
        summaryPanel.add(windPanel);

        var cloudCoverPanel = createPanel(createLabel("Cloud cover"), createLabel(cloudCover + " %"));
        summaryPanel.add(cloudCoverPanel);

        return summaryPanel;
    }
}