package net.javaguides.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.ToDoDto;
import net.javaguides.todo.service.ToDoService;

@RestController
@RequestMapping("api/todos")//defines base url for our api's
@AllArgsConstructor
public class ToDoController {

	//we are going to use constructor dependency injection
	private ToDoService toDoService;
	
	//Build Add ToDo REST API
	@PostMapping
	public ResponseEntity<ToDoDto> addTodo(@RequestBody ToDoDto toDoDto){
		ToDoDto savedToDo=toDoService.addToDo(toDoDto);
		return new ResponseEntity<>(savedToDo,HttpStatus.CREATED);
	}
	
	//
	@GetMapping("{id}")
	public ResponseEntity<ToDoDto> getToDo(@PathVariable("id") Long todoId){
		ToDoDto todoDto=toDoService.getToDo(todoId);
		return new ResponseEntity<>(todoDto,HttpStatus.OK);
	}
	
	@GetMapping
	//Build Get all todos rest api
	public ResponseEntity<List<ToDoDto>> getAllTodo(){
		List<ToDoDto> getAll=toDoService.getAllTodos();
		//return new ResponseEntity<>(getAll,HttpStatus.OK);
		
		//shortcut to above statement is below statement
		return ResponseEntity.ok(getAll); //!!
	}
	
	//build update todo REST API
	@PostMapping("{id}")
	public ResponseEntity<ToDoDto> updateToDo(@RequestBody ToDoDto toDoDto , @PathVariable("id") Long todoId){
		ToDoDto updatedToDo=toDoService.updateTodo(toDoDto, todoId);
		return ResponseEntity.ok(updatedToDo);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String>deleteTodo(@PathVariable("id") Long toDoId){
		toDoService.deleteTodo(toDoId);
		return ResponseEntity.ok("To do deleted successfully");
	}
	
	@PatchMapping("{id}/complete")
	public ResponseEntity<ToDoDto> completeTodo(@PathVariable("id")Long todoId){
		ToDoDto updatedToDoDto=toDoService.completeTodo(todoId);
		return ResponseEntity.ok(updatedToDoDto);
	}
	
	@PatchMapping("{id}/in-complete")
	public ResponseEntity<ToDoDto> inCompleteTodo(@PathVariable("id") Long todoId){
		ToDoDto updatedtoDoDto=toDoService.inCompleteTodo(todoId);
		return ResponseEntity.ok(updatedtoDoDto);
	}
}
