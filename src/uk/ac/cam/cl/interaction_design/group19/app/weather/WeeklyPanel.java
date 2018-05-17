package uk.ac.cam.cl.interaction_design.group19.app.weather;

import javax.swing.JPanel;
import uk.ac.cam.cl.interaction_design.group19.app.MetOfficeAPI;

public class WeeklyPanel extends JPanel
{
    public WeeklyPanel() {
        WeeklyTable table = new WeeklyTable((new MetOfficeAPI()).fiveDayForecast(3066).get(0));
        this.add(table);
    }
}
