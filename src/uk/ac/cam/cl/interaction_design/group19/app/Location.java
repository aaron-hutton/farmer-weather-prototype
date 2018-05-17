package uk.ac.cam.cl.interaction_design.group19.app;

public class Location {
    public final double latitude;
    public final double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double distance(Location other) {
        /*
        Return the square of the distance between two locations.
        This works because the Earth is flat.
         */
        return Math.pow(this.latitude - other.latitude, 2) + Math.pow(this.longitude - other.longitude, 2);
    }
}
