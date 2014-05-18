package com.imherolddev.meetupwith;

import com.imherolddev.meetupwith.DAO.DAOFactory;
import com.imherolddev.meetupwith.DAO.UserDAO;
import com.imherolddev.meetupwith.GUI.Menu;
import com.imherolddev.resources.logger.IHOD_Logger;

import java.io.IOException;

/**
 * com.imherolddev.meetupwith<br></br>
 * <br><br/>
 * Created by imherolddev on 3/30/14.<br></br>
 * Last edited: creation<br></br>
 * <br></br>
 * Class Description:<br></br>
 * -Main method for Meet Up With<br></br>
 * <br></br>
 * ------------------------------<br></br>
 * Change log:<br></br>
 * -<br></br>
 */
public class MeetUpWithMain {

    //main entry for MeetUpWith
    public static void main(String[] args) {

        //setup Logger
        try {
            IHOD_Logger.setup();
        } catch(IOException ioex) {
            ioex.printStackTrace();
            throw new RuntimeException("Error creating log files");
        } //end try/catch

        //User object to be passed through methods
        User user;
        //Instantiate User Persistence
        UserPersist persist = new UserPersist();
        //Menu class to be used for inputs
        Menu menu = new Menu();
        //Instantiate DAO factory and return DAOImpl
        DAOFactory factory = new DAOFactory();
        UserDAO dao = factory.getDAO();
        //attempt to read serialized User
        if((user = persist.readUser()) == null) {
            user = menu.welcomeMenu(dao);
        } //end if

        if(user == null) {
            System.out.println("we are having issues...");
        } else {
            System.out.println("user created successfully");
            
            System.out.println("User: "
                    + user.getNickname() +
                    "\n" + user.getEmail());
        }
        
        MUW_Process process = new MUW_Process();
        process.startProcess(user, dao);
        
        //write user to file for persistence
        persist.writeUser(user);

    } //end main()

} //end MeetUpWithMain
