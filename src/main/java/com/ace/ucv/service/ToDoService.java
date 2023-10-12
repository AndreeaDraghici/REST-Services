package com.ace.ucv.service;

import com.ace.ucv.model.ToDo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andreea Draghici on 10/12/2023
 * Name of project: HttpCalls
 */
public class ToDoService {

    public void getTodos() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println(response.body());
        } else {
            System.out.println("Error: " + response.statusCode());
        }
    }

    public void createTodo(ToDo todo) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos"))
                .POST(HttpRequest.BodyPublishers.ofString(todoToJson(todo)))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 201) {
            System.out.println("Created Todo: " + response.body());
        } else {
            System.out.println("Error: " + response.statusCode());
        }
    }

    public void updateTodo(int id, ToDo todo) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos/" + id))
                .PUT(HttpRequest.BodyPublishers.ofString(todoToJson(todo)))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("Updated Todo: " + response.body());
        } else {
            System.out.println("Error: " + response.statusCode());
        }
    }

    public void deleteTodo(int id) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("Deleted Todo with ID " + id);
        } else {
            System.out.println("Error: " + response.statusCode());
        }
    }

    public String todoToJson(ToDo todo) {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("title", todo.getTitle());
        jsonMap.put("completed", todo.isCompleted());

        return "{\"title\":\"" + todo.getTitle() + "\",\"completed\":" + todo.isCompleted() + "}";
    }
}
