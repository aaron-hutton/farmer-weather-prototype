package uk.ac.cam.cl.interaction_design.group19.app;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import uk.ac.cam.cl.interaction_design.group19.app.api.Location;
import uk.ac.cam.cl.interaction_design.group19.app.api.MetOfficeLocation;
import uk.ac.cam.cl.interaction_design.group19.app.settings.ExtremeEvent;

public class Model {
    private final Map<ExtremeEvent, Boolean> alerts;
    private       MetOfficeLocation          location;
    //TODO: ugly hack, fix, need function :: location -> postcode
    private       String                     postcode;
    private       boolean                    highContrast;
    
    public Model() {
        //TODO: initialize with user's current location instead
        setPostcode("CB2 1RH");
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
    
    public String getPostcode() {
        return postcode;
    }
    
    public void setPostcode(String postcode) {
        var loc = Location.fromAddress(postcode);
        //TODO: implement
        //location = MetOfficeLocation(loc)
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
