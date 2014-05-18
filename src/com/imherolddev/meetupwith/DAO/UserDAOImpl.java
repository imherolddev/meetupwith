/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imherolddev.meetupwith.DAO;

import com.imherolddev.meetupwith.User;
import com.imherolddev.resources.LocationServices;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author imherolddev
 */
public class UserDAOImpl implements UserDAO {

    private static final Logger LOG = Logger.getLogger(UserDAOImpl.class.getName());

    private Connection con = null;
    private final String ERROR = "Error with database connection";
    private final String DAO_ERR = "Error accessing the Data Object";

    UserDAOImpl() {

        String url = "jdbc:mysql://localhost:3306/meetupwith";
        String username = "root";
        String password = "yoshiashy";

        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException sqlex) {
            LOG.log(Level.SEVERE, "Error connecting to database", sqlex);
        }

    }

    @Override
    public void create(User user) throws DAOException {
        
        LOG.entering("UserDAOImpl", "create()");

        try (java.sql.Statement statement = this.con.createStatement()) {

            String query = "INSERT INTO users(nickname, firstName, lastName, email, password, school, birthday) "
                    + "VALUES('" + user.getNickname() + "', "
                    + "'" + user.getFirstName() + "', "
                    + "'" + user.getLastName() + "', "
                    + "'" + user.getEmail() + "', "
                    + "'" + new String(user.getPassword()) + "', "
                    + "'" + user.getSchool() + "', "
                    + "'" + new java.sql.Date(user.getBirthday().getTime().getTime()) + "');";

            if (statement.executeUpdate(query) != 1) {

                throw new DAOException("Error adding user to database");

            } else {
                System.out.println("Success!");
            }

        } catch (SQLException sqlex) {

            LOG.log(Level.SEVERE, this.ERROR, new DAOException(this.DAO_ERR, sqlex));

        }
        
        LOG.exiting("UserDAOImpl", "create()");

    }

    @Override
    public User read(String nickname, String password) {

        User foundUser;

        try (java.sql.Statement statement = con.createStatement()) {

            String query = "SELECT * FROM users "
                    + "WHERE users.nickname = '" + nickname + "' "
                    + "AND users.password = '" + password + "';";

            ResultSet rs = statement.executeQuery(query);
            Calendar cal = Calendar.getInstance();

            if (rs.next()) {

                foundUser = new User(rs.getString("nickname"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("password").toCharArray(),
                        rs.getString("school"),
                        cal,
                        rs.getBoolean("locationReporting"),
                        rs.getBoolean("emailReporting"),
                        rs.getLong("refreshPreference"),
                        rs.getBoolean("purchased"));

            } else {
                foundUser = null;
                System.out.println("Sorry we could not find your account, please make sure your username and password are correct!");
            }

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            foundUser = null;
            LOG.log(Level.SEVERE, this.ERROR, new DAOException(this.DAO_ERR));

        }

        return foundUser;

    }
    
    @Override
    public void findNearbyUsers(String nickname) throws DAOException {
        
        try(java.sql.Statement statement = con.createStatement()) {
            
            String query = "SELECT nickname FROM users WHERE location < 50 && nickname != '" + nickname + "';";
            
            ResultSet rs = statement.executeQuery(query);
            
            while(rs.next()) {
                
                System.out.println("User, " + rs.getString("nickname") + ", is nearby");
                
            }
            
        } catch(SQLException sqlex) {
            System.out.print(sqlex);
        }
        
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateLocation(String nickname) throws DAOException {

        LocationServices locServ = new LocationServices();
        List<String> users = new ArrayList<>();

        try (java.sql.Statement statement = con.createStatement()) {

            String query = "SELECT nickname FROM users "
                    + "WHERE users.nickname != '" + nickname + "';";

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                users.add(rs.getString(1));

            }

        } catch (SQLException sqlex) {

            //Logging
        }
        
        try(java.sql.Statement statement = con.createStatement()) {
            
            for(String user : users) {
                
                String query = "UPDATE users SET location = " + locServ.locate() + " WHERE nickname = '" + user + "';";
                if(statement.executeUpdate(query) != 1) {
                    throw new DAOException("Error changing location");
                }
                
            }
            
        } catch(SQLException sqlex) {
            //Logging
        }

    }

    @Override
    public void delete(String nickname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
