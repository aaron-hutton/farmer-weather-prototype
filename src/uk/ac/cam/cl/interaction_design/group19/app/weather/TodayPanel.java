package uk.ac.cam.cl.interaction_design.group19.app.weather;

import javax.swing.*;
import java.awt.*;

public class TodayPanel extends JPanel {

    public TodayPanel() {
        JLabel label = new JLabel("Today");
        this.add(label);
        this.setSize(new Dimension(200, 200));
    }
}
