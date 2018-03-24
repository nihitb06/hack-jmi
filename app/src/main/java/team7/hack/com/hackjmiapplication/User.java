package team7.hack.com.hackjmiapplication;

/**
 * Created by AM on 24-03-2018.
 */

public class User {
    String userId;
    String userPhone;
    String userName;
    String userDob;
    String userPassword;
    String userBg;

    public User(){}

    public User(String userId, String userPhone, String userPassword, String userName, String userDob, String userBg) {
        this.userId = userId;
        this.userPhone = userPhone;
        this.userPassword = userPassword;

        this.userName = userName;
        this.userDob = userDob;
        this.userBg = userBg;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserDob() {
        return userDob;
    }
    public String getUserBg(){
        return userBg;
    }
}

