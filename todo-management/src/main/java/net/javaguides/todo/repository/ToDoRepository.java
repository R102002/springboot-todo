package net.javaguides.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.todo.entity.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
//as it extends JpaRepository will get methods for crud operations
	//next will do chng in svc lyr
}
