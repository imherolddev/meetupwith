/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.imherolddev.meetupwith.DAO;

/**
 *
 * @author imherolddev
 */
public class DAOFactory {
    
    public UserDAO getDAO() {
        
        return new UserDAOImpl();
        
    }
    
}
