package com.kittel.todoapp;

import com.kittel.todoapp.Controller.TodoController;
import com.kittel.todoapp.Entity.Todo;
import com.kittel.todoapp.Repository.TodoRepository;
import com.kittel.todoapp.Service.TodoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
class TodoappApplicationTests {
	@Mock
	private TodoRepository todoRepository;

	@Test
	@DisplayName("Should throw Exception if no Todo was found")
	void testTodoServiceFindById() throws Exception {
		TodoService todoService = new TodoService(todoRepository);
		Assertions.assertThrows(Exception.class, () -> {
			todoService.findById(1);
		});
	}

	@Test
	@DisplayName("Should find todos and should see given values")
	void testTodoServiceFindByIdSuccess() {
		when(todoRepository.findById(1L)).thenReturn(Optional.of(new Todo(1L, "Java gegen C++ austauschen", false)));
		when(todoRepository.findById(2L)).thenReturn(Optional.of(new Todo(2L, "C++ gegen Rust austauschen", true)));
		Optional<Todo> todo = todoRepository.findById(1L);
		Optional<Todo> todo2 = todoRepository.findById(2L);
		Assertions.assertEquals(1L, todo.get().getId());
		Assertions.assertEquals("Java gegen C++ austauschen", todo.get().getDescription());
		Assertions.assertEquals(true, todo2.get().getFinished());
	}

	@Test
	@DisplayName("Should throw Exception if Todo which should be deleted was not found in database")
	void testTodoServiceDelete() throws Exception {
		TodoService todoService = new TodoService(todoRepository);
		Assertions.assertThrows(Exception.class, () -> {
			todoService.delete(new Todo(1L, "Weltfrieden schaffen", false));
		});
	}

	@Test
	@DisplayName("Should throw Exception if Todo which should be updated was not found in database")
	void testTodoServiceUpdate() throws Exception {
		TodoService todoService = new TodoService(todoRepository);
		Assertions.assertThrows(Exception.class, () -> {
			todoService.update(new Todo(1L, "Weltfrieden schaffen", false));
		});
	}

	@Test
	@DisplayName("Should compare to items from database with correct attribute values for equality")
	void testTodoServiceGet() throws Exception {
		List<Todo> result = new ArrayList<>();
		result.add(new Todo(1L, "chillen", true));
		result.add(new Todo(2L, "grillen", false));
		when(todoRepository.findAll()).thenReturn(result);
		TodoService todoService = new TodoService(todoRepository);
		List<Todo> todoList = todoService.get();
		Assertions.assertEquals(1L, todoList.get(0).getId());
		Assertions.assertEquals("chillen", todoList.get(0).getDescription());
		Assertions.assertEquals(true, todoList.get(0).getFinished());
		Assertions.assertEquals(2L, todoList.get(1).getId());
		Assertions.assertEquals("grillen", todoList.get(1).getDescription());
		Assertions.assertEquals(false, todoList.get(1).getFinished());
	}

	@Test
	@DisplayName("TodoController Get Route should return status 200 and message body")
	void testTodoControllerGet() throws Exception {
		TodoController controller = new TodoController(new TodoService(todoRepository));
		Assertions.assertEquals(HttpStatus.OK, controller.get().getStatusCode());
		Assertions.assertEquals("{data=[], message=Success, status=200}", controller.get().getBody().toString());
	}

}
