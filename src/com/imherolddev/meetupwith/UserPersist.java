package com.imherolddev.meetupwith;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * com.imherolddev.meetupwith<br></br>
 * <br><br/>
 * Created by imherolddev on 3/30/14.<br></br>
 * Last edited: creation<br></br>
 * <br></br>
 * Class Description:<br></br>
 * -Persistence methods for serializing and de-serializing<br></br>
 * <br></br>
 * ------------------------------<br></br>
 * Change log:<br></br>
 * -<br></br>
 */
public class UserPersist {

    private final static Logger LOG = Logger.getLogger(UserPersist.class.getName());

    protected User readUser() {

        //User object to be read into
        User user = new User();

        //Serialized file
        File userFile = new File("user.ser");

        //Test for serialized user file and read
        if (userFile.exists() && !userFile.isDirectory() && userFile.canRead()) {

            try (FileInputStream fileIn = new FileInputStream("user.ser");
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {

                user = (User) in.readObject();

            } catch (IOException | ClassNotFoundException ioex) {

                LOG.log(Level.SEVERE, "Error reading User object", ioex);

            } //end try/catch

        } else {

            //No serialized user exists, return null to register a new user
            user = null;

        } //end if/else

        //Return resulting User
        return user;

    } //end readUser()

    protected void writeUser(User user) {

        if (user != null) {

            //write serialized user object to file
            try(FileOutputStream fileOut = new FileOutputStream("user.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

                out.writeObject(user);

            } catch(IOException ioex) {

                LOG.log(Level.SEVERE, "Error writing User object", ioex);

            } //end try/catch

        } //end if

    } //end writeUser()

} //end UserPersist
