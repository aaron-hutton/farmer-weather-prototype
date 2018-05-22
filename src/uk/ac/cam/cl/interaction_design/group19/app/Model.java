package uk.ac.cam.cl.interaction_design.group19.app;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import uk.ac.cam.cl.interaction_design.group19.app.api.Location;
import uk.ac.cam.cl.interaction_design.group19.app.api.MetOfficeAPI;
import uk.ac.cam.cl.interaction_design.group19.app.api.MetOfficeLocation;
import uk.ac.cam.cl.interaction_design.group19.app.util.ExtremeEvent;

/**
 * Model stores all user-specific data
 * that includes location, whether high contrast mode is enabled, and alerts
 */
public class Model {
    private static final String INITIAL_POSTCODE = "IV36 3UN";
    private final Map<ExtremeEvent, Boolean> alerts;
    private       MetOfficeLocation          location;
    //TODO: ugly hack, fix, need function :: location -> postcode
    private       String                     postcode;
    private       boolean                    highContrastMode;
    private final Runnable                     updateLocation;
    private final Runnable                     setHighContrast;
    private final Runnable                     setLowContrast;
    
    public Model(Runnable updateLocation, Runnable setHighContrast, Runnable setLowContrast) {
        this.updateLocation = updateLocation;
        this.setHighContrast = setHighContrast;
        this.setLowContrast = setLowContrast;
        //TODO: initialize with user's current location instead
        this.postcode = INITIAL_POSTCODE;
        var loc = Location.fromAddress(this.postcode);
        if(loc!=null){
            this.location = loc.closest(MetOfficeAPI.hourlyLocationList());
        }
        setHighContrastMode(false);
        alerts = Arrays.stream(ExtremeEvent.values())
                       .collect(Collectors.toMap(e -> e, e -> false));
    }
    
    public int getLocationID() {
        return location != null ? location.id : -1;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public String getPostcode() {
        return postcode;
    }
    
    public void setPostcode(String postcode) {
        var loc = Location.fromAddress(postcode);
        if(loc!=null){
            var closest = loc.closest(MetOfficeAPI.hourlyLocationList());
            if(closest!=null){
                this.location = closest;
                this.postcode = postcode;
                this.updateLocation.run();
                System.out.println(this.location);
                System.out.println(this.postcode);
            }
        }
    }
    
    public boolean getHighContrastMode() {
        return highContrastMode;
    }
    
    public void setHighContrastMode(boolean highContrast) {
        this.highContrastMode = highContrast;
        if(highContrast) {
            this.setHighContrast.run();
        } else {
            this.setLowContrast.run();
        }
    }
    
    public boolean getAlert(ExtremeEvent e) {
        return alerts.get(e);
    }
    
    public void setAlert(ExtremeEvent e, boolean value) {
        alerts.put(e, value);
    }
}
