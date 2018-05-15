package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SummaryPanel extends JPanel {
    //private WeatherType weather;
    private int precipitation;
    private int frost;
    private int temperature;
    private int tempLow;
    private int tempHigh;
    private final JButton moreInfo;
    private final JButton hourly;

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
    public SummaryPanel()
    {
        moreInfo = new JButton("< more info");
        hourly = new JButton("hourly >");

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.85;
        c.weightx = 1;
        this.add(createSummaryPane(), c);
        c.weighty = 0.15;
        c.gridy=1;
        this.add(createSummaryButtons(), c);
    }

    private JPanel createSummaryButtons()
    {
        var bottomButtons = new JPanel();
        bottomButtons.setLayout(new GridLayout(1, 2));

        moreInfo.setHorizontalAlignment(SwingConstants.LEFT);
        bottomButtons.add(moreInfo);

        hourly.setHorizontalAlignment(SwingConstants.RIGHT);
        bottomButtons.add(hourly);

        return bottomButtons;
    }

    private JPanel createSummaryPane()
    {
        var summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.PAGE_AXIS));

        DateFormat dateFormat = new SimpleDateFormat("EEE dd MMMM");
        var date = createPanel(new JLabel(dateFormat.format(new Date())));
        summaryPanel.add(date);

        var weatherIcon = createPanel(new JLabel("<html>VERY BIG<br>WEATHER ICON</html>"));
        summaryPanel.add(weatherIcon);

        var precipitationPanel = createPanel(new JLabel("RAINDROP"), new JLabel(precipitation+" %"));
        summaryPanel.add(precipitationPanel);

        var frostPanel = createPanel(new JLabel("FROST"), new JLabel(frost+" %"));
        summaryPanel.add(frostPanel);

        var temperaturePanel = createPanel(new JLabel(temperature+" °C"));
        summaryPanel.add(temperaturePanel);

        var tempLowPanel = createPanel(new JLabel("Lo."), new JLabel(tempLow+" °C"));
        summaryPanel.add(tempLowPanel);

        var tempHighPanel = createPanel(new JLabel("Hi."), new JLabel(tempHigh+" °C"));
        summaryPanel.add(tempHighPanel);

        return summaryPanel;
    }

    private JPanel createPanel(JComponent... components)
    {
        var panel = new JPanel();
        Stream.of(components).forEachOrdered(panel::add);
        //panel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        return panel;
    }
}