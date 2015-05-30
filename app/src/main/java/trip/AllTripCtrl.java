package trip;

import java.util.ArrayList;

import trip.Trip;

/**
 * Created by Windows 8 on 30-May-15.
 */
public class AllTripCtrl {
    private ArrayList<Trip> allOtherTrip;
    private ArrayList<Trip> allOwnerTrip;

    public AllTripCtrl()
    {
        //retrieve all data in database
    }

    public ArrayList<Trip> getAllOtherTrip() {
        return allOtherTrip;
    }

    public ArrayList<Trip> getAllOwnerTrip() {
        return allOwnerTrip;
    }

    //All below method should be have parameter
    //Not sure need what first
    public boolean joinTrip()
    {
        return true;
    }

    public boolean rejectRequest()
    {
        return true;
    }

    public boolean acceptRequest()
    {
        return true;
    }

    public void registerNewTrip()
    {

    }
}
