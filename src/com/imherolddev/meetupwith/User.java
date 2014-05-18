package com.imherolddev.meetupwith;

import java.io.Serializable;
import java.util.Calendar;

/**
 * com.imherolddev.meetupwith<br></br>
 * <br><br/>
 * Created by imherolddev on 3/30/14.<br></br>
 * Last edited: creation<br></br>
 * <br></br>
 * Class Description:<br></br>
 * -POJO, uses only getter methods. The only means of setting fields is through the constructor.<br></br>
 * <br></br>
 * ------------------------------<br></br>
 * Change log:<br></br>
 * -<br></br>
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nickname;
    private String firstName;
    private String lastName;
    private String email;
    private char[] password;
    private String school;
    private Calendar birthday;
    private boolean locationReporting;
    private boolean emailReporting;
    private long refreshRate;
    private boolean purchased;

    /**
     * No arg constructor for creation of User from serialized object
     */
    public User(){}

    /**
     * -Protected constructor, the only method for setting fields, for use only in DAO
     * @param nickname - Users nickname
     * @param firstName - Users first name
     * @param lastName - Users last name
     * @param email - Users email address
     * @param password - Users password
     * @param school - Users school
     * @param birthday - Users birthday
     * @param locationReporting - Users location reporting preference
     * @param emailReporting - Users email reporting preference
     * @param refreshRate - Users refresh rate preference
     * @param purchased - Users purchased status
     */
    public User(String nickname, String firstName, String lastName, String email, char[] password,
                   String school, Calendar birthday, boolean locationReporting, boolean emailReporting,
                   long refreshRate, boolean purchased) {

        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.school = school;
        this.birthday = birthday;
        this.locationReporting = locationReporting;
        this.emailReporting = emailReporting;
        this.refreshRate = refreshRate;
        this.purchased = purchased;

    }


    public String getNickname() {
        return nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public char[] getPassword() {
        return password;
    }

    public String getSchool() {
        return school;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public boolean isLocationReporting() {
        return locationReporting;
    }

    public boolean isEmailReporting() {
        return emailReporting;
    }

    public long getRefreshRate() {
        return refreshRate;
    }

    public boolean isPurchased() {
        return purchased;
    }
}
