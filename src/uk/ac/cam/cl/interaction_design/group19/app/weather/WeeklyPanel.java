package uk.ac.cam.cl.interaction_design.group19.app.weather;

import javax.swing.*;
import java.awt.*;

public class WeeklyPanel extends JPanel {

    public WeeklyPanel() {
        JLabel label = new JLabel("Weekly");
        this.add(label);
        this.setSize(new Dimension(200, 200));
    }
}
