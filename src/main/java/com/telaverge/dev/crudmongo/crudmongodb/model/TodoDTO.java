/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telaverge.dev.crudmongo.crudmongodb.model;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author devsu
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="todos")
public class TodoDTO {
    @Id
    private String id;
    
    @NotNull(message = "Todo cannot be null")
    private String todo;
    
    @NotNull(message = "Description cannot be null")
    private String description;
    
    @NotNull(message = "Completed cannot be null")
    private Boolean completed;
    
    
    private Date createdAt;
    
    
    private Date updatedAt;

    
    
}
