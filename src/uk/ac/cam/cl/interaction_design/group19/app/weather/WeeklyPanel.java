package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WeeklyPanel extends JPanel
{

    public WeeklyPanel()
    {
        JLabel label = new JLabel("Weekly");
        this.add(label);
        this.setSize(new Dimension(200, 200));
    }
}
