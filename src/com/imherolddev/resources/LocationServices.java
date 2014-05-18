package com.imherolddev.resources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author imherolddev
 */
public class LocationServices {
    
    private int distance = 0;
    private final int MIN = 1;
    private final int MAX = 1000;
    
    public int locate() {
        
        this.distance = this.MIN + (int)(Math.random() * ((this.MAX - this.MIN) + 1));
        return this.distance;
        
    }
    
}
