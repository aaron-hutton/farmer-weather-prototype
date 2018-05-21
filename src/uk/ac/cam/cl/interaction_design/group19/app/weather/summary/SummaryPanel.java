package uk.ac.cam.cl.interaction_design.group19.app.weather.summary;

import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import uk.ac.cam.cl.interaction_design.group19.app.util.IconType;
import uk.ac.cam.cl.interaction_design.group19.app.util.Icons;
import uk.ac.cam.cl.interaction_design.group19.app.api.DayData;
import uk.ac.cam.cl.interaction_design.group19.app.api.MetOfficeAPI;
import uk.ac.cam.cl.interaction_design.group19.app.weather.WeatherType;

public class SummaryPanel extends WeatherPanel {
    private static final int    DEFAULT_ICON_WIDTH = 150;
    private static final double ICON_WIDTH_RATIO   = 0.5;
    
    private static final int STATIC_ICON_WIDTH = 50;
    
    private static final float HI_LO_FONT_SIZE = 16;
    private static final float DATE_FONT_SIZE  = 18;
    
    private final JLabel      dateLabel          = createLabel(DATE_FONT_SIZE);
    private final JLabel      weatherIconLabel   = new JLabel();
    private final JLabel      precipitationLabel = createLabel();
    private final JLabel      frostLabel         = createLabel();
    private final JLabel      tempLabel          = createLabel();
    private final JLabel      tempHighLabel      = createLabel();
    private final JLabel      tempLowLabel       = createLabel();
    private final JButton     moreInfo           = new JButton("< more info");
    private final JButton     hourly             = new JButton("hourly >");
    private       WeatherType weather;
    private       int         precipitation;
    private       int         frost;
    private       int         temperature;
    private       int         tempLow;
    private       int         tempHigh;
    
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
    public SummaryPanel(Supplier<LocalDateTime> dateSupplier, Runnable showMoreInfo, Runnable showHourly) {
        super(dateSupplier);
        addOnClick(moreInfo, showMoreInfo);
        addOnClick(hourly, showHourly);
        populate();
    }
    
    @Override
    protected JPanel createMainPanel() {
        var summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.PAGE_AXIS));
        
        var date = createPanel(dateLabel);
        summaryPanel.add(date);
        
        var weatherIcon = createPanel(weatherIconLabel);
        summaryPanel.add(weatherIcon);
        
        var precipitationPanel = createPanel(
                new JLabel(new ImageIcon(Icons.getSizedWidthIcon(IconType.RAINDROP, STATIC_ICON_WIDTH))),
                precipitationLabel);
        summaryPanel.add(precipitationPanel);
        
        var frostPanel = createPanel(
                new JLabel(new ImageIcon(Icons.getSizedWidthIcon(IconType.SNOWFLAKE, STATIC_ICON_WIDTH))),
                frostLabel);
        summaryPanel.add(frostPanel);
        
        var temperaturePanel = createPanel(tempLabel);
        summaryPanel.add(temperaturePanel);
        
        var tempLowPanel = createPanel(createLabel("Lo.", HI_LO_FONT_SIZE), tempLowLabel);
        summaryPanel.add(tempLowPanel);
        
        var tempHighPanel = createPanel(createLabel("Hi.", HI_LO_FONT_SIZE), tempHighLabel);
        summaryPanel.add(tempHighPanel);
        
        return summaryPanel;
    }
    
    @Override
    protected JPanel createButtonsPanel() {
        var bottomButtons = new JPanel();
        bottomButtons.setLayout(new GridLayout(1, 2));
        
        moreInfo.setHorizontalAlignment(SwingConstants.LEFT);
        bottomButtons.add(moreInfo);
        
        hourly.setHorizontalAlignment(SwingConstants.RIGHT);
        bottomButtons.add(hourly);
        
        return bottomButtons;
    }
    
    @Override
    public void update() {
        updateData();
        updateLabels();
    }
    
    private void updateData() {
        // TODO: fix location id
        DayData data = MetOfficeAPI.getDayData(dateSupplier.get(), 0);
        if (data == null) {
            return;
        }
        precipitation = data.precipitation_prob;
        weather = data.weather;
        frost = data.frost_prob;
        temperature = data.temperature;
        tempLow = data.low_temperature;
        tempHigh = data.high_temperature;
    }
    
    private void updateLabels() {
        var formatter = DateTimeFormatter.ofPattern("EEE dd MMMM");
        dateLabel.setText(dateSupplier.get().format(formatter));
        var iconWidth = this.getWidth() > 0 ? (int) (this.getWidth() * ICON_WIDTH_RATIO) : DEFAULT_ICON_WIDTH;
        weatherIconLabel.setIcon(new ImageIcon(Icons.getSizedWidthIcon(weather, iconWidth)));
        precipitationLabel.setText(precipitation + " %");
        frostLabel.setText(frost + " %");
        tempLabel.setText(temperature + " °C");
        tempLowLabel.setText(tempLow + " °C");
        tempHighLabel.setText(tempHigh + " °C");
    }
}
