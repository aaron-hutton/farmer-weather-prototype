package uk.ac.cam.cl.interaction_design.group19.app.weather;

import javax.swing.*;
import java.awt.*;

public class TomorrowPanel extends JPanel {

    public TomorrowPanel() {
        JLabel label = new JLabel("Tomorrow");
        this.add(label);
        this.setSize(new Dimension(200, 200));
    }
}
