package people;

/**
 * Created by JKGan on 5/16/15.
 */
public class Users {
    private String userID;
    private String userName;
    private String userPassword;
    private String fullName;

    /**
     * Current login user constructor
     */
    public Users(String userID,String userName, String userPassword, String fullName) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.fullName = fullName;
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

    /**
     * Get user password
     */
    public String getUserPassword() {
        return this.userPassword;
    }

    /**
     * Get user full name
     */
    public String getFullName() {
        return this.fullName;
    }
}
