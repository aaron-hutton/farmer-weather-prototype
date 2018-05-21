package uk.ac.cam.cl.interaction_design.group19.app.GDDs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public static double gddSince(int postCode, double baseTemp, Date s) throws IllegalArgumentException {

        DegreeDaysApi api = new DegreeDaysApi(
                new AccountKey("test-test-test"),
                new SecurityKey("test-test-test-test-test-test-test-test-test-test-test-test-test"));

        Calendar cal = Calendar.getInstance();
        Day end = new Day(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
        cal.setTime(s);
        Day start = new Day(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
        DayRange range = new DayRange(start, end);

        DatedDataSpec hddSpec = DataSpec.dated(
                Calculation.heatingDegreeDays(Temperature.celsius(baseTemp)),
                DatedBreakdown.monthly(Period.dayRange(range)));

        LocationDataRequest request = new LocationDataRequest(
                Location.postalCode("02630", "US"),
//                Location.postalCode(Integer.toString(postCode), "US"),
                new DataSpecs(hddSpec));

        LocationDataResponse response = api.dataApi().getLocationData(request);

        DatedDataSet hddData = response.dataSets().getDated(hddSpec);

        double tot=0;
        for (DatedDataValue v : hddData.getValues()) {
            tot+=v.value();
        }

        return tot;
    }

    public static ArrayList<Double> gddForecast(int location, double base) {
        return MetOfficeAPI.gddForecast(location, base);
    }

    public static void main(String[] args) {

        SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yy");
        Date d = null;
        try {
            d = sdf.parse("12-12-2012");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(gddSince(1, 10, d));
    }
}
