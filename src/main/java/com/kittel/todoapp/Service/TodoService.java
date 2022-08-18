package com.kittel.todoapp.Service;

import com.kittel.todoapp.Entity.Todo;
import com.kittel.todoapp.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TodoService {
    TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }
    public List<Todo> get(){
        return todoRepository.findAll();
    }

    public Todo findById(long id) throws Exception {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isEmpty()){
            throw new Exception();
        }
        return todo.get();
    }

    public void delete(Todo todo) throws Exception {
        findById(todo.getId());
        todoRepository.delete(todo);
    }

    public void update(Todo todo) throws Exception {
        findById(todo.getId());
        todoRepository.saveAndFlush(todo);
    }

    public void create(Todo todo)  {
        todoRepository.saveAndFlush(todo);
    }
}
