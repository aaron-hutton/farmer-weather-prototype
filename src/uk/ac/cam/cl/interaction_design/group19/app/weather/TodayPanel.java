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
        for (WeatherType w : WeatherType.values()) {
            JLabel label2 = new JLabel(w.toString());
            this.add(label2);
            JLabel iconImage = new JLabel(new ImageIcon(Icons.getSizedWidthIcon(w, 100)));
            this.add(iconImage);
        }
        this.setVisible(true);
    }
}
