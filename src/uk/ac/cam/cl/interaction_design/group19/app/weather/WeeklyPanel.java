package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.util.List;
import javax.swing.JPanel;
import uk.ac.cam.cl.interaction_design.group19.app.api.Location;
import uk.ac.cam.cl.interaction_design.group19.app.api.MetOfficeAPI;
import uk.ac.cam.cl.interaction_design.group19.app.api.MetOfficeLocation;

public class WeeklyPanel extends JPanel {
    public WeeklyPanel() {
        List<MetOfficeLocation> available_locations = MetOfficeAPI.hourlyLocationList();
        Location                homerton            = Location.fromAddress("Homerton College, Cambridge");
        MetOfficeLocation       closest             = homerton.closest(available_locations);
        int                     nearest_id          = closest != null ? closest.id : 0;
        WeeklyTable             table               = new WeeklyTable(MetOfficeAPI.fiveDayForecast(nearest_id).get(0));
        this.add(table);
    }
}
