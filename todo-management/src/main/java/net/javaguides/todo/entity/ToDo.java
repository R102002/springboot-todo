package net.javaguides.todo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="todos")
public class ToDo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //GenerationType.IDENTITY provides primary key as autoincrement
	private Long id;
	@Column(nullable=false)
	private String title;
	@Column(nullable=false)
	private String description;
	private boolean completed;
}
