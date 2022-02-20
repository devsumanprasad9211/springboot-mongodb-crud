/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.telaverge.dev.crudmongo.crudmongodb.repository;

import com.telaverge.dev.crudmongo.crudmongodb.model.TodoDTO;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author devsu
 */
@Repository
public interface TodoRepository extends MongoRepository<TodoDTO,String> {
    
    @Query("{'todo':?0}")
    Optional<TodoDTO> findByTodo(String todo);
    
}
