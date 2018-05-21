package uk.ac.cam.cl.interaction_design.group19.app.api;

public class MetOfficeLocation extends Location {
    public final int id;
    
    public MetOfficeLocation(int id, double latitude, double longitude) {
        super(latitude, longitude);
        this.id = id;
    }
    
    @Override
    public String toString(){
        return "id="+id+" "+super.toString();
    }
}
