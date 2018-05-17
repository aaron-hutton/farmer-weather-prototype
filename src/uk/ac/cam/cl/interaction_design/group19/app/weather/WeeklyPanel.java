package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.util.List;
import javax.swing.JPanel;
import uk.ac.cam.cl.interaction_design.group19.app.Location;
import uk.ac.cam.cl.interaction_design.group19.app.MetOfficeAPI;
import uk.ac.cam.cl.interaction_design.group19.app.MetOfficeLocation;

public class WeeklyPanel extends JPanel
{
    public WeeklyPanel() {
        List<MetOfficeLocation> available_locations = new MetOfficeAPI().hourlyLocationList();
        Location homerton = Location.fromAddress("Homerton College, Cambridge");
        int nearest_id = homerton.closest(available_locations).id;
        WeeklyTable table = new WeeklyTable((new MetOfficeAPI()).fiveDayForecast(nearest_id).get(0));
        this.add(table);
    }
}
