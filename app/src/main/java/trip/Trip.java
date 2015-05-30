package trip;
/**
 * Created by User on 16 05 2015.
 */

import java.util.Calendar;

public class Trip {

    private String destination;
    private int time;
    private Calendar date;
    private int capacity;

    public Trip(String destination,int time, Calendar date, int capacity){
        this.destination = destination;
        this.time = time;
        this.date = date;
        this.capacity = capacity;
    }

    private String getDestination(){
        return destination;
    }

    private int getTime(){
        return time;
    }

    private Calendar getDate(){
        return date;
    }

    private int getCapacity(){
        return capacity;
    }
}
