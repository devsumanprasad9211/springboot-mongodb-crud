/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telaverge.dev.crudmongo.crudmongodb.exception;

/**
 *
 * @author devsu
 */
public class TodoCollectionException extends Exception {
    
    
    
    private static final long serialVersionUID = 1L; 
    
    public TodoCollectionException(String message){
        super(message);
    }
    
    public static String NotFoundException(String id){
        return "todp not found with id "+id;
    }
    
    public static String TodoAlreadyExists(){
        return "Todo with given name already exists";
    }
    
}
