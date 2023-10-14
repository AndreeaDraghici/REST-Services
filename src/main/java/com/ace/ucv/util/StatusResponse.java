package com.ace.ucv.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.http.HttpResponse;


/**
 * Created by Andreea Draghici on 10/14/2023
 * Name of project: HttpCalls
 */
public class StatusResponse {

    private static final Logger logger = LogManager.getLogger(StatusResponse.class);

    // Helper method to check the status code of an HTTP response and log the corresponding message.
    public static void getCodeAndStatusResponse(HttpResponse<String> response, int expectedStatusCode, String format) {
        // Check the status code of the response.
        if (response.statusCode() == expectedStatusCode) {
            // If the status code is as expected, log an information message with the formatted body of the response.
            logger.info(new StringBuilder().append(format).append(response.body()).toString());
        } else {
            // If the status code is not as expected, log an error message with the actual status code.
            logger.error(new StringBuilder().append("Error: ").append(response.statusCode()).toString());
        }
    }

    // Helper method specific to DELETE operations to check the status code of an HTTP response and log the corresponding message.
    public static void getCodeAndStatusResponseUsingID(int id, HttpResponse<String> response) {
        // Check if the status code matches the expected status code for a successful DELETE operation (typically 200 or 204).
        if (response.statusCode() == 200 || response.statusCode() == 204) {
            // If the status code is as expected, log an information message indicating the successful deletion of the todo with the given ID.
            logger.info(new StringBuilder().append("Deleted Todo with ID ").append(id).toString());
        } else {
            // If the status code is not as expected, log an error message with the actual status code.
            logger.error(new StringBuilder().append("Error: ").append(response.statusCode()).toString());
        }
    }

}
