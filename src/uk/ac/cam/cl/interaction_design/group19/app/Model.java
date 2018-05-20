package uk.ac.cam.cl.interaction_design.group19.app;

import uk.ac.cam.cl.interaction_design.group19.app.api.Location;
import uk.ac.cam.cl.interaction_design.group19.app.api.MetOfficeLocation;

public class Model {
    private MetOfficeLocation location;

    public int getLocationID() {
        return location.id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(MetOfficeLocation location) {
        this.location = location;
    }
}
