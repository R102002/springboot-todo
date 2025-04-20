package net.javaguides.todo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.ToDoDto;
import net.javaguides.todo.entity.ToDo;
import net.javaguides.todo.exception.ResourceNotFoundException;
import net.javaguides.todo.repository.ToDoRepository;
import net.javaguides.todo.service.ToDoService;

@Service
@AllArgsConstructor
public class ToDoServiceImpl  implements ToDoService{

	//here we are doing constructor injection instead of writing constructor we are using @AllArgsConstructor annotation of lombok
	private ToDoRepository toDoRepository;
	
	private ModelMapper modelMapper;

	@Override
	public ToDoDto addToDo(ToDoDto toDoDto) {
		//first we need to convert ToDoDto into ToDo JPA Entity
//		ToDo todo=new ToDo();
//		todo.setTitle(toDoDto.getTitle());
//		todo.setDescription(toDoDto.getDescription());
//		todo.setCompleted(toDoDto.isCompleted());
		
		ToDo todo=modelMapper.map(toDoDto, ToDo.class);
		
		//save ToDo JPA Entity
		ToDo savedTodo=toDoRepository.save(todo);
		
		//convert savedTodo JPA Entity  into ToDoDto
//		ToDoDto savedtoDoDto=new ToDoDto();
//		savedtoDoDto.setId(savedTodo.getId());
//		savedtoDoDto.setTitle(savedTodo.getTitle());
//		savedtoDoDto.setDescription(savedTodo.getDescription());
//		savedtoDoDto.setCompleted(savedTodo.isCompleted());
		
		ToDoDto savedtoDoDto=modelMapper.map(savedTodo, ToDoDto.class);
		return savedtoDoDto;
	}

	@Override
	public ToDoDto getToDo(Long id) {
//		ToDo todo=toDoRepository.findById(id).get();  //returns optional
		
		ToDo todo=toDoRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("To do not found with id: "+id));
		
		return modelMapper.map(todo,ToDoDto.class);
	}

	@Override
	public List<ToDoDto> getAllTodos() {
		List<ToDo> todos=toDoRepository.findAll();
		
		return todos.stream().map((todo)->modelMapper.map(todo, ToDoDto.class)).collect(Collectors.toList());
	}

	@Override
	public ToDoDto updateTodo(ToDoDto toDoDto, Long id) {
	ToDo todo=	toDoRepository.findById(id)
		.orElseThrow(()->new ResourceNotFoundException("To do not found with id: "+id));
	
	todo.setTitle(toDoDto.getTitle());
	todo.setDescription(toDoDto.getDescription());
	todo.setCompleted(toDoDto.isCompleted());
	
	ToDo updatedTodo=toDoRepository.save(todo);
		return modelMapper.map(updatedTodo, ToDoDto.class);
	}

	@Override
	public void deleteTodo(Long id) {
		ToDo todo=toDoRepository.findById(id)
		.orElseThrow(()->new ResourceNotFoundException("To do not found with id: "+id));
		toDoRepository.deleteById(id);
	}

	@Override
	public ToDoDto completeTodo(Long id) {
		ToDo todo=toDoRepository.findById(id)
		.orElseThrow(()->new ResourceNotFoundException("To do not found with id: "+id));
		
		todo.setCompleted(Boolean.TRUE);
		ToDo updatedTodo=toDoRepository.save(todo);
		
		return modelMapper.map(updatedTodo, ToDoDto.class);
	}

	@Override
	public ToDoDto inCompleteTodo(Long id) {
		ToDo todo=toDoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("To do not found with id: "+id));
		todo.setCompleted(Boolean.FALSE);
		ToDo updatedTodo=toDoRepository.save(todo);
		return modelMapper.map(updatedTodo, ToDoDto.class);
	}
	
}
