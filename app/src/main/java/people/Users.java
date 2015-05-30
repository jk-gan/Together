package people;

/**
 * Created by JKGan on 5/16/15.
 */
public class Users {
    private String userID;
    private String userName;

    /**
     * Current login user constructor
     */
    public Users(String userID,String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    /**
     * Get user name
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Get user ID
     */
    public String getUserID() {
        return this.userID;
    }
}
