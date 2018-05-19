package uk.ac.cam.cl.interaction_design.group19.app;

import uk.ac.cam.cl.interaction_design.group19.app.api.Location;
import uk.ac.cam.cl.interaction_design.group19.app.api.MetOfficeAPI;

public class Model
{
    private Location location;

    public int getLocationID()
    {
        //TODO: implement using MetOfficeAPI
        //
        return 0;
    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }
}
