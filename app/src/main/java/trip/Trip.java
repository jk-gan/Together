package trip;
/**
 * Created by User on 16 05 2015.
 */

import java.util.ArrayList;
import java.util.Calendar;

public class Trip {

    private String tripID;
    private String description;
    private String destination;
    private int time;
    private Calendar date;
    private int capacity;
    private String departPLace;
    private int status;
    private ArrayList<Request> requests;



    public Trip(String description, String tripID){
        this.tripID = tripID;
        this.description = description;
        this.destination = destination;
        this.time = time;
        this.date = date;
        this.capacity = capacity;
        this.departPLace = departPLace;
        this.status = status;
    }

    public void addRequests(Request request)
    {
        this.requests.add(request);
    }

    public String getDestination(){
        return destination;
    }

    public int getTime(){
        return time;
    }

    public Calendar getDate(){
        return date;
    }

    public int getCapacity(){
        return capacity;
    }

    public String getTripID() {
        return tripID;
    }

    public String getDescription() {
        return description;
    }

    public String getDepartPLace() {
        return departPLace;
    }

    public int getStatus() {
        return status;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }
}
