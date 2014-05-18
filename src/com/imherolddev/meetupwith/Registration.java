package com.imherolddev.meetupwith;

import java.util.Calendar;

/**
 * com.imherolddev.meetupwith<br></br>
 * <br><br/>
 * Created by imherolddev on 3/30/14.<br></br>
 * Last edited: creation<br></br>
 * <br></br>
 * Class Description:<br></br>
 * -<br></br>
 * <br></br>
 * ------------------------------<br></br>
 * Change log:<br></br>
 * -<br></br>
 */
public class Registration {

    private String nickname = "";
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private char[] password = null;
    private String school = "";
    private Calendar birthday = null;
    private boolean locationReporting = true;
    private boolean emailReporting = true;
    private long refreshRate = 0;
    private boolean purchased = false;


    public User newUser() {

        return new User(nickname, firstName, lastName, email,
                password, school, birthday, locationReporting,
                emailReporting, refreshRate, purchased);

    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public void setLocationReporting(boolean locationReporting) {
        this.locationReporting = locationReporting;
    }

    public void setEmailReporting(boolean emailReporting) {
        this.emailReporting = emailReporting;
    }

    public void setRefreshRate(long refreshRate) {
        this.refreshRate = refreshRate;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }
}
