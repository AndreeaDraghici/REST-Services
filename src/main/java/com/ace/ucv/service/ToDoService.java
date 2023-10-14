package com.ace.ucv.service;

import com.ace.ucv.model.ToDo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import static com.ace.ucv.util.StatusResponse.getCodeAndStatusResponse;
import static com.ace.ucv.util.StatusResponse.getCodeAndStatusResponseUsingID;
import static com.ace.ucv.util.Utils.HTTPS_JSONPLACEHOLDER_TYPICODE_COM_TODOS;

/**
 * Created by Andreea Draghici on 10/12/2023
 * Name of project: HttpCalls
 */
public class ToDoService {


    // This method performs an HTTP GET request to retrieve the list of "todos" from JSONPlaceholder.
    public void getTodos() throws Exception {
        // Create an HttpClient object to make HTTP requests.
        HttpClient client = HttpClient.newHttpClient();

        // Build an HTTP GET request to the specified URL.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HTTPS_JSONPLACEHOLDER_TYPICODE_COM_TODOS))
                .build();

        // Execute the request and receive the response.
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

       getCodeAndStatusResponse(response,200,"Get ToDos: ");
    }


    // This method performs an HTTP POST request to create a new "task" on JSONPlaceholder.
    public void createTodo(ToDo todo) throws Exception {
        // Create an HttpClient object to make HTTP requests.
        HttpClient client = HttpClient.newHttpClient();

        // Build an HTTP POST request to the specified URL for creating todos.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HTTPS_JSONPLACEHOLDER_TYPICODE_COM_TODOS))
                .POST(HttpRequest.BodyPublishers.ofString(todoToJson(todo)))
                .header("Content-Type", "application/json")
                .build();

        // Execute the POST request and receive the response.
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        getCodeAndStatusResponse(response, 201, "Created Todo: ");
    }


    // This method performs an HTTP PUT request to update an existing "todo" on JSONPlaceholder.
    public void updateTodo(int id, ToDo todo) throws Exception {
        // Create an HttpClient object to make HTTP requests.
        HttpClient client = HttpClient.newHttpClient();

        // Build an HTTP PUT request to the specified URL for updating todos with the given ID.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(new StringBuilder().append(HTTPS_JSONPLACEHOLDER_TYPICODE_COM_TODOS).append("/").append(id).toString()))
                .PUT(HttpRequest.BodyPublishers.ofString(todoToJson(todo)))
                .header("Content-Type", "application/json")
                .build();

        // Execute the PUT request and receive the response.
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check the status code of the response and print the corresponding message.
        getCodeAndStatusResponse(response, 200, "Updated Todo: ");
    }

    // This method performs an HTTP DELETE request to remove an existing "todo" on JSONPlaceholder.
    public void deleteTodo(int id) throws Exception {
        // Create an HttpClient object to make HTTP requests.
        HttpClient client = HttpClient.newHttpClient();

        // Build an HTTP DELETE request to the specified URL for deleting the todo with the given ID.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(new StringBuilder().append(HTTPS_JSONPLACEHOLDER_TYPICODE_COM_TODOS).append("/").append(id).toString()))
                .DELETE()
                .build();

        // Execute the DELETE request and receive the response.
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check the status code of the response and print the corresponding message.
        getCodeAndStatusResponseUsingID(id, response);
    }


    // This method converts a ToDo object to its JSON representation as a string.
    public String todoToJson(ToDo todo) {
        // Create a HashMap to represent the key-value pairs of the JSON object.
        Map<String, Object> jsonMap = new HashMap<>();

        // Add the "title" and "completed" properties to the JSON map based on the ToDo object.
        jsonMap.put("title", todo.getTitle());
        jsonMap.put("completed", todo.isCompleted());

        // Build the JSON string using a StringBuilder for efficiency.
        return new StringBuilder()
                .append("{\"title\":\"").append(todo.getTitle()) // Add the title property.
                .append("\",\"completed\":").append(todo.isCompleted()) // Add the completed property.
                .append("}")
                .toString();
    }

}
