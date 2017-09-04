package com.cloudcog.automaton.todo;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;

@UIScope
@SpringComponent
public class TodoList extends VerticalLayout implements TodoChangeListener {
    @Autowired
    TodoRepository repository;
    private List<Todo> todos;

    @PostConstruct
    void init() {
        setWidth("80%");

        update();
    }

    private void update() {
        setTodos(repository.findAll());
    }

    private void setTodos(List<Todo> todos) {
        this.todos = todos;
        removeAllComponents();
        todos.forEach(todo -> addComponent(new TodoLayout(todo, this)));
    }

     void addTodo(Todo todo) {
        repository.save(todo);
        update();
    }

    @Override
    public void todoChanged(Todo todo) {
        addTodo(todo);
    }


    public void deleteCompleted() {
        repository.deleteByDone(true);
        update();
    }
}
