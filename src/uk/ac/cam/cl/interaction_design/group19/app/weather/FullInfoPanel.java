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
    private final JLabel soilMoistLabel = new JLabel();
    private final JLabel soilTempLabel = new JLabel();
    private final JLabel windSpeedLabel = new JLabel();
    private final JLabel cloudCoverLabel = new JLabel();
    private final JButton summary = new JButton("summary >");
    private int soilMoist;
    private int soilTemp;
    //private WindDir windDir;
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
        //windDir = NE;
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
//        windDirLabel.setText(windDir);
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

        var soilMoistPanel = createPanel(new JLabel("Soil moisture"), new JLabel(soilMoist + " %"));
        summaryPanel.add(soilMoistPanel);

        var soilTempPanel = createPanel(new JLabel("Soil temperature"), new JLabel(soilTemp + " °C"));
        summaryPanel.add(soilTempPanel);

        var windPanel = createPanel(
                new JLabel("Wind"),
                new JLabel("[WIND DIR ICON]"),
//                new JLabel(windDir+""), //TODO: write enum
                new JLabel(windSpeed + " km/h"));
        summaryPanel.add(windPanel);

        var cloudCoverPanel = createPanel(new JLabel("Cloud cover"), new JLabel(cloudCover + " %"));
        summaryPanel.add(cloudCoverPanel);

        return summaryPanel;
    }
}