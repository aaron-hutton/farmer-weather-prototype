package uk.ac.cam.cl.interaction_design.group19.app;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import java.io.IOException;
import java.util.List;

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

    public MetOfficeLocation closest(List<MetOfficeLocation> others) {
        /*
        Get the closest location from a list.
         */
        double best_distance = Double.POSITIVE_INFINITY;
        MetOfficeLocation best = null;
        for (MetOfficeLocation other : others) {
            if (this.distance(other) < best_distance) {
                best_distance = this.distance(other);
                best = other;
            }
        }
        return best;
    }

    public static Location fromAddress(String address) {
        GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSy" + "CtBJf79pbh3v8Ko4aWZkFHeCHqhCP1OpA").build();    // definitely not an API key
        try {
            GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
            return new Location(results[0].geometry.location.lat, results[0].geometry.location.lng);
        } catch (IOException | ApiException | InterruptedException e) {
            System.err.println("Error in finding lat/long for location");
            return new Location(50, 50);
        }
    }
}
