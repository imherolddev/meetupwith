package com.imherolddev.meetupwith.GUI;

import com.imherolddev.meetupwith.DAO.DAOException;
import com.imherolddev.meetupwith.DAO.UserDAO;
import com.imherolddev.meetupwith.Registration;
import com.imherolddev.meetupwith.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * com.imherolddev.meetupwith.GUI<br></br>
 * <br><br/>
 * Created by imherolddev on 3/30/14.<br></br>
 * Last edited: creation<br></br>
 * <br></br>
 * Class Description:<br></br>
 * -Menu for User Interface<br></br>
 * <br></br>
 * ------------------------------<br></br>
 * Change log:<br></br>
 * -<br></br>
 */
public class Menu {
    
    /**
     * Logger instance
     */
    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    /**
     * User object to be populated
     */
    private User user;
    
    /**
     * Scanner to collect user input
     */
    private Scanner input = new Scanner(System.in);
    /**
     * Registration to register user
     */
    private Registration newReg = null;
    /**
     * Choice user will make in integer
     */
    private int choice = 0;

    /**
     * sentinel value
     */
    private final int QUIT = 99;

    /**
     * Method Description:<br></br>
     * -Main menu of choices for user to navigate
     * @param dao allows user to be stored to database
     * @return User that was either created or returned from database
     */
    public User welcomeMenu(UserDAO dao) {

        while (choice != QUIT) {
        	

            System.out.println("Welcome to Meet Up With! "
                    + "\n"
                    + "\n    -If you already have an account please enter [2] to log in "
                    + "\n"
                    + "\n    -If you are new here, please enter [1] to create a new account "
                    + "\n"
                    + "\n    (If you are in the wrong place, enter [99] to exit)");

            try {
                choice = input.nextInt();
            } catch (InputMismatchException imex) {
                System.err.println("\nPlease use a number to make your selection\n");
                input.next();
                continue;
            }

            switch (choice) {

                case 1:
                    //Use Registration to create new user
                    newReg = new Registration();
                    this.user = this.newRegister(newReg, dao);
                    choice = QUIT;
                    break;

                case 2:
                    //run login menu
                    this.user = this.login(dao);
                    choice = QUIT;
                    break;

                case 99:
                    System.out.println("We hope you find what you are looking for");
                    break;

                default:
                    System.out.println("Please enter one of the number options from the menu, "
                            + "\nIf you are not sure you belong here, please enter [99]");

            } //end switch()

        } //end while()
        
        //return registered, or logged in user
        return this.user;

    } //end welcomeMenu()

    /**
     * Method Description:<br></br>
     * -Registration menu
     * @param newReg allows registration methods to be used
     * @param dao allows user to be stored to database
     * @return User that was either created
     */
    private User newRegister(Registration newReg, UserDAO dao) {

        System.out.println("Glad you are interested in becoming a registerd user!"
                + "\nPlease follow the instructions so we can collet the necessary info to get you started!");
        
        while(choice != QUIT) {
            
            try {
                
            System.out.println("\nWhat is your first name?\n");
            newReg.setFirstName(input.next());
            
            System.out.println("OK, what is your last name?");
            newReg.setLastName(input.next());
            
            System.out.println("Enter your e-mail");
            newReg.setEmail(input.next());
            
            System.out.println("What is your birthday? example: 10/03/1984 (We may just send you a cake, "
                    + "\nor atleast a picture of one)");
            Calendar cal = Calendar.getInstance();
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            
            try {
            cal.setTime((date = df.parse(input.next())));
            } catch(ParseException pex) {
                //Logging
            }
            newReg.setBirthday(cal);
            
            System.out.println("Pick a nickname that suits you:");
            newReg.setNickname(input.next());
            
            System.out.println("Please enter a password, 8-20 characters");
            newReg.setPassword(input.next().toCharArray());
            
            System.out.println("If you would like to let others know what school you went to"
                    + "\nenter its name here:");
            newReg.setSchool(input.next().toUpperCase());
            
            } catch(InputMismatchException imex) {
                System.out.println("Oops, you entered something we weren't expecting, please try again");
                input.next();
            }
            
            this.user = newReg.newUser();
            
            try{
            dao.create(user);
            } catch(DAOException daoex) {
                LOG.log(Level.SEVERE, "Error creating user", daoex);
            }

            choice = QUIT;
            
        }
        
        return this.user;

    } //end newRegister()

    /**
     * Method Description:<br></br>
     * -login menu
     * @param dao allows user to be retrieved from database
     * @return User that was returned from database
     */
    private User login(UserDAO dao) {
        
        User loggedUser = null;
        System.out.println("Please enter your username:");
        
        String nickname = input.next();
        
        System.out.println("Please enter your password:");
        
        String password = input.next();
        
        try {
            
            loggedUser = dao.read(nickname, password);
        } catch(DAOException daoex) {
            LOG.log(Level.SEVERE, "Error reading user", daoex);
        }
        
        return loggedUser;

    } //end login()

} //end Menu
