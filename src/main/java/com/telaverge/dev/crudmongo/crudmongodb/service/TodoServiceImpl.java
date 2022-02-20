/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telaverge.dev.crudmongo.crudmongodb.service;

import com.telaverge.dev.crudmongo.crudmongodb.exception.TodoCollectionException;
import com.telaverge.dev.crudmongo.crudmongodb.model.TodoDTO;
import com.telaverge.dev.crudmongo.crudmongodb.repository.TodoRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author devsu
 */
@Service
public class TodoServiceImpl implements TodoService {
    
    @Autowired
    private TodoRepository todoRepo;
   
    @Override
    public void createTodo(TodoDTO todo) throws ConstraintViolationException,TodoCollectionException {
        
        Optional<TodoDTO> optional = todoRepo.findByTodo(todo.getTodo());
        if(optional.isPresent()){
            throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
        }else{
            todo.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepo.save(todo);
        }
    }

    @Override
    public List<TodoDTO> getAllTodos() {
       List<TodoDTO> todos = todoRepo.findAll();
       if(todos.size()>0){
           return todos;
       }
       else{
           return new ArrayList<TodoDTO>();
       }
    }

    @Override
    public TodoDTO getSinglrTodo(String id) throws TodoCollectionException {
        Optional<TodoDTO> optional = todoRepo.findById(id);
        if(!optional.isPresent()){
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }else{
            return optional.get();
        }
    }

    @Override
    public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException {
        Optional<TodoDTO> todoWithId = todoRepo.findById(id);
        Optional<TodoDTO> todoWithSameName = todoRepo.findByTodo(todo.getTodo());
        
        if(todoWithId.isPresent()){
            if(todoWithSameName.isPresent() && !todoWithSameName.get().getId().equals(id)){
                throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
            }
            TodoDTO todoToUpdate = todoWithId.get();
            
            todoToUpdate.setTodo(todo.getTodo());
            todoToUpdate.setDescription(todo.getDescription());
            todoToUpdate.setCompleted(todo.getCompleted());
            todoToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
            todoRepo.save(todoToUpdate);
        }else{
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
        
    }

    @Override
    public void deleteTodoById(String id) throws TodoCollectionException {
        Optional<TodoDTO> optional = todoRepo.findById(id);
        if(!optional.isPresent()){
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
            
        }else{
            todoRepo.deleteById(id);
            
        }
        
    }
    
    
    
}
