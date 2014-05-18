/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.imherolddev.meetupwith.DAO;

import com.imherolddev.meetupwith.User;

/**
 *
 * @author imherolddev
 */
public interface UserDAO {
    
    /**
     * Create user in database
     * @param user 
     * @throws com.imherolddev.meetupwith.DAO.DAOException 
     */
    public void create(User user) throws DAOException;
    
    /**
     * Login user by nickname and password
     * @param nickname
     * @param password
     * @return user
     * @throws com.imherolddev.meetupwith.DAO.DAOException
     */
    public User read(String nickname, String password) throws DAOException;
    
    /**
     * Gets users nearby user
     * @param nickname
     * @throws DAOException 
     */
    public void findNearbyUsers(String nickname) throws DAOException;
    
    /**
     * Update user
     * @param user 
     * @throws com.imherolddev.meetupwith.DAO.DAOException 
     */
    public void update(User user) throws DAOException;
    
    /**
     * Update users locations
     * @param nickname - current users nickname
     * @throws DAOException 
     */
    public void updateLocation(String nickname) throws DAOException;
    
    /**
     * Delete user by nickname
     * @param nickname 
     * @throws com.imherolddev.meetupwith.DAO.DAOException 
     */
    public void delete(String nickname) throws DAOException;
    
}
