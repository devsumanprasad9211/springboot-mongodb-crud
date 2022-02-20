/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telaverge.dev.crudmongo.crudmongodb.controller;

import com.telaverge.dev.crudmongo.crudmongodb.exception.TodoCollectionException;
import com.telaverge.dev.crudmongo.crudmongodb.model.TodoDTO;
import com.telaverge.dev.crudmongo.crudmongodb.repository.TodoRepository;
import com.telaverge.dev.crudmongo.crudmongodb.service.TodoService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author devsu
 */
@RestController
public class TodoController {
    
    @Autowired
    private TodoRepository todoRepo;
    
    @Autowired
    private TodoService todoService;
    
    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos(){
        List<TodoDTO> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, todos.size()>0?HttpStatus.OK:HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo){
        try{
           //todo.setCreatedAt(new Date(System.currentTimeMillis()));
           
           //todoRepo.save(todo);
           todoService.createTodo(todo);
           return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
        }catch(ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            
        }catch(TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    
    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id){
        try{
           return new ResponseEntity<>(todoService.getSinglrTodo(id), HttpStatus.OK); 
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        
    }
    
    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody TodoDTO todo){
        try{
            todoService.updateTodo(id, todo);
            return new ResponseEntity<>("updated todo with id "+id, HttpStatus.OK);
        }
        catch(ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }catch(TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable("id") String id ){
        try{
            todoService.deleteTodoById(id);
            return new ResponseEntity<>("Successfully deleted with id "+id, HttpStatus.OK);
        }catch(TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
            
        }
    }
    
    
    
}
