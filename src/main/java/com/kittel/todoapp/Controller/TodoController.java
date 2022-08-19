package com.kittel.todoapp.Controller;


import com.kittel.todoapp.Entity.Todo;
import com.kittel.todoapp.Errorhandling.ResponseHandler;
import com.google.gson.Gson;
import com.kittel.todoapp.Service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private static final Gson gson = new Gson();
    private TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Todo todo){
        try
        {
            todoService.create(todo);
        }
        catch(Exception e){
            return ResponseHandler.generateResponse("Todo could not be created!", HttpStatus.BAD_REQUEST, todo);
        }
        return ResponseHandler.generateResponse("Todo created!", HttpStatus.OK, todo);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Todo todo) {
        try
        {
            todoService.update(todo);
        }
        catch(Exception e){
            return ResponseHandler.generateResponse("Todo could not be updated!", HttpStatus.BAD_REQUEST, todo);
        }
        return ResponseHandler.generateResponse("Todo updated!", HttpStatus.OK, todo);
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Todo todo){
        try
        {
            todoService.delete(todo);
        }
        catch(Exception e){
            return ResponseHandler.generateResponse("Todo not found or could not be deleted!", HttpStatus.BAD_REQUEST, todo);
        }
        return ResponseHandler.generateResponse("Todo deleted!", HttpStatus.OK, todo);
    }

    @GetMapping
    public ResponseEntity<Object> get(Optional<Boolean> finished){
        List list = null;
        try
        {
            if(finished.isPresent()){
                list = todoService.get(finished.get());
            } else {
                list = todoService.get();
            }
        }
        catch(Exception e){
            return ResponseHandler.generateResponse("Error", HttpStatus.BAD_REQUEST, "");
        }
        return ResponseHandler.generateResponse("Success", HttpStatus.OK,  (list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable Long id){
        Todo todo = null;
        try
        {
            todo = todoService.findById(id);
        }
        catch(Exception e){
            return ResponseHandler.generateResponse("Error", HttpStatus.BAD_REQUEST, "");
        }
        return ResponseHandler.generateResponse("Success", HttpStatus.OK,  gson.toJson(todo));
    }
}
