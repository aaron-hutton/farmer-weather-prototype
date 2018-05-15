package uk.ac.cam.cl.interaction_design.group19.app.weather;

import javax.swing.*;
import java.awt.*;
import uk.ac.cam.cl.interaction_design.group19.app.Icons;
import uk.ac.cam.cl.interaction_design.group19.app.WeatherType;

public class TodayPanel extends JPanel {

    public TodayPanel() {
        JLabel label = new JLabel("Today");
        this.add(label);
        this.setSize(new Dimension(200, 200));

        JLabel iconImage = new JLabel(new ImageIcon(Icons.getSizedIcon(WeatherType.THUNDER, 40)));
        this.add(iconImage);
        this.setVisible(true);
    }
}
