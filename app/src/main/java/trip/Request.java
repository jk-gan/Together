package trip;

/**
 * Created by Windows 8 on 30-May-15.
 */
public class Request {

    private String fullName;
    private int status;

    public Request(String fullName, int status )
    {
        this.fullName = fullName;
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public int getStatus() {
        return status;
    }
}
