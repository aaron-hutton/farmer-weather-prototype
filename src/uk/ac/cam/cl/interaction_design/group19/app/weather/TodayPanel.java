package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.GridLayout;
import javax.swing.JPanel;
import uk.ac.cam.cl.interaction_design.group19.app.Icons;
import uk.ac.cam.cl.interaction_design.group19.app.WeatherType;

public class TodayPanel extends JPanel
{

    public TodayPanel() {
        this.setLayout(new GridLayout(1,1));
        this.add(new SummaryPanel());

        JLabel iconImage = new JLabel(new ImageIcon(Icons.getSizedIcon(WeatherType.THUNDER, 40)));
        this.add(iconImage);
        this.setVisible(true);
    }
}
