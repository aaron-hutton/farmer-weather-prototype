package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SummaryPanel extends WeatherPanel
{
    private final JLabel dateLabel = new JLabel();
    private final JLabel weatherIconLabel = new JLabel();
    private final JLabel precipitationLabel = new JLabel();
    private final JLabel frostLabel = new JLabel();
    private final JLabel tempLabel = new JLabel();
    private final JLabel tempHighLabel = new JLabel();
    private final JLabel tempLowLabel = new JLabel();
    private final JButton moreInfo = new JButton("< more info");
    private final JButton hourly = new JButton("hourly >");
    //private WeatherType weather;
    private int precipitation;
    private int frost;
    private int temperature;
    private int tempLow;
    private int tempHigh;

    /**
     * Date
     * weather type icon
     * precipitation icon | precipitation probability
     * frost icon | frost chance
     * temperature now
     * text label for low | temperature low
     * text lavel for high | temperature high
     * << more info button |   | hourly button >>>
     */
    public SummaryPanel(Supplier<LocalDateTime> dateSupplier, Runnable showMoreInfo, Runnable showHourly)
    {
        super(dateSupplier);
        precipitation = 54;
        frost = 0;
        temperature = 18;
        tempLow = 4;
        tempHigh = 19;
        addOnClick(moreInfo, showMoreInfo);
        addOnClick(hourly, showHourly);
        populate();
    }

    @Override
    public void update()
    {
        var formatter = DateTimeFormatter.ofPattern("EEE dd MMMM");
        dateLabel.setText(dateSupplier.get().format(formatter));
        weatherIconLabel.setText("<html>V<br>E<br>R<br>Y <br>BIG<br>WEATHER<br> ICON</html>");
        precipitationLabel.setText(precipitation + " %");
        frostLabel.setText(frost + " %");
        tempLabel.setText(temperature + " °C");
        tempLowLabel.setText(tempLow + " °C");
        tempHighLabel.setText(tempHigh + " °C");
    }

    @Override
    protected JPanel createButtonsPanel()
    {
        var bottomButtons = new JPanel();
        bottomButtons.setLayout(new GridLayout(1, 2));

        moreInfo.setHorizontalAlignment(SwingConstants.LEFT);
        bottomButtons.add(moreInfo);

        hourly.setHorizontalAlignment(SwingConstants.RIGHT);
        bottomButtons.add(hourly);

        return bottomButtons;
    }

    @Override
    protected JPanel createMainPanel()
    {
        var summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.PAGE_AXIS));

        var date = createPanel(dateLabel);
        summaryPanel.add(date);

        var weatherIcon = createPanel(weatherIconLabel);
        summaryPanel.add(weatherIcon);

        var precipitationPanel = createPanel(new JLabel("RAINDROP"), precipitationLabel);
        summaryPanel.add(precipitationPanel);

        var frostPanel = createPanel(new JLabel("FROST"), frostLabel);
        summaryPanel.add(frostPanel);

        var temperaturePanel = createPanel(tempLabel);
        summaryPanel.add(temperaturePanel);

        var tempLowPanel = createPanel(new JLabel("Lo."), tempLowLabel);
        summaryPanel.add(tempLowPanel);

        var tempHighPanel = createPanel(new JLabel("Hi."), tempHighLabel);
        summaryPanel.add(tempHighPanel);

        return summaryPanel;
    }
}