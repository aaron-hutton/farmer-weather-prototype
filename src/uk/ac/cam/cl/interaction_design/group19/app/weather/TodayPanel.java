package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.GridLayout;
import javax.swing.JPanel;

public class TodayPanel extends JPanel
{

    public TodayPanel() {
        this.setLayout(new GridLayout(1,1));
        this.add(new SummaryPanel());
    }
}
