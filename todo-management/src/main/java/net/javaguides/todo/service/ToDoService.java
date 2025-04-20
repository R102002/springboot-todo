package net.javaguides.todo.service;

import java.util.List;

import net.javaguides.todo.dto.ToDoDto;

public interface ToDoService {

	ToDoDto addToDo(ToDoDto toDoDto);
	
	ToDoDto getToDo(Long id);
	
	List<ToDoDto> getAllTodos();
	
	ToDoDto updateTodo(ToDoDto toDoDto,Long id);
	
	void deleteTodo(Long id);
	
	ToDoDto completeTodo(Long id);
	
	ToDoDto inCompleteTodo(Long id);
}
