package com.ace.ucv;

import com.ace.ucv.model.ToDo;
import com.ace.ucv.service.ToDoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Andreea Draghici on 10/12/2023
 * Name of project: HttpCalls
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        // Instantiate a ToDoService object for interacting with the ToDo API.
        ToDoService service = new ToDoService();

        try {
            // Attempt to perform a series of operations on the ToDo service (get, create, update, delete).

            // Get the list of todos.
            service.getTodos();

            // Create a new todo.
            service.createTodo(new ToDo("New Todo", false));

            // Update an existing todo.
            service.updateTodo(1, new ToDo("Updated Todo", true));

            // Delete a todo with ID 1.
            service.deleteTodo(1);

        } catch (Exception e) {
            // If an exception occurs during the operations, log an error message with the details of the failure.
            logger.error(new StringBuilder().append("Failed due to: ").append(e.getMessage()).toString());
        }
    }
}