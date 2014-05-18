/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imherolddev.meetupwith;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.imherolddev.meetupwith.DAO.DAOException;
import com.imherolddev.meetupwith.DAO.UserDAO;

/**
 *
 * @author imherolddev
 */
public class MUW_Process {

    public void startProcess(final User user, final UserDAO dao) {

        final ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();

        Runnable task1 = new Runnable() {
            
            public void run() {
                
                try {
                    dao.updateLocation(user.getNickname());
                    dao.findNearbyUsers(user.getNickname());
                } catch(DAOException daoex) {
                    //Logging
                }

            }
        };
        exec.scheduleAtFixedRate(task1, 0, 5000, TimeUnit.MILLISECONDS);

    }

}
