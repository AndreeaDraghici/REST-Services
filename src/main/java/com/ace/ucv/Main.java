package com.ace.ucv;

import com.ace.ucv.model.ToDo;
import com.ace.ucv.service.ToDoService;

/**
 * Created by Andreea Draghici on ${DATE}
 * Name of project: HttpCalls
 */
public class Main {
    public static void main(String[] args) {
        try {

            ToDoService service = new ToDoService();
            service.getTodos();

            service.createTodo(new ToDo("New Todo", false));
            service.updateTodo(1, new ToDo("Updated Todo", true));
            service.deleteTodo(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}