package com.cloudcog.automaton.todo;


import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@UIScope
@SpringView
public class TodoView extends VerticalLayout implements View {
	private static final long serialVersionUID = -2725007493454155448L;

	public static final String VIEW_NAME = "Todo";

	private VerticalLayout layout;

    @Autowired
    TodoList todoList;

	@Override
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
        setupLayout();
        addHeader();
        addForm();
        addTodoList();
        addActionButtons();

	}

    private void setupLayout() {
        layout = new VerticalLayout();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		addComponent(layout);
    }

    private void addHeader() {
        Label header = new Label("TODO");
        header.addStyleName(ValoTheme.LABEL_H1);
        layout.addComponent(header);

    }

    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("80%");

        TextField taskField = new TextField();
        taskField.focus();
        Button addButton = new Button("");

        formLayout.addComponentsAndExpand(taskField);
        formLayout.addComponent(addButton);
        layout.addComponent(formLayout);

        addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        addButton.setIcon(VaadinIcons.PLUS);

        addButton.addClickListener(click -> {
            todoList.addTodo(new Todo(taskField.getValue()));
            taskField.setValue("");
            taskField.focus();
        });
        addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    }

    private void addTodoList() {
        layout.addComponent(todoList);
    }

    private void addActionButtons() {
        Button deleteButton = new Button("Delete completed items");

        deleteButton.addClickListener(click->todoList.deleteCompleted());

        layout.addComponent(deleteButton);

    }
}
