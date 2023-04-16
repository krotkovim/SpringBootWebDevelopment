# SpringBootWebDevelopment
This repository contains the backend code for a task management web application, developed as part of a programming assignment. The assignment required implementing a Spring Boot web application that allows managing tasks, using Hibernate to store tasks in a database.

The application provides an API that supports the following operations:

Creating a new task by sending a POST request to /tasks with the task title and description as parameters.
Retrieving a specific task by sending a GET request to /tasks/{id}, where {id} is the ID of the task to retrieve.
Retrieving a list of all tasks by sending a GET request to /tasks.
Updating a task by sending a PATCH request to /tasks/{id}, with the ID of the task to update and one or more fields to modify.
Deleting a task by sending a DELETE request to /tasks/{id}, with the ID of the task to delete.
The project uses Java 11, Spring Boot 2.5.0, and Hibernate 5. The code is structured following the Model-View-Controller (MVC) design pattern, with the controller handling the HTTP requests and responses, the model defining the data entities and the persistence layer, and the view layer not used in this case.
