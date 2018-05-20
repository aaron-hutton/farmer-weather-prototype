package uk.ac.cam.cl.interaction_design.group19.app;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import uk.ac.cam.cl.interaction_design.group19.app.api.Location;
import uk.ac.cam.cl.interaction_design.group19.app.api.MetOfficeLocation;
import uk.ac.cam.cl.interaction_design.group19.app.settings.ExtremeEvent;

public class Model {
    private       MetOfficeLocation          location;
    private       boolean                    highContrast;
    private final Map<ExtremeEvent, Boolean> alerts;
    
    public Model() {
        //TODO: initialize with sensible location
        location = new MetOfficeLocation(0,0,0);
        highContrast = false;
        alerts = Arrays.stream(ExtremeEvent.values())
                       .collect(Collectors.toMap(e -> e, e -> false));
    }
    
    public int getLocationID() {
        return location.id;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public void setLocation(MetOfficeLocation location) {
        this.location = location;
    }
    
    public String getPostcode() {
        //TODO: implement
        return "CB2 1RH";
    }
    
    public void setPostcode(String postcode) {
        //TODO: implement
    }
    
    public boolean getHighContrast() {
        return highContrast;
    }
    
    public void setHighContrast(boolean highContrast) {
        this.highContrast = highContrast;
    }
    
    public boolean getAlert(ExtremeEvent e) {
        return alerts.get(e);
    }
    
    public void setAlert(ExtremeEvent e, boolean value) {
        alerts.put(e, value);
    }
}
