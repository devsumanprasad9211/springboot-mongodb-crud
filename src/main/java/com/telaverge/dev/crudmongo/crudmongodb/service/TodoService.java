/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.telaverge.dev.crudmongo.crudmongodb.service;

import com.telaverge.dev.crudmongo.crudmongodb.exception.TodoCollectionException;
import com.telaverge.dev.crudmongo.crudmongodb.model.TodoDTO;
import java.util.List;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author devsu
 */
public interface TodoService {
    public void createTodo(TodoDTO todo) throws ConstraintViolationException,TodoCollectionException;
    
    public List<TodoDTO> getAllTodos();
    
    public TodoDTO getSinglrTodo(String id) throws TodoCollectionException;
    
    public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException;
    
    public void deleteTodoById(String id) throws TodoCollectionException;
}
