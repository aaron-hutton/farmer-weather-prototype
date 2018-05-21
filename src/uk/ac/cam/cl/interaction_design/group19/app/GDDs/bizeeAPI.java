package uk.ac.cam.cl.interaction_design.group19.app.GDDs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import net.degreedays.api.AccountKey;
import net.degreedays.api.DegreeDaysApi;
import net.degreedays.api.SecurityKey;
import net.degreedays.api.data.Calculation;
import net.degreedays.api.data.DataSpec;
import net.degreedays.api.data.DataSpecs;
import net.degreedays.api.data.DatedBreakdown;
import net.degreedays.api.data.DatedDataSet;
import net.degreedays.api.data.DatedDataSpec;
import net.degreedays.api.data.DatedDataValue;
import net.degreedays.api.data.Location;
import net.degreedays.api.data.LocationDataRequest;
import net.degreedays.api.data.LocationDataResponse;
import net.degreedays.api.data.Period;
import net.degreedays.api.data.Temperature;
import net.degreedays.time.Day;
import net.degreedays.time.DayRange;
import uk.ac.cam.cl.interaction_design.group19.app.api.MetOfficeAPI;

public class bizeeAPI {
    
    //Get the total number of degree days since a specified start date at a specified location
    public double gddSince(int postCode, double baseTemp, Date s) throws IllegalArgumentException {
        //Using the bizee degree days API free trial (DegreeDaysApi-1.1.2.jar)
        
        //Test account with api
        DegreeDaysApi api = new DegreeDaysApi(
                new AccountKey("test-test-test"),
                new SecurityKey("test-test-test-test-test-test-test-test-test-test-test-test-test"));

        Calendar cal = Calendar.getInstance();
        
        //Get current day
        Day end = new Day(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
        
        //Set time to given start date
        cal.setTime(s);
        //Get start date
        Day start = new Day(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
        //Create new dayrange (api class) for the range from the start date to today
        DayRange range = new DayRange(start, end);
        
        //Pass date arguments to api call
        DatedDataSpec hddSpec = DataSpec.dated(
                Calculation.heatingDegreeDays(Temperature.celsius(baseTemp)),
                DatedBreakdown.monthly(Period.dayRange(range)));
        
        //Pass location arguments to api call
        LocationDataRequest request = new LocationDataRequest(
                //Free trial is only for Cape Cod so the location is static in this prototype
                Location.postalCode("02630", "US"),
//                Location.postalCode(Integer.toString(postCode), "US"),
                new DataSpecs(hddSpec));
        
        //Request data from server
        LocationDataResponse response = api.dataApi().getLocationData(request);
        
        //Receive data from server
        DatedDataSet hddData = response.dataSets().getDated(hddSpec);
        
        //Sum up all degree days for the range
        double tot=0;
        for (DatedDataValue v : hddData.getValues()) {
            tot+=v.value();
        }
        
        //Return a double of the total degree days since the given start date
        return tot;
    }
    
    //Make a request to the MetOfficeApi to get the GDDs for the next five days
    public ArrayList<Double> gddForecast(int location, double base) {
        MetOfficeAPI api = new MetOfficeAPI();
        return api.gddForecast(location, base);
    }

//    public static void main(String[] args) {
//
//        SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yy");
//        Date d = null;
//        try {
//            d = sdf.parse("12-12-2012");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(gddSince(1, 10, d));
//    }
}
